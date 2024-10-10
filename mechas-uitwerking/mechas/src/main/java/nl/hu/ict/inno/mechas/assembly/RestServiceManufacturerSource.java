package nl.hu.ict.inno.mechas.assembly;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RestServiceManufacturerSource implements ManufacturerSource {

    RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<ManufacturerDTO> getAllParts() {

        ManufacturerDTO[] manufacturerDTOS = restTemplate.getForEntity("http://localhost:8080/manufacturers", ManufacturerDTO[].class ).getBody();
        List<ManufacturerDTO> dtos = new ArrayList<>();
        for (ManufacturerDTO manufacturerDTO : manufacturerDTOS) {
            dtos.add(manufacturerDTO);
        }

        return dtos;
    }

    @Override
    public Optional<ManufacturerDTO> getPartById(Long id) {
        return Optional.empty();
    }
}
