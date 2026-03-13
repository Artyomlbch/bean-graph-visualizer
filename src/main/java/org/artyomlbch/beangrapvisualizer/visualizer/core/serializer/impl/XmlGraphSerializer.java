package org.artyomlbch.beangrapvisualizer.visualizer.core.serializer.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.artyomlbch.beangrapvisualizer.visualizer.core.serializer.GraphSerializer;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.springframework.stereotype.Component;

@Component
public class XmlGraphSerializer implements GraphSerializer {

    private final XmlMapper xmlMapper;

    public XmlGraphSerializer() {
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public String serialize(BeanGraph graph) {
        try {
            return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(graph);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize graph to XML", e);
        }
    }

    @Override
    public String getFormat() {
        return "xml";
    }
}

