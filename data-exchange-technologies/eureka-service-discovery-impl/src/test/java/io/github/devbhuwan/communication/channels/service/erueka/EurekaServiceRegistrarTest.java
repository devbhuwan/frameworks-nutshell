package io.github.devbhuwan.communication.channels.service.erueka;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import org.junit.Test;

import java.util.Map;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/30/2017
 */
public class EurekaServiceRegistrarTest {
    /*
        http://eureka.cfapps.io/eureka/apps/configserver
     */
    @Test
    public void register() throws Exception {
        EurekaInstanceConfig eurekaInstanceConfig = buildEurekaInstanceConfig();
        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(eurekaInstanceConfig, new EurekaConfigBasedInstanceInfoProvider(eurekaInstanceConfig).get());
        EurekaClientConfig eurekaClientConfig = new DefaultEurekaClientConfig();
        EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, eurekaClientConfig);
        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.STARTING);
        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        //String vipAddress = "localhost";
        eurekaClient.getNextServerFromEureka("configserver", false);
    }

    private EurekaInstanceConfig buildEurekaInstanceConfig() {
        EurekaInstanceConfig eurekaInstanceConfig = new MyDataCenterInstanceConfig();
        Map<String, String> configMap = eurekaInstanceConfig.getMetadataMap();
        configMap.put("eureka.instance.instanceId", "eureka-impl-server" + System.nanoTime());
        configMap.put("eureka.instance.hostname", "configserver");
        configMap.put("eureka.instance.app", "eureka-impl-server");
        configMap.put("eureka.instance.ipAddr", "127.0.0.1");
        configMap.put("eureka.instance.dataCenterInfo.name", "MyOwn");
        configMap.put("eureka.instance.homePageUrl", "http://localhost:9898/eureka-impl/");
        configMap.put("eureka.instance.healthCheckUrl", "http://localhost:9898/eureka-impl/rest/person/hello");
        configMap.put("eureka.instance.vipAddress", "configserver");
        return eurekaInstanceConfig;
    }

}