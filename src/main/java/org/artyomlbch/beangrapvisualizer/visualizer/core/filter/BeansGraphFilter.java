package org.artyomlbch.beangrapvisualizer.visualizer.core.filter;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

public interface BeansGraphFilter {
    boolean isApplicable(GraphFilterCriteria criteria);
    BeanGraph apply(BeanGraph originalGraph, GraphFilterCriteria criteria);
}
