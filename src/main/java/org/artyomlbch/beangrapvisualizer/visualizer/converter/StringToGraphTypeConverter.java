package org.artyomlbch.beangrapvisualizer.visualizer.converter;

import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToGraphTypeConverter implements Converter<String, GraphType> {
    @Override
    public GraphType convert(String source) {
        return GraphType.valueOf(source.toUpperCase());
    }
}