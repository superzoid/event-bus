package com.andy.eventbus.testobjects;

import com.google.common.eventbus.Subscribe;

/**
 * Created by a.macgilvery on 05/09/2014.
 */
public class Subscriber {

    public ImportantEvent getEvent() {
        return event;
    }

    private ImportantEvent event;

    @Subscribe
    public void listen(ImportantEvent event){
        this.event = event;
    }
}
