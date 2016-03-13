package dominium.Cards;

import dominium.CardStack;
import dominium.GameMaster;

public class Workshop extends Card implements ActionCard {
    public Workshop() {
        cost = 3;
        text = "Gain a card costing up to 4 Coins.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        CardStack cardsToChooseFrom = master.kingdomCards().keysOfNonEmptyStacks()
                .filterCards(4);
        Card selectedCard = master.currentPlayer().selectCard(cardsToChooseFrom);
        if (selectedCard == null) {
            return;
        }
        master.currentPlayer().discardedCards().add(
                master.kingdomCards().get(selectedCard).pop()
        );
    }
}
