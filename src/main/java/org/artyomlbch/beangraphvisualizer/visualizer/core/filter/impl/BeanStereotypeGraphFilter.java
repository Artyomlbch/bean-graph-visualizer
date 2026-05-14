package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.Filter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanNode;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.Stereotype;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanStereotypeGraphFilter implements Filter {

    private final Stereotype stereotype;

    public BeanStereotypeGraphFilter(Stereotype stereotype) {
        this.stereotype = stereotype;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        List<BeanNode> filteredNodes = originalGraph.getNodes().stream()
                .filter(node -> node.stereotype() == this.stereotype)
                .toList();
        filteredNodes.forEach(filteredGraph::addNode);

        originalGraph.getSoloNodes().stream()
                .filter(node -> node.stereotype() == this.stereotype)
                .forEach(filteredGraph::addSoloNode);

        Set<String> allowedIds = filteredNodes.stream()
                .map(BeanNode::id)
                .collect(Collectors.toSet());

        originalGraph.getEdges().stream()
                .filter(edge -> allowedIds.contains(edge.source().id()))
                .forEach(filteredGraph::addEdge);

        return filteredGraph;
    }
}
