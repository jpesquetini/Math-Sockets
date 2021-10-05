import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase define la logica de la lista de juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class GameList {
    private final DoublyLinkedList gameData;
    private Server gameServer;
    private Client gameClient;
    private Game_window gameWindow_p1;
    private Game_window gameWindow_p2;

    static String[] typeArray = new String[]{"Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Reto",
            "Trampa", "Trampa", "Trampa", "Trampa",
            "Tunel", "Tunel", "Tunel"};

    /**
     * Este metodo se encarga de generar un array de strings aleatorios los cuales indican el tipo de casilla para la
     * lista de juego y retorna este array.
     *
     * @return
     */
    public static String[] listRandomizer() {
        List<String> typeList = Arrays.asList(typeArray);
        Collections.shuffle(typeList);
        typeList.toArray(typeArray);
        return typeArray;
    }

    /**
     * Este metodo construye la lista de juego.
     */
    public GameList() {
        gameData = new DoublyLinkedList();
    }

    /**
     * Este metodo se encarga de crear y asignar los nodos que almacenaran la informacion de las casillas de juego.
     */
    public void gameListAssignment() {
        int[] xcoords = {-115, 35, 185, 335, -115, 35, 185, 335, -115, 35, 185, 335, -115, 35, 185, 335};
        int[] ycoords = {-105, -105, -105, -105, 70, 70, 70, 70, 245, 245, 245, 245, 420, 420, 420, 420};
        //Sumarle 85 para obtener la  "y" del jugador 2

        this.gameData.insertLast("Inicio", 1, xcoords[0], ycoords[0]);

        int i = 0;
        String[] types = listRandomizer();
        int listPosition = 2;
        int n = 1;

        while (i < 14) {
            this.gameData.insertLast(types[i], listPosition, xcoords[n], ycoords[n]);
            i++;
            listPosition++;
            n++;
        }

        this.gameData.insertLast("Fin", 16, xcoords[15], ycoords[15]);
    }

    /**
     * Este metodo imprime la lista de juego.
     */
    public void printGameList() {
        Node temp = this.gameData.getHead();

        while (temp != null) {
            System.out.println("[" + temp.getType() + ", " + temp.getListPosition() + ", " + temp.getXcoords() + ", " +
                    temp.getYcoords() + "]");
            temp = temp.getNext();
        }
    }

    /**
     * Este metodo retorna el nodo que es la cabeza de la lista de juego.
     *
     * @return
     */
    public Node get_head() {
        return gameData.getHead();
    }

    /**
     * Este metodo se encarga de la logica de los movimientos del jugador 1 en el tablero de juego.
     *
     * @param i
     * @param firstTime
     * @param socket
     * @param reto
     * @param game_window
     * @throws IOException
     */
    public void movePlayer1(int i, boolean firstTime, boolean socket, boolean reto, Game_window game_window) throws
            IOException {
        if (i > 0) {
            while (i != 0) {
                String position = gameData.player1.getType();

                if (!position.equals("Fin")) {
                    gameData.player1 = gameData.player1.getNext();
                    i--;
                } else {
                    break;
                }
            }
            game_window.move_jugador1(gameData.player1.getXcoords(), gameData.player1.getYcoords());
            if (firstTime) {
                String casilla = gameData.player1.getType();
                if (casilla.equals("Reto")) {
                    reto("player1", game_window);
                }
                if (casilla.equals("Tunel")) {
                    tunel("player1", game_window);
                }
                if (casilla.equals("Trampa")) {
                    trampa("player1", game_window);
                }
            } else {
                if (!socket) {
                    if (reto) {
                        gameServer.my_turn(i);
                        gameWindow_p2.reto_logic("player2");
                    } else {
                        gameServer.my_turn(i);
                    }
                }
            }
        }
        if (i < 0) {
            while (i != 0) {
                String position = gameData.player1.getType();
                if (!position.equals("Inicio")) {
                    gameData.player1 = gameData.player1.getPrev();
                    i++;
                } else {
                    break;
                }
            }
            game_window.jugador1.setLocation(gameData.player1.getXcoords(), gameData.player1.getYcoords());
        }
        if (!socket) {
            gameServer.my_turn(i);
        }
    }

    /**
     * Este metodo se encarga de la logica de los movimientos del jugador 2 en el tablero de juego.
     *
     * @param i
     * @param firstTime
     * @param socket
     * @param reto
     * @param game_window
     * @throws IOException
     */
    public void movePlayer2(int i, boolean firstTime, boolean socket, boolean reto, Game_window game_window) throws
            IOException {
        if (i > 0) {
            while (i != 0) {
                String position = gameData.player2.getType();

                if (!position.equals("Fin")) {
                    gameData.player2 = gameData.player2.getNext();
                    i--;
                } else {
                    break;
                }
            }
            game_window.move_jugador2(gameData.player2.getXcoords(), gameData.player2.getYcoords());
            if (firstTime) {
                String casilla = gameData.player2.getType();

                if (casilla.equals("Reto")) {
                    reto("player2", game_window);
                }
                if (casilla.equals("Tunel")) {
                    tunel("player2", game_window);
                }
                if (casilla.equals("Trampa")) {
                    trampa("player2", game_window);
                }
            } else {
                if (!socket) {
                    if (reto) {
                        gameClient.my_turn(i);
                        gameWindow_p1.reto_logic("player1");
                    } else {
                        gameClient.my_turn(i);
                    }
                }
            }
        }
        if (i < 0) {
            while (i != 0) {
                String position = gameData.player2.getType();

                if (!position.equals("Inicio")) {
                    gameData.player2 = gameData.player2.getPrev();
                    i++;
                } else {
                    break;
                }
            }
            game_window.move_jugador2(gameData.player2.getXcoords(), gameData.player2.getYcoords());
        }
        if (!socket) {
            gameClient.my_turn(i);
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo tunel.
     *
     * @param currentPlayer
     * @param game_window
     * @throws IOException
     */
    public void tunel(String currentPlayer, Game_window game_window) throws IOException {
        int rnd = (int) (Math.random() * 3 + 1);
        System.out.println(rnd);


        if (currentPlayer.equals("player1")) {
            movePlayer1(rnd, false, false, false, game_window);
        }
        if (currentPlayer.equals("player2")) {
            movePlayer2(rnd, false, false, false, game_window);
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo trampa.
     *
     * @param currentPlayer
     * @param game_window
     * @throws IOException
     */
    public void trampa(String currentPlayer, Game_window game_window) throws IOException {
        int rnd = (int) (Math.random() * 3 + 1);
        rnd *= -1;
        System.out.println(rnd);

        if (currentPlayer.equals("player1")) {
            movePlayer1(rnd, false, false, false, game_window);
        }
        if (currentPlayer.equals("player2")) {
            movePlayer2(rnd, false, false, false, game_window);
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo reto.
     *
     * @param currentPlayer
     * @param game_window
     * @throws IOException
     */
    public void reto(String currentPlayer, Game_window game_window) throws IOException {
        if (currentPlayer.equals("player1")) {
            movePlayer1(1, false, false, true, game_window);
        }
        if (currentPlayer.equals("player2")) {
            movePlayer2(1, false, false, true, game_window);
        }
    }

    /**
     * @return
     */
    public Node get_player1() {
        return gameData.getPlayer1(); //.getType();
    }

    /**
     * @return
     */
    public Node get_player2() {
        return gameData.getPlayer2(); //.getType();
    }

    /**
     * @param player1
     */
    public void set_server(Server player1) {
        this.gameServer = player1;
    }

    /**
     * @param player2
     */
    public void set_client(Client player2) {
        this.gameClient = player2;
    }

    /**
     * @param gameWindow
     */
    public void set_gameWindow1(Game_window gameWindow) {
        this.gameWindow_p1 = gameWindow;
    }

    /**
     * @param gameWindow2
     */
    public void set_gameWindow2(Game_window gameWindow2) {
        this.gameWindow_p2 = gameWindow2;
    }
}