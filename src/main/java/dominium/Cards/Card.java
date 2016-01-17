package dominium.Cards;

public abstract class Card {
    protected int cost;

    public int getCost() {
        return cost;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}



