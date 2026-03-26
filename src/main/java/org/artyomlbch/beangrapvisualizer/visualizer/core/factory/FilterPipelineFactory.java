package org.artyomlbch.beangrapvisualizer.visualizer.core.factory;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl.ElementsGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl.PackageGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl.TypeGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.pipeline.FilterPipeline;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphElementType;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.artyomlbch.beangrapvisualizer.visualizer.model.filter.FilterDto;
import org.artyomlbch.beangrapvisualizer.visualizer.model.filter.GraphRequestDto;

import java.util.ArrayList;
import java.util.List;

public class FilterPipelineFactory {

    public FilterPipeline newInstance(GraphRequestDto requestDto) {
        List<BeansGraphFilter> filters = new ArrayList<>();

        if (requestDto == null || requestDto.getFilters().isEmpty()) {
            return new FilterPipeline(filters);
        }

        for (FilterDto  dto : requestDto.getFilters()) {
            if (dto.getValue() == null || dto.getValue().isBlank()) continue;

            try {
                switch (dto.getType()) {
                    case PACKAGE -> filters.add(new PackageGraphFilter(dto.getValue()));
                    case TYPE -> filters.add(new TypeGraphFilter(GraphType.valueOf(dto.getValue())));
                    case ELEMENTS -> filters.add(new ElementsGraphFilter(GraphElementType.valueOf(dto.getValue())));
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Wrong value '" + dto.getValue() + "' for filter " + dto.getType()
                );
            }
        }

        return new FilterPipeline(filters);
    }

}
