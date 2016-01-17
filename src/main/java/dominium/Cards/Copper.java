package dominium.Cards;

public class Copper extends Card implements TreasureCard {
    public Copper() {
        this.cost = 0;
    }

    @Override
    public int getValue() {
        return 1;
    }
}
