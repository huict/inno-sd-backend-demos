package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchId implements Serializable {
    private String tourney_id;
    private int match_num;
    private String tour;

    protected MatchId(){}

    public MatchId(String tourney_id, int match_num, String tour) {
        this.tourney_id = tourney_id;
        this.match_num = match_num;
        this.tour = tour;
    }

    public String getTourney_id() {
        return tourney_id;
    }

    public int getMatch_num() {
        return match_num;
    }

    public String getTour() {
        return tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return match_num == matchId.match_num && tourney_id.equals(matchId.tourney_id) && tour.equals(matchId.tour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourney_id, match_num, tour);
    }
}
