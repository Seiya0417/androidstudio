package com.example.calculator.com.buttons.calculator.operator;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;

public class CalcSurplus extends OperatorButton {
    //%ボタン実行
    public CalcSurplus(Calculator calculator, CalcView view){
        super(calculator, view);
        this.calcType = "%";
    }

    @Override
    public void push() {
        executePreOperate();
        calculator.setCalcType(this.calcType);
        super.push();
    }
}
