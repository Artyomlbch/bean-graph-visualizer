package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeanGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;

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

        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(node -> node.isSystem() == filterSystem)
                .toList();

        filteredNodes.forEach(filteredGraph::addNode);

        Set<String> allowedIds = filteredNodes.stream().map(BeanNode::getId).collect(Collectors.toSet());

        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.getSource().getId()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}
