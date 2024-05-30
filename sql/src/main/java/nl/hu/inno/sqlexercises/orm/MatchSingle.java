package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "matches_singles")
public class MatchSingle extends Match {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "winner_id")
    private Player winner_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "loser_id")
    private Player loser_id;

    protected MatchSingle(){}

    public MatchSingle(String tourneyId, int matchNum, String tour, Player winner, Player loser) {
        this.winner_id = winner;
        this.loser_id = loser;
        this.id = new MatchId(tourneyId, matchNum, tour);
    }


    public Player getWinner_id() {
        return winner_id;
    }

    public Player getLoser_id() {
        return loser_id;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "match_singles_tourney_id", referencedColumnName = "tourney_id", nullable = false),
            @JoinColumn(name = "match_singles_match_num", referencedColumnName = "match_num", nullable = false),
            @JoinColumn(name = "match_singles_tour", referencedColumnName = "tour", nullable = false)
    })
    private List<ScoredPoint> points = new ArrayList<>();

    public List<ScoredPoint> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public void addPoint(Player p, Speed km, String elapsedTime){
        //TODO: validate;
        this.points.add(new ScoredPoint(km, p, elapsedTime));
    }

    //Normaal wil je deze setters niet, maar nu zijn ze even handig voor een demo
    public void setWinner_id(Player winner_id) {
        this.winner_id = winner_id;
    }

    public void setLoser_id(Player loser_id) {
        this.loser_id = loser_id;
    }
}
