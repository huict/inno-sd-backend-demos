package nl.hu.inno.sqlexercises.curiouscases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpookySaveCase implements CuriousCase{

    @Autowired
    private SpookySave spookySave;
    @Override
    public void run() {
        try {
            System.out.println("Doing a weird save");
            spookySave.addMatchBadly();
        }finally {
            System.out.println("Setting back original data");
            spookySave.putItBackInOrder();
        }
    }
}
