package nl.hu.ict.inno.mechs.parts;

import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class PartsController {
    private final EntityManager entities;

    public PartsController(EntityManager entities) {
        this.entities = entities;
    }

    @GetMapping("/manufacturers")
    public List<Manufacturer> allManufacturers() {
        return entities.createQuery("select m from Manufacturer m", Manufacturer.class).getResultList();
    }

    @GetMapping("/parts")
    public List<Part> allParts() {
        return entities.createQuery("select p from Part p", Part.class).getResultList();
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<Part> partById(@PathVariable("id") Long id) {
        Part maybePart = this.entities.find(Part.class, id);
        if(maybePart != null){
            return ResponseEntity.ok(maybePart);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/parts/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable("id") Long id, @RequestBody Part updatedFields) {
        Part maybePart = this.entities.find(Part.class, id);
        if(maybePart != null){
            Manufacturer m = entities.find(Manufacturer.class, updatedFields.getManufacturer().getId());
            if(m == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Manufacturer not found");
            }
            maybePart.update(updatedFields, m);
            return ResponseEntity.ok(maybePart);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/parts")
    public ResponseEntity<Part> addPart(@RequestBody Part p) {
        Manufacturer m = entities.find(Manufacturer.class, p.getManufacturer().getId());
        if(m == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Manufacturer not found");
        }
        Part newPart = new Part(p.getModel(), p.getWeight(), p.getSlot(), m);
        entities.persist(newPart);
        return ResponseEntity.created(URI.create(String.format("/parts/%s", newPart.getId()))).body(newPart);
    }


    @DeleteMapping("/parts/{id}")
    public ResponseEntity<Part> removePart(@PathVariable("id") Long id) {
        Part maybePart = this.entities.find(Part.class, id);
        if(maybePart != null){
            entities.remove(maybePart);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
