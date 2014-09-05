package com.andy.eventbus;

public interface EventBus {

    void register(java.lang.Object object) ;

    void unregister(java.lang.Object object);

    void post(java.lang.Object event);
}
