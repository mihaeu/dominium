package dominium.Cards;

public class Duchy extends Card implements VictoryCard {
    public Duchy() {
        this.cost = 5;
    }

    @Override
    public int getVictoryPoints() {
        return 3;
    }
}
