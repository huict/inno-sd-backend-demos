package nl.hu.ict.inno.crudhello;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository extends JpaRepository<CrudEntity, Long> {
}
