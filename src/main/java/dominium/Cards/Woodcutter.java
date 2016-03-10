package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Woodcutter extends Card implements ActionCard {
    public Woodcutter() {
        cost = 3;
        text = "+1 Buy +2 Coins";
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        player.setBuys(player.getBuys() + 1);
        player.setCoins(player.getCoins() + 2);
    }
}
