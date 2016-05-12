package MachineLearning;

import java.util.Scanner;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class MainLearner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name, price, location,brand,type;
        Learn learn = new Learn();
        while (true) {
            System.out.print("Product type: ");
            type = sc.nextLine();
            System.out.print("Product name: ");
            name = sc.nextLine();
            System.out.print("Product brand: ");
            brand = sc.nextLine();
            System.out.print("Product price: ");
            price = sc.nextLine();
            System.out.print("Product location: ");
            location = sc.nextLine();
            learn.learn(name, price, location,brand,type);
        }
    }
}
