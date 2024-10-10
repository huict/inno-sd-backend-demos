package nl.hu.ict.inno.mechas.assembly;

import nl.hu.ict.inno.mechas.assembly.Part;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyPartRepository {
    Optional<Part> findById(Long id);
    List<Part> findAll();
}
