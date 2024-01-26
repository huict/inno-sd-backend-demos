package nl.hu.sd.inno;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

//    @Bean
    public ObjectMapper mapper(){
        ObjectMapper om = new ObjectMapper();
        return om;
    }
}
