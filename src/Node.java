public class Node {
    private String type;
    private int xcoords;
    private int ycoords;
    private int listPosition;
    private Node next;
    private Node prev;

    public Node(String type, int listPosition, int xcoords, int ycoords) {
        this.next = null;
        this.prev = null;
        this.type = type;
        this.listPosition = listPosition;
        this.xcoords = xcoords;
        this.ycoords = ycoords;
    }

    public String getType() {
        return this.type;
    }

    public int getListPosition() {
        return this.listPosition;
    }

    public int getXcoords() {
        return this.xcoords;
    }

    public int getYcoords() {
        return this.ycoords;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setPrev(Node node) {
        this.prev = node;
    }

    /*
    public void movepPlayer(int i, Node temp) {
        if (i > 0) {
            while (i != 0) {
                System.out.println(temp.getListPosition());
                temp = temp.getNext();
                System.out.println(temp.getListPosition());
                i--;
            }
        }
        
        if (i < 0) {
            while (i != 0) {
                System.out.println(temp.getListPosition());
                temp = temp.getPrev();
                System.out.println(temp.getListPosition());
                i++;
            }
        } 
    }
    */
}
