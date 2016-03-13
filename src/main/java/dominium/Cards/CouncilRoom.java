package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class CouncilRoom extends Card implements ActionCard {
    public CouncilRoom() {
        cost = 5;
        text = "+4 Cards +1 Buy Each other player draws a card";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.drawCards(4);
        player.setBuys(player.getBuys() + 1);

        otherPlayers.forEach(otherPlayer -> otherPlayer.drawCards(1));
    }
}
