package com.andy.eventbus;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class AutoSubscriberRegisteringEventBus implements EventBus, ApplicationListener<ContextRefreshedEvent>{
    private final EventBus eventBus;

    public AutoSubscriberRegisteringEventBus(EventBus eventBus) {
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        final List<Object> subscriberBeans = getSubscriberBeansFrom(event.getApplicationContext());
        for(Object subscriber : subscriberBeans){
            eventBus.register(subscriber);
        }
    }

    private List<Object> getSubscriberBeansFrom(ApplicationContext applicationContext) {
        final ImmutableList.Builder<Object> listBuilder = ImmutableList.builder();
        final String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String name : beanDefinitionNames){
            final Object bean = applicationContext.getBean(name);
            if(hasSubscriberAnnotationOnMethod(bean)){
                listBuilder.add(bean);
            }
        }
        return listBuilder.build();
    }

    private boolean hasSubscriberAnnotationOnMethod(Object bean) {
        final Method[] methods = bean.getClass().getMethods();
        for(final Method method : methods){
            final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for(final Annotation annotation : declaredAnnotations){
                if(annotation instanceof Subscribe){
                    return true;
                }
            }
        }
        return false;
    }
}
