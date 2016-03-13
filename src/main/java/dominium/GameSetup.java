package dominium;

import dominium.Cards.*;
import dominium.Players.Player;

import java.util.*;
import java.util.stream.IntStream;

public class GameSetup {
    private static final int NUMBER_OF_NORMAL_KINGDOM_CARD = 10;
    private static final int NUMBER_OF_VICTORY_CARDS = 12;
    private static final int NUMBER_COPPER_START_HAND = 7;
    private static final int NUMBER_ESTATES_START_HAND = 3;
    private static final int TOTAL_NUMBER_OF_COPPER = 60;
    private static final int NUMBER_OF_GOLD_CARDS = 30;
    private static final int NUMBER_OF_SILVER_CARDS = 40;

    public GameState initiateGameState(List<Player> players) {
        int numberOfCoppers = TOTAL_NUMBER_OF_COPPER - players.size() * NUMBER_COPPER_START_HAND;
        int numberOfEstates = NUMBER_OF_VICTORY_CARDS - players.size() * NUMBER_ESTATES_START_HAND;
        int numberOfProvinces = NUMBER_OF_VICTORY_CARDS - ((4 - players.size()) * 2);

        KingdomCardMap kingdomCards = new KingdomCardMap();
        kingdomCards.put(new Copper(), createCardStack(new Copper(), numberOfCoppers));
        kingdomCards.put(new Silver(), createCardStack(new Silver(), NUMBER_OF_SILVER_CARDS));
        kingdomCards.put(new Gold(), createCardStack(new Gold(), NUMBER_OF_GOLD_CARDS));
        kingdomCards.put(new Estate(), createCardStack(new Estate(), numberOfEstates));
        kingdomCards.put(new Duchy(), createCardStack(new Duchy(), NUMBER_OF_VICTORY_CARDS));
        kingdomCards.put(new Province(), createCardStack(new Province(), numberOfProvinces));

        kingdomCards.put(new Cellar(), createCardStack(new Cellar(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Market(), createCardStack(new Market(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Militia(), createCardStack(new Militia(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Mine(), createCardStack(new Mine(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Moat(), createCardStack(new Moat(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Remodel(), createCardStack(new Remodel(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Smithy(), createCardStack(new Smithy(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Village(), createCardStack(new Village(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Woodcutter(), createCardStack(new Woodcutter(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(new Workshop(), createCardStack(new Workshop(), NUMBER_OF_NORMAL_KINGDOM_CARD));

        GameState gameState = new GameState(kingdomCards);
        for (Player player : players) {
            CardStack startCards = new CardStack();
            IntStream.range(0, 7).forEach(i -> startCards.push(new Copper()));
            IntStream.range(0, 3).forEach(i -> startCards.push(new Estate()));
            startCards.shuffle();

            CardStack handCards = new CardStack();
            for (int i = 0; i < 5; i++) {
                Card card = startCards.pop();
                handCards.add(card);
            }

            player.deckCards().addAll(startCards);
            player.handCards().addAll(handCards);
        }

        return gameState;
    }

    private CardStack createCardStack(Card card, int numberOfCards) {
        CardStack cards = new CardStack();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(card);
        }
        return cards;
    }
}
