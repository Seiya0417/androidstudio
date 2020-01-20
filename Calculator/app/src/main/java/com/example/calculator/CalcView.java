package com.example.calculator;

import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.widget.TextView;

public class CalcView {

    private Activity activity;
    private TextView view;
    private TextView answerView;
    private Calculator calculator;


    CalcView(Activity activity, Calculator calculator){
        this.activity = activity;
        this.calculator = calculator;

    }
    public void start() {
        this.view = this.activity.findViewById(R.id.answer);
        this.answerView = this.activity.findViewById(R.id.answerframe);
        view.setText("0");

        view.setGravity(Gravity.CENTER);
        view.setMovementMethod(ScrollingMovementMethod.getInstance());
        view.setTextSize(50f);

        answerView.setText("0");
        answerView.setGravity(Gravity.CENTER);

        //答え枠の行のスクロール
        answerView.setMovementMethod(ScrollingMovementMethod.getInstance());

        answerView.setTextSize(50f);
    }

    public void setText(String text){
        view.setText(text);
    }

    public void setAnswerView(String text){
        answerView.setText(text);
    }

}
