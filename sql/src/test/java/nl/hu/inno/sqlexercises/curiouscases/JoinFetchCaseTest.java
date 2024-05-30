package nl.hu.inno.sqlexercises.curiouscases;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JoinFetchCaseTest {

    @Autowired
    private JoinFetchCase joinFetchCase;

    @Autowired
    private EntityManager entities;

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        Session session = entities.unwrap(Session.class);
        statistics = session.getSessionFactory().getStatistics();
        statistics.clear();
        statistics.setStatisticsEnabled(true);
    }

    @Test
    public void shouldBeSimilar(){
        joinFetchCase.fetchWithJoin();

        long nrOfQueries = statistics.getPrepareStatementCount();
        long nrOfEntitiesLoaded = statistics.getEntityLoadCount();

        entities.clear();
        statistics.clear();

        //In de console kun je zien waarom de findNormal zo langzaam is.
        joinFetchCase.findNormal();
        //Het is wmbt een open vraag of dit met puur mappings goed op te lossen is in JPA
        //(rauwe Hibernate kan dit met @FetchMode, maar daar lijkt JPA niet naar te luisteren?)

        long nrOfQueries2 = statistics.getPrepareStatementCount();
        long nrOfEntitiesLoaded2 = statistics.getEntityLoadCount();

        assertEquals(nrOfEntitiesLoaded, nrOfEntitiesLoaded2);
        assertEquals(nrOfQueries, nrOfQueries2);
        //Deze vergelijking is niet helemaal eerlijk, want 1 dure query kan langzamer zijn dan vele kleine, maar het
        //verschil is dusdanig dat je hopelijk accepteert dat hier een naar probleem zit.
    }
}
