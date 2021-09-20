public class DoublyLinkedList {
    private Node head;
    private Node player1;
    private Node player2;
    private int size;

    public DoublyLinkedList() {
            this.head = null;
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

    public Node getPlayer1() {
        return this.player1;
    }

    public Node getPlayer2() {
        return this.player2;
    }

    /*
    public void insertLast(Object data) {
            Node newNode = new Node(data);

            if (this.isEmpty()) {
                this.head = this.last = newNode;
            }
            else {
                Node temp = this.last;
                
                temp.setNext(newNode);
                newNode.setPrev(temp);
                this.last = newNode;
            }
            this.size++;
    }

    public Node deleteFirst() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            Node temp = this.head;

            this.head = temp.getNext();
            this.size--;
            return temp;
        }
    }
    */
}
