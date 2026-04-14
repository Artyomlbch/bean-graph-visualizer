package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.pipeline;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeanGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

import java.util.List;

public class FilterPipeline {
    private final List<BeanGraphFilter> filters;

    public FilterPipeline(List<BeanGraphFilter> filters) {
        this.filters = filters;
    }

    public BeanGraph process(BeanGraph graph) {
        BeanGraph currentGraph = graph;
        for (BeanGraphFilter filter : filters) {
            currentGraph = filter.apply(currentGraph);
        }
        return currentGraph;
    }
}
