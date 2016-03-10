package dominium.Players;

import dominium.Cards.*;

import java.util.List;

public class TreasureOrProvincePlayer extends AIPlayer {

    public TreasureOrProvincePlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        if (cards.isEmpty()) {
            return null;
        }

        Card cardToPick = null;
        if (hasCard(cards, Province.class)) {
            cardToPick = findCard(cards, Province.class);
        } else if (hasCard(cards, Gold.class)) {
            cardToPick = findCard(cards, Gold.class);
        } else if (hasCard(cards, Silver.class)) {
            cardToPick = findCard(cards, Silver.class);
        } else if (hasCard(cards, Duchy.class)) {
            cardToPick = findCard(cards, Duchy.class);
        } else if (hasCard(cards, Copper.class)) {
            cardToPick = findCard(cards, Copper.class);
        } else if (hasCard(cards, Estate.class)) {
            cardToPick = findCard(cards, Estate.class);
        } else {
            cardToPick = cards.get((int) (Math.random() * cards.size()));
        }
        return cardToPick;
    }
}
