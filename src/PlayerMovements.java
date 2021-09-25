public class PlayerMovements {
    public PlayerMovements(int i, Node temp, boolean firstTime) {
        if (i > 0) {
            while (i != 0) {
                System.out.println(temp.getListPosition());
                temp = temp.getNext();
                System.out.println(temp.getListPosition());
                i--;
            }

            if (firstTime) {
                String casilla = temp.getType();
                System.out.println(casilla);
            }
        }
        
        if (i < 0) {
            while (i != 0) {
                System.out.println(temp.getListPosition());
                temp = temp.getPrev();
                System.out.println(temp.getListPosition());
                i++;
            }

            if (firstTime) {
                String casilla = temp.getType();
                System.out.println(casilla);
            }
        } 
    }
}
