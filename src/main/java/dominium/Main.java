package dominium;

public class Main {
    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup();
        GameMaster gameMaster = gameSetup.setUpGame(1, 1);
        gameMaster.startGame();
    }
}
