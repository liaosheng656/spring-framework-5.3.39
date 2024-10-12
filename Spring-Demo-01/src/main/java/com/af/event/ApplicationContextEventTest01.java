package com.af.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class ApplicationContextEventTest01 extends ApplicationContextEvent {


    /**
     * Create a new {@code ApplicationContextEvent}.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public ApplicationContextEventTest01(ApplicationContext source) {
        super(source);
    }

    public ApplicationContextEventTest01(ApplicationContext source,String message) {
        super(source);
        this.message = message;
    }

    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
