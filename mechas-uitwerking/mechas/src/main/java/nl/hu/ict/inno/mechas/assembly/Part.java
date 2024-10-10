package nl.hu.ict.inno.mechas.assembly;

import jakarta.persistence.Embeddable;

@Embeddable
public record Part(Long id, String name, int weight, Slot slot) {

}
