package nl.hu.inno.sqlexercises.curiouscases;

import jakarta.persistence.EntityManager;
import nl.hu.inno.sqlexercises.orm.Player;
import nl.hu.inno.sqlexercises.orm.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

//@Component Ben even kwijt wat hier het punt was
@Transactional
public class OptimisticConcurrency implements CuriousCase {

    private static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        public Throwable throwable;

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            throwable = e;
        }
    }

    Logger logger = LoggerFactory.getLogger(OptimisticConcurrency.class);

    private final PlayerRepository players;
    private final EntityManager entities;
    private final ExceptionHandler handler = new ExceptionHandler();

    private Lock lock = new ReentrantLock();

    public OptimisticConcurrency(PlayerRepository players, EntityManager entities) {
        this.players = players;
        this.entities = entities;
    }

    public void takeLock() {
        lock.lock();
    }

    public void releaseLock() {
        lock.unlock();
    }

    public Throwable getError() {
        return this.handler.throwable;
    }

    public Thread performPlayerActionInThread(Consumer<Player> playerAction) throws InterruptedException {
        Thread action = new Thread(() -> {
            try {
                EntityManager em = entities.getEntityManagerFactory().createEntityManager();
                em.getTransaction().begin();
                Player player = em.find(Player.class, 101736L);
                int oldHeight = player.getHeight();
//                lock.lock();
                playerAction.accept(player);
                logger.warn(String.format("Setting height from %s to %s on version %s", oldHeight, player.getHeight(), player.getVersion()));

                em.getTransaction().commit();
            } finally {
                lock.unlock();
            }
        });

        action.setUncaughtExceptionHandler(this.handler);

        action.start();
        return action;
    }


    @Override
    public void run() {
        try {
            performPlayerActionInThread(p -> p.setHeight(205));
            performPlayerActionInThread(p -> p.setHeight(100));
            performPlayerActionInThread(p -> p.setHeight(180));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
