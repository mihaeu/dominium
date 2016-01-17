package dominium.Cards;

public class Gold extends Card implements TreasureCard {
    private int cost = 6;

    @Override
    public int getValue() {
        return 3;
    }
}
