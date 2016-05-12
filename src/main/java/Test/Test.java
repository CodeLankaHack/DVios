package Test;

import Linguistics.PriceLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Test {
    public static void main(String[] args) {
        PriceLogic logic = new PriceLogic();
        List<String> strings = new ArrayList<String>();
        strings.add("dettol");
        System.out.println(logic.price(strings).toString());
    }
}
