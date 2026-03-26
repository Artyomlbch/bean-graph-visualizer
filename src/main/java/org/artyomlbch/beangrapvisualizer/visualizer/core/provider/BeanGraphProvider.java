package org.artyomlbch.beangrapvisualizer.visualizer.core.provider;

import org.artyomlbch.beangrapvisualizer.visualizer.core.cache.BeanGraphCache;
import org.artyomlbch.beangrapvisualizer.visualizer.core.factory.BeanGraphFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BeanGraphProvider implements ApplicationListener<ContextRefreshedEvent> {

    private final BeanGraphCache graphCache;

    public BeanGraphProvider(BeanGraphCache graphCache) {
        this.graphCache = graphCache;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            graphCache.put(new BeanGraphFactory(event.getApplicationContext()).newInstance());
        }
    }
}
