package nl.hu.ict.inno.mechas.assembly;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class CachedPartRepository implements ReadOnlyPartRepository {
    private final RestPartRepository source;

    private List<Part> cachedParts = new ArrayList<>();
    private LocalDateTime lastUpdate = LocalDateTime.MIN;

    public CachedPartRepository(RestPartRepository source) {
        this.source = source;
    }

    @Override
    public Optional<Part> findById(Long id) {
        refresh();
        return this.cachedParts.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    @Override
    public List<Part> findAll() {
        refresh();
        return this.cachedParts;
    }

    private void refresh(){
        if (LocalDateTime.now().isAfter(lastUpdate.plusMinutes(10))) {
            this.cachedParts = new ArrayList<>(this.source.findAll());
            this.lastUpdate = LocalDateTime.now();
        }
    }



    private record ManufacturerDTO(Long id, String company) {
    }
    private record PartDTO(Long id, String model, int weight, Slot slot, ManufacturerDTO manufacturer) {
        Part toPart(){
            return new Part(this.id(), String.format("%s-%s", this.manufacturer().company(), this.model()), this.weight(), this.slot());
        }
    }
    private enum Event { ADDED, DELETED, UPDATED }
    private record PartEventDTO(Event e, PartDTO part){}

    @RabbitListener(queues = "parts-cache")
    public void handlePartEvent(PartEventDTO event){
        switch (event.e()){
            case ADDED -> this.cachedParts.add(event.part().toPart());
            case DELETED -> this.cachedParts.removeIf(p -> p.id().equals(event.part().id()));
            case UPDATED -> {
                this.cachedParts.removeIf(p -> p.id().equals(event.part().id()));
                this.cachedParts.add(event.part().toPart());
            }
        }
    }
}
