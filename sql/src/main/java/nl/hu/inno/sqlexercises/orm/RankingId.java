package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;


@Embeddable
public class RankingId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player_id;

    @Column(name = "ranking_date")
    private String rankingdate;
    private String tour;

    public Player getPlayer_id() {
        return player_id;
    }

    public String getRanking_date() {
        return rankingdate;
    }

    public String getTour() {
        return tour;
    }
}
