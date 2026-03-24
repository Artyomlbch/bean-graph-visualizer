package org.artyomlbch.beangrapvisualizer.visualizer.service;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.core.provider.BeanGraphProvider;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanGraphService {

    private final BeanGraphProvider beanGraphProvider;
    private final List<BeansGraphFilter> filters;

    public BeanGraphService(BeanGraphProvider beanGraphProvider, List<BeansGraphFilter> filters) {
        this.beanGraphProvider = beanGraphProvider;
        this.filters = filters;
    }

    public BeanGraph getGraph(GraphFilterCriteria filterCriteria) {
        BeanGraph currentGraph = beanGraphProvider.get();

        for (BeansGraphFilter filter : filters) {
            if (filter.isApplicable(filterCriteria)) {
                currentGraph = filter.apply(currentGraph, filterCriteria);
            }
        }

        return currentGraph;
    }
}
