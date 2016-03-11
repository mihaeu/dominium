package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Mine extends Card implements ActionCard {
    public Mine() {
        cost = 5;
        text = "Trash a Treasure card from your hand. "
                + "Gain a Treasure card costing up to 3 Coins more; put it into your hand.";
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        Card selectedCardForTrashing = player.selectCard(player.handCards());
        player.trashCardFromHand(selectedCardForTrashing);

        int maxCost = selectedCardForTrashing.getCost() + 3;
        List<Card> cardsToChooseFrom = master.kingdomCards().values().stream()
                .filter(stack -> stack.size() > 0)
                .filter(stack -> stack.peek().cost <= maxCost)
                .map(Stack::peek)
                .collect(Collectors.toList());
//        List<Card> cardsToChooseFrom = new ArrayList<>();
//        for (Stack<Card> stack : master.kingdomCards().values()) {
//            if (!stack.empty() && stack.peek().cost <= maxCost) {
//                cardsToChooseFrom.add(stack.peek());
//            }
//        }

        Card selectedCard = player.selectCard(cardsToChooseFrom);
        player.handCards().add(
            master.kingdomCards().get(selectedCard.getClass()).pop()
        );
    }
}
