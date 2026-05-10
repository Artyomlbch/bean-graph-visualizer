package org.artyomlbch.beangraphvisualizer.visualizer.core.repository;

import org.springframework.beans.factory.config.BeanDefinition;

public interface BeanMetadataRepository {
    String[] getBeanDefinitionNames();
    String[] getDependenciesForBean(String beanName);
    Class<?> getBeanClass(String beanName);
    BeanDefinition getBeanDefinition(String beanName);
}
