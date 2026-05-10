package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.BeanGraphFilter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.GraphElementType;

public class ElementsGraphFilter implements BeanGraphFilter {

    private final GraphElementType elementType;

    public ElementsGraphFilter(GraphElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        if (elementType == GraphElementType.NODES_ONLY) {
            originalGraph.getNodes().forEach(filteredGraph::addNode);
            originalGraph.getSoloNodes().forEach(filteredGraph::addSoloNode);

        } else if (elementType == GraphElementType.EDGES_ONLY) {
            originalGraph.getEdges().forEach(filteredGraph::addEdge);
        }

        return filteredGraph;
    }
}
