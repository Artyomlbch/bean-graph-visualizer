package org.artyomlbch.beangrapvisualizer.visualizer.core.repository.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.repository.BeanMetadataRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigurableListableBeanFactoryBeanMetadataRepository implements BeanMetadataRepository, ApplicationContextAware {

    private ConfigurableListableBeanFactory factory;

    @Override
    public String[] getBeanDefinitionNames() {
        return factory.getBeanDefinitionNames();
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return factory.getDependenciesForBean(beanName);
    }

    @Override
    public Class<?> getBeanClass(String beanName) {
        return factory.getType(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return factory.getBeanDefinition(beanName);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.factory = (ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }
}
