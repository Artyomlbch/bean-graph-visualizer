package org.artyomlbch.beangraphvisualizer.visualizer.core.filter.impl;

import org.artyomlbch.beangraphvisualizer.visualizer.core.filter.Filter;
import org.artyomlbch.beangraphvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangraphvisualizer.visualizer.model.filter.DetailLevel;

public class DetailLevelGraphFilter implements Filter {

    private final DetailLevel detailLevel;

    public DetailLevelGraphFilter(DetailLevel detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Override
    public BeanGraph apply(BeanGraph originalGraph) {
        BeanGraph filteredGraph = new BeanGraph();

        if (detailLevel == DetailLevel.CONNECTED_ONLY) {
            originalGraph.getNodes().forEach(filteredGraph::addNode);
        } else if (detailLevel == DetailLevel.SOLO_ONLY) {
            originalGraph.getSoloNodes().forEach(filteredGraph::addSoloNode);
        } else {
            originalGraph.getNodes().forEach(filteredGraph::addNode);
            originalGraph.getSoloNodes().forEach(filteredGraph::addSoloNode);
        }

        return filteredGraph;
    }
}
