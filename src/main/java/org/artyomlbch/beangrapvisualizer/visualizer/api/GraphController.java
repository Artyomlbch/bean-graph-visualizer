package org.artyomlbch.beangrapvisualizer.visualizer.api;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria.GraphFilterCriteria;
import org.artyomlbch.beangrapvisualizer.visualizer.core.serializer.GraphSerializer;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphElementType;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.artyomlbch.beangrapvisualizer.visualizer.service.BeanGraphService;
import org.artyomlbch.beangrapvisualizer.visualizer.service.SerializerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphController {

    private final SerializerService serializerService;
    private final BeanGraphService beanGraphService;

    public GraphController(SerializerService serializerService, BeanGraphService beanGraphService) {
        this.serializerService = serializerService;
        this.beanGraphService = beanGraphService;
    }

    @GetMapping(value = "/api/ioc-visualizer/graph", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGraph(
            @RequestParam(defaultValue = "json") String format,
            @RequestParam(defaultValue = "ALL") GraphType type,
            @RequestParam(required = false) String packageName,
            @RequestParam(defaultValue = "ALL") GraphElementType elements
    ) {
        GraphFilterCriteria criteria = new GraphFilterCriteria(type, packageName, elements);

        BeanGraph graph = beanGraphService.getGraph(criteria);
        GraphSerializer serializer = serializerService.getGraphSerializer(format);

        return serializer.serialize(graph);
    }
}
