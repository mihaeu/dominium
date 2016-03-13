package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Militia extends Card implements ActionCard, AttackCard {
    public Militia() {
        cost = 4;
        text = "+2 Coins, Each other player discards down to 3 cards in his hand.";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.setCoins(player.getCoins() + 2);

        for (Player otherPlayer : otherPlayers) {
            int cardsToDiscard = otherPlayer.handCards().size() - 3;

            if (otherPlayer.handCards().hasCard(new Moat())) {
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
