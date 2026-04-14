package org.artyomlbch.beangrapvisualizer.visualizer.core.factory;

import org.artyomlbch.beangrapvisualizer.visualizer.core.repository.BeanMetadataRepository;
import org.artyomlbch.beangrapvisualizer.visualizer.model.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Component
public class BeanGraphFactory {

    private final BeanMetadataRepository beanRepository;

    public BeanGraphFactory(BeanMetadataRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    public BeanGraph newInstance() {
        BeanGraph graph = new BeanGraph();

        String[] beanNames = beanRepository.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            BeanNode node = createNode(beanName, beanRepository);

            String[] dependencies = beanRepository.getDependenciesForBean(beanName);
            if (dependencies.length == 0) {
                graph.addSoloNode(node);
            } else {
                graph.addNode(node);

                for (String depName : dependencies) {
                    if (beanName.equals(depName)) continue;

                    InjectionType type = determineInjectionType(beanName, depName, beanRepository);

                    graph.addEdge(new BeanEdge(
                            createNode(beanName, beanRepository),
                            createNode(depName, beanRepository),
                            type)
                    );
                }
            }
        }
        return graph;
    }

    private BeanNode createNode(String beanName, BeanMetadataRepository beanRepository) {
        Class<?> beanClass = null;
        try {
            beanClass = beanRepository.getBeanClass(beanName);
        } catch (Exception _) {
        }

        String className = (beanClass != null) ? beanClass.getName() : "Unknown";

        BeanScope scope = BeanScope.UNKNOWN;
        int role = BeanDefinition.ROLE_APPLICATION;

        try {
            BeanDefinition bd = beanRepository.getBeanDefinition(beanName);
            role = bd.getRole();

            if (bd.isSingleton()) scope = BeanScope.SINGLETON;
            else if (bd.isPrototype()) scope = BeanScope.PROTOTYPE;
        } catch (Exception e) {
            scope = BeanScope.SINGLETON;
        }

        boolean isSystem = isSystemBean(className, role);
        return new BeanNode(beanName, className, scope, isSystem);
    }

    private boolean isSystemBean(String className, int role) {
        if (className == null || className.equals("Unknown")) {
            return true;
        }

        if (role == BeanDefinition.ROLE_INFRASTRUCTURE || role == BeanDefinition.ROLE_SUPPORT) {
            return true;
        }

        return className.startsWith("org.springframework") ||
                className.startsWith("java.") ||
                className.startsWith("javax.") ||
                className.startsWith("jakarta.") ||
                className.startsWith("com.fasterxml.jackson") ||
                className.startsWith("ch.qos.logback") ||
                className.startsWith("org.slf4j") ||
                className.startsWith("org.apache.tomcat") ||
                className.startsWith("org.apache.catalina") ||
                className.startsWith("org.hibernate") ||
                className.startsWith("com.zaxxer.hikari") ||
                className.startsWith("sun.") ||
                className.startsWith("jdk.");
    }

    private InjectionType determineInjectionType(String beanName, String depName, BeanMetadataRepository beanRepository) {
        Class<?> beanClass;
        Class<?> depClass;
        try {
            beanClass = beanRepository.getBeanClass(beanName);
            depClass = beanRepository.getBeanClass(depName);
        } catch (Exception e) {
            return InjectionType.UNKNOWN;
        }

        if (beanClass == null || depClass == null) return InjectionType.UNKNOWN;

        for (Constructor<?> constructor : beanClass.getConstructors()) {
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();

            for (Type paramType : genericParameterTypes) {
                if (paramType instanceof Class<?> && ((Class<?>) paramType).isAssignableFrom(depClass)) {
                    return InjectionType.CONSTRUCTOR;
                }

                if (paramType instanceof ParameterizedType parameterizedType) {
                    for (Type arg :  parameterizedType.getActualTypeArguments()) {
                        if (arg instanceof Class<?> && ((Class<?>) arg).isAssignableFrom(depClass)) {
                            return InjectionType.CONSTRUCTOR;
                        }
                    }
                }
            }
        }

        for (Field field : beanClass.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(depClass)) {
                return InjectionType.FIELD;
            }
        }

        return InjectionType.UNKNOWN;
    }

}
