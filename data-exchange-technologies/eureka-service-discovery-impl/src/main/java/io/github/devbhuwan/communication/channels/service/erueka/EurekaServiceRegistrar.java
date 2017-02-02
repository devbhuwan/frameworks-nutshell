package io.github.devbhuwan.communication.channels.service.erueka;

import com.netflix.appinfo.*;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import io.github.devbhuwan.communication.channels.api.ServiceRegistrar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static io.github.devbhuwan.communication.channels.api.ServiceDiscovery.*;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/29/2017
 */
public class EurekaServiceRegistrar implements ServiceRegistrar {

    private final String serviceIp;
    private final String serviceAppGroup;
    private final String serviceHostname;
    private final String serviceRootPath;
    private final String nodeId;
    private final int servicePort;

    protected static final ConcurrentMap<String, EurekaClient> eurekaClientMap = new ConcurrentHashMap<>();

    public EurekaServiceRegistrar() {
        this.serviceIp = "127.0.0.1";
        this.serviceAppGroup = "GRP";
        this.serviceHostname = "localhost";
        this.servicePort = 9898;
        this.nodeId = "node1";
        this.serviceRootPath = "/";
    }

    @Override
    public void register(Map<String, String> params) {
        EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig(params.get(SERVICE_NAME));
        InstanceInfo instanceInfo = buildInstanceInfo(params);
        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);
        EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, new DefaultEurekaClientConfig());
        eurekaClient.registerHealthCheck(instanceStatus -> {
            return InstanceInfo.InstanceStatus.DOWN;
        });
        eurekaClientMap.put(params.get(SERVICE_NAME), eurekaClient);
    }

    private InstanceInfo buildInstanceInfo(Map<String, String> params) {
        return InstanceInfo.Builder.newBuilder()
                .setAppName(params.get(SERVICE_NAME)) /*SERVICE-NAME*/
                .setIPAddr(serviceIp) /*SERVICE HOST IP*/
                .setAppGroupName(serviceAppGroup)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setHostName(serviceHostname) /*SERVICE HOST NAME*/
                .setInstanceId(buildInstanceId(params.get(SERVICE_NAME)))
                .setPort(servicePort) /*SERVICE HOST SERVER PORT*/
                .setVIPAddress(params.get(SERVICE_NAME))
                .setHealthCheckUrls(buildHealthCheckUrl(params.get(HEALTH_CHECK_PATH)), null, null) /*SERVICE HEALTH CHECK URL*/
                .setHomePageUrl(buildHomePageUrl(params.get(SERVICE_NAME)), null)
                .setStatusPageUrl(buildStatusUrl(params.get(STATUS_PATH)), null)
                .setLeaseInfo(LeaseInfo.Builder.newBuilder().setDurationInSecs(10).build())
                .build();
    }

    private String buildStatusUrl(String statusUrl) {
        return concatServiceRootPathAndUrl(statusUrl);
    }

    private String buildHomePageUrl(String homePageUrl) {
        return concatServiceRootPathAndUrl(homePageUrl);
    }

    private String buildHealthCheckUrl(String healthCheckUrl) {
        return concatServiceRootPathAndUrl(healthCheckUrl);
    }

    private String concatServiceRootPathAndUrl(String url) {
        return this.serviceRootPath + url;
    }

    private String buildInstanceId(String serviceName) {
        return serviceName + "_" + nodeId;
    }

}
