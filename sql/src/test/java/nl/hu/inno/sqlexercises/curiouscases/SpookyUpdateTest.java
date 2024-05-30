package nl.hu.inno.sqlexercises.curiouscases;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpookyUpdateTest {

    @Autowired
    private SpookySave spookySave;

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
    void reset(){
        spookySave.putItBackInOrder();
    }


    @Test
    public void spookyUpdate(){

        spookySave.fetchMatch();

        spookySave.addMatchBadly();

        assertEquals(1, statistics.getEntityInsertCount());
        assertEquals(0, statistics.getEntityUpdateCount());

    }

}
