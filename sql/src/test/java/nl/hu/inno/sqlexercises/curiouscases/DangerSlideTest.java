package nl.hu.inno.sqlexercises.curiouscases;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DangerSlideTest {

    @Autowired
    private DangerSlide dangerSlide;

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

    @AfterEach
    void cleanup(){
        dangerSlide.removeDummyMatch();
        dangerSlide.removeDummyPlayer();
    }

    @Test
    public void doubleFind(){
        dangerSlide.doubleFind();
        assertEquals(2, statistics.getPrepareStatementCount());
    }

    @Test
    public void insertMatch(){
        dangerSlide.saveNewMatch();
    }
    //Hier mist nog een voorbeeld met een Sequence...

    @Test
    public void insertPlayer(){
        dangerSlide.saveNewPlayer();
    }

}
