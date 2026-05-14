package org.artyomlbch.beangraphvisualizer.visualizer.service;

import org.artyomlbch.beangraphvisualizer.visualizer.core.factory.FilterPipelineFactory;
import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.pipeline.FilterPipeline;
import org.artyomlbch.beangraphvisualizer.visualizer.core.repository.GraphRepository;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.GraphRequestDto;
import org.springframework.stereotype.Service;

@Service
public class GraphService {

    private final GraphRepository graphRepository;

    public GraphService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public BeanGraph getGraph(GraphRequestDto request) {
        FilterPipeline filterPipeline = new FilterPipelineFactory().newInstance(request);
        return filterPipeline.process(graphRepository.getGraph());
    }
}
