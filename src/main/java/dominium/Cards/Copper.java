package dominium.Cards;

public class Copper extends Card implements TreasureCard {
    protected int cost = 0;

    @Override
    public int getValue() {
        return 1;
    }
}
