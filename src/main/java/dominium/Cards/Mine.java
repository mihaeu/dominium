package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;
import dominium.Players.Player;

public class Mine extends Card implements ActionCard {
    public Mine() {
        cost = 5;
        text = "Trash a Treasure card from your hand. "
                + "Gain a Treasure card costing up to 3 Coins more; put it into your hand.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        Card selectedCardForTrashing = player.selectCard(
                master.currentPlayer().handCards().filterCards(CardType.Treasure)
        );
        if (selectedCardForTrashing == null) {
            return;
        }
        player.trashCardFromHand(selectedCardForTrashing);

        int maxCost = selectedCardForTrashing.getCost() + 3;
        CardStack cardsToChooseFrom = master.kingdomCards().keysOfNonEmptyStacks()
                .filterCards(maxCost)
                .filterCards(CardType.Treasure);

        Card selectedCard = player.selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }

        player.handCards().add(
            master.kingdomCards().get(selectedCard).pop()
        );
    }
}
