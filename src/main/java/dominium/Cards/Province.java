package dominium.Cards;

public class Province extends Card implements VictoryCard {
    public Province() {
        cost = 8;
        text = "6 Victory Points";
        types.add(CardType.Victory);
    }

    @Override
    public int getVictoryPoints() {
        return 6;
    }
}
