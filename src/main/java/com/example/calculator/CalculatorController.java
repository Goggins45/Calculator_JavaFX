package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label display;
    private String operator = "";
    private double number1 = 0.0;
    private double number2 = 0.0;
    private boolean start = true;

    @FXML
    private void processNumber(ActionEvent event) {
        if (start) {
            display.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        display.setText(display.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (!"=".equals(value)) {
            if (!operator.isEmpty()) {
                return;
            }
            operator = value;
            number1 = Double.parseDouble(display.getText());
            display.setText("");
        } else {
            if (operator.isEmpty()) {
                return;
            }
            number2 = Double.parseDouble(display.getText());
            double result = calculate(number1, number2, operator);
            display.setText(String.valueOf(result));
            operator = "";
            start = true;
        }
    }

    @FXML
    private void processFunction(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        double number = Double.parseDouble(display.getText());
        switch (value) {
            case "x!":
                display.setText(String.valueOf(factorial(number)));
                break;
            case "1/x":
                display.setText(String.valueOf(1 / number));
                break;
            case "±":
                display.setText(String.valueOf(-number));
                break;
            case "C":
                display.setText("");
                break;
            case "CE":
                if (display.getText().length() > 0) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
                break;
            case "√n":
                operator = "√n";
                number1 = Double.parseDouble(display.getText());
                display.setText("");
                break;
            case "logn":
                operator = "logn";
                number1 = Double.parseDouble(display.getText());
                display.setText("");
                break;
            case "sin":
                display.setText(String.valueOf(Math.sin(number)));
                break;
            case "cos":
                display.setText(String.valueOf(Math.cos(number)));
                break;
            case "tan":
                display.setText(String.valueOf(Math.tan(number)));
                break;
            case "asin":
                display.setText(String.valueOf(Math.asin(number)));
                break;
            case "acos":
                display.setText(String.valueOf(Math.acos(number)));
                break;
            case "atan":
                display.setText(String.valueOf(Math.atan(number)));
                break;
        }
    }

    private double calculate(double number1, double number2, String operator) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                    return 0;
                }
                return number1 / number2;
            case "mod":
                return number1 % number2;
            case "^":
                return Math.pow(number1, number2);
            case "sin":
                return Math.sin(number1);
            case "cos":
                return Math.cos(number1);
            case "tan":
                return Math.tan(number1);
            case "asin":
                return Math.asin(number1);
            case "acos":
                return Math.acos(number1);
            case "atan":
                return Math.atan(number1);
            case "√n":
                return Math.pow(number1, 1/number2);
            case "logn":
                return Math.log(number1) / Math.log(number2);
            default:
                return 0;
        }
    }

    private double factorial(double number) {
        if (number == 0) {
            return 1;
        }
        double result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}