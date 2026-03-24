package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.criteria;

import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphElementType;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;

public record GraphFilterCriteria(GraphType graphType, String packageName, GraphElementType elementType) {
    public GraphFilterCriteria(GraphType graphType, String packageName, GraphElementType elementType) {
        this.graphType = (graphType != null) ? graphType : GraphType.ALL;
        this.packageName = packageName;
        this.elementType = (elementType != null) ? elementType : GraphElementType.ALL;
    }
}
