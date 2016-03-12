package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Market extends Card implements ActionCard {
    public Market() {
        cost = 5;
        text = "+1 Card +1 Buy +1 Action +1 Coin";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        player.drawCards(1);
        player.setBuys(player.getBuys() + 1);
        player.setActions(player.getActions() + 1);
        player.setCoins(player.getCoins() + 1);
    }
}
