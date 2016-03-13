package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;

import java.util.stream.Collectors;

public class Workshop extends Card implements ActionCard {
    public Workshop() {
        cost = 3;
        text = "Gain a card costing up to 4 Coins.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        CardStack cardsToChooseFrom = master.kingdomCards().keySet().stream()
            .filter(card -> master.kingdomCards().get(card).size() > 0)
            .filter(card -> card.getCost() <= 4)
            .collect(Collectors.toCollection(CardStack::new));
        Card selectedCard = master.currentPlayer().selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }
        master.currentPlayer().discardedCards().add(
                master.kingdomCards().get(selectedCard).pop()
        );
    }
}
