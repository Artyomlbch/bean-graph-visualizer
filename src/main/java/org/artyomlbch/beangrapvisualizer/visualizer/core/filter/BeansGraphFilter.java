package org.artyomlbch.beangrapvisualizer.visualizer.core.filter;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;

public interface BeansGraphFilter {
    BeanGraph analyze(BeanGraph beanGraph);
    GraphType getGraphType();
}
