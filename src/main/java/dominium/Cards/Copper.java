package dominium.Cards;

public class Copper extends Card implements TreasureCard {
    public Copper() {
        cost = 0;
        text = "1 Coin";
        types.add(CardType.Treasure);
    }

    @Override
    public int getValue() {
        return 1;
    }
}
