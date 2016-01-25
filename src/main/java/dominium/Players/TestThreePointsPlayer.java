package dominium.Players;

import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.NoEffectCard1;
import dominium.Cards.NoEffectCard2;

import java.util.List;

public class TestThreePointsPlayer extends AIPlayer {

    public TestThreePointsPlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        Card cardToPick = null;
        if (hasCard(cards, Copper.class)) {
            cardToPick = findCard(cards, Copper.class);
        } else if (hasCard(cards, NoEffectCard1.class)) {
            cardToPick = findCard(cards, NoEffectCard1.class);
        } else if (hasCard(cards, NoEffectCard2.class)) {
            cardToPick = findCard(cards, NoEffectCard2.class);
        }
        return cardToPick;
    }
}
