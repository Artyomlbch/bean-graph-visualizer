package org.artyomlbch.beangrapvisualizer.visualizer.core.repository;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.List;

public interface BeanMetadataRepository {
    String[] getBeanDefinitionNames();
    String[] getDependenciesForBean(String beanName);
    Class<?> getBeanClass(String beanName);
    BeanDefinition getBeanDefinition(String beanName);
}
