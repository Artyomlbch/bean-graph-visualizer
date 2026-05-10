package org.artyomlbch.beangraphvisualizer.visualizer.core.factory;

import org.artyomlbch.beangraphvisualizer.visualizer.core.repository.BeanMetadataRepository;
import org.artyomlbch.beangraphvisualizer.visualizer.model.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GraphFactory {

    private final BeanMetadataRepository beanRepository;
    private final List<String> userPackages;

    public GraphFactory(BeanMetadataRepository beanRepository, ApplicationContext context) {
        this.beanRepository = beanRepository;
        this.userPackages = AutoConfigurationPackages.get(context);
    }

    public BeanGraph newInstance() {
        BeanGraph graph = new BeanGraph();
        String[] beanNames = beanRepository.getBeanDefinitionNames();

        Map<String, BeanNode> nodeCache = new HashMap<>();
        for (String name : beanNames) {
            nodeCache.put(name, createNode(name, beanRepository));
        }

        for (String beanName : beanNames) {
            BeanNode currentNode = nodeCache.get(beanName);
            String[] dependencies = beanRepository.getDependenciesForBean(beanName);

            if (dependencies.length == 0) {
                graph.addSoloNode(currentNode);
            } else {
                graph.addNode(currentNode);
                for (String depName : dependencies) {
                    if (beanName.equals(depName)) continue;

                    BeanNode depNode = nodeCache.get(depName);
                    if (depNode == null) continue;

                    InjectionType type = determineInjectionType(beanName, depName, beanRepository);
                    graph.addEdge(new BeanEdge(currentNode, depNode, type));
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
        if (className == null || "Unknown".equals(className)) return true;
        if (role == BeanDefinition.ROLE_INFRASTRUCTURE || role == BeanDefinition.ROLE_SUPPORT) return true;

        boolean isUserBean = userPackages.stream().anyMatch(className::startsWith);

        return !isUserBean;
    }

    private InjectionType determineInjectionType(String beanName, String depName, BeanMetadataRepository beanRepository) {
        try {
            Class<?> beanClass = beanRepository.getBeanClass(beanName);
            Class<?> depClass = beanRepository.getBeanClass(depName);
            if (beanClass == null || depClass == null) return InjectionType.UNKNOWN;

            for (Constructor<?> constructor : beanClass.getConstructors()) {
                if (isMatch(constructor.getGenericParameterTypes(), depClass)) return InjectionType.CONSTRUCTOR;
            }

            for (Field field : beanClass.getDeclaredFields()) {
                if (isMatch(new Type[]{field.getGenericType()}, depClass)) return InjectionType.FIELD;
            }
        } catch (Exception _) {}
        return InjectionType.UNKNOWN;
    }

    private boolean isMatch(Type[] types, Class<?> targetClass) {
        for (Type type : types) {
            if (type instanceof Class<?> clazz && clazz.isAssignableFrom(targetClass)) return true;
            if (type instanceof ParameterizedType pt) {
                for (Type arg : pt.getActualTypeArguments()) {
                    if (arg instanceof Class<?> clazz && clazz.isAssignableFrom(targetClass)) return true;
                }
            }
        }
        return false;
    }
}
