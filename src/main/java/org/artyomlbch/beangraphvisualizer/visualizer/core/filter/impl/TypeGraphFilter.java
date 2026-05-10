package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.BeanGraphFilter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangraphvisualizer.visualizer.model.GraphType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TypeGraphFilter implements BeanGraphFilter {

    private final GraphType graphType;

    public TypeGraphFilter(GraphType graphType) {
        this.graphType = graphType;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();
        boolean filterSystem = graphType == GraphType.SYSTEM;

        originalGraph.getNodes().stream()
                .filter(node -> node.isSystem() == filterSystem)
                .forEach(filteredGraph::addNode);

        originalGraph.getSoloNodes().stream()
                .filter(node -> node.isSystem() == filterSystem)
                .forEach(filteredGraph::addSoloNode);

        Set<String> allowedIds = filteredGraph.getNodes().stream().map(BeanNode::id).collect(Collectors.toSet());

        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.source().id()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}
