package org.artyomlbch.beangraphvisualizer.visualizer.core.filter;

import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;

public interface BeanGraphFilter {
    BeanGraph apply(BeanGraph originalGraph);
}
