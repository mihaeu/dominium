package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;
import dominium.Players.Player;

import java.util.Stack;
import java.util.stream.Collectors;

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
        CardStack treasureCardsOnHand = player.handCards().stream()
                .filter(card -> card instanceof TreasureCard)
                .collect(Collectors.toCollection(CardStack::new));
        Card selectedCardForTrashing = player.selectCard(treasureCardsOnHand);
        if (selectedCardForTrashing == null) {
            return;
        }
        player.trashCardFromHand(selectedCardForTrashing);

        int maxCost = selectedCardForTrashing.getCost() + 3;
        CardStack cardsToChooseFrom = master.kingdomCards().values().stream()
                .filter(stack -> stack.size() > 0)
                .filter(stack -> stack.peek().cost <= maxCost)
                .filter(stack -> stack.peek() instanceof TreasureCard)
                .map(Stack::peek)
                .collect(Collectors.toCollection(CardStack::new));

        Card selectedCard = player.selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }

        player.handCards().add(
            master.kingdomCards().get(selectedCard).pop()
        );
    }
}
