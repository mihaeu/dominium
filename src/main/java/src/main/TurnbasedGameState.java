package src.main;

import java.util.Collection;

/**
 * Created by SWINE on 10.01.2016.
 */
public class TurnbasedGameState {
    public static final int BUY_PHASE = 0;
    public static final int CLEAN_UP_PHASE = 1;
    public int actionsLeft;
    public int buysLeft;
    public int coinsLeft;
    private Player currentPlayer;
    public int currentPhase;


    private Collection<Card> trashedCards;

    public TurnbasedGameState() {
        actionsLeft = 1;
        buysLeft = 1;
        coinsLeft = 0;
        currentPhase = BUY_PHASE;
    }

    public void setStartPlayer(Player startPlayer) {
        currentPlayer = startPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }




    public Collection<Card> getTrashedCards() {
        return trashedCards;
    }

    public void setTrashedCards(Collection<Card> trashedCards) {
        this.trashedCards = trashedCards;
    }
}
