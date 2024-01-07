package nl.hu.ict.inno.observability.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pingpong")
public class PingPongController {


    private Logger logger = LoggerFactory.getLogger(PingPongController.class);

    @GetMapping
    public String getScore() throws InterruptedException {
        logger.debug("Returning Ping Debug");
        logger.info("Returning Ping Info");
        Thread.sleep(100);
        return "Ping";
    }

}
