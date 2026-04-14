package org.artyomlbch.beangrapvisualizer.visualizer.model.filter;

import java.util.List;

public class GraphRequestDto {

    private List<FilterDto> filters;

    public GraphRequestDto(List<FilterDto> filters) {
        this.filters = filters;
    }

    public GraphRequestDto() {
    }

    public List<FilterDto> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDto> filters) {
        this.filters = filters;
    }
}
