package com.example.calculator.com.buttons.calclator.numbers;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;
import com.example.calculator.com.buttons.CalculatorButton;

public abstract class NumberButton extends CalculatorButton {
    protected String number;
    protected CalcView calcView;

    protected NumberButton(Calculator calculator, CalcView view){
        super(calculator);
        this.calcView = view;
    }

    public String getNumber() {
        return this.number;
    }


    public void push(){
        this.calculator.getSetNumber(this.number, calcView);
    }
}
