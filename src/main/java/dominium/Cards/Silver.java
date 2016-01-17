package dominium.Cards;

public class Silver extends Card implements TreasureCard {
    public Silver() {
        this.cost = 3;
    }

    @Override
    public int getValue() {
        return 2;
    }
}
