import java.util.Collection;

/**
 * Created by SWINE on 10.01.2016.
 */
public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 2;
    public static final int MAX_PLAYER_NUMBER = 4;
    public static final int MIN_KINGDOMCARDSETNUMBER = 1;
    public static final int MAX_KINGDOMCARDSETNUMBER = 1;
    PermanentGameState permanentGameState = 

    // private Collection<Card> KingdomCardSets;      first only preselected Sets

    public void setUpGame(int playerNumber, int kingdomCardSetNumber ){

        boolean initiationPossible = verifyStartConditions(playerNumber,kingdomCardSetNumber );
        //todo react to false input

        GameMaster gameMaster = new GameMaster();
        PermanentGameState permanentGameState = new PermanentGameState();
        TurnbasedGameState turnbasedGameState = new TurnbasedGameState();


        initiatePlayers(playerNumber);


        //todo impl rest of init
    }

    private void initiatePlayers(int playerNumber) {
        PermanentGameState permanentGameState = ;
        while(playerNumber > 0){
            permanentGameState.addPlayer(new Player());
            --playerNumber;
        }
    }

    private boolean verifyStartConditions(int playerNumber, int kingdomCardSetNumber) {
        boolean initiationPossible = true;
        boolean playerNumberOkay = (playerNumber <= MAX_PLAYER_NUMBER && playerNumber >= MIN_PLAYER_NUMBER) ;
        boolean kingdomCardSetOkay = (kingdomCardSetNumber >= MIN_KINGDOMCARDSETNUMBER && kingdomCardSetNumber <= MAX_KINGDOMCARDSETNUMBER);
        initiationPossible = playerNumberOkay && kingdomCardSetOkay;

        return initiationPossible;
    }
}
