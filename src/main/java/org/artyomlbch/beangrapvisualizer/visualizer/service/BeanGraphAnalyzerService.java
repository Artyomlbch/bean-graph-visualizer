package org.artyomlbch.beangrapvisualizer.visualizer.service;

import org.artyomlbch.beangrapvisualizer.visualizer.core.analyzer.BeansGraphAnalyzer;
import org.artyomlbch.beangrapvisualizer.visualizer.core.collector.BeanGraphCollector;
import org.artyomlbch.beangrapvisualizer.visualizer.model.BeanGraph;
import org.artyomlbch.beangrapvisualizer.visualizer.model.GraphType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanGraphAnalyzerService {

    private final BeanGraphCollector beanGraphCollector;
    private final List<BeansGraphAnalyzer> beansGraphAnalyzers;

    public BeanGraphAnalyzerService(BeanGraphCollector beanGraphCollector, List<BeansGraphAnalyzer> beansGraphAnalyzers) {
        this.beanGraphCollector = beanGraphCollector;
        this.beansGraphAnalyzers = beansGraphAnalyzers;
    }

    public BeanGraph getBeanGraph(GraphType graphType) {
        return beansGraphAnalyzers.stream()
                .filter(beanGraphAnalyzer -> beanGraphAnalyzer.getGraphType() == graphType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No bean graph analyzer found for graph type: " + graphType))
                .analyze(beanGraphCollector.getGraph());
    }

}
