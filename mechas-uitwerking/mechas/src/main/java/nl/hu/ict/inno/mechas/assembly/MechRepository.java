package nl.hu.ict.inno.mechas.assembly;

import nl.hu.ict.inno.mechs.assembly.Mech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechRepository extends JpaRepository<Mech, Long> {
}
