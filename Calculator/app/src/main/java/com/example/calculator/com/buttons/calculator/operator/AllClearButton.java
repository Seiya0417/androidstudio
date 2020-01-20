package com.example.calculator.com.buttons.calculator.operator;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;
import com.example.calculator.com.buttons.CalculatorButton;

public class AllClearButton extends CalculatorButton {

    private CalcView calcView;
    public AllClearButton(Calculator calculator, CalcView view) {
        super(calculator);
        this.calcView = view;
    }

    @Override
    public void push(){
        calculator.clearNum1();
        calculator.clearNum2();
        calculator.clearBuilder();
        calculator.clearResults();
        calcView.setText("0");
        calcView.setAnswerView("0");
    }
}
