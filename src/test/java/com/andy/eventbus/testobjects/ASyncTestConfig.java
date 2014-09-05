package com.andy.eventbus.testobjects;

import com.andy.eventbus.AutoSubscriberRegisteringEventBus;
import com.andy.eventbus.EventBus;
import com.andy.eventbus.GuavaEventBus;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class ASyncTestConfig {

    @Bean
    public EventBus getEventBus(){
        GuavaEventBus eventBus = new GuavaEventBus(new AsyncEventBus(new ForkJoinPool()));
        return new AutoSubscriberRegisteringEventBus(eventBus);
    }

    @Bean
    public Subscriber getSubscriber(){
        return new Subscriber();
    }
}
