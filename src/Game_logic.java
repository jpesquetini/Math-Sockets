import java.io.IOException;

/**
 * Crear ventanas de juego en base a conexiones realizadas en el menu
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Game_logic extends Thread {
    private Server jugador1;
    private Client jugador2;
    private Node temp;
    private GameList gameList;

    int value = (int) (Math.random() * (3 - 1)) + 1;

    /**
     * @param jugador1
     */
    public void registerServer(Server jugador1) {
        this.jugador1 = jugador1;
    }

    /**
     * @param jugador2
     */
    public void registerClient(Client jugador2) {
        this.jugador2 = jugador2;
    }

    /**
     * @param temp
     * @param gameList
     */
    public void registerGamedata(Node temp, GameList gameList) {
        this.temp = temp;
        this.gameList = gameList;
    }

    /**
     * @param type
     * @throws IOException
     */
    public void create_game(String type) throws IOException {
        String currentPlayer;
        if (value == 1) {
            currentPlayer = "player1";
        } else {
            currentPlayer = "player2";
        }

        Game_window game;
        if (type.equals("Server")) {
            game = new Game_window(temp, "player1", gameList, currentPlayer, jugador1, jugador2);
            gameList.set_gameWindow1(game);
        } else {
            game = new Game_window(temp, "player2", gameList, currentPlayer, jugador1, jugador2);
            gameList.set_gameWindow2(game);
        }
    }
}