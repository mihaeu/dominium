package dominium.Cards;

public class Gold extends Card implements TreasureCard {
    public Gold() {
        cost = 6;
        text = "3 Coins";
        types.add(CardType.Treasure);
    }

    @Override
    public int getValue() {
        return 3;
    }
}
