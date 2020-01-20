package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.com.buttons.CalculatorButton;
import com.example.calculator.com.buttons.calclator.numbers.DecimalPoint;
import com.example.calculator.com.buttons.calclator.numbers.NumEight;
import com.example.calculator.com.buttons.calclator.numbers.NumFive;
import com.example.calculator.com.buttons.calclator.numbers.NumFour;
import com.example.calculator.com.buttons.calclator.numbers.NumNine;
import com.example.calculator.com.buttons.calclator.numbers.NumSeven;
import com.example.calculator.com.buttons.calclator.numbers.NumSix;
import com.example.calculator.com.buttons.calclator.numbers.NumThree;
import com.example.calculator.com.buttons.calclator.numbers.NumZero;
import com.example.calculator.com.buttons.calclator.numbers.SelectMinusButton;
import com.example.calculator.com.buttons.calculator.operator.AllClearButton;
import com.example.calculator.com.buttons.calculator.operator.CalcAdd;
import com.example.calculator.com.buttons.calculator.operator.CalcDivision;
import com.example.calculator.com.buttons.calculator.operator.CalcMultiply;
import com.example.calculator.com.buttons.calculator.operator.CalcSubstract;
import com.example.calculator.com.buttons.calculator.operator.CalcSurplus;
import com.example.calculator.com.buttons.calculator.operator.ClearButton;
import com.example.calculator.com.buttons.calculator.operator.EqualButton;
import com.example.calculator.com.buttons.calclator.numbers.NumOne;
import com.example.calculator.com.buttons.calclator.numbers.NumTwo;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Calculator calculator;
    CalcView calcView;

    HashMap<Integer, CalculatorButton> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.calculator = new Calculator(this);
        this.calcView = new CalcView(this, this.calculator);
        this.numbers = new HashMap<>();

        numbers.put(R.id.one, new NumOne(this.calculator, this.calcView));
        numbers.put(R.id.two, new NumTwo(this.calculator, this.calcView));
        numbers.put(R.id.three, new NumThree(this.calculator, this.calcView));
        numbers.put(R.id.four, new NumFour(this.calculator, this.calcView));
        numbers.put(R.id.five, new NumFive(this.calculator, this.calcView));
        numbers.put(R.id.six, new NumSix(this.calculator, this.calcView));
        numbers.put(R.id.seven, new NumSeven(this.calculator, this.calcView));
        numbers.put(R.id.eight, new NumEight(this.calculator, this.calcView));
        numbers.put(R.id.nine, new NumNine(this.calculator, this.calcView));
        numbers.put(R.id.zero, new NumZero(this.calculator, this.calcView));

        numbers.put(R.id.plus, new CalcAdd(this.calculator, this.calcView));
        numbers.put(R.id.minus, new CalcSubstract(this.calculator, this.calcView));
        numbers.put(R.id.multiply, new CalcMultiply(this.calculator, this.calcView));
        numbers.put(R.id.division, new CalcDivision(this.calculator, this.calcView));
        numbers.put(R.id.surplus, new CalcSurplus(this.calculator, this.calcView));
        numbers.put(R.id.decimalPoint, new DecimalPoint(this.calculator, this.calcView));
        numbers.put(R.id.equals, new EqualButton(this, this.calculator, this.calcView));
        numbers.put(R.id.clear, new ClearButton(this.calculator, calcView));
        numbers.put(R.id.select_minus_button, new SelectMinusButton(this.calculator, this.calcView));
        numbers.put(R.id.allClear, new AllClearButton(this.calculator, this.calcView));

    }

    public void onStart(){
        super.onStart();
        calcView.start();
    }

    //idと対応するコマンド実行
    public void onClick(View v){
        numbers.get(v.getId()).push();
    }
}
