import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameList {
    private DoublyLinkedList gameData;
    static String[] typeArray = new String[] {"Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Reto",
            "Trampa", "Trampa", "Trampa", "Trampa",
            "Tunel", "Tunel", "Tunel"};

    public static String[] listRandomizer() {
        List<String> typeList = Arrays.asList(typeArray);
        Collections.shuffle(typeList);
        typeList.toArray(typeArray);
        return typeArray;
    }

    public GameList() {
        gameData = new DoublyLinkedList();
    }

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

    public void printGameList() {
        Node temp = this.gameData.getHead();

        while (temp != null) {
            System.out.println("[" + temp.getType() + ", " + temp.getListPosition() + ", " + temp.getXcoords() + ", " + temp.getYcoords() + "]");
            temp = temp.getNext();
        }
    }

    public Node get_head() {
        return  gameData.getHead();
    }

    public Node get_player1() {
        return gameData.getPlayer1();
    }

    public Node get_player2() {
        return gameData.getPlayer2();
    }

    public void movePlayer(int i, boolean firstTime) {
        if (i > 0) {
            while (i != 0) {
                System.out.println(gameData.player1);
                gameData.player1 = gameData.player1.getNext();
                System.out.println(gameData.player1.getListPosition());
                i--;
            }

            if (firstTime) {
                String casilla = gameData.player1.getType();
                System.out.println(casilla);
            }
        }
    }
}

