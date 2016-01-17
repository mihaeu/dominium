package dominium.Cards;

public class Estate extends Card implements VictoryCard {
    private int cost = 2;

    @Override
    public int getVictoryPoints() {
        return 1;
    }
}
