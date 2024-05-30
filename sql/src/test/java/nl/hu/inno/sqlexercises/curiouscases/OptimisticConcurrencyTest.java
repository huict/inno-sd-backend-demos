package nl.hu.inno.sqlexercises.curiouscases;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OptimisticConcurrencyTest {

    Logger logger = LoggerFactory.getLogger(OptimisticConcurrency.class);
    @Autowired
    private OptimisticConcurrency optimisticConcurrency;


    @Test
    public void canPreventVersioningIssues() throws InterruptedException {
        optimisticConcurrency.takeLock();

        Thread growThread = optimisticConcurrency.performPlayerActionInThread(p -> {
            logger.warn("T1");
            p.setHeight(p.getHeight() + 1);
        });

        Thread shrinkThread = optimisticConcurrency.performPlayerActionInThread(p -> {
            logger.warn("T2");
            p.setHeight(p.getHeight() - 1);
        });

        optimisticConcurrency.releaseLock();

        growThread.join();
        shrinkThread.join(); //Je zou willen dat deze tweede een exception gooit! Je kunt niet tegelijkertijd 2 acties doen.
        // (nouja, in dit geval wel, omdat het puur een veldje zetten is, maar als 1 van de twee de rekening betaalt, en de ander er een extra tv in stopt niet)

        assertNotNull(optimisticConcurrency.getError());

    }
}
