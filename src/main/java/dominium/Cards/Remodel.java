package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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

        List<Card> cardsToChooseFrom = master.kingdomCards().values().stream()
            .filter(stack -> stack.size() > 0)
            .filter(stack -> stack.peek().getCost() <= selectedCardForTrashing.getCost() + 2)
            .map(Stack::peek)
            .collect(Collectors.toList());
        Card selectedCard = player.selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }
        player.discardedCards().add(
            master.kingdomCards().get(selectedCard).pop()
        );
    }
}
