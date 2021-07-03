package main;

import java.util.Stack;
import java.util.logging.Logger;

public class Calculation {

    Logger logger = Logger.getLogger(this.getClass().getName());

//    private int previousValue;
//    private int currentValue;
//    private int finalValue;
//    private String currentAriOperation;
//    private ArrayList<String> currentTrigOperation;

    private Stack<Operator> operatorStack;
    private Stack<Double> operandStack;


    public Calculation() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
        operatorStack.push(Operator.OPNBKT);
    }


    void pushOperand(double operand){
        operandStack.add(operand);
    }

    void pushOperator(Operator operator){
        operatorStack.add(operator);
    }

    public double calculate(String exp){
        exp = clean(exp);
        evaluate(exp);
        if(!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals(Operator.OPNBKT))
                throw new RuntimeException("Wrong Expression : Mismatched Bracket");
            else
                throw new RuntimeException("Wrong Expression : Unused Operator");
        }
        if(operandStack.size()>1)
            throw new RuntimeException("Wrong Expression : Unused Value");

        return operandStack.peek();
    }


    private String clean(String exp) {
        //use String builder
        StringBuilder expression = new StringBuilder("("+exp+")");

        return null;
    }


    /*
    This method handles the evaluation of expression that is present in the stack
     */
    private void evaluate(String exp){
        if (operatorStack.isEmpty()) return;

        while(operatorStack.peek() != Operator.OPNBKT) {
            if("BINARY".equals(getOperatorType(operatorStack.peek())) && operandStack.size()>=2){
                double secNum = operandStack.pop();
                double firstNum = operandStack.pop();
                operandStack.push(getValue(operatorStack.pop(), firstNum, secNum));
            }
            else if("UNARY".equals(getOperatorType(operatorStack.peek())) && operandStack.size()>=1){
                operandStack.push(getValue(operatorStack.pop(),operandStack.pop()));
            }
        }
        operatorStack.pop();
    }

    private String getOperatorType(Operator op){
        switch (op){
            case POW:
            case DIVIDE:
            case MULTIPLY:
            case ADD:
            case SUBTRACT: return "BINARY";
            case EXP:
            case ROOT:
            case SIN:
            case COS:
            case TAN:
            case LOG: return "UNARY";
        }
        return "NULL";
    }


    public double getValue(Operator operator, double number){
        switch (operator){
            case EXP:
                return Math.exp(number);
            case ROOT:
                return Math.sqrt(number);
            case SIN:
                return Math.sin(number);
            case COS:
                return Math.cos(number);
            case TAN:
                return Math.tan(number);
            case LOG:
                return Math.log(number);
        }
        return -1;
    }

    public double getValue(Operator operator, double firstnum, double secnum){
        switch (operator){
            case POW:
                return Math.pow( firstnum, secnum );
            case DIVIDE:
                return ( firstnum / secnum );
            case MULTIPLY:
                return ( firstnum * secnum );
            case ADD:
                return ( firstnum + secnum );
            case SUBTRACT:
                return ( firstnum - secnum );
        }
        return -1;
    }



    public void clearAll() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
        operatorStack.push(Operator.OPNBKT);
    }


    public static void main(String[] args){
        Calculation calc = new Calculation();
        String exp = "3+4+5";
        System.out.println("Exp :" + exp);
        exp = calc.clean(exp);
        System.out.println("Cleaned Exp : " + exp);
    }



}
