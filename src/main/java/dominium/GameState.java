package dominium;

import dominium.Cards.Card;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;

public class GameState {
    public static final int BUY_PHASE = 0;
    public static final int CLEAN_UP_PHASE = 1;
    public int actionsLeft;
    public int buysLeft;
    public int coinsLeft;
    public int currentPhase;
    private Map<Class, Stack<Card>> kingdomCards;

    private Collection<Card> trashedCards;

    public GameState(Map<Class, Stack<Card>> kingdomCards) {
        actionsLeft = 1;
        buysLeft = 1;
        coinsLeft = 0;
        currentPhase = BUY_PHASE;
        this.kingdomCards = kingdomCards;
    }

    public Map<Class, Stack<Card>> getKingdomCards() {
        return kingdomCards;
    }
}
