package org.example;

import java.util.Locale;
import java.util.Scanner;

public class ShoppingCartService {
    public static double calculateTotal(int itemCount, Locale locale) {
        Scanner scan = new Scanner(System.in);
        double totalCost = 0.0;

        for (int i = 1; i <= itemCount; i++) {
            System.out.print("Price " + i + ": ");
            double price = scan.nextDouble();
            System.out.print("Quantity " + i + ": ");
            int qty = scan.nextInt();
            totalCost += price * qty;
        }
        return totalCost;
    }
}
