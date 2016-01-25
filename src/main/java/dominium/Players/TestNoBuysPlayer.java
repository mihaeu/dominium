package dominium.Players;

import dominium.Cards.Card;

import java.util.List;

public class TestNoBuysPlayer extends AIPlayer {

    public TestNoBuysPlayer(String name){
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        return null;
    }
}
