package nl.hu.inno.sqlexercises.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchSingleRepository extends JpaRepository<MatchSingle, MatchId> {

    @Query("select m from MatchSingle m join fetch m.winner_id join fetch m.loser_id")
    List<MatchSingle> findAllWithJoin();

}
