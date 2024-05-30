package nl.hu.inno.sqlexercises;

import nl.hu.inno.sqlexercises.curiouscases.CuriousCase;
import nl.hu.inno.sqlexercises.curiouscases.DangerSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SqlexercisesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SqlexercisesApplication.class, args);
    }



    @Autowired
    List<CuriousCase> cases;
    @Override
    public void run(String... args) throws Exception {
        for(var c: cases){
            c.run();
        }
    }
}
