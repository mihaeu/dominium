package dominium.Cards;

import dominium.GameMaster;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Workshop extends Card implements ActionCard {
    public Workshop() {
        cost = 3;
        text = "Gain a card costing up to 4 Coins.";
    }

    @Override
    public void resolve(GameMaster master) {
        List<Card> cardsToChooseFrom = master.kingdomCards().values().stream()
            .filter(stack -> stack.size() > 0)
            .filter(stack -> stack.peek().getCost() <= 4)
            .map(Stack::peek)
            .collect(Collectors.toList());
        Card selectedCard = master.currentPlayer().selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }
        master.currentPlayer().discardedCards().add(
                master.kingdomCards().get(selectedCard.getClass()).pop()
        );
    }
}
