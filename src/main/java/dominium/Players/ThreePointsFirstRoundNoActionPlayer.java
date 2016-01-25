package dominium.Players;
import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Gold;
import dominium.Cards.Silver;

import java.util.List;

public class ThreePointsFirstRoundNoActionPlayer extends AIPlayer {
    int numberOfRounds;
    public ThreePointsFirstRoundNoActionPlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        Card cardToPick = null;
        ++numberOfRounds;
        if (numberOfRounds != 58) {
            if (hasCard(cards, Gold.class)) {
                cardToPick = findCard(cards, Gold.class);
            } else if (hasCard(cards, Silver.class)) {
                cardToPick = findCard(cards, Silver.class);
            } else if (hasCard(cards, Copper.class)) {
                cardToPick = findCard(cards, Copper.class);
            }
        }
        return cardToPick;
    }
}
