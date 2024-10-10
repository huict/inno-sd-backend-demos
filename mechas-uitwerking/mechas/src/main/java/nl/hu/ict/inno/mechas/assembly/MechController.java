package nl.hu.ict.inno.mechas.assembly;

import nl.hu.ict.inno.mechas.assembly.Mech;
import nl.hu.ict.inno.mechas.assembly.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mechs")
@Transactional
public class MechController {

    public record AddPartDTO(Long id){}

    public record PartDTO(Long id, String label, int weight) {
        public static PartDTO fromPart(Part part) {
            return new PartDTO(part.id(), part.name(), part.weight());
        }

        public static List<PartDTO> fromParts(List<Part> parts) {
            return parts.stream().map(PartDTO::fromPart).toList();
        }
    }

    public record MechDTO(long id, int currentWeight, int maxWeight, boolean valid, List<PartDTO> parts) {
        public static MechDTO fromMech(Mech mech) {
            return new MechDTO(mech.getId(), mech.getTotalWeight(), mech.getMaxTonnage(), mech.isValid(), PartDTO.fromParts(mech.getParts()));
        }
    }

    private final MechRepository mechs;
    private final ReadOnlyPartRepository parts;

    public MechController(MechRepository mechs, ReadOnlyPartRepository parts) {
        this.mechs = mechs;
        this.parts = parts;
    }

    @GetMapping
    public List<MechDTO> getAll() {
        return mechs.findAll().stream().map(MechDTO::fromMech).toList();
    }


    @GetMapping("/availableParts")
    public List<PartDTO> getAvailableParts() {
        return parts.findAll().stream().map(PartDTO::fromPart).toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<MechDTO> getById(@PathVariable("id") Long id) {
        Optional<Mech> maybeMech = mechs.findById(id);
        return maybeMech
                .map(mech -> ResponseEntity.ok(MechDTO.fromMech(mech)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeById(@PathVariable("id") Long id) {
        Optional<Mech> maybeMech = mechs.findById(id);
        return maybeMech
                .map(mech -> {
                    mechs.delete(mech);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MechDTO> newMech() {
        Mech newMech = mechs.save(new Mech());
        return ResponseEntity
                .created(URI.create(String.format("/mechs/%s", newMech.getId())))
                .body(MechDTO.fromMech(newMech));
    }


    @GetMapping("{id}/parts")
    public ResponseEntity<List<PartDTO>> getParts(@PathVariable("id") Long id) {
        Optional<Mech> maybeMech = mechs.findById(id);
        return maybeMech
                .map(mech -> ResponseEntity.ok(PartDTO.fromParts(mech.getParts())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("{id}/parts")
    public ResponseEntity<List<PartDTO>> addOrReplacePart(@PathVariable("id") Long id, @RequestBody AddPartDTO part) {
        Part toAdd = parts.findById(part.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Part %s not found", part.id())));

        Optional<Mech> maybeMech = mechs.findById(id);
        return maybeMech
                .map(mech -> {
                    mech.addOrReplacePart(toAdd);
                    return ResponseEntity.ok(PartDTO.fromParts(mech.getParts()));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
