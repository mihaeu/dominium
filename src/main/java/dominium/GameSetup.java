package dominium;

import java.util.ArrayList;
import java.util.Collection;

public class GameSetup {
    public static final int MIN_PLAYER_NUMBER = 1;
    public static final int MAX_PLAYER_NUMBER = 4;
    public static final int MIN_KINGDOMCARD_SET_NUMBER = 1;
    public static final int MAX_KINGDOMCARD_SET_NUMBER = 1;
    private PermanentGameState permanentGameState;
    private TurnBasedGameState turnBasedGameState;
    private Collection<Player> playerCollection;
    private Collection<Card> cardCollection;
    private Player startPlayer;
    private Collection<Collection<Card>> kingdomCards;


    public GameMaster setUpGame(int playerNumber, int kingdomCardSetNumber) {

        boolean initiationPossible = verifyStartConditions(playerNumber, kingdomCardSetNumber);
        GameMaster master = null;
        if (initiationPossible) {
            //Dont change order of initiation!!!!!
            initiatePlayers(playerNumber);
            initiateKingdomCardSets(kingdomCardSetNumber, playerNumber);
            initiatePermanentGameState(playerCollection, cardCollection, startPlayer);
            initiateTurnbasedGameState(cardCollection, playerNumber);

            //todo impl rest of init

            master = new GameMaster(permanentGameState, turnBasedGameState);
        }
        return master;
    }

    private void initiateTurnbasedGameState(Collection<Card> cardCollection, int playerNumber) {
        //todo impl different player numbers

        kingdomCards = new ArrayList<Collection<Card>>();

        //fill with basic Cards, always the same
        int numberOfCoppers = 60 - playerNumber * 7;

        //todo check real number for point cards !!!!
        int numberOfEstates = 12 - playerNumber * 3;
        fillKingdomCards(new Card("Copper", Card.Type.Money, 0, 1), numberOfCoppers);
        fillKingdomCards(new Card("Silver", Card.Type.Money, 3, 2), 40);
        fillKingdomCards(new Card("Gold", Card.Type.Money, 6, 3), 30);
        fillKingdomCards(new Card("Estate", Card.Type.Point, 2), numberOfEstates);
        fillKingdomCards(new Card("Duchy", Card.Type.Point, 5), 12);
        fillKingdomCards(new Card("Province", Card.Type.Point, 8), 12);


        fillKingdomCards(new Card("Curse", Card.Type.Point, 0), 0);


        turnBasedGameState = new TurnBasedGameState(kingdomCards);
    }

    private void fillKingdomCards(Card card, int numberOfCards) {

        //This is correct I hope .
        if (numberOfCards > 0) {

            Collection<Card> cardToFillCollection = new ArrayList<Card>();

            while (numberOfCards > 0) {
                --numberOfCards;
                cardToFillCollection.add(card);
            }
            kingdomCards.add(cardToFillCollection);
        }
    }

    private void initiatePermanentGameState(Collection<Player> playerCollection, Collection<Card> cardCollection, Player startPlayer) {
        permanentGameState = new PermanentGameState(playerCollection, cardCollection, startPlayer);
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
