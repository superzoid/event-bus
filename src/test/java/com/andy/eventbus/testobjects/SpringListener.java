package com.andy.eventbus.testobjects;

import com.google.common.eventbus.Subscribe;

public class SpringListener{

    public SpringTestEvent getEvent() {
        return event;
    }

    private SpringTestEvent event;

    @Subscribe
    public void listen(SpringTestEvent event) {
        this.event = event;
    }
}
