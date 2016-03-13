package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;
import dominium.Players.Player;

public class Remodel extends Card implements ActionCard {
    public Remodel() {
        cost = 4;
        text = "Trash a card from your hand. Gain a card costing up to 2 Coins more than the trashed card.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        Card selectedCardForTrashing = player.selectCard(player.handCards());
        if (selectedCardForTrashing == null) {
            return;
        }
        player.trashCardFromHand(selectedCardForTrashing);

        CardStack cardsToChooseFrom = master.kingdomCards().keysOfNonEmptyStacks()
                .filterCards(selectedCardForTrashing.getCost() + 2);
        Card selectedCard = player.selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }
        player.discardedCards().add(
            master.kingdomCards().get(selectedCard).pop()
        );
    }
}
