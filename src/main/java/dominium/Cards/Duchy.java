package dominium.Cards;

public class Duchy extends Card implements VictoryCard {
    private int cost = 5;

    @Override
    public int getVictoryPoints() {
        return 3;
    }
}
