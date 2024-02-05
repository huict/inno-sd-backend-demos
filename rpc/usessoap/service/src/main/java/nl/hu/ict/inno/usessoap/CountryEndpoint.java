package nl.hu.ict.inno.usessoap;

import nl.hu.ict.inno.usessoap.gen.Country;
import nl.hu.ict.inno.usessoap.gen.Currency;
import nl.hu.ict.inno.usessoap.gen.GetCountryRequest;
import nl.hu.ict.inno.usessoap.gen.GetCountryResponse;
import nl.hu.ict.inno.usessoap.gen.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://inno.ict.hu.nl/usessoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest req) {
        String name = req.getName();
        Country c = new Country();
        c.setName(name);
        c.setPopulation(999);
        c.setCurrency(Currency.EUR);
        c.setCapital("Hoofdstad!");

        GetCountryResponse resp = new GetCountryResponse();
        resp.setCountry(c);
        return resp;
    }
}
