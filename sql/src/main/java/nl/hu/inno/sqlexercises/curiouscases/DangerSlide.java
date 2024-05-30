package nl.hu.inno.sqlexercises.curiouscases;

import nl.hu.inno.sqlexercises.orm.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DangerSlide implements CuriousCase {
    private PlayerRepository players;
    private MatchSingleRepository matches;

    public DangerSlide(PlayerRepository players, MatchSingleRepository matches) {
        this.players = players;
        this.matches = matches;
    }

    public void run(){
        System.out.println("We gaan twee keer hetzelfde object vinden. Hoeveel selects verwacht je?");
        this.doubleFind();
    }

    public void doubleFind() {
        Player p = players.findById(100001L).orElseThrow();
        Player p2 = players.findById(100001L).orElseThrow();
    }


    public void saveNewMatch() {
        Player p = players.findById(100001L).orElseThrow();
        Player p2 = players.findById(100002L).orElseThrow();

        System.out.println("We gaan een match saven");
        matches.save(new MatchSingle("test-bla", 1, "atp", p, p2));
        System.out.println("We hebben een match gesaved");
    }

    public void saveNewPlayer() {
        Player newP = new Player("Bob", "Test");
        System.out.println("We gaan een player saven");
        players.save(newP);
        System.out.println("We hebben een player gesaved");
    }


    public void removeDummyMatch() {
        var ms = matches.findById(new MatchId("test-bla", 1, "atp"));
        if (ms.isPresent()) {
            System.out.println("We gaan deleten");
            this.matches.delete(ms.get());
            System.out.println("We hebben gedeleted");
        }
    }

    public void removeDummyPlayer() {
        var p = players.findPlayerByName("Bob", "Test");
        if (p.isPresent()) {
            this.players.delete(p.get());
        }
    }
}
