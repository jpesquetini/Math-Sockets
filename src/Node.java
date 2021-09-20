public class Node {
    private String type;
    private Node next;
    private Node prev;

    public Node(String type) {
        this.next = null;
        this.prev = null;
        this.type = type;
    }

    public String getType() {
        return this.type;
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
}
