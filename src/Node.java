/**
 * Esta clase define la logica de los nodos.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */

public class Node {
    private String type;
    private int xcoords;
    private int ycoords;
    private int listPosition;
    private Node next;
    private Node prev;

    /**
     * Este metodo cronstruye el nodo.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node(String type, int listPosition, int xcoords, int ycoords) {
        this.next = null;
        this.prev = null;
        this.type = type;
        this.listPosition = listPosition;
        this.xcoords = xcoords;
        this.ycoords = ycoords;
    }


    /**
     * Este metodo retorna un string cual indica el tipo de casilla que almacena este nodo.
     *
     * @return String de tipo de casilla
     */
    public String getType() {
        return this.type;
    }


    /**
     * Este metodo retorna un entero el cual indica el numero de posicion del nodo en la lista del juego.
     *
     * @return int de la posicion en la lista
     */
    public int getListPosition() {
        return this.listPosition;
    }

    /**
     * Este metodo retorna un entero el cual indica la coordenada x del jugador en el mapa de juego.
     *
     * @return int de la coordenada x
     */
    public int getXcoords() {
        return this.xcoords;
    }

    /**
     * Este metodo retorna un entero el cual indica la coordenada y del jugador en el mapa de juego.
     *
     * @throws
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */
    public int getYcoords() {
        return this.ycoords;
    }

    /**
     * Este metodo retorna el siguiente nodo en la lista.
     *
     * @throws
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node getNext() {
        return this.next;
    }

    /**
     * Este metodo se encarga de asignar la referencia al siguiente nodo en la lista.
     */
    public void setNext(Node node) {
        this.next = node;
    }

    /**
     * Este metodo retorna el nodo anterior en la lista.
     *
     * @throws 
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node getPrev() {
        return this.prev;
    }

    /**
     * Este metodo se encarga de asignar la referencia al nodo anterior de la lista.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void setPrev(Node node) {
        this.prev = node;
    }
}
