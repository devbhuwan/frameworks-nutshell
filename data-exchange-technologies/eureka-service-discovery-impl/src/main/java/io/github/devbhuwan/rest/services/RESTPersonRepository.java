package io.github.devbhuwan.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.devbhuwan.communication.channels.api.ServiceDiscoveryConstants.*;
import static io.github.devbhuwan.rest.services.RESTPersonRepository.BASE_PATH;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */
@Path(BASE_PATH)
public class RESTPersonRepository extends AbstractRESTRepository {

    public static final String BASE_PATH = "/person";
    private static final String PERSON_SERVICE_BY_ID = "person-service-by-id";
    private static final String PERSON_SERVICE_BY_ID_PATH = "/{id}";

    public RESTPersonRepository() {
        super.registerServices(this.buildServicesMap());
    }

    private List<Map<String, String>> buildServicesMap() {
        List<Map<String, String>> services = new ArrayList<>();
        services.add(buildServiceMap(PERSON_SERVICE_BY_ID, BASE_PATH + PERSON_SERVICE_BY_ID_PATH));
        return services;
    }

    private Map<String, String> buildServiceMap(String serviceName, String servicePath) {
        Map<String, String> map = new HashMap<>();
        map.put(SERVICE_NAME, serviceName);
        map.put(SERVICE_PATH, BASE_PATH + servicePath);
        map.put(SERVICE_STATUS_PATH, BASE_PATH + STATUS_CHECK_PATH);
        map.put(SERVICE_HEALTH_PATH, BASE_PATH + HEALTH_CHECK_PATH);
        return map;
    }

    @GET
    @Path(PERSON_SERVICE_BY_ID_PATH)
    public Response getMsg(@PathParam("id") String msg) {
        return Response.status(200).entity("Person Id {" + msg + "}").build();
    }

}
