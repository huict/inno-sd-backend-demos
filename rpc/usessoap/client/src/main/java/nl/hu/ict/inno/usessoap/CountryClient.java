package nl.hu.ict.inno.usessoap;

import nl.hu.ict.inno.usessoap.gen.GetCountryRequest;
import nl.hu.ict.inno.usessoap.gen.GetCountryResponse;
import nl.hu.ict.inno.usessoap.gen.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {
    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return response;
    }

}
