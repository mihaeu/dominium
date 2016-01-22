package dominium.Players;

import java.util.List;

import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Duchy;
import dominium.Cards.Estate;
import dominium.Cards.Gold;
import dominium.Cards.Province;
import dominium.Cards.Silver;

public class ThreePointsPlayer extends AIPlayer {

    public ThreePointsPlayer(String name) {
        this.name = name;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        Card cardToPick = null;
        if (hasCard(cards, Gold.class)) {
            cardToPick = findCard(cards, Gold.class);
        } else if (hasCard(cards, Silver.class)) {
            cardToPick = findCard(cards, Silver.class);
        }else if (hasCard(cards, Copper.class)) {
            cardToPick = findCard(cards, Copper.class);
        }
        return cardToPick;
    }
}
