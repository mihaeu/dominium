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

    @Override
    public boolean equals(Object o) {
        return this.getClass() == o.getClass();

    }

    @Override
    public int hashCode() {
        int result = cost;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (played ? 1 : 0);
        return result;
    }
}
