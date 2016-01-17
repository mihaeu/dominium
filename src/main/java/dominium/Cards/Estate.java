package dominium.Cards;

public class Estate extends Card implements VictoryCard {
    public Estate() {
        this.cost = 2;
    }

    @Override
    public int getVictoryPoints() {
        return 1;
    }
}
