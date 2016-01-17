package dominium.Cards;

public class Province extends Card implements VictoryCard {
    private int cost = 8;

    @Override
    public int getVictoryPoints() {
        return 6;
    }
}
