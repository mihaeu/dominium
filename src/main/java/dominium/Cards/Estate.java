package dominium.Cards;

public class Estate extends Card implements VictoryCard {
    public Estate() {
        cost = 2;
        text = "1 Victory Point";
    }

    @Override
    public int getVictoryPoints() {
        return 1;
    }
}
