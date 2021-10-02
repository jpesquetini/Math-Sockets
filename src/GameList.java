import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Esta clase define la logica de la lista de juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class GameList {
    private DoublyLinkedList gameData;
    private Server jugador1;
    private Client jugador2;

    static String[] typeArray = new String[]{"Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Reto",
            "Trampa", "Trampa", "Trampa", "Trampa",
            "Tunel", "Tunel", "Tunel"};

    /**
     * Este metodo se encarga de randomiiisar un array de strings los cuales indican el tipo de casilla para la lista de
     * juego y retorna este array.
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
     */
    public Node get_head() {
        return gameData.getHead();
    }

    /**
     * Este metodo se encarga de la logica de los movimientos del jugador 1 en el tablero de juego.
     */
    public void movePlayer1(int i, boolean firstTime, Game_window game_window) {
        if (i > 0) {
            //System.out.println(gameData.player1.getListPosition());

            while (i != 0) {

                String position = gameData.player1.getType();
                if (!position.equals("Fin")) {
                    gameData.player1 = gameData.player1.getNext();
                    game_window.move_jugador1(gameData.player1.getXcoords(),gameData.player1.getYcoords());
                    i--;
                } else {
                    //System.out.println("Se ha llegado al Fin");
                    break;
                }

            }


            if (firstTime) {
                String casilla = gameData.player1.getType();
                //System.out.println(casilla);

                if (casilla.equals("Reto")) {
                    reto("player1",game_window);
                }
                if (casilla.equals("Tunel")) {
                    tunel("player1",game_window);
                }
                if (casilla.equals("Trampa")) {
                    trampa("player1",game_window);
                }
            }
        }

        if (i < 0) {
            //System.out.println(gameData.player1.getListPosition());

            while (i != 0) {
                String position = gameData.player1.getType();

                if (!position.equals("Inicio")) {
                    gameData.player1 = gameData.player1.getPrev();
                    game_window.jugador1.setLocation(gameData.player1.getXcoords(),gameData.player1.getYcoords());
                    i++;
                } else {
                    //System.out.println("Se ha llegado al Inicio");
                    break;
                }
            }

            //System.out.println(gameData.player1.getListPosition());
        }
    }

    /**
     * Este metodo se encarga de la logica de los movimientos del jugador 2 en el tablero de juego.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void movePlayer2(int i, boolean firstTime, Game_window game_window) {
        if (i > 0) {
            //System.out.println(gameData.player2.getListPosition());
            while (i != 0) {
                String position = gameData.player2.getType();

                if (!position.equals("Fin")) {
                    gameData.player2 = gameData.player2.getNext();
                    game_window.move_jugador2(gameData.player2.getXcoords(),gameData.player2.getYcoords());
                    i--;
                } else {
                    //System.out.println("Se ha llegado al Fin");
                    break;
                }
            }




            if (firstTime) {
                String casilla = gameData.player2.getType();
                //System.out.println(casilla);

                if (casilla.equals("Reto")) {
                    reto("player2", game_window);
                }
                if (casilla.equals("Tunel")) {
                    tunel("player2", game_window);
                }
                if (casilla.equals("Trampa")) {
                    trampa("player2", game_window);
                }
            }
        }

        if (i < 0) {
            //System.out.println(gameData.player2.getListPosition());

            while (i != 0) {
                String position = gameData.player2.getType();

                if (!position.equals("Inicio")) {
                    gameData.player2 = gameData.player2.getPrev();
                    game_window.move_jugador2(gameData.player2.getXcoords(),gameData.player2.getYcoords());
                    i++;
                } else {
                    //System.out.println("Se ha llegado al Inicio");
                    break;
                }
            }

            //System.out.println(gameData.player2.getListPosition());
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo tunel.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void tunel(String currentPlayer, Game_window game_window) {
        int rnd = (int) (Math.random() * 3 + 1);
        System.out.println(rnd);


        if (currentPlayer.equals("player1")) {
            movePlayer1(rnd, false, game_window);
        }
        if (currentPlayer.equals("player2")) {
            movePlayer2(rnd, false, game_window);
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo trampa.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void trampa(String currentPlayer, Game_window game_window) {
        int rnd = (int) (Math.random() * 3 + 1);
        rnd *= -1;
        System.out.println(rnd);

        if (currentPlayer.equals("player1")) {
            movePlayer1(rnd, false,game_window);
        }
        if (currentPlayer.equals("player2")) {
            movePlayer2(rnd, false, game_window);
        }
    }

    /**
     * Este metodo se encarga de la logica de las casillas de tipo reto.
     */
    public void reto(String currentPlayer, Game_window game_window) {
        String playerChallenged = "";




        if (currentPlayer.equals("player1")) {
            movePlayer1(1, false, game_window);
            playerChallenged = "player2";
        }
        if (currentPlayer.equals("player2")) {
            movePlayer2(1, false, game_window);
            playerChallenged = "player1";
        }

        int arg1 = (int) (Math.random() * 50 + 1);
        int arg2 = (int) (Math.random() * 50 + 1);
        String[] operatorArray = {"+", "-", "*", "/"};
        int i = new Random().nextInt(operatorArray.length);
        String operator = operatorArray[i];
        String operation = arg1 + operator + arg2;



        game_window.reto.setText("Su reto es: " + operation);
        game_window.side_panel.add(game_window.enviar_respuesta);
        game_window.side_panel.add(game_window.answer);
        game_window.side_panel.add(game_window.respuesta);
        game_window.side_panel.add(game_window.reto);
        game_window.side_panel.setVisible(true);

        game_window.enviar_respuesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int retoAnswer = Integer.parseInt(game_window.respuesta.getText());
                int retoResult = 0;
                if (operator.equals("+")) {
                    retoResult = arg1 + arg2;
                }
                if (operator.equals("-")) {
                    retoResult = arg1 - arg2;
                }
                if (operator.equals("*")) {
                    retoResult = arg1 * arg2;
                }
                if (operator.equals("/")) {
                    retoResult = (int) (arg1 / arg2);
                }


                if (retoAnswer == retoResult) {
                    System.out.println("CORRECT");
                }
                if (retoAnswer != retoResult) {
                    System.out.println(":(");

                    if (currentPlayer.equals("player1")) {
                        movePlayer2(-1, false, game_window);
                    }
                    if (currentPlayer.equals("player2")) {
                        movePlayer1(-1, false, game_window);
                    }
                }




            }
        });



    }
}