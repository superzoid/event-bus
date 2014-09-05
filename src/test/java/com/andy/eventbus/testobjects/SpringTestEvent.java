package com.andy.eventbus.testobjects;

import org.springframework.context.ApplicationEvent;

public class SpringTestEvent extends ApplicationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public SpringTestEvent(Object source) {
        super(source);
    }

    public String getBody(){
        return (String) source;
    }
}
