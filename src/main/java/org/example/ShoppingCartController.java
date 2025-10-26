package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCartController {

    @FXML private Text titleText;
    @FXML private ChoiceBox<String> languageChoice;
    @FXML private VBox itemsContainer;
    @FXML private Button addItemButton;
    @FXML private Button calculateButton;
    @FXML private TextArea outputArea;

    private ResourceBundle bundle;
    private Locale currentLocale;
    private List<ItemRow> itemRows = new ArrayList<>();

    @FXML
    public void initialize() {
        // Setup languages
        languageChoice.getItems().addAll("English", "Finnish", "Swedish", "Japanese");
        languageChoice.setValue("English");

        currentLocale = Locale.ENGLISH;
        bundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        updatePrompts();

        handleAddItem();

        languageChoice.setOnAction(e -> {
            switch (languageChoice.getValue()) {
                case "Finnish" -> currentLocale = new Locale("fi", "FI");
                case "Swedish" -> currentLocale = new Locale("sv", "SE");
                case "Japanese" -> currentLocale = new Locale("ja", "JP");
                default -> currentLocale = new Locale("en", "US");
            }
            bundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
            updatePrompts();
            handleCalculate();
        });

        addItemButton.setOnAction(e -> handleAddItem());

        calculateButton.setOnAction(e -> handleCalculate());
    }

    private void updatePrompts() {
        titleText.setText(bundle.containsKey("title") ? bundle.getString("title") : "Shopping Cart");

        for (int i = 0; i < itemRows.size(); i++) {
            ItemRow row = itemRows.get(i);
            row.priceField.setPromptText(bundle.getString("prompt.price") + " " + (i + 1));
            row.quantityField.setPromptText(bundle.getString("prompt.quantity") + " " + (i + 1));
        }

        outputArea.setPromptText(bundle.containsKey("prompt.result") ? bundle.getString("prompt.result") : "Result will appear here");
    }

    @FXML
    private void handleAddItem() {
        HBox row = new HBox(10);

        TextField priceField = new TextField();
        priceField.setPromptText(bundle.getString("prompt.price") + " " + (itemRows.size() + 1));

        TextField quantityField = new TextField();
        quantityField.setPromptText(bundle.getString("prompt.quantity") + " " + (itemRows.size() + 1));

        row.getChildren().addAll(new Label((itemRows.size() + 1) + ":"), priceField, quantityField);
        itemsContainer.getChildren().add(row);

        itemRows.add(new ItemRow(priceField, quantityField));
    }

    @FXML
    private void handleCalculate() {
        double totalCost = 0.0;

        for (ItemRow row : itemRows) {
            try {
                double price = Double.parseDouble(row.priceField.getText());
                int quantity = Integer.parseInt(row.quantityField.getText());
                totalCost += price * quantity;
            } catch (NumberFormatException e) {
                /*
                outputArea.setText(bundle.containsKey("invalid.input") ? bundle.getString("invalid.input") : "Invalid input in one of the items.");
                return;
                 */
            }
        }

        outputArea.setText(bundle.getString("total") + " " + String.format("$%.2f", totalCost));
    }

    private static class ItemRow {
        TextField priceField;
        TextField quantityField;

        ItemRow(TextField priceField, TextField quantityField) {
            this.priceField = priceField;
            this.quantityField = quantityField;
        }
    }
}