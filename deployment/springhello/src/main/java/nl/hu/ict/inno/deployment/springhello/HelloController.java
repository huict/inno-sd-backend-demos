package nl.hu.ict.inno.deployment.springhello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello, world! It is now " + LocalDateTime.now();
    }
}
