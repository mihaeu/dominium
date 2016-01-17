package dominium.Cards;

public class Silver extends Card implements TreasureCard {
    private int cost = 3;

    @Override
    public int getValue() {
        return 2;
    }
}
