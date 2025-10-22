package org.example;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    @Test
    void testEnglishBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("en", "US"));
        assertEquals("Enter the number of items to purchase:", rb.getString("prompt.items"));
    }

    @Test
    void testFinnishBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("fi", "FI"));
        assertEquals("Anna ostettavien tuotteiden määrä:", rb.getString("prompt.items"));
    }

    @Test
    void testSwedishBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("sv", "SE"));
        assertEquals("Ange antalet varor att köpa:", rb.getString("prompt.items"));
    }

    @Test
    void testJapaneseBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("ja", "JP"));
        assertEquals("購入する商品の数を入力してください:", rb.getString("prompt.items"));
    }
}
