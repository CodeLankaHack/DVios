package Test;

import Linguistics.ComparatorLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Test {
    public static void main(String[] args) {
        ComparatorLogic logic = new ComparatorLogic();
        List<String> strings = new ArrayList<String>();
        strings.add("lux");
        System.out.println(logic.compare(strings, "cheapest").toString());
    }
}
