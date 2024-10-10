package nl.hu.ict.inno.mechs.parts;

import jakarta.persistence.EntityManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InitialData implements ApplicationRunner {

    private final EntityManager entities;

    public InitialData(EntityManager entities){
        this.entities = entities;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        //Inspiratie van https://nullsignal.games/
        Manufacturer jinteki = new Manufacturer("Jinteki");
        Manufacturer haas = new Manufacturer("Haas-Bioroid");

        for(Object entity: List.of(
                jinteki,
                haas,
                new Part("FancySchouders", 10, Slot.Shoulder, jinteki),
                new Part("Vleugels", 35, Slot.Shoulder, haas),
                new Part("Robotpoten", 25, Slot.Legs, jinteki),
                new Part("Kippenpoten", 15, Slot.Legs, haas),
                new Part("Mokerhamers", 10, Slot.Arms, jinteki),
                new Part("Octopusarmen", 10, Slot.Arms, haas)
        )){
            if(entity instanceof Part){
                ((Part) entity).deliverToStock(1);
            }
            entities.persist(entity);
        }
    }
}
