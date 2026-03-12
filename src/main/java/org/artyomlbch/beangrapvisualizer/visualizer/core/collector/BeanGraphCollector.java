package org.artyomlbch.beangrapvisualizer.visualizer.core.collector;

import org.artyomlbch.beangrapvisualizer.visualizer.model.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Component
public class BeanGraphCollector implements ApplicationListener<ContextRefreshedEvent> {

    private BeanGraph cachedGraph;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null && cachedGraph == null) {
            cachedGraph = buildGraph(event.getApplicationContext());
        }
    }

    public BeanGraph getGraph() {
        return cachedGraph;
    }

    private BeanGraph buildGraph(ApplicationContext applicationContext) {
        BeanGraph graph = new BeanGraph();
        ConfigurableListableBeanFactory factory =
                (ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

        String[] beanNames = factory.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            BeanNode node = createNode(beanName, factory);
            graph.addNode(node);

            String[] dependencies = factory.getDependenciesForBean(beanName);
            for (String depName : dependencies) {
                if (beanName.equals(depName)) continue;

                InjectionType type = determineInjectionType(beanName, depName, factory);

                graph.addEdge(new BeanEdge(
                        createNode(beanName, factory),
                        createNode(depName, factory),
                        type)
                );
            }
        }
        return graph;
    }

    private BeanNode createNode(String beanName, ConfigurableListableBeanFactory factory) {
        Class<?> beanClass = null;
        try {
            beanClass = factory.getType(beanName);
        } catch (Exception _) {
        }

        String className = (beanClass != null) ? beanClass.getName() : "Unknown";
        String label = (beanClass != null) ? beanClass.getSimpleName() : beanName;
        boolean isSystem = className.startsWith("org.springframework"); // доделать, тут сейчас только бины спринга

        BeanScope scope = BeanScope.UNKNOWN;
        try {
            BeanDefinition bd = factory.getBeanDefinition(beanName);
            if (bd.isSingleton()) scope = BeanScope.SINGLETON;
            else if (bd.isPrototype()) scope = BeanScope.PROTOTYPE;
        } catch (Exception e) {
            scope = BeanScope.SINGLETON;
        }

        return new BeanNode(beanName, label, className, scope, isSystem);
    }

    private InjectionType determineInjectionType(String beanName, String depName, ConfigurableListableBeanFactory factory) {
        Class<?> beanClass = null;
        Class<?> depClass = null;
        try {
            beanClass = factory.getType(beanName);
            depClass = factory.getType(depName);
        } catch (Exception e) {
            return InjectionType.UNKNOWN;
        }

        if (beanClass == null || depClass == null) return InjectionType.UNKNOWN;

        for (Constructor<?> constructor : beanClass.getConstructors()) {
            for (Class<?> paramType : constructor.getParameterTypes()) {
                if (paramType.isAssignableFrom(depClass)) {
                    return InjectionType.CONSTRUCTOR;
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
