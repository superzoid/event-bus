package com.andy.eventbus;

import com.andy.eventbus.testobjects.*;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SpringEventBusBridgeTest {

    private static final String BODY = "Event payload.";

    @Test
    public void canBridgeBetweenTheSpringAndEventBus(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringEventBusBridgeTestConfig.class);
        final SpringListener listener = context.getBean(SpringListener.class);
        final SpringTestEvent event = new SpringTestEvent(BODY);

        context.publishEvent(event);

        assertThat(BODY, is(equalTo(listener.getEvent().getBody())));
    }
}
