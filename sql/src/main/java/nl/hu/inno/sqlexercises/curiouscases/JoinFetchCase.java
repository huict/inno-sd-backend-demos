package nl.hu.inno.sqlexercises.curiouscases;

import nl.hu.inno.sqlexercises.orm.MatchSingle;
import nl.hu.inno.sqlexercises.orm.MatchSingleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
@Transactional
public class JoinFetchCase implements CuriousCase {

    private final MatchSingleRepository matches;

    public JoinFetchCase(MatchSingleRepository matches) {
        this.matches = matches;
    }

    public List<MatchSingle> fetchWithJoin(){
        return matches.findAllWithJoin();
    }

    public List<MatchSingle> findNormal(){
        return matches.findAll();
    }

    @Override
    public void run() {
        System.out.println("Without fetch");
        this.findNormal();
        System.out.println("With fetch");
        this.fetchWithJoin();
    }
}
