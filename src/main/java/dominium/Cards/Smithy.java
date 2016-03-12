package dominium.Cards;

import dominium.GameMaster;

public class Smithy extends Card implements ActionCard {
    public Smithy() {
        cost = 4;
        text = "+3 Cards";
        types.add(CardType.Action);
    }

    @Override
    public void resolve(GameMaster master) {
        master.currentPlayer().drawCards(3);
    }
}
