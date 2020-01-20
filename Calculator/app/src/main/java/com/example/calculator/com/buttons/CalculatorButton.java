package com.example.calculator.com.buttons;

import com.example.calculator.Calculator;

public abstract class CalculatorButton {
    protected Calculator calculator;

    public CalculatorButton(Calculator calculator){
        this.calculator = calculator;
    }

    public void push(){

    }
}
