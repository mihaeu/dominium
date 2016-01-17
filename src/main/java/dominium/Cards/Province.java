package dominium.Cards;

public class Province extends Card implements VictoryCard {
    public Province() {
        this.cost = 8;
    }

    @Override
    public int getVictoryPoints() {
        return 6;
    }
}
