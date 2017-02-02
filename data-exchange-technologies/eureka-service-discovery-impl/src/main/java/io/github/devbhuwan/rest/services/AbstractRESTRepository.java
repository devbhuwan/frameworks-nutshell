package io.github.devbhuwan.rest.services;

import io.github.devbhuwan.communication.channels.api.ServiceRegistrar;
import io.github.devbhuwan.communication.channels.service.erueka.EurekaServiceRegistrar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static io.github.devbhuwan.communication.channels.api.ServiceDiscovery.HEALTH_CHECK_PATH;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */
public abstract class AbstractRESTRepository {

    protected ServiceRegistrar serviceRegistrar = new EurekaServiceRegistrar();

    protected void registerServices(List<Map<String, String>> servicesMap) {
        servicesMap.forEach(serviceRegistrar::register);
    }

    @GET
    @Path(HEALTH_CHECK_PATH)
    public Response getMsg() {
        return Response.status(200).entity("Ok").build();
    }


}
