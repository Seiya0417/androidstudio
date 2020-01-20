package com.example.calculator.com.buttons.calculator.operator;

import android.content.Context;
import android.widget.Toast;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;
import com.example.calculator.com.buttons.CalculatorButton;

public class EqualButton extends CalculatorButton {
    private CalcView calcView;
    private Context context;

    public EqualButton(Context context, Calculator calculator, CalcView view){
        super(calculator);
        this.context = context;
        this.calcView = view;
    }

    public void push(){
        if(!calculator.checkNullNumber1()){
            if(!calculator.isCalcType()){
                if(!calculator.isInputNum()) {
                    calculator.setNumber2(calcView);
                    calculator.answerCalc();
                    calcView.setAnswerView(calculator.getResults());
                }
            }else {
                if(!calculator.isInputNum()) {
                    Toast.makeText(this.context, "演算子を指定してください。\n" +
                            "前回の演算結果 [" + calculator.getNumber1() + "]。\n" +
                            "現在の入力値 [" + calculator.getBuilder() + "]は消去します。", Toast.LENGTH_LONG).show();
                    calculator.clearBuilder();
                    calcView.setText(calculator.getNumber1());
                }else{
                    Toast.makeText(this.context, "演算子を指定してください。\n" +
                            "前回の演算結果 ["+ calculator.getNumber1() +"]。\n", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
