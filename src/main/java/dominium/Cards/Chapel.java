package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Chapel extends Card implements ActionCard {
    public Chapel() {
        cost = 2;
        text = "Trash up to 4 cards from your hand.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        int maxCards = 4;
        Card selectedCard = player.selectCard(player.handCards());
        while (maxCards > 0
                && selectedCard != null) {
            --maxCards;
            player.trashCardFromHand(selectedCard);
            selectedCard = player.selectCard(player.handCards());
        }
    }
}
