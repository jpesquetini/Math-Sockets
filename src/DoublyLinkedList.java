/**
  * Esta clase define la logica de una lista doblemente enlazada.
  *
  * @author Andres Uriza
  * @author Daniel Castro
  * @author Jose Pablo Esquetini
  */

public class DoublyLinkedList {
    private Node head;
    private Node last;
    public Node player1;
    public Node player2;
    private int size;

    /**
     * Este metodo cronstruye la lista doblemente enlazada.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public DoublyLinkedList() {
        this.head = null;
        this.last = null;
        this.player1 = null;
        this.player2 = null;
        this.size = 0;
    }

    /**
     * Este metodo retorn un boolean indicando si la lista esta vacia.
     *
     * @throws true
     * @throws false 
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Este metodo retorna un entero el cual indica el tamaño de la lista.
     *
     * @throws size 
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public int getSize() {
        return this.size;
    }

    /**
     * Este metodo retorna el nodo que es la cabeza de la lista.
     *
     * @throws head 
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node getHead() {
        return this.head;
    }

    /**
     * Este metodo retorna el ultimo nodo de la lista.
     *
     * @throws last
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node getLast() {
        return this.last;
    }

    /**
     * Este metodo retorna el nodo en el cual se encuentra actualmente el jugador1.
     *
     * @throws player1 
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node getPlayer1() {
        return this.player1;
    }

    /**
     * Este metodo retorna el nodo en el cual se encuentra actualmente el jugador2.
     *
     * @throws player2
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Node getPlayer2() {
        return this.player2;
    }
    
    /**
     * Este metodo se encarga de insertar al final de la lista los nuevos nodos.
     *
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void insertLast(String type, int listPosition, int xcoords, int ycoords) {
        Node newNode = new Node(type, listPosition, xcoords, ycoords);

        if (this.isEmpty()) {
            this.head = this.last = this.player1 = this.player2 = newNode;
        }
        else {
            Node temp = this.last;

            temp.setNext(newNode);
            newNode.setPrev(temp);
            this.last = newNode;
        }
        this.size++;
    }
}
