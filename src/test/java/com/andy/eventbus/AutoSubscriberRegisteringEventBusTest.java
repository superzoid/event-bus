package com.andy.eventbus;

import com.andy.eventbus.testobjects.ASyncTestConfig;
import com.andy.eventbus.testobjects.ImportantEvent;
import com.andy.eventbus.testobjects.Subscriber;
import com.andy.eventbus.testobjects.SyncTestConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AutoSubscriberRegisteringEventBusTest {
    public static final String BODY = "Important event body";
    public static final String SECOND_BODY = "Another important event body";

    @Test
    public void registersBeansOnSynBus(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SyncTestConfig.class);
        final EventBus eventBus = context.getBean(EventBus.class);
        final Subscriber subscriber = context.getBean(Subscriber.class);
        context.start();

        eventBus.post(new ImportantEvent(BODY));

        assertThat(BODY, is(equalTo(subscriber.getEvent().getBody())));

        eventBus.unregister(subscriber);

        eventBus.post(new ImportantEvent(SECOND_BODY));

        assertThat(BODY, is(equalTo(subscriber.getEvent().getBody())));

        eventBus.register(subscriber);

        eventBus.post(new ImportantEvent(SECOND_BODY));

        assertThat(SECOND_BODY, is(equalTo(subscriber.getEvent().getBody())));
    }

    @Test
    public void registersBeansOnASyncBus() throws InterruptedException {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ASyncTestConfig.class);
        final EventBus eventBus = context.getBean(EventBus.class);
        final Subscriber subscriber = context.getBean(Subscriber.class);
        context.start();

        eventBus.post(new ImportantEvent(BODY));

        while(subscriber.getEvent() == null){
            System.out.println("sleep");
            Thread.sleep(5);
        }

        assertThat(BODY, is(equalTo(subscriber.getEvent().getBody())));
    }

}