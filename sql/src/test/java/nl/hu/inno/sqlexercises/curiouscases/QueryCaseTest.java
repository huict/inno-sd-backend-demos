package nl.hu.inno.sqlexercises.curiouscases;

import nl.hu.inno.sqlexercises.orm.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QueryCaseTest {
    @Autowired
    private SeparateQueries caseOfTheCuriousQuery;

    @Test
    public void canCombineLists(){
        List<Player> lefties = caseOfTheCuriousQuery.findLefties();

        List<Player> tallPlayers = caseOfTheCuriousQuery.findTallFolks();

        //Hier vragen we aan de database om alle 'lange linkshandigen' te vinden.
        List<Player> tallLefties = caseOfTheCuriousQuery.findTallLefties();

        //En hier rekenen we het zelf uit, door alle lange spelers uit de lijst linkshandigen te filteren...
        List<Player> tallLeftiesObj = lefties.stream().filter(tallPlayers::contains).toList();

        assertTrue(lefties.size() > 0);
        assertTrue(tallPlayers.size() > 0);

        //Dit zouden toch dezelfde waardes op moeten leveren niet?
        assertEquals(tallLefties.size(), tallLeftiesObj.size());
    }
}
