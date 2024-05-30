package nl.hu.inno.sqlexercises.curiouscases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LazyCaseTest {

    @Autowired
    private LazyCase lazyCase;

    @Test
    public void canAccessProperties(){
        lazyCase.fetchPlayer();
        lazyCase.accessMatches();
    }

}
