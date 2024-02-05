package nl.hu.ict.inno.twophase.helloneo;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BestellingRepository extends Neo4jRepository<Bestelling, Long> {
}
