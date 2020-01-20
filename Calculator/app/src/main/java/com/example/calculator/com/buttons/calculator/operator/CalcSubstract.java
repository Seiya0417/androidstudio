package com.example.calculator.com.buttons.calculator.operator;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;

public class CalcSubstract extends OperatorButton {
    //ーボタン実行
    public CalcSubstract(Calculator calculator, CalcView view){
        super(calculator, view);
        this.calcType = "-";
    }

    @Override
    public void push() {
        executePreOperate();
        calculator.setCalcType(this.calcType);
        super.push();
    }
}
