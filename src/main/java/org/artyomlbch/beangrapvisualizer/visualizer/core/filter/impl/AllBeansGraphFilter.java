package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.stereotype.Component;

@Component
public class AllBeansGraphFilter implements BeansGraphFilter {
    @Override
    public BeanGraph analyze(BeanGraph beanGraph) {
        return beanGraph;
    }

    @Override
    public GraphType getGraphType() {
        return GraphType.ALL;
    }
}
