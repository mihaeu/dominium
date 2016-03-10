package dominium.Cards;

public abstract class Card {
    protected int cost;
    protected String text;
    private boolean played;

    public int getCost() {
        return cost;
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public String getText() {
        return text;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
}



