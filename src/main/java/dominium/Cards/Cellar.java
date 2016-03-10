package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Cellar extends Card implements ActionCard {
    public Cellar() {
        cost = 2;
        text = "+1 Action, Discard any number of cards. +1 Card per card discarded.";
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        player.setActions(player.getActions() + 1);

        int cardsDiscarded = 0;
        Card selectedCard = player.selectCard(player.handCards());
        while (selectedCard != null) {
            cardsDiscarded++;
            player.discardCard(selectedCard);
            selectedCard = player.selectCard(player.handCards());
        }
        player.drawCards(cardsDiscarded);
    }
}
