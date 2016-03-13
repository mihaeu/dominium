package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Smithy extends Card implements ActionCard {
    public Smithy() {
        cost = 4;
        text = "+3 Cards";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.drawCards(3);
    }
}
