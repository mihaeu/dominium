package dominium.Players;

import java.util.List;

import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Duchy;
import dominium.Cards.Estate;
import dominium.Cards.Gold;
import dominium.Cards.NoEffectCard1;
import dominium.Cards.NoEffectCard2;
import dominium.Cards.Province;
import dominium.Cards.Silver;

public class TestThreePointsPlayer extends AIPlayer {

    public TestThreePointsPlayer(String name) {
        this.name = name;
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
