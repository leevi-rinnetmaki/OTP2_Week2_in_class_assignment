package org.example;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCartController {

    @FXML private ChoiceBox<String> languageChoice;
    @FXML private TextField itemCountField;
    @FXML private TextArea outputArea;
    @FXML private Text titleText;
    @FXML private Button calculateButton;

    private ResourceBundle bundle;
    private Locale locale;

    @FXML
    public void initialize() {
        languageChoice.setItems(FXCollections.observableArrayList("English", "Finnish", "Swedish", "Japanese"));
        languageChoice.setValue("English");
        setLocale(new Locale("en", "US"));

        languageChoice.setOnAction(e -> updateLanguage());
    }

    private void updateLanguage() {
        String choice = languageChoice.getValue();
        switch (choice) {
            case "Finnish" -> setLocale(new Locale("fi", "FI"));
            case "Swedish" -> setLocale(new Locale("sv", "SE"));
            case "Japanese" -> setLocale(new Locale("ja", "JP"));
            default -> setLocale(new Locale("en", "US"));
        }
    }

    private void setLocale(Locale locale) {
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("MessagesBundle", locale);
        updateUIText();
    }

    private void updateUIText() {
        titleText.setText(bundle.getString("app.title"));
        calculateButton.setText(bundle.getString("button.calculate"));
        outputArea.setPromptText(bundle.getString("prompt.output"));
    }

    @FXML
    private void handleCalculate() {
        try {
            int itemCount = Integer.parseInt(itemCountField.getText());
            double total = ShoppingCartService.calculateTotal(itemCount, locale);
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            outputArea.setText(bundle.getString("total") + ": " + currencyFormat.format(total));
        } catch (NumberFormatException e) {
            outputArea.setText(bundle.getString("error.invalid"));
        }
    }
}
