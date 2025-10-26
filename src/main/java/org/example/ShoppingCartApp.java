package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCartApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.ENGLISH);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/main.fxml"), bundle);

        Scene scene = new Scene(loader.load(), 400, 300);
        stage.setScene(scene);
        stage.setTitle("Leevi Rinnetmäki");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}