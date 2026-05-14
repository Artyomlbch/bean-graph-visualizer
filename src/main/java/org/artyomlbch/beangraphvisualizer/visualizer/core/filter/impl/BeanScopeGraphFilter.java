package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.Filter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.ScopeType;

public class BeanScopeGraphFilter implements Filter {

    private final ScopeType scopeType;

    public BeanScopeGraphFilter(ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        return null;
    }
}
