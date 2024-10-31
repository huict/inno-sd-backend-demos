package nl.hu.ict.inno.crudhello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crud")
public class CrudController {

    @Autowired
    private CrudRepository crudRepository;

    @PostMapping
    public CrudEntity createEntity(@RequestBody CrudEntity entity) {
        return crudRepository.save(entity);
    }

    @GetMapping
    public List<CrudEntity> getAllEntities() {
        return crudRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrudEntity> getEntityById(@PathVariable Long id) {
        Optional<CrudEntity> entity = crudRepository.findById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrudEntity> updateEntity(@PathVariable Long id, @RequestBody CrudEntity entityDetails) {
        Optional<CrudEntity> entity = crudRepository.findById(id);
        if (entity.isPresent()) {
            CrudEntity updatedEntity = entity.get();
            updatedEntity.setName(entityDetails.getName());
            updatedEntity.setDescription(entityDetails.getDescription());
            return ResponseEntity.ok(crudRepository.save(updatedEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        if (crudRepository.existsById(id)) {
            crudRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}