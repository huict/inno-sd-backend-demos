package nl.hu.inno.hulp.democlient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import nl.hu.inno.hulp.SomeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DemoController {

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public String getMessage(){

        return this.message + " " + SomeFile.someString;
    }


    @Autowired
    private EurekaClient discoveryClient;

    @GetMapping("/services")
    public List<InstanceInfo> services(){
        return this.discoveryClient.getApplication("demo").getInstances();
    }


    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/message2")
    public String getDiscoveredMessage(){
        return restTemplate.getForObject("http://demo/message", String.class);
    }
}
