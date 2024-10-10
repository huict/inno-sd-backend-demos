package nl.hu.ict.inno.mechs.assembly;

import nl.hu.ict.inno.mechs.parts.Part;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyPartRepository {
    Optional<Part> findById(Long id);
    List<Part> findAll();
}
