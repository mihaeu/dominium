package dominium;

import java.util.ArrayList;
import java.util.Collection;

public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 2;
    public static final int MAX_PLAYER_NUMBER = 4;
    public static final int MIN_KINGDOMCARD_SET_NUMBER = 1;
    public static final int MAX_KINGDOMCARD_SET_NUMBER = 1;
    PermanentGameState permanentGameState;
    Collection<Player> playerCollection;
    Collection<Card> cardCollection;
    private Player startPlayer;


    public boolean setUpGame(int playerNumber, int kingdomCardSetNumber) {

        boolean initiationPossible = verifyStartConditions(playerNumber, kingdomCardSetNumber);
        if (initiationPossible) {

            initiatePlayers(playerNumber);
            initiateKingdomCardSets(kingdomCardSetNumber, playerNumber);


            permanentGameState = new PermanentGameState(playerCollection, cardCollection, startPlayer);
            TurnbasedGameState turnbasedGameState = new TurnbasedGameState();

            //todo impl rest of init

            GameMaster gameMaster = new GameMaster(permanentGameState, turnbasedGameState);
        }
        return initiationPossible;
    }

    private void initiateKingdomCardSets(int kingdomCardSetNumber, int playerNumber) {
        //todo impl diff cardsets und playernumber
        cardCollection = new ArrayList<Card>();
        cardCollection.add(new Card("Copper", Card.Type.Money, 0, 1));
        cardCollection.add(new Card("Silver", Card.Type.Money, 3, 2));
        cardCollection.add(new Card("Gold", Card.Type.Money, 6, 3));
        cardCollection.add(new Card("Estate", Card.Type.Point, 2));
        cardCollection.add(new Card("Duchy", Card.Type.Point, 5));
        cardCollection.add(new Card("Province", Card.Type.Point, 8));
        cardCollection.add(new Card("Curse", Card.Type.Point, 0));
    }


    private void initiatePlayers(int playerNumber) {
        playerCollection = new ArrayList<Player>();
        while (playerNumber > 0) {
            playerCollection.add(new Player());
            --playerNumber;
        }

        //todo impl real selection system

        startPlayer = playerCollection.iterator().next();
    }

    private boolean verifyStartConditions(int playerNumber, int kingdomCardSetNumber) {
        boolean initiationPossible = true;
        boolean playerNumberOkay = (playerNumber <= MAX_PLAYER_NUMBER && playerNumber >= MIN_PLAYER_NUMBER);
        boolean kingdomCardSetOkay = (kingdomCardSetNumber >= MIN_KINGDOMCARD_SET_NUMBER && kingdomCardSetNumber <= MAX_KINGDOMCARD_SET_NUMBER);
        initiationPossible = playerNumberOkay && kingdomCardSetOkay;

        return initiationPossible;
    }
}
