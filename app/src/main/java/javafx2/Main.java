package javafx2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Label display = new Label("0");
    private String currentNumber = "";
    private double firstOperand = 0;
    private String operator = "";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        VBox root = new VBox();
        root.getStyleClass().add("root");
        
        display.getStyleClass().add("display");
        
        GridPane gridPane = createGridPane();
        root.getChildren().addAll(display, gridPane);

        Scene scene = new Scene(root, 300, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("grid-pane");

        String[] buttonTexts = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        int row = 0;
        int col = 0;
        for (String text : buttonTexts) {
            Button button = new Button(text);
            button.getStyleClass().add("button");
            button.setOnAction(e -> handleButtonClick(text));
            gridPane.add(button, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        return gridPane;
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                currentNumber = "";
                firstOperand = 0;
                operator = "";
                display.setText("0");
                break;
            case "=":
                double secondOperand = Double.parseDouble(currentNumber);
                double result = calculateResult(firstOperand, secondOperand, operator);
                display.setText(String.valueOf(result));
                currentNumber = "";
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                firstOperand = Double.parseDouble(currentNumber);
                operator = text;
                currentNumber = "";
                break;
            default:
                currentNumber += text;
                display.setText(currentNumber);
                break;
        }
    }

    private double calculateResult(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}