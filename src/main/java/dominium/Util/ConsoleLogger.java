package dominium.Util;

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
}
