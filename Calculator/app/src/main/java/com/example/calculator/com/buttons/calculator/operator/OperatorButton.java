package com.example.calculator.com.buttons.calculator.operator;

import com.example.calculator.CalcView;
import com.example.calculator.Calculator;
import com.example.calculator.com.buttons.CalculatorButton;

public abstract class OperatorButton extends CalculatorButton {
    //resultsに結果を記録し，表示するときは参照する
    protected CalcView view;
    protected String calcType;

    public OperatorButton(Calculator calculator, CalcView view){
        super(calculator);
        this.view = view;
    }

    //演算子が異なるときに前回の演算を実行するメソッド
    protected void executePreOperate(){
        if(!calculator.checkNullNumber1() && !calculator.isCalcType() && !calculator.equalsCalcType(this.calcType)){
            calculator.setNumber2(view);
            calculator.answerCalc();
            view.setAnswerView(calculator.getResults());
        }
    }
    public void push(){
        //演算ボタン実行時にbuilderの中身をCalculatorのフィールド: number1,2に格納する
        if(calculator.checkNullNumber1()){
            if(!calculator.isInputNum())
                calculator.setNumber1(view);
        }else {//number1に数値あるとき
            if(!calculator.isCalcType()){
                if(!calculator.isInputNum()) {
                    calculator.setNumber2(view);
                    calculator.answerCalc();
                    view.setAnswerView(calculator.getResults());

                    //前回の演算終わったら今回の演算の表示依頼
                    calculator.setCalcType(this.calcType);
                    calculator.setBuilder(view);
                }else{//results表示→　演算子追加のとき
                    calculator.setBuilder(view);
                }
            }
        }
    }


}
