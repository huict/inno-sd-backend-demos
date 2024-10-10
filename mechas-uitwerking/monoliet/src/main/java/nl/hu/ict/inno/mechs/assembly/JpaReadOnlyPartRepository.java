package nl.hu.ict.inno.mechs.assembly;

import jakarta.persistence.EntityManager;
import nl.hu.ict.inno.mechs.parts.Part;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaReadOnlyPartRepository implements ReadOnlyPartRepository {

    private final EntityManager entities;

    public JpaReadOnlyPartRepository(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    public Optional<Part> findById(Long id) {
        Part maybePart = this.entities.find(Part.class, id);
        return Optional.ofNullable(maybePart);
    }

    @Override
    public List<Part> findAll() {
        return this.entities.createQuery("select p from Part p", Part.class).getResultList();
    }
}
