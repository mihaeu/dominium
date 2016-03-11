package dominium.Util;

import dominium.Players.Player;

import java.util.logging.Level;

public class ConsoleLogger implements Logger {
    private java.util.logging.Logger logger;

    public ConsoleLogger() {
        logger = java.util.logging.Logger.getLogger("Default");
    }

    @Override
    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    @Override
    public void debug(String message) {
        logger.log(Level.FINE, message);
    }

    @Override
    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    @Override
    public void info(String message, Player player) {
        logger.log(Level.INFO, message + String.format(
                " - Ha:%2d|Di:%2d|De:%2d|Bu:%2d|Ac:%2d|Co:%2d",
                player.handCards().size(),
                player.discardedCards().size(),
                player.deckCards().size(),
                player.getBuys(),
                player.getActions(),
                player.getCoins()
            )
        );
    }

    @Override
    public void debug(String message, Player player) {
        logger.log(Level.FINE, message + String.format(
                " - Ha:%2d|Di:%2d|De:%2d|Bu:%2d|Ac:%2d|Co:%2d",
                player.handCards().size(),
                player.discardedCards().size(),
                player.deckCards().size(),
                player.getBuys(),
                player.getActions(),
                player.getCoins()
                )
        );
    }

    @Override
    public void error(String message, Player player) {
        logger.log(Level.SEVERE, message + String.format(
                " - Ha:%2d|Di:%2d|De:%2d|Bu:%2d|Ac:%2d|Co:%2d",
                player.handCards().size(),
                player.discardedCards().size(),
                player.deckCards().size(),
                player.getBuys(),
                player.getActions(),
                player.getCoins()
                )
        );
    }
}
