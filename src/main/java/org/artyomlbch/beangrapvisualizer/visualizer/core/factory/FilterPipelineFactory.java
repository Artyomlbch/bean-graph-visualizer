package org.artyomlbch.beangrapvisualizer.visualizer.core.factory;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeanGraphFilter;
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
        List<BeanGraphFilter> filters = new ArrayList<>();

        if (requestDto == null || requestDto.getFilters().isEmpty()) {
            return new FilterPipeline(filters);
        }

        for (FilterDto  dto : requestDto.getFilters()) {
            if (dto.value() == null || dto.value().isBlank()) continue;

            try {
                switch (dto.type()) {
                    case PACKAGE -> filters.add(new PackageGraphFilter(dto.value()));
                    case TYPE -> filters.add(new TypeGraphFilter(GraphType.valueOf(dto.value())));
                    case ELEMENTS -> filters.add(new ElementsGraphFilter(GraphElementType.valueOf(dto.value())));
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Wrong value '" + dto.value() + "' for filter " + dto.type()
                );
            }
        }

        return new FilterPipeline(filters);
    }

}
