package com.andy.eventbus.testobjects;

/**
 * Created by a.macgilvery on 05/09/2014.
 */
public class ImportantEvent {
    private final String name;

    public String getBody() {
        return name;
    }

    public ImportantEvent(String name) {
        this.name = name;

    }
}
