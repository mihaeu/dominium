package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Market extends Card implements ActionCard {
    public Market() {
        cost = 5;
        text = "+1 Card +1 Buy +1 Action +1 Coin";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.drawCards(1);
        player.setBuys(player.getBuys() + 1);
        player.setActions(player.getActions() + 1);
        player.setCoins(player.getCoins() + 1);
    }
}
