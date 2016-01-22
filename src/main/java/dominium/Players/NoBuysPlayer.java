package dominium.Players;

import java.util.List;

import dominium.Cards.Card;

public class NoBuysPlayer extends AIPlayer {

    public NoBuysPlayer(String name){
        this.name = name;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        return null;
    }
}
