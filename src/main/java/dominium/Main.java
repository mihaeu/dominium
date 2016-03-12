package dominium;

import dominium.Players.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Map<Integer, List<Player>> games = new HashMap<>();

    public static void main(String[] args) {
        List<Player> players = getPlayersFromCliArguments(args);
        Logger logger = getLoggerFromCliArguments(args);
        int numberOfGames = getNumberOfGamesFromCliArguments(args);

        for (int i = 0; i < numberOfGames; i++) {
            List<Player> winners = runGame(players, logger);

            System.out.printf("\nGame %3d: ", i + 1);
            winners.forEach(player -> System.out.print(player + " "));
            games.put(i, winners);
        }

        if (getStatisticsFromCliArguments(args)) {
            printStatistics(players);
        }
    }

    public static List<Player> runGame(List<Player> players, Logger logger) {
        players.forEach(Player::reset);

        GameSetup gameSetup = new GameSetup();
        GameState state = gameSetup.initiateGameState(players);

        GameMaster gameMaster = new GameMaster(players, state, logger);
        gameMaster.startGame();
        return gameMaster.winner();
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

    private static List<Player> getPlayersFromCliArguments(String[] args) {
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

    private static Logger getLoggerFromCliArguments(String[] args) {
        for (String arg : args) {
            if (arg.matches("--debug")) {
                return LoggerFactory.getLogger("STDOUT");
            }
        }
        return LoggerFactory.getLogger("quiet");
    }

    private static boolean getStatisticsFromCliArguments(String[] args) {
        for (String arg : args) {
            if (arg.matches("--statistics")) {
                return true;
            }
        }
        return false;
    }

    private static void printStatistics(List<Player> players) {
        // initialize
        Map<Player, Integer> winsPerPlayer = new HashMap<>();
        for (Player player : players) {
            winsPerPlayer.put(player, 0);
        }

        // count wins
        for (Integer gameNumber : games.keySet()) {
            for (Player player : games.get(gameNumber)) {
                winsPerPlayer.put(player, winsPerPlayer.get(player) + 1);
            }
        }

        // compute and print
        System.out.println("\n\nStatistics:\n");
        for (Player player : winsPerPlayer.keySet()) {
            System.out.printf(
                "%-20s: %3d / %3d (%.1f%%)\n",
                player.getName(),
                winsPerPlayer.get(player),
                games.size(),
                (float) winsPerPlayer.get(player) / games.size() * 100
            );
        }
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
