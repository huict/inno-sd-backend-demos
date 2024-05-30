package nl.hu.inno.sqlexercises.curiouscases;

import jakarta.transaction.Transactional;
import nl.hu.inno.sqlexercises.orm.Player;
import nl.hu.inno.sqlexercises.orm.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class LazyCaseTransactional implements CuriousCase{
    private Player player;
    private final PlayerRepository players;

    public LazyCaseTransactional(PlayerRepository players) {
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
