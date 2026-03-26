package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.pipeline;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;

import java.util.List;

public class FilterPipeline {
    private final List<BeansGraphFilter> filters;

    public FilterPipeline(List<BeansGraphFilter> filters) {
        this.filters = filters;
    }

    public BeanGraph process(BeanGraph graph) {
        BeanGraph currentGraph = graph;
        for (BeansGraphFilter filter : filters) {
            currentGraph = filter.apply(currentGraph);
        }
        return currentGraph;
    }
}
