package com.example.calculator.com.buttons.calclator.numbers;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;

public class DecimalPoint extends NumberButton {
    //小数点のクラス
    public DecimalPoint(Calculator calculator, CalcView view){
        super(calculator, view);
        this.number = ".";
    }


    @Override
    public void push(){
        if(!calculator.checkHasDecimal()) {//小数点１つも入力に入っていないなら
            super.push(); //「.」を格納
            //小数点有効化  無効化はclear時にする
            calculator.onDecimalPoint();
        }
    }
}
