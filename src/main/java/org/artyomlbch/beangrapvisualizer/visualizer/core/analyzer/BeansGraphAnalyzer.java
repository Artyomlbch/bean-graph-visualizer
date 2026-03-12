package org.artyomlbch.beangrapvisualizer.visualizer.core.analyzer;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;

public interface BeansGraphAnalyzer {
    BeanGraph analyze(BeanGraph beanGraph);
    GraphType getGraphType();
}
