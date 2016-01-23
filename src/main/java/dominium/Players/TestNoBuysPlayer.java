package dominium.Players;

import java.util.List;

import dominium.Cards.Card;

public class TestNoBuysPlayer extends AIPlayer {

    public TestNoBuysPlayer(String name){
        this.name = name;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        return null;
    }
}
