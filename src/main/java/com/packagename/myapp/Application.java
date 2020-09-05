package com.packagename.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.SessionDestroyEvent;
import com.vaadin.flow.server.SessionDestroyListener;
import com.vaadin.flow.server.SessionInitEvent;
import com.vaadin.flow.server.SessionInitListener;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

/**
 * The entry point of the Spring Boot application.
 */
@VaadinSessionScope
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    	
    }

	

}
