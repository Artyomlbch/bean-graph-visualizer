package org.artyomlbch.beangrapvisualizer.visualizer.core.initializer;

import lombok.RequiredArgsConstructor;
import org.artyomlbch.beangrapvisualizer.visualizer.core.factory.BeanGraphFactory;
import org.artyomlbch.beangrapvisualizer.visualizer.core.repository.BeanGraphRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeanGraphInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final BeanGraphFactory beanGraphFactory;
    private final BeanGraphRepository beanGraphRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            beanGraphRepository.write(beanGraphFactory.newInstance());
        }
    }
}
