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

        Map<Class, Stack<Card>> kingdomCards = new HashMap<>();
        kingdomCards.put(Copper.class, createCardStack(new Copper(), numberOfCoppers));
        kingdomCards.put(Silver.class, createCardStack(new Silver(), NUMBER_OF_SILVER_CARDS));
        kingdomCards.put(Gold.class, createCardStack(new Gold(), NUMBER_OF_GOLD_CARDS));
        kingdomCards.put(Estate.class, createCardStack(new Estate(), numberOfEstates));
        kingdomCards.put(Duchy.class, createCardStack(new Duchy(), NUMBER_OF_VICTORY_CARDS));
        kingdomCards.put(Province.class, createCardStack(new Province(), numberOfProvinces));
        kingdomCards.put(Province.class, createCardStack(new Province(), numberOfProvinces));

        kingdomCards.put(CouncilRoom.class, createCardStack(new CouncilRoom(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(Laboratory.class, createCardStack(new Laboratory(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(Market.class, createCardStack(new Market(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(Moat.class, createCardStack(new Moat(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(Smithy.class, createCardStack(new Smithy(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(Village.class, createCardStack(new Village(), NUMBER_OF_NORMAL_KINGDOM_CARD));
        kingdomCards.put(Woodcutter.class, createCardStack(new Woodcutter(), NUMBER_OF_NORMAL_KINGDOM_CARD));

        GameState gameState = new GameState(kingdomCards);
        for (Player player : players) {
            CardStack startCards = new CardStack();
            IntStream.range(0, 7).forEach(i -> startCards.push(new Copper()));
            IntStream.range(0, 3).forEach(i -> startCards.push(new Estate()));
            startCards.shuffle();

            List<Card> handCards = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Card card = startCards.pop();
                handCards.add(card);
            }

            player.deckCards().addAll(startCards);
            player.handCards().addAll(handCards);
        }

        return gameState;
    }

    private Stack<Card> createCardStack(Card card, int numberOfCards) {
        Stack<Card> cards = new Stack<>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(card);
        }
        return cards;
    }
}
