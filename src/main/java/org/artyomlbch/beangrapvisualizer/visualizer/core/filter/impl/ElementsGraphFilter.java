package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphElementType;

public class ElementsGraphFilter implements BeansGraphFilter {

    private final GraphElementType elementType;

    public ElementsGraphFilter(GraphElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        if (elementType == GraphElementType.NODES_ONLY) {
            originalGraph.getNodes().forEach(filteredGraph::addNode);

        } else if (elementType == GraphElementType.EDGES_ONLY) {
            originalGraph.getEdges().forEach(filteredGraph::addEdge);
        }

        return filteredGraph;
    }
}
