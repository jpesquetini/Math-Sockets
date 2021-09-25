public class DoublyLinkedList {
    private Node head;
    private Node last;
    private Node player1;
    private Node player2;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.last = null;
        this.player1 = null;
        this.player2 = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int getSize() {
        return this.size;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getLast() {
        return this.last;
    }

    public Node getPlayer1() {
        return this.player1;
    }

    public Node getPlayer2() {
        return this.player2;
    }

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
