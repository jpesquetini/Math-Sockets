import java.io.IOException;

/**
 * Crear servidor y cliente y manejar ambos desde aqui
 * <p>
 * Manejar 2 game windows y en base a los mensajes aca modificar la interfaz
 */
public class Game_logic {
    private Server jugador1;
    private Client jugador2;
    private Game_window game;
    private Node temp;
    private GameList gameList;

    public void registerServer(Server jugador1) {
        this.jugador1 = jugador1;
    }

    public void registerClient(Client jugador2) {
        this.jugador2 = jugador2;
    }

    public void registerGamedata(Node temp, GameList gameList) {
        this.temp = temp;
        this.gameList = gameList;
    }

    public void create_game(String type) throws IOException {
        // 50/50 de jugador inicial
        if (type.equals("Server")) {
            game = new Game_window("Server", gameList, "player1", jugador1, jugador2);
        } else {
            game = new Game_window("Client", gameList, "player2", jugador1, jugador2);
        }
        game.boardlogic(temp);
    }
}