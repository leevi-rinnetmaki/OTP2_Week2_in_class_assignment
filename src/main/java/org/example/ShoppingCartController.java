package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class ShoppingCartController {

    @FXML private Text titleText;
    @FXML private ChoiceBox<String> languageChoice;
    @FXML private TextField itemCountField;
    @FXML private Button calculateButton;
    @FXML private TextArea outputArea;

    @FXML
    private void handleCalculate() {
        try {
            int itemCount = Integer.parseInt(itemCountField.getText());
            outputArea.setText("Total items: " + itemCount);
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input");
        }
    }

    @FXML
    public void initialize() {
        languageChoice.getItems().addAll("English", "Finnish", "Swedish", "Japanese");
        languageChoice.setValue("English");
    }
}
