package src.main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by SWINE on 10.01.2016.
 */
public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 2;
    public static final int MAX_PLAYER_NUMBER = 4;
    public static final int MIN_KINGDOMCARD_SET_NUMBER = 1;
    public static final int MAX_KINGDOMCARD_SET_NUMBER = 1;
    PermanentGameState permanentGameState;
    Collection<Player> playerCollection;



    public boolean setUpGame(int playerNumber, int kingdomCardSetNumber) {

        boolean initiationPossible = verifyStartConditions(playerNumber, kingdomCardSetNumber);
        if (initiationPossible) {

            initiatePlayers(playerNumber);
            initiateKingdomCardSets(kingdomCardSetNumber);

            permanentGameState = new PermanentGameState(playerCollection);
            TurnbasedGameState turnbasedGameState = new TurnbasedGameState();


            //todo impl rest of init

            GameMaster gameMaster = new GameMaster(permanentGameState,turnbasedGameState);

        }

        return initiationPossible;

    }

    private void initiateKingdomCardSets(int kingdomCardSetNumber) {

    }

    private void initiatePlayers(int playerNumber) {
        playerCollection = new ArrayList<Player>();
        while (playerNumber > 0) {
            playerCollection.add(new Player());
            --playerNumber;
        }
    }

    private boolean verifyStartConditions(int playerNumber, int kingdomCardSetNumber) {
        boolean initiationPossible = true;
        boolean playerNumberOkay = (playerNumber <= MAX_PLAYER_NUMBER && playerNumber >= MIN_PLAYER_NUMBER);
        boolean kingdomCardSetOkay = (kingdomCardSetNumber >= MIN_KINGDOMCARD_SET_NUMBER && kingdomCardSetNumber <= MAX_KINGDOMCARD_SET_NUMBER);
        initiationPossible = playerNumberOkay && kingdomCardSetOkay;

        return initiationPossible;
    }
}
