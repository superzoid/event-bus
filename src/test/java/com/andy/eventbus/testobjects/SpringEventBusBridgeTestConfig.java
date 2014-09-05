package com.andy.eventbus.testobjects;

import com.andy.eventbus.AutoSubscriberRegisteringEventBus;
import com.andy.eventbus.GuavaEventBus;
import com.andy.eventbus.SpringEventBusBridge;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringEventBusBridgeTestConfig {

    @Bean
    public SpringListener listener(){
        return new SpringListener();
    }

    @Bean
    public AutoSubscriberRegisteringEventBus bus(){
        return new AutoSubscriberRegisteringEventBus(new GuavaEventBus(new EventBus()));
    }
    @Bean
    @Autowired
    public SpringEventBusBridge bridge(com.andy.eventbus.EventBus bus){
        return new SpringEventBusBridge(bus);
    }
}
