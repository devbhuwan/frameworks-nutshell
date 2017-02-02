package io.github.devbhuwan.communication.channels.api;

import java.util.Map;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */

public interface ServiceDiscovery {

    String HEALTH_CHECK_PATH = "/healthCheck";
    String SERVICE_PATH = "SERVICE_PATH";
    String SERVICE_NAME = "SERVICE_NAME";
    String STATUS_PATH = "STATUS_PATH";

    String getService(String serviceName, Map<String, String> restArgument);
}
