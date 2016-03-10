package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Laboratory extends Card implements ActionCard {
    public Laboratory() {
        cost = 5;
        text = "+2 Cards +1 Action";
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        player.drawCards(2);
        player.setActions(player.getActions() + 1);
    }
}
