package src.main;

/**
 * Created by SWINE on 10.01.2016.
 */
public class Card {
    private int cost;
    private Type type;
    private int value;
    private String name;

    public Card(Type type, int cost){
        this.type = type;
        this.cost = cost;
    }


    public Card(Type type, int cost,int value){
        this.type = type;
        this.cost = cost;
        this.value = value;
    }

    public int getCost() {
        return cost;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    public enum Type {
        Money,Point
    }
}



