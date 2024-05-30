package nl.hu.inno.sqlexercises.orm;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long player_id;
    private String tour;

    @OneToMany(mappedBy = "winner_id")
    private List<MatchSingle> matches_won;

    @OneToMany(mappedBy = "loser_id")
    private List<MatchSingle> matches_lost;

    protected Player(){}

    public Player(String firstname, String lastname) {
        this.name_first = firstname;
        this.name_last = lastname;
    }

    public List<MatchSingle> matches(){
        List<MatchSingle> all = new ArrayList<>();
        all.addAll(this.matches_lost);
        all.addAll(this.matches_won);
        return all;
    }

    private String name_first;
    private String name_last;

    private Integer height;
    private String hand;
    private String dob;
    private String ioc;

    public Long getPlayer_id() {
        return player_id;
    }

    public String getTour() {
        return tour;
    }

    public String getName_first() {
        return name_first;
    }

    public String getName_last() {
        return name_last;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public String getHand() {
        return hand;
    }

    public String getDob() {
        return dob;
    }

    public String getIoc() {
        return ioc;
    }

    private int version;

    public int getVersion() {
        return version;
    }
}
