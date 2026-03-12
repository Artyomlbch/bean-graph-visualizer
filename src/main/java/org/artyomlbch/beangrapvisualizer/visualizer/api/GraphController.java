package org.artyomlbch.beangrapvisualizer.visualizer.api;

import org.artyomlbch.beangrapvisualizer.visualizer.core.serializer.GraphSerializer;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.artyomlbch.beangrapvisualizer.visualizer.service.BeanGraphAnalyzerService;
import org.artyomlbch.beangrapvisualizer.visualizer.service.SerializerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphController {

    private final SerializerService serializerService;
    private final BeanGraphAnalyzerService beanGraphAnalyzerService;

    public GraphController(SerializerService serializerService, BeanGraphAnalyzerService beanGraphAnalyzerService) {
        this.serializerService = serializerService;
        this.beanGraphAnalyzerService = beanGraphAnalyzerService;
    }

    @GetMapping(value = "/api/ioc-visualizer/graph", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGraph(
            @RequestParam(defaultValue = "json") String format,
            @RequestParam(defaultValue = "ALL") GraphType type
    ) {
        BeanGraph graph = beanGraphAnalyzerService.getBeanGraph(type);
        GraphSerializer serializer = serializerService.getGraphSerializer(format);
        return serializer.serialize(graph);
    }
}
