package nl.hu.ict.inno.usessoap;

import nl.hu.ict.inno.usessoap.gen.GetCountryRequest;
import nl.hu.ict.inno.usessoap.gen.GetCountryResponse;
import nl.hu.ict.inno.usessoap.gen.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfig {

    @Bean
    public CountryClient countryClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(GetCountryRequest.class, GetCountryResponse.class);

        CountryClient client = new CountryClient();

        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
