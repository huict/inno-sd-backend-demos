package nl.hu.inno.sqlexercises.curiouscases;

import nl.hu.inno.sqlexercises.orm.Player;
import nl.hu.inno.sqlexercises.orm.PlayerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LazyCase implements CuriousCase{
    private Player player;
    private final PlayerRepository players;

    public LazyCase(PlayerRepository players) {
        this.players = players;
    }

    public void fetchPlayer(){
        this.player = this.players.findById(101736L).orElseThrow();
    }

    public void accessMatches(){
        this.player.matches().stream().forEach(m -> System.out.println(m.getWinner_id().getHand()));
    }


    @Override
    public void run() {
        fetchPlayer();
        try {
            accessMatches();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
