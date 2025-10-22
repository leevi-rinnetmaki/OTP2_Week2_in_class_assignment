package org.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in, "UTF-8");

        System.out.println("Select the language:");
        System.out.println("1. English");
        System.out.println("2. Finnish");
        System.out.println("3. Swedish");
        System.out.println("4. Japanese");
        System.out.print("> ");

        int choice = scan.nextInt();

        Locale locale;
        switch (choice) {
            case 1 -> locale = new Locale("en", "US");
            case 2 -> locale = new Locale("fi", "FI");
            case 3 -> locale = new Locale("sv", "SE");
            case 4 -> locale = new Locale("ja", "JP");
            default -> locale = new Locale("en", "US");
        }

        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", locale);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

        // --- Use ResourceBundle for all prompts ---
        System.out.print(rb.getString("prompt.items") + " ");
        int itemCount = scan.nextInt();

        double totalCost = 0.0;

        for (int i = 1; i <= itemCount; i++) {
            System.out.print(rb.getString("prompt.price") + " " + i + ": ");
            double price = scan.nextDouble();

            System.out.print(rb.getString("prompt.quantity") + " " + i + ": ");
            int quantity = scan.nextInt();

            totalCost += price * quantity;
        }

        System.out.println("-----------------------------------");
        System.out.println(rb.getString("total") + " " + currencyFormat.format(totalCost));
    }
}
