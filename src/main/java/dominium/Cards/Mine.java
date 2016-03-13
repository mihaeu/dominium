package dominium.Cards;

import dominium.CardStack;
import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Mine extends Card implements ActionCard {
    public Mine() {
        cost = 5;
        text = "Trash a Treasure card from your hand. "
                + "Gain a Treasure card costing up to 3 Coins more; put it into your hand.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        Card selectedCardForTrashing = player.selectCard(
                player.handCards().filterCards(CardType.Treasure)
        );
        if (selectedCardForTrashing == null) {
            return;
        }
        player.trashCardFromHand(selectedCardForTrashing);

        int maxCost = selectedCardForTrashing.getCost() + 3;
        CardStack cardsToChooseFrom = kingdomCards.keysOfNonEmptyStacks()
                .filterCards(maxCost)
                .filterCards(CardType.Treasure);

        Card selectedCard = player.selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }

        player.handCards().add(
            kingdomCards.get(selectedCard).pop()
        );
    }
}
