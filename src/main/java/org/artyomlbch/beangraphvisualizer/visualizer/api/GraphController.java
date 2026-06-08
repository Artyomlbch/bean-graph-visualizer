package org.artyomlbch.beangraphvisualizer.visualizer.api;

import org.artyomlbch.beangraphvisualizer.visualizer.core.serializer.Serializer;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.GraphRequestDto;
import org.artyomlbch.beangraphvisualizer.visualizer.service.GraphService;
import org.artyomlbch.beangraphvisualizer.visualizer.core.factory.SerializerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GraphController {

    private final SerializerFactory serializerFactory;
    private final GraphService graphService;

    public GraphController(SerializerFactory serializerFactory, GraphService graphService) {
        this.serializerFactory = serializerFactory;
        this.graphService = graphService;
    }

    @PostMapping(value = "/api/ioc-visualizer/graph")
    public String getGraph(
            @RequestParam(defaultValue = "json") String format,
            @RequestBody(required = false) GraphRequestDto requestDto
    ) {
        BeanGraph graph = graphService.getGraph(requestDto);

        Serializer serializer = serializerFactory.getGraphSerializer(format);

        return serializer.serialize(graph);
    }
}
