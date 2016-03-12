package dominium.Cards;

import dominium.GameMaster;
import dominium.Players.Player;

public class Village extends Card implements ActionCard {
    public Village() {
        cost = 3;
        text = "+1 Card +2 Actions";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        Player player = master.currentPlayer();
        player.drawCards(1);
        player.setActions(player.getActions() + 2);
    }
}
