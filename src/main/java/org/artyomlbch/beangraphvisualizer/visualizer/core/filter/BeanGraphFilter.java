package org.artyomlbch.beangrapvisualizer.visualizer.core.filter;

import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

public interface BeanGraphFilter {
    BeanGraph apply(BeanGraph originalGraph);
}
