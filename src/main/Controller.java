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
    private Button operatorButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button calculateButton;

    private String currentText;
    private String previousText;
    private Calculation calculation;


    public Controller() throws IOException {
        calculation = new Calculation();
    }

    @FXML
    public void initialize() throws IOException {
        currentText = "";
        previousText = "";
//        calculatedTextField.setEditable(false);
    }


    @FXML
    public void inputNumber(ActionEvent event) {
        numberButton = (Button) event.getSource();
        String number = numberButton.getText();

        currentText += number;
        inputTextField.setText(currentText);

    }


    @FXML
    public void operation(ActionEvent event){
        operatorButton = (Button) event.getSource();
        String op = operatorButton.getText();

        try {
            Double num = 0.0 ;
            if (currentText.equals("π"))
                num = Math.PI;
            else
                num = Double.parseDouble(currentText);

            calculation.pushOperand(num);
            previousText = previousText + currentText;
        }
        catch (Exception e) {
            clearInputText();
        }

        previousText += op;
        calculatedTextField.setText(previousText);
        calculation.pushOperator(getOperator(op));

        currentText = "";
        inputTextField.setText(currentText);

    }

    private Operator getOperator(String string) {
        switch (string){
            case "e^" :return Operator.EXP;
            case "√" :return Operator.ROOT;
            case "sin" :return Operator.SIN;
            case "cos" :return Operator.COS;
            case "tan" :return Operator.TAN;
            case "log" :return Operator.LOG;
            case "^" : return Operator.POW;
            case "/" : return Operator.DIVIDE;
            case "*" : return Operator.MULTIPLY;
            case "+" : return Operator.ADD;
            case "-" : return Operator.SUBTRACT;
            case "(" : return Operator.OPNBKT;
            case ")" : return Operator.CLSBKT;
        }
        return null;
    }


    private void clearInputText() {
        inputTextField.setText("");
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

            calculation.clearAll();
        }
    }

    @FXML
    public void finalCalculation(ActionEvent event) {
        calculateButton = (Button) event.getSource();

        try {
            Double num = 0.0 ;
            if (currentText.equals("π"))
                num = Math.PI;
            else
                num = Double.parseDouble(currentText);

            calculation.pushOperand(num);
        }
        catch (Exception e){
            clearInputText();
        }


        previousText = Double.toString(calculation.calculate(null));
        calculatedTextField.setText(formatString(previousText) );

        currentText = "";
        inputTextField.setText(currentText);
    }

    //Format this method to output correct string to ui
    private String formatString(String previousText) {
        return previousText;
    }


}
