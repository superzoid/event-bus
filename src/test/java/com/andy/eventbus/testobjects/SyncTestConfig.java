package com.andy.eventbus.testobjects;

import com.andy.eventbus.AutoSubscriberRegisteringEventBus;
import com.andy.eventbus.EventBus;
import com.andy.eventbus.GuavaEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SyncTestConfig {

    @Bean
    public EventBus getEventBus(){
        return new AutoSubscriberRegisteringEventBus(new GuavaEventBus(new com.google.common.eventbus.EventBus()));
    }

    @Bean
    public Subscriber getSubscriber(){
        return new Subscriber();
    }
}
