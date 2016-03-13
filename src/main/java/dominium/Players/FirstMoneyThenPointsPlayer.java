package dominium.Players;

import dominium.CardStack;
import dominium.Cards.*;

public class FirstMoneyThenPointsPlayer extends AIPlayer {
    private int goldOrSilverCards = 0;

    public FirstMoneyThenPointsPlayer(String name) {
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
        } else if (cards.hasCard(new Gold()) && goldOrSilverCards <= 5) {
            cardToPick = findCard(cards, Gold.class);
            ++goldOrSilverCards;
        } else if (cards.hasCard(new Duchy())) {
            cardToPick = findCard(cards, Duchy.class);
        } else if (cards.hasCard(new Silver()) && turns <= 10) {
            cardToPick = findCard(cards, Silver.class);
            ++goldOrSilverCards;
        } else if (cards.hasCard(new Estate()) && turns >= 15) {
            cardToPick = findCard(cards, Estate.class);
        } else {
            cardToPick = cards.get((int) (Math.random() * cards.size()));
        }

        return cardToPick;
    }
}
