package dominium.Players;

import dominium.Cards.*;

import java.util.List;

public class TreasureOrProvincePlayer extends Player {
    public TreasureOrProvincePlayer(String name) {
        this.name = name;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        Card cardToPick = null;
        if (hasCard(cards, Province.class)) {
            cardToPick = findCard(cards, Province.class);
        } else if (hasCard(cards, Gold.class)) {
            cardToPick = findCard(cards, Gold.class);
        } else if (hasCard(cards, Silver.class)) {
            cardToPick = findCard(cards, Silver.class);
        } else if (hasCard(cards, Copper.class)) {
            cardToPick = findCard(cards, Copper.class);
        } else if (hasCard(cards, Duchy.class)) {
            cardToPick = findCard(cards, Duchy.class);
        } else if (hasCard(cards, Estate.class)) {
            cardToPick = findCard(cards, Estate.class);
        }
        return cardToPick;
    }

    private boolean hasCard(List<Card> cards, Class search) {
        for (Card card : cards) {
            if (card.getClass() == search) {
                return true;
            }
        }
        return false;
    }

    private Card findCard(List<Card> cards, Class search) {
        for (Card card : cards) {
            if (card.getClass() == search) {
                return card;
            }
        }
        return null;
    }
}
