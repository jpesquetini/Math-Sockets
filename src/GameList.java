import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameList {
    static String[] typeArray = new String[] {"Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Reto", "Tunel", "Tunel", "Tunel", "Tunel", "Trampa", "Trampa", "Trampa", "Trampa"};
        
    public static String[] listRandomizer() {
        List<String> typeList = Arrays.asList(typeArray);
        Collections.shuffle(typeList);
        typeList.toArray(typeArray);
        return typeArray;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(typeArray));

        String[] newList = listRandomizer();
        
        System.out.println(Arrays.toString(newList));
    }
}
