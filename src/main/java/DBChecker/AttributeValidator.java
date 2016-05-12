package DBChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class AttributeValidator {
    public static List<String> getValidAttributes(List<String> nouns) {
        List<String> strings = new ArrayList<String>();
        for (String i : nouns) {
            if (i.toLowerCase().equals("price")) {
                strings.add("price");
            } else if (i.toLowerCase().equals("location")) {
                strings.add("location");
            } else if (i.toLowerCase().equals("brand") || i.toLowerCase().equals("brands")) {
                strings.add("brand");
            }
        }
        return strings;
    }
}
