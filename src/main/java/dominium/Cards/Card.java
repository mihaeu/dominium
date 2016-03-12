package dominium.Cards;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    protected int cost;
    protected String text;
    protected List<CardType> types = new ArrayList<>();
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

    public List<CardType> getTypes() {
        return types;
    }
}
