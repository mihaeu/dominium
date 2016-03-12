package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Chapel extends Card implements ActionCard {
    public Chapel() {
        cost = 2;
        text = "Trash up to 4 cards from your hand.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        int maxCards = 4;
        Player player = master.currentPlayer();
        Card selectedCard = player.selectCard(player.handCards());
        while (maxCards > 0
                && selectedCard != null) {
            --maxCards;
            player.trashCardFromHand(selectedCard);
            selectedCard = player.selectCard(player.handCards());
        }
    }
}
