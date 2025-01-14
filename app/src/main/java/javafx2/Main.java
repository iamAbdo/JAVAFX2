package javafx2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // Label to display the current number or result
    private Label display = new Label("0");
    // String to hold the current number being input
    private String currentNumber = "";
    // Double to hold the first operand in a calculation
    private double firstOperand = 0;
    // String to hold the operator for the calculation
    private String operator = "";

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the main window
        primaryStage.setTitle("Calculator");

        // Create a VBox to hold the display and buttons
        VBox root = new VBox();
        root.getStyleClass().add("root");
        
        // Add style class to the display label
        display.getStyleClass().add("display");
        
        // Create and add the GridPane containing the buttons
        GridPane gridPane = createGridPane();
        root.getChildren().addAll(display, gridPane);

        // Create a scene with the root layout and set its size
        Scene scene = new Scene(root, 300, 400);
        // Add the stylesheet for styling
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        // Set the scene to the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create the GridPane containing the calculator buttons
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("grid-pane");

        // Array of button labels
        String[] buttonTexts = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        int row = 0;
        int col = 0;
        for (String text : buttonTexts) {
            // Create a button with the current text
            Button button = new Button(text);
            button.getStyleClass().add("button");
            // Add an action handler for the button click
            button.setOnAction(e -> handleButtonClick(text));
            // Add the button to the grid at the current row and column
            gridPane.add(button, col, row);
            col++;
            if (col == 4) {
                // Move to the next row after every 4 columns
                col = 0;
                row++;
            }
        }

        return gridPane;
    }

    // Method to handle button click events
    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                // Clear the current number, first operand, operator, and display
                currentNumber = "";
                firstOperand = 0;
                operator = "";
                display.setText("0");
                break;
            case "=":
                // Calculate the result using the first operand, current number, and operator
                double secondOperand = Double.parseDouble(currentNumber);
                double result = calculateResult(firstOperand, secondOperand, operator);
                display.setText(String.valueOf(result));
                currentNumber = "";
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                // Set the first operand and operator, clear the current number
                firstOperand = Double.parseDouble(currentNumber);
                operator = text;
                currentNumber = "";
                break;
            default:
                // Append the button text to the current number and update the display
                currentNumber += text;
                display.setText(currentNumber);
                break;
        }
    }

    // Method to perform the calculation based on the operator
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
        // Launch the JavaFX application
        launch(args);
    }
}