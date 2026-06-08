package org.artyomlbch.beangraphvisualizer.visualizer;

import org.artyomlbch.beangraphvisualizer.visualizer.config.VisualizerScanConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(VisualizerScanConfiguration.class)
public class IocVisualizerAutoConfiguration {
}
