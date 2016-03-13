package dominium.Cards;

import dominium.CardStack;
import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Workshop extends Card implements ActionCard {
    public Workshop() {
        cost = 3;
        text = "Gain a card costing up to 4 Coins.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        CardStack cardsToChooseFrom = kingdomCards.keysOfNonEmptyStacks()
                .filterCards(4);
        Card selectedCard = player.selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }
        player.discardedCards().add(
            kingdomCards.get(selectedCard).pop()
        );
    }
}
