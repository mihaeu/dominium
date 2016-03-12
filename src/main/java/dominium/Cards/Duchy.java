package dominium.Cards;

public class Duchy extends Card implements VictoryCard {
    public Duchy() {
        cost = 5;
        text = "3 Victory Points";
        types.add(CardType.Victory);
    }

    @Override
    public int getVictoryPoints() {
        return 3;
    }
}
