package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Laboratory extends Card implements ActionCard {
    public Laboratory() {
        cost = 5;
        text = "+2 Cards +1 Action";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.drawCards(2);
        player.setActions(player.getActions() + 1);
    }
}
