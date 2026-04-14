package org.artyomlbch.beangrapvisualizer.visualizer.service;

import lombok.RequiredArgsConstructor;
import org.artyomlbch.beangrapvisualizer.visualizer.core.factory.FilterPipelineFactory;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.pipeline.FilterPipeline;
import org.artyomlbch.beangrapvisualizer.visualizer.core.repository.BeanGraphRepository;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.filter.GraphRequestDto;
import org.springframework.stereotype.Service;

@Service
public class BeanGraphService {

    private final BeanGraphRepository beanGraphRepository;

    public BeanGraphService(BeanGraphRepository beanGraphRepository) {
        this.beanGraphRepository = beanGraphRepository;
    }

    public BeanGraph getGraph(GraphRequestDto request) {
        FilterPipeline filterPipeline = new FilterPipelineFactory().newInstance(request);
        return filterPipeline.process(beanGraphRepository.getGraph());
    }
}
