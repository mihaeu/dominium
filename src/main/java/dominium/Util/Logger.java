package dominium.Util;

import dominium.Players.Player;

public interface Logger {
    void info(String message);
    void debug(String message);
    void error(String message);

    void info(String message, Player player);
    void debug(String message, Player player);
    void error(String message, Player player);
}
