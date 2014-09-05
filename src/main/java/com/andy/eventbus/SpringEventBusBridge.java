package com.andy.eventbus;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class SpringEventBusBridge implements ApplicationListener<ApplicationEvent>{
    private EventBus bus;

    public SpringEventBusBridge(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        bus.post(event);
    }
}
