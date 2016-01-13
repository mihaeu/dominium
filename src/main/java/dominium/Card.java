package dominium;

public class Card {
    private int cost;
    private Type type;
    private int value;
    private String name;

    public Card(Type type, int cost){
        this.type = type;
        this.cost = cost;
    }

    public Card(String name,Type type, int cost){
        this.type = type;
        this.cost = cost;
        this.name = name;
    }


    public Card(Type type, int cost,int value){
        this.type = type;
        this.cost = cost;
        this.value = value;
    }

    public Card(String name,Type type, int cost,int value){
        this.type = type;
        this.cost = cost;
        this.value = value;
        this.name = name;
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



