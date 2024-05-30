package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Match {

    @EmbeddedId
    protected MatchId id;

    protected String tourney_name;
    protected String tourney_date;
    protected String surface;

    public MatchId getId() {
        return id;
    }

    public String getTourney_name() {
        return tourney_name;
    }

    public String getTourney_date() {
        return tourney_date;
    }

    public String getSurface() {
        return surface;
    }


    //Normaal wil je deze setters niet, maar nu zijn ze handig voor een demo:
    public void setTourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public void setTourney_date(String tourney_date) {
        this.tourney_date = tourney_date;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }
}
