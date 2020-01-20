package com.example.calculator.com.buttons.calculator.operator;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;
import com.example.calculator.com.buttons.CalculatorButton;

public class ClearButton extends CalculatorButton {

    private CalcView calcView;
    public ClearButton(Calculator calculator, CalcView view){
        super(calculator);
        this.calcView = view;
    }

    public void push(){ //clear未完成
        calculator.clearBuilder(); //各builder中身空にする
        if(!calculator.checkDecimalNum1()){//number1が小数点でないとき小数点有効状態なら小数点無効にする.
            calculator.clearDecimalPoint();
        }
        if(!calculator.checkNullNumber1() && !calculator.isCalcType()){
            calculator.clearSettingNumBuilder();
            calcView.setText(calculator.getBuilder());
            calcView.setAnswerView("0");
        } else if(calculator.checkNullNumber1()){
            calculator.clearDecimalPoint();
            calcView.setText("0");
        }
    }
}
