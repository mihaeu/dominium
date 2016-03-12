package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Militia extends Card implements ActionCard, AttackCard {
    public Militia() {
        cost = 4;
        text = "+2 Coins, Each other player discards down to 3 cards in his hand.";
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        player.setCoins(player.getCoins() + 2);

        for (Player otherPlayer : master.otherPlayers()) {
            int cardsToDiscard = otherPlayer.handCards().size() - 3;

            if (otherPlayer.handCards().hasCard(Moat.class)) {
                cardsToDiscard = 0;
            }

            while (cardsToDiscard > 0) {
                Card selectedCard = otherPlayer.selectCard(otherPlayer.handCards());
                otherPlayer.discardCard(selectedCard);
                --cardsToDiscard;
            }
        }
    }
}
