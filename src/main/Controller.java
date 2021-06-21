package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField calculatedTextField;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button numberButton;

    @FXML
    private Button symbolButton;

    @FXML
    private Button stringButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button calculateButton;

    private String currentText;
    private String previousText;
    private Calculation calculate;


    public void Controller() throws IOException {
    }

    @FXML
    public void initialize() throws IOException {
        currentText = "";
        previousText = "";
        calculate = new Calculation();
    }


    @FXML
    public void inputNumber(ActionEvent event) {
        numberButton = (Button) event.getSource();
        String number = numberButton.getText();
        currentText += number;
        inputTextField.setText(currentText);

    }

    @FXML
    public void arithmeticCalculation(ActionEvent event) {
        symbolButton = (Button) event.getSource();
        String function = symbolButton.getText();
        previousText += currentText+function;
        calculatedTextField.setText(previousText);
        calculate.arithmeticalValue(currentText, function);
        currentText = "";
        inputTextField.setText(currentText);
    }

    @FXML
    public void trigonometricCalculation(ActionEvent event) {
        stringButton = (Button) event.getSource();
        String function = stringButton.getText();
        previousText += currentText+function+"(";
        calculatedTextField.setText(previousText);
        currentText = "";
        inputTextField.setText(currentText);
    }

    @FXML
    public void finalCalculation(ActionEvent event) {
        calculateButton = (Button) event.getSource();
        previousText = previousText+currentText;
        calculatedTextField.setText(previousText);          //Need Text Area for "\n"
        inputTextField.setText(Integer.toString(calculate.getCalculatedValue()));
        currentText = "";

    }

    @FXML
    public void clearValue(ActionEvent event) {
        clearButton = (Button) event.getSource();
        if ( "C".equals(clearButton.getText()) && currentText.length()>0) {
            currentText = currentText.substring(0, currentText.length() - 1);
            inputTextField.setText(currentText);
        }
        else {
            currentText = "";
            inputTextField.setText(currentText);
            previousText = "";
            calculatedTextField.setText(previousText);
        }
    }


}
