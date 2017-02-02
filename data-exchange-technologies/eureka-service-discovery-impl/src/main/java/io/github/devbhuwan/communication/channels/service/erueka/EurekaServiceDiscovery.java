package io.github.devbhuwan.communication.channels.service.erueka;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.devbhuwan.communication.channels.api.ServiceDiscovery;

import java.util.Map;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */
public class EurekaServiceDiscovery implements ServiceDiscovery {

    @Override
    public String getService(String serviceName, Map<String, String> restArgument) {
        String serviceUrl = null;
        EurekaClient eurekaClient = EurekaServiceRegistrar.eurekaClientMap.get(serviceName);
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(serviceName, false);
        if (instanceInfo != null && instanceInfo.getStatus().compareTo(InstanceInfo.InstanceStatus.UP) == 0) {
            serviceUrl = instanceInfo.getHomePageUrl();
        }
        return serviceUrl;
    }

}
