package src.main;

/**
 * Created by SWINE on 10.01.2016.
 */
public class Card {
    int cost;
    Type type;
    int value;

    public Card(Type type, int cost){
        this.type = type;
        this.cost = cost;
    }


    public Card(Type type, int cost,int value){
        this.type = type;
        this.cost = cost;
        this.value = value;
    }



    public enum Type {
        Money,Point
    }
}



