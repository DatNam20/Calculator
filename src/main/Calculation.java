package main;

import java.util.logging.Logger;

public class Calculation {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private int previousValue;
    private int currentValue;
    private int calculatedValue;


    public void Calculation() {
        previousValue = 0;
        currentValue = 0;
        calculatedValue = 0;
    }


    private void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }

    private void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    private void setCalculatedValue(int calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public int getCalculatedValue() {
        return calculatedValue;
    }

    public void arithmeticalValue(String currentNumber, String function) {
        logger.info("arithmeticalValue called : "+ currentNumber+" | " + function);
        currentValue = Integer.parseInt(currentNumber);

        if (previousValue!=0) {
            switch(function)
            {
                case "+":   calculatedValue = previousValue + currentValue ;
                            previousValue = calculatedValue;
                            break;
                case "-":   calculatedValue = previousValue - currentValue ;
                            previousValue = calculatedValue;
                            break;
                case "*":   calculatedValue = previousValue * currentValue ;
                            previousValue = calculatedValue;
                            break;
                case "/":   calculatedValue = previousValue / currentValue ;
                            previousValue = calculatedValue;
                            break;
            }
        }
        else {
            calculatedValue = currentValue;
            previousValue = currentValue;
            currentValue = 0;
        }

    }

    public void trigonometricalValue(String currentNumber, String function) {
        currentValue = Integer.parseInt(currentNumber);

        if (currentValue!=0) {
            switch(function)
            {
                case "+":   calculatedValue = previousValue + currentValue ;
                    previousValue = calculatedValue;
                    break;
                case "-":   calculatedValue = previousValue - currentValue ;
                    previousValue = calculatedValue;
                    break;
                case "*":   calculatedValue = previousValue * currentValue ;
                    previousValue = calculatedValue;
                    break;
                case "/":   calculatedValue = previousValue / currentValue ;
                    previousValue = calculatedValue;
                    break;
            }
        }
        else {
            calculatedValue = currentValue;
            previousValue = currentValue;
            currentValue = 0;
        }

    }



}
