package dominium.Cards;

import dominium.GameMaster;

public class Moat extends Card implements ActionCard {
    public Moat() {
        cost = 2;
        text = "+2 Cards";
    }

    @Override
    public void resolve(GameMaster master) {
        master.currentPlayer().drawCards(2);
    }
}
