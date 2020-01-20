package com.example.calculator.com.buttons.calclator.numbers;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;

public class SelectMinusButton extends NumberButton {
    //マイナス入力クラス
    private String plusChange;
    public SelectMinusButton(Calculator calculator, CalcView calcView){
        super(calculator, calcView);
    }

    public void push(){
        calculator.setSign(calcView);
    }
}
