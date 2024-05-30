package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "matches_doubles")
public class MatchDouble extends Match {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winner1_id")
    private Player winner1_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winner2_id")
    private Player winner2_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loser1_id")
    private Player loser1_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loser2_id")
    private Player loser2_id;

    public Player getWinner1_id() {
        return winner1_id;
    }

    public Player getWinner2_id() {
        return winner2_id;
    }

    public Player getLoser1_id() {
        return loser1_id;
    }

    public Player getLoser2_id() {
        return loser2_id;
    }
}
