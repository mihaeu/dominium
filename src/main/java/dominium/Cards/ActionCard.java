package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public interface ActionCard {
    void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards);
}
