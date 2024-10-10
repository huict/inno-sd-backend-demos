package nl.hu.ict.inno.mechas.assembly;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RestPartRepository implements ReadOnlyPartRepository {
    private RestTemplate restTemplate = new RestTemplate();

    private String baseAddress = "http://localhost:8080/parts";

    private record ManufacturerDTO(Long id, String company) {
    }

    private record PartDTO(Long id, String model, int weight, Slot slot, ManufacturerDTO manufacturer) {

        Part toPart(){
            return new Part(this.id(), String.format("%s-%s", this.manufacturer().company(), this.model()), this.weight(), this.slot());
        }
    }

    @Override
    public Optional<Part> findById(Long id) {
        var resp = this.restTemplate.getForEntity(baseAddress + "/" + id, PartDTO.class);
        if (resp.getStatusCode().is2xxSuccessful()) {
            PartDTO dto = resp.getBody();

            return Optional.of(dto.toPart());
        }else if (resp.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return Optional.empty();
        }else{
            throw new RuntimeException("Error while fetching part: " + resp.getStatusCode());
        }
    }

    @Override
    public List<Part> findAll() {
        PartDTO[] parts = this.restTemplate.getForEntity(baseAddress, PartDTO[].class).getBody();

        if (parts == null) {
            return List.of();
        }
        return Arrays.stream(parts).map(PartDTO::toPart).toList();
    }
}
