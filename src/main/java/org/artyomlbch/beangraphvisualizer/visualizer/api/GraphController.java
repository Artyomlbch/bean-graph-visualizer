package org.artyomlbch.beangraphvisualizer.visualizer.api;

import org.artyomlbch.beangraphvisualizer.visualizer.core.serializer.GraphSerializer;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.GraphRequestDto;
import org.artyomlbch.beangraphvisualizer.visualizer.service.BeanGraphService;
import org.artyomlbch.beangraphvisualizer.visualizer.service.SerializerService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GraphController {

    private final SerializerService serializerService;
    private final BeanGraphService beanGraphService;

    public GraphController(SerializerService serializerService, BeanGraphService beanGraphService) {
        this.serializerService = serializerService;
        this.beanGraphService = beanGraphService;
    }

    @GetMapping(value = "/api/ioc-visualizer/graph")
    public String getGraph(
            @RequestParam(defaultValue = "json") String format,
            @RequestBody(required = false) GraphRequestDto requestDto
    ) {
        BeanGraph graph = beanGraphService.getGraph(requestDto);

        GraphSerializer serializer = serializerService.getGraphSerializer(format);

        return serializer.serialize(graph);
    }
}
