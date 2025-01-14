package javafx2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a simple UI
        Label label = new Label("Hello, JavaFX!");
        Button button = new Button("Click Me");

        VBox root = new VBox(label, button);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}