package io.github.devbhuwan.communication.channels.api;

import java.util.Map;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */

public interface ServiceDiscovery {
    String getService(String serviceName, Map<String, String> restArgument);
}
