package org.artyomlbch.beangraphvisualizer.visualizer.core.filter;

import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;

public interface Filter {
    BeanGraph apply(BeanGraph originalGraph);
}
