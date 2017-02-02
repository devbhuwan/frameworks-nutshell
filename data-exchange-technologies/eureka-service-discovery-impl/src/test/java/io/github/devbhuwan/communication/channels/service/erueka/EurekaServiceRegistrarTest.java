package io.github.devbhuwan.communication.channels.service.erueka;

import com.netflix.appinfo.*;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertNotNull;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/30/2017
 */
@Ignore
public class EurekaServiceRegistrarTest {
    /*
        http://eureka.cfapps.io/eureka/apps/configserver
     */
    @Test
    public void register() throws Exception {

        EurekaClientConfig eurekaClientConfig = buildEurekaClientConfig();

        EurekaInstanceConfig eurekaInstanceConfig = new MyDataCenterInstanceConfig();
        eurekaInstanceConfig.getMetadataMap().put("eureka.serviceUrl.default", "http://localhost:9093/eureka/v2/");

        InstanceInfo instanceInfo = buildInstanceInfo();
        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(eurekaInstanceConfig, instanceInfo);
        EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, eurekaClientConfig);

        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.STARTING);
        Thread.sleep(2000);
        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        Thread.sleep(10000);
        eurekaClient.getNextServerFromEureka("eureka-impl-server", false);

        int myServingPort = applicationInfoManager.getInfo().getPort();
        ServerSocket serverSocket = new ServerSocket(myServingPort);
        final Socket s = serverSocket.accept();
        BufferedReader rd = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line = rd.readLine();
        if (line != null) {
            System.out.println("Received a request from the example client: " + line);
        }
        assertNotNull(line);
    }

    private EurekaClientConfig buildEurekaClientConfig() {
        EurekaClientConfig eurekaClientConfig = new DefaultEurekaClientConfig();

        return eurekaClientConfig;
    }

    private InstanceInfo buildInstanceInfo() {
        return InstanceInfo.Builder.newBuilder()
                .setAppName("eureka-impl-server") /*SERVICE-NAME*/
                .setIPAddr("127.0.0.1") /*SERVICE HOST IP*/
                .setAppGroupName("GRP")
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setHostName("localhost") /*SERVICE HOST NAME*/
                .setInstanceId("eureka-impl-server" + System.nanoTime())
                .setPort(9898) /*SERVICE HOST SERVER PORT*/
                .setVIPAddress("eureka-impl-server")
                .setHealthCheckUrls("/eureka-impl/rest/person/hello", null, null) /*SERVICE HEALTH CHECK URL*/
                .setLeaseInfo(LeaseInfo.Builder.newBuilder().setDurationInSecs(10).build())
                .build();
    }

}