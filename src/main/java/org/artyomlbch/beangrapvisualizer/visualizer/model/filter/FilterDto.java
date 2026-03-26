package org.artyomlbch.beangrapvisualizer.visualizer.model.filter;

public class FilterDto {
    private FilterName type;
    private String value;

    public FilterName getType() {
        return type;
    }
    public void setType(FilterName type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
