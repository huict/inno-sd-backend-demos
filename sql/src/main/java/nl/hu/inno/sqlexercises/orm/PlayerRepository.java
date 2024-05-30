package nl.hu.inno.sqlexercises.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select p from Player p where p.name_first=:nameFirst and p.name_last=:nameLast")
    Optional<Player> findPlayerByName(String nameFirst, String nameLast);

    List<Player> findPlayersByHand(String handValue);

    List<Player> findPlayerByHeightIsGreaterThanEqual(int minHeight);

    List<Player> findPlayerByHeightIsGreaterThanEqualAndHand(int minHeight, String hand);

}
