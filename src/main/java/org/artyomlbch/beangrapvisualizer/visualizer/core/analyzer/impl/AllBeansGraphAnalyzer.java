package org.artyomlbch.beangrapvisualizer.visualizer.core.analyzer.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.analyzer.BeansGraphAnalyzer;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.stereotype.Component;

@Component
public class AllBeansGraphAnalyzer implements BeansGraphAnalyzer {
    @Override
    public BeanGraph analyze(BeanGraph beanGraph) {
        return beanGraph;
    }

    @Override
    public GraphType getGraphType() {
        return GraphType.ALL;
    }
}
