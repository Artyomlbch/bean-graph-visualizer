package org.artyomlbch.beangrapvisualizer.visualizer.core.filter;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

public interface BeansGraphFilter {
    BeanGraph apply(BeanGraph originalGraph);
}
