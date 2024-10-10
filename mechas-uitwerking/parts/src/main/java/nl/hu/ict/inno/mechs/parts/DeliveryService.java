package nl.hu.ict.inno.mechs.parts;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class DeliveryService {



    public void deliver(List<Part> parts) {
        parts.forEach(part -> {
            part.deliverToAssembly();
        });
    }
}
