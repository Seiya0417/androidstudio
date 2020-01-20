package com.example.calculator.com.buttons.calculator.operator;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;

public class CalcDivision extends OperatorButton {
    //÷ボタン実行
    public CalcDivision(Calculator calculator, CalcView view){
        super(calculator, view);
        this.calcType = "÷";
    }

    @Override
    public void push() {
        executePreOperate();
        calculator.setCalcType(this.calcType);
        super.push();
    }
}
