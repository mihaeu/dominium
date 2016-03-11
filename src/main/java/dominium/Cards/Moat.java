package dominium.Cards;

import dominium.GameMaster;

public class Moat extends Card implements ActionCard {
    public Moat() {
        cost = 2;
        text = "+2 Cards, When another player plays an Attack card, you may reveal this from your hand."
                + " If you do, you are unaffected by that Attack.";
    }

    @Override
    public void resolve(GameMaster master) {
        master.currentPlayer().drawCards(2);
    }
}
