package dominium.Players;

        import dominium.Cards.Card;
import dominium.Cards.Copper;
import dominium.Cards.Gold;
import dominium.Cards.Silver;

import java.util.List;

public class NormalThreePointsPlayer extends AIPlayer {

    public NormalThreePointsPlayer(String name) {
        super(name);
    }

    @Override
    public Card selectCard(List<Card> cards) {
        Card cardToPick = null;
        if (hasCard(cards, Copper.class)) {
            cardToPick = findCard(cards, Copper.class);
        } else if (hasCard(cards, Silver.class)) {
            cardToPick = findCard(cards, Silver.class);
        } else if (hasCard(cards, Gold.class)) {
            cardToPick = findCard(cards, Gold.class);
        }
        return cardToPick;
    }
}

