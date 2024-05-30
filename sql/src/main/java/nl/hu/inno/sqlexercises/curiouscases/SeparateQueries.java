package nl.hu.inno.sqlexercises.curiouscases;

import nl.hu.inno.sqlexercises.orm.Player;
import nl.hu.inno.sqlexercises.orm.PlayerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class SeparateQueries {


    public SeparateQueries(PlayerRepository players) {
        this.players = players;
    }

    private PlayerRepository players;

    public List<Player> findLefties() {
        return this.players.findPlayersByHand("L");
    }

    public List<Player> findTallFolks() {
        return this.players.findPlayerByHeightIsGreaterThanEqual(200);
    }

    public List<Player> findTallLefties() {
        return this.players.findPlayerByHeightIsGreaterThanEqualAndHand(200, "L");
    }

}
