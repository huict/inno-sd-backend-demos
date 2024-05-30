package nl.hu.inno.sqlexercises.curiouscases;

import nl.hu.inno.sqlexercises.orm.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SpookySave {


    private final MatchSingleRepository matches;

    private final PlayerRepository players;

    public SpookySave(MatchSingleRepository matches, PlayerRepository players) {
        this.matches = matches;
        this.players = players;
    }

    //Some Sample Record
    //1969-9277,Curacao,atp,Hard,19690224,296,100119,100258

    public MatchSingle fetchMatch(){
        return matches.findById(new MatchId("1969-9277", 296, "atp")).orElseThrow();
    }

    public MatchSingle addMatchBadly(){

        Player p1 = players.findById(100008L).orElseThrow();
        Player p2 = players.findById(100009L).orElseThrow();

        MatchSingle newMatch = new MatchSingle("1969-9277", 296, "atp", p1, p2);

        this.matches.save(newMatch); //Wat verwacht je hier? Een update of een insert? Een error?

        return newMatch;
    }

    public void putItBackInOrder(){
        MatchSingle fetchMatch = this.fetchMatch();

        Player p1 = players.findById(100119L).orElseThrow();
        Player p2 = players.findById(100258L).orElseThrow();

        fetchMatch.setWinner_id(p1);
        fetchMatch.setWinner_id(p2);

        fetchMatch.setSurface("Hard");
        fetchMatch.setTourney_name("Curacao");
        fetchMatch.setTourney_date("19690224");
    }
}
