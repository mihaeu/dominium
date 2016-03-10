package dominium;

import dominium.Players.*;
import dominium.Util.ConsoleLogger;
import dominium.Util.Logger;
import dominium.Util.NullLogger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfGames = getNumberOfGamesFromCliArguments(args);

        for (int i = 0; i < numberOfGames; i++) {
            runGame(args);
        }
    }

    public static void runGame(String[] args) {
        List<Player> players = getPlayersFromCliArguments(args);
        Logger logger = getLoggerFromCliArguments(args);

        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players);

        GameMaster gameMaster = new GameMaster(players, state, logger);
        gameMaster.startGame();
        gameMaster.winner().forEach(System.out::println);
    }

    private static int getNumberOfGamesFromCliArguments(String[] args) {
        for (String arg : args) {
            if (arg.matches("--runs=\\d+")) {
                String numberOfRuns = arg.replaceFirst("--runs=(\\d+)", "$1");
                return Integer.valueOf(numberOfRuns);
            }
        }
        return 1;
    }

    public static List<Player> getPlayersFromCliArguments(String[] args) {
        String playerConfiguration = "";
        for (String arg : args) {
            if (arg.matches("--players=.+")) {
                playerConfiguration = arg.replaceFirst("--players=(.+)", "$1");
            }
        }

        if (playerConfiguration.equals("")) {
            throw new IllegalArgumentException("No player configuration provided. Use e.g. --players=M,S");
        }

        List<Player> players = new ArrayList<>();
        for (String playerShorthand : playerConfiguration.split(",")) {
            switch (playerShorthand.toLowerCase()) {
                case "m":
                    players.add(new TreasureOrProvincePlayer("Michi-Bot " + generateRandomName()));
                    break;
                case "s":
                    players.add(new FirstMoneyThenPointsPlayer("Stef-Bot " + generateRandomName()));
                    break;
                case "r":
                    players.add(new RandomPlayer("Random-Bot " + generateRandomName()));
                    break;
                case "c":
                    players.add(new ConsolePlayer("Console-Player " + generateRandomName()));
                    break;
                default:
                    throw new IllegalArgumentException("Player configuration invalid");
            }
        }

        return players;
    }

    public static Logger getLoggerFromCliArguments(String[] args) {
        for (String arg : args) {
            if (arg.matches("--debug")) {
                return new ConsoleLogger();
            }
        }
        return new NullLogger();
    }

    private static String generateRandomName() {
        String[] randomNames = {
                "James",
                "John",
                "Robert",
                "Michael",
                "William",
                "David",
                "Richard",
                "Joseph",
                "Charles",
                "Thomas",
                "Christopher",
                "Daniel",
                "Matthew",
                "Donald",
                "Anthony",
                "Mark",
                "Paul",
                "Steven",
                "George",
                "Kenneth",
                "Andrew",
                "Edward",
                "Joshua",
                "Brian",
                "Kevin",
                "Ronald",
                "Timothy",
                "Jason",
                "Jeffrey",
                "Ryan",
                "Gary",
                "Nicholas",
                "Eric",
                "Jacob",
                "Stephen",
                "Jonathan",
                "Larry",
                "Frank",
                "Scott",
                "Justin",
                "Brandon",
                "Raymond",
                "Gregory",
                "Samuel",
                "Benjamin",
                "Patrick",
                "Jack",
                "Dennis",
                "Alexander",
                "Jerry",
                "Tyler",
                "Henry",
                "Douglas",
                "Aaron",
                "Peter",
                "Jose",
                "Walter",
                "Adam",
                "Zachary",
                "Nathan",
                "Harold",
                "Kyle",
                "Carl",
                "Arthur",
                "Gerald",
                "Roger",
                "Keith",
                "Lawrence",
                "Jeremy",
                "Terry",
                "Albert",
                "Joe",
                "Sean",
                "Willie",
                "Christian",
                "Jesse",
                "Austin",
                "Billy",
                "Bruce",
                "Ralph",
                "Bryan",
                "Ethan",
                "Roy",
                "Eugene",
                "Jordan",
                "Louis",
                "Wayne",
                "Alan",
                "Harry",
                "Russell",
                "Juan",
                "Dylan",
                "Randy",
                "Philip",
                "Vincent",
                "Noah",
                "Bobby",
                "Howard",
                "Gabriel",
                "Johnny"
        };
        return randomNames[(int) (Math.random() * randomNames.length)];
    }
}
