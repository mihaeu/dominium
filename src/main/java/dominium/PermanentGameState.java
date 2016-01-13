package dominium;

import java.util.Collection;

public class PermanentGameState {
    private Collection<Player> playerCollection;
    private Collection<Card> cardCollection;
    private Player startPlayer;

    public PermanentGameState(Collection<Player> playerCollection, Collection<Card> cardCollection, Player startPlayer) {
        this.playerCollection = playerCollection;
        this.cardCollection = cardCollection;
        this.startPlayer = startPlayer;
    }

    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public Player getStartPlayer() {
        return startPlayer;
    }
}
