package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Village extends Card implements ActionCard {
    public Village() {
        cost = 3;
        text = "+1 Card +2 Actions";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.drawCards(1);
        player.setActions(player.getActions() + 2);
    }
}
