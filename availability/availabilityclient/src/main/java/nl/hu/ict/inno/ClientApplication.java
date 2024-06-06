package nl.hu.ict.inno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            try {
                String message = restTemplate.getForObject("http://availability-demo/", String.class);
                System.out.println(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                Thread.sleep(2000);
            }

        }
    }
}
