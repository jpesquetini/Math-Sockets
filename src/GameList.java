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
    private DoublyLinkedList gameData;
    static String[] typeArray = new String[] {"Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Reto",
            "Trampa", "Trampa", "Trampa", "Trampa",
            "Tunel", "Tunel", "Tunel"};

    /**
     * Este metodo se encarga de randomiiisar un array de strings los cuales indican el tipo de casilla para la lista de juego y
     * retorna este array.
     *
     * @throws typeArray
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public static String[] listRandomizer() {
        List<String> typeList = Arrays.asList(typeArray);
        Collections.shuffle(typeList);
        typeList.toArray(typeArray);
        return typeArray;
    }

    /**
     * Este metodo construye la lista de juego.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public GameList() {
        gameData = new DoublyLinkedList();
    }

    /**
     * Este metodo se encarga de crear y asignar los nodos que almacenaran la informacion de las casillas de juego.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void gameListAssignment() {
        int[] xcoords = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600};
        int[] ycoords = {110, 220, 330, 440, 550, 660, 770, 880, 990, 1010, 1110, 1220, 1330, 1440, 1550, 1660};

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
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void printGameList() {
        Node temp = this.gameData.getHead();

        while (temp != null) {
            System.out.println("[" + temp.getType() + ", " + temp.getListPosition() + ", " + temp.getXcoords() + ", " + temp.getYcoords() + "]");
            temp = temp.getNext();
        }
    }

    /**
     * Este metodo retorna el nodo que es la cabeza de la lista de juego.
     *
     * @throws head 
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node get_head() {
        return  gameData.getHead();
    }

    /**
     * Este metodo se encarga de la logica de los movimientos del jugador 1 en el tablero de juego.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void movePlayer1(int i, boolean firstTime) {
        if (i > 0) {
            System.out.println(gameData.player1.getListPosition());

            while (i != 0) {
                String position = gameData.player1.getType();

                if (position != "Fin") {
                    gameData.player1 = gameData.player1.getNext();
                    i--;
                } else {
                    System.out.println("Se ha llegado al Fin");
                    break;
                }
            }

            System.out.println(gameData.player1.getListPosition());

            if (firstTime) {
                String casilla = gameData.player1.getType();
                System.out.println(casilla);

                if (casilla == "Reto") {
                    System.out.println("Esta es una casilla de Reto");
                }
                if (casilla == "Tunel") {
                    tunel("player1");
                }
                if (casilla == "Trampa") {
                    trampa("player1");
                }
            }
        }

        if (i < 0) {
            System.out.println(gameData.player1.getListPosition());

            while (i != 0) {
                String position = gameData.player1.getType();

                if (position != "Inicio"){
                    gameData.player1 = gameData.player1.getPrev();
                    i++;
                } else {
                    System.out.println("Se ha llegado al Inicio");
                    break;
                }
            }

            System.out.println(gameData.player1.getListPosition());

            if (firstTime) {
                String casilla = gameData.player1.getType();
                System.out.println(casilla);

                if (casilla == "Reto") {
                    System.out.println("Esta es una casilla de Reto");
                }
                if (casilla == "Tunel") {
                    tunel("player1");
                }
                if (casilla == "Trampa") {
                    trampa("player1");
                }
            }
        }
    }

    /**
     * Este metodo se encarga de la logica de los movimientos del jugador 2 en el tablero de juego.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void movePlayer2(int i, boolean firstTime) {
        if (i > 0) {
            System.out.println(gameData.player2.getListPosition());

            while (i != 0) {
                String position = gameData.player2.getType();

                if (position != "Fin") {
                    gameData.player1 = gameData.player2.getNext();
                    i--;
                } else {
                    System.out.println("Se ha llegado al Fin");
                    break;
                }
            }

            System.out.println(gameData.player2.getListPosition());

            if (firstTime) {
                String casilla = gameData.player2.getType();
                System.out.println(casilla);

                if (casilla == "Reto") {
                    System.out.println("Esta es una casilla de Reto");
                }
                if (casilla == "Tunel") {
                    tunel("player2");
                }
                if (casilla == "Trampa") {
                    trampa("player2");
                }
            }
        }

        if (i < 0) {
            System.out.println(gameData.player2.getListPosition());

            while (i != 0) {
                String position = gameData.player2.getType();

                if (position != "Inicio"){
                    gameData.player1 = gameData.player2.getPrev();
                    i++;
                } else {
                    System.out.println("Se ha llegado al Inicio");
                    break;
                }
            }

            System.out.println(gameData.player2.getListPosition());

            if (firstTime) {
                String casilla = gameData.player2.getType();
                System.out.println(casilla);

                if (casilla == "Reto") {
                    System.out.println("Esta es una casilla de Reto");
                }
                if (casilla == "Tunel") {
                    tunel("player2");
                }
                if (casilla == "Trampa") {
                    trampa("player2");
                }
            }
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo tunel.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void tunel(String currentPlayer) {
        int rnd = (int)(Math.random()*3 + 1);
        System.out.println(rnd);

        if (currentPlayer == "player1") {
            movePlayer1(rnd, false);
        }
        if (currentPlayer == "player2") {
            movePlayer2(rnd, false);
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo trampa.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void trampa(String currentPlayer) {
        int rnd = (int)(Math.random()*3 + 1);
        rnd *= -1;
        System.out.println(rnd);

        if (currentPlayer == "player1") {
            movePlayer1(rnd, false);
        }
        if (currentPlayer == "player2") {
            movePlayer2(rnd, false);
        }
    }
}

