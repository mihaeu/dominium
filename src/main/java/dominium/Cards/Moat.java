package dominium.Cards;

import dominium.KingdomCardMap;
import dominium.Players.Player;

import java.util.List;

public class Moat extends Card implements ActionCard, ReactionCard {
    public Moat() {
        cost = 2;
        text = "+2 Cards, When another player plays an Attack card, you may reveal this from your hand."
                + " If you do, you are unaffected by that Attack.";
        types.add(CardType.Action);
        types.add(CardType.Reaction);
    }

    @Override
    public void resolve(Player player, List<Player> otherPlayers, KingdomCardMap kingdomCards) {
        player.drawCards(2);
    }
}
