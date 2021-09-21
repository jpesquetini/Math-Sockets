import java.util.ArrayList;
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
        this.gameData.insertLast("Inicio");

        int i = 0;
        String[] types = listRandomizer();

        while (i < 14) {
            this.gameData.insertLast(types[i]);
            i++;
        }

        this.gameData.insertLast("Fin");
    }

    public void printGameList() {
        Node temp = this.gameData.getHead();

        while (temp != null) {
            System.out.println(temp.getType());
            temp = temp.getNext();
        }
    }
    public ArrayList<String> arraylist(){
        ArrayList<String> lista = new ArrayList<>();
        Node aux = this.gameData.getHead();

        while (aux != null) {
            lista.add(aux.getType());
            //System.out.println(aux.getType());
            aux = aux.getNext();
        }
        return lista;
    }

    //public static void main(String[] args) {
    //GameList gameData = new GameList();
    //gameData.gameListAssignment();
    //gameData.printGameList();
    //System.out.println(gameData.arraylist());
    //}
}
