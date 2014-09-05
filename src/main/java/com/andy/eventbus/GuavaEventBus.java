package com.andy.eventbus;

public class GuavaEventBus implements EventBus {
    private final com.google.common.eventbus.EventBus eventBus;

    public GuavaEventBus(com.google.common.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object object) {
        eventBus.register(object);
    }

    @Override
    public void unregister(Object object) {
        eventBus.unregister(object);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
