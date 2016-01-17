package dominium.Cards;

public class Gold extends Card implements TreasureCard {
    public Gold() {
        this.cost = 6;
    }

    @Override
    public int getValue() {
        return 3;
    }
}
