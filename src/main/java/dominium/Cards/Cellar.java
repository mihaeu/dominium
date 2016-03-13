package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Cellar extends Card implements ActionCard {
    public Cellar() {
        cost = 2;
        text = "+1 Action, Discard any number of cards. +1 Card per card discarded.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.setActions(player.getActions() + 1);

        int cardsDiscarded = 0;
        Card selectedCard = player.selectCard(player.handCards());
        while (selectedCard != null) {
            cardsDiscarded++;
            player.discardCard(selectedCard);
            selectedCard = player.selectCard(player.handCards());
        }
        player.drawCards(cardsDiscarded);
    }
}
