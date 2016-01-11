package src.main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by SWINE on 10.01.2016.
 */
public class PermanentGameState {
    Collection<Player> playerCollection;
    Collection<Card> cardCollection;


    public PermanentGameState() {
        playerCollection = new ArrayList<Player>();

    }

    public void addPlayer(Player player) {
        playerCollection.add(player);
    }
}
