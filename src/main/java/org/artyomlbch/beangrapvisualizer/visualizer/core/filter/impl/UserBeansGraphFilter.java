package org.artyomlbch.beangrapvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanEdge;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserBeansGraphFilter implements BeansGraphFilter {
    @Override
    public BeanGraph analyze(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(beanNode -> !beanNode.isSystem())
                .toList();

        filteredNodes.forEach(filteredGraph::addNode);

        Set<String> allowedNodeIds = filteredNodes.stream()
                .map(BeanNode::getId)
                .collect(Collectors.toSet());

        List<BeanEdge> filteredEdges = originalGraph.getEdges().stream()
                .filter(edge -> allowedNodeIds.contains(edge.getSource().getId()) &&
                        allowedNodeIds.contains(edge.getTarget().getId()))
                .toList();

        filteredEdges.forEach(filteredGraph::addEdge);

        return filteredGraph;
    }

    @Override
    public GraphType getGraphType() {
        return GraphType.USER;
    }
}

