package io.github.devbhuwan.rest.config;


import io.github.devbhuwan.rest.services.RESTPersonRepository;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */
public class ApplicationResourceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(RESTPersonRepository.class);
        return classes;
    }

}
