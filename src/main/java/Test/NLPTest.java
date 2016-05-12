package Test;

import Language.Classifier;
import Language.Parser;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class NLPTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser p = new Parser();
        while (true) {
            System.out.print("Enter a text to parse: ");

            Classifier cs = p.getClassifier(scanner.nextLine());

            for (String i : cs.getHashMap().keySet()) {
                System.out.print(i);
                System.out.println(" : " + Arrays.toString(cs.getHashMap().get(i).toArray()));
            }
        }
    }
}
