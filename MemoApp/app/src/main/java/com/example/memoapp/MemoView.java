package com.example.memoapp;
import android.app.Activity;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.graphics.Color;

public class MemoView {
    private Activity activity;
    private MemoModel model;
    private TextView wDate;
    private TextView wText;

    public MemoView(Activity activity, MemoModel model){
        this.activity = activity;
        this.model = model;
    }

    public void start(){
        activity.setContentView(R.layout.activity_main);

        EditText editText = activity.findViewById(R.id.date_frame);


        this.wDate = (TextView)editText;//layoutのid取得
        this.wText = (TextView)activity.findViewById(R.id.text_frame);



        //外枠レイアウト 　id設定が必要となるのでxmlのほうで良いかも...
        LinearLayout layout = activity.findViewById(R.id.outframe);
        layout.setBackgroundColor(Color.parseColor("#f0e68c"));



        //レイアウト内のテキストの表示位置を設定 日時
        //EditText editText = activity.findViewById(R.id.date_frame);
        editText.setGravity(Gravity.CENTER);

        //MemoViewからのtextboxのxmlへアクセス
        editText.setBackgroundResource(R.drawable.textbox_bkground);
        /*
        GradientDrawable bgShape =(GradientDrawable)editText.getBackground();
        bgShape.setColor(Color.parseColor("#faebd7"));
        bgShape.setStroke(4, Color.parseColor("#000000"));
        bgShape.setCornerRadius(10f);
         */
        //editText.setBackgroundColor(Color.parseColor("#faebd7"));
        //フォント設定
        editText.setTypeface(Typeface.MONOSPACE);


        //submit
        Button btn_submit = activity.findViewById(R.id.submit_button);
        btn_submit.setTextColor(Color.rgb(150,150,0));
        btn_submit.setBackgroundResource(R.drawable.button_state);

        //clear　各ボタンの色 color.xmlで設定したのを使うのでなく、カラーコードで指定すれば独立性保たれる
        Button btn_clear = activity.findViewById(R.id.clear_button);
        btn_clear.setTextColor(Color.parseColor("#4682b4"));
        btn_clear.setBackgroundResource(R.drawable.button_state);


        Button btn_search = activity.findViewById(R.id.search_button);
        btn_search.setTextColor(Color.parseColor("#228b22"));
        btn_search.setBackgroundResource(R.drawable.button_state);


        //テキストの枠
        EditText textbox = activity.findViewById(R.id.text_frame);


    }

    public String getDate(){
        return this.wDate.getText().toString();
    }

    public String getText(){
        return this.wText.getText().toString();
    }

    public void setDate(String date){
        this.wDate.setText(date);
    }

    public void setText(String text){
        this.wText.setText(text);
    }

}
