package nl.hu.ict.inno.mechas.assembly;

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
            this.cachedParts = this.source.findAll();
            this.lastUpdate = LocalDateTime.now();
        }
    }
}
