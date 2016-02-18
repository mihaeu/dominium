package dominium.Cards;

public class Silver extends Card implements TreasureCard {
    public Silver() {
        cost = 3;
        text = "2 Coin";
    }

    @Override
    public int getValue() {
        return 2;
    }
}
