package dominium.Cards;

public abstract class Card {
    protected int cost;
    protected String text;

    public int getCost() {
        return cost;
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public String getText() {
        return text;
    }
}



