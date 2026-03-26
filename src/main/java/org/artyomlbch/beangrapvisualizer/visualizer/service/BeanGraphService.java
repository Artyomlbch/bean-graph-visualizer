package org.artyomlbch.beangrapvisualizer.visualizer.service;

import org.artyomlbch.beangrapvisualizer.visualizer.core.factory.FilterPipelineFactory;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.core.cache.BeanGraphCache;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.pipeline.FilterPipeline;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.filter.GraphRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanGraphService {

    private final BeanGraphCache beanGraphCache;

    public BeanGraphService(BeanGraphCache beanGraphCache) {
        this.beanGraphCache = beanGraphCache;
    }

    public BeanGraph getGraph(GraphRequestDto request) {
        FilterPipeline filterPipeline = new FilterPipelineFactory().newInstance(request);

        return filterPipeline.process(beanGraphCache.get());
    }
}
