package dominium.Players;

import java.util.List;

import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Duchy;
import dominium.Cards.Estate;
import dominium.Cards.Gold;
import dominium.Cards.Province;
import dominium.Cards.Silver;

public class FirstMoneyThenPointsPlayer extends AIPlayer {
    private int goldOrSilverCards = 0;
    private int victoryCards = 0;
    private int numberOfRounds = 0;

    public FirstMoneyThenPointsPlayer(String name) {
        this.name = name;
    }

    @Override
    public Card selectCard(List<Card> cards) {
        ++numberOfRounds;

        Card cardToPick = null;
        if (hasCard(cards, Province.class)) {
            cardToPick = findCard(cards, Province.class);
            ++victoryCards;
        } else if (hasCard(cards, Gold.class) && goldOrSilverCards <= 5) {
            cardToPick = findCard(cards, Gold.class);
            ++goldOrSilverCards;
        } else if (hasCard(cards, Duchy.class)) {
            cardToPick = findCard(cards, Duchy.class);
            ++victoryCards;
        } else if (hasCard(cards, Silver.class) && numberOfRounds <= 10) {
            cardToPick = findCard(cards, Silver.class);
            ++goldOrSilverCards;
        } else if (hasCard(cards, Estate.class) && numberOfRounds >= 15) {
            cardToPick = findCard(cards, Estate.class);
            ++victoryCards;
        }


        return cardToPick;
    }

}
