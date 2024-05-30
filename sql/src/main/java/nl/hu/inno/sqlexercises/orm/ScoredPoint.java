package nl.hu.inno.sqlexercises.orm;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class ScoredPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String elapsedTime;

    @Embedded
    private Speed speed;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player scorer;

    protected ScoredPoint(){}

    public ScoredPoint(Speed speed, Player scorer, String elapsedTime) {
        this.speed = speed;
        this.scorer = scorer;
        this.elapsedTime = elapsedTime;
    }

    public Long getId() {
        return id;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Player getScorer() {
        return scorer;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }
}
