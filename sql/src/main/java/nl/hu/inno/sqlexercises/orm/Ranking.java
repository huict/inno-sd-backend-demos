package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rankings")
public class Ranking {
    @EmbeddedId
    private RankingId id;

    private int rank;

    public RankingId getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }
}
