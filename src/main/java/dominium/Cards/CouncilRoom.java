package dominium.Cards;

import dominium.GameMaster;

public class CouncilRoom extends Card implements ActionCard {
    public CouncilRoom() {
        cost = 5;
        text = "+4 Cards +1 Buy Each other player draws a card";
    }

    @Override
    public void resolve(GameMaster master) {
        master.currentPlayer().drawCards(4);
        master.currentPlayer().setBuys(master.currentPlayer().getBuys() + 1);

        master.otherPlayers().forEach(player -> player.drawCards(1));
    }
}
