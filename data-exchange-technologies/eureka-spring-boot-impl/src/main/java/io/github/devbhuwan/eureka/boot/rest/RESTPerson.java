package io.github.devbhuwan.eureka.boot.rest;

import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/30/2017
 */
@RestController
public class RESTPerson {

    @Path("/health")
    public Response health() {
        return Response.ok("Available").build();
    }
}
