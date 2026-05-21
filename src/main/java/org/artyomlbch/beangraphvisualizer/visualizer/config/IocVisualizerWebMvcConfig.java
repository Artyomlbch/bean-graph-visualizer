package org.artyomlbch.beangraphvisualizer.visualizer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IocVisualizerWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/ioc-visualizer", "/ioc-visualizer/index.html");
        registry.addRedirectViewController("/ioc-visualizer/", "/ioc-visualizer/index.html");
    }
}
