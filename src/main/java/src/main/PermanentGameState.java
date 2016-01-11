package src.main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by SWINE on 10.01.2016.
 */
public class PermanentGameState {
    private Collection<Player> playerCollection;
    private Collection<Card> cardCollection;

    public PermanentGameState(Collection<Player> playerCollection, Collection<Card> cardCollection) {
        this.playerCollection = playerCollection;
        this.cardCollection = cardCollection;
    }

    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public Collection<Card> getCardCollection() {
        return cardCollection;
    }
}
