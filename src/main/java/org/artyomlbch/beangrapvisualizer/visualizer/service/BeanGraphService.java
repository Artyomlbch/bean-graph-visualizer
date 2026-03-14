package org.artyomlbch.beangrapvisualizer.visualizer.service;

import org.artyomlbch.beangrapvisualizer.visualizer.core.filter.BeansGraphFilter;
import org.artyomlbch.beangrapvisualizer.visualizer.core.collector.BeanGraphCollector;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BeanGraphService {

    private final BeanGraphCollector beanGraphCollector;
    private final Map<FilterType, BeansGraphFilter> filtersMap = new HashMap<>();

    public BeanGraphService(BeanGraphCollector beanGraphCollector, List<BeansGraphFilter> beansGraphFilters) {
        this.beanGraphCollector = beanGraphCollector;
        // todo: добавить фильтры под каждый типа фильтрации
    }

    public BeanGraph getBeanGraph(GraphType graphType) {
        return beansGraphFilters.stream()
                .filter(beanGraphAnalyzer -> beanGraphAnalyzer.getGraphType() == graphType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No bean graph analyzer found for graph type: " + graphType))
                .analyze(beanGraphCollector.getGraph());
    }
}
