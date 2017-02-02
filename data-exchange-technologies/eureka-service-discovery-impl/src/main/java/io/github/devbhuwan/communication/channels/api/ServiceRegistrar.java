package io.github.devbhuwan.communication.channels.api;

import java.util.Map;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */
public interface ServiceRegistrar {

    void register(Map<String, String> params);
}
