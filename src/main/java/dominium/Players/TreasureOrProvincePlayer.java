package dominium.Players;

import dominium.CardStack;
import dominium.Cards.*;

public class TreasureOrProvincePlayer extends AIPlayer {

    public TreasureOrProvincePlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(CardStack cards) {
        if (cards.isEmpty()) {
            return null;
        }

        Card cardToPick;
        if (cards.hasCard(new Province())) {
            cardToPick = findCard(cards, Province.class);
        } else if (cards.hasCard(new Gold())) {
            cardToPick = findCard(cards, Gold.class);
        } else if (cards.hasCard(new Silver())) {
            cardToPick = findCard(cards, Silver.class);
        } else if (cards.hasCard(new Duchy())) {
            cardToPick = findCard(cards, Duchy.class);
        } else if (cards.hasCard(new Copper())) {
            cardToPick = findCard(cards, Copper.class);
        } else if (cards.hasCard(new Estate())) {
            cardToPick = findCard(cards, Estate.class);
        } else {
            cardToPick = cards.get((int) (Math.random() * cards.size()));
        }
        return cardToPick;
    }
}
