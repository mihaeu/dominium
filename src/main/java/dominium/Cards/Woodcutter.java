package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Woodcutter extends Card implements ActionCard {
    public Woodcutter() {
        cost = 3;
        text = "+1 Buy +2 Coins";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.setBuys(player.getBuys() + 1);
        player.setCoins(player.getCoins() + 2);
    }
}
