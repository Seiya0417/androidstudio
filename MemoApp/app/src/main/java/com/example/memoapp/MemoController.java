package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.HashMap;
import android.view.View;

public class MemoController extends AppCompatActivity {


    //private MemoModel memoModel;
    private MemoModel2 memoModel;
    private MemoView2 memoView;
    private HashMap<Integer, MemoCommand> comList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comList = new HashMap<>();

        //memoModel = new MemoModel(this);
        memoModel = new MemoModel2(this);
        memoView = new MemoView2(this, memoModel);

        comList.put(R.id.submit_button,new ComSubmit(memoModel, memoView)); //　ComSubmit
        comList.put(R.id.clear_button,new ComClear(memoModel, memoView));
        comList.put(R.id.search_button,new ComSearch(memoModel, memoView));
        comList.put(R.id.current_button,new ComShowCurrent(memoModel, memoView));
        comList.put(R.id.previous_button,new ComShowPrevious(memoModel, memoView));
        comList.put(R.id.next_button,new ComShowNext(memoModel, memoView));

        comList.put(R.id.menu_button, new ComMenu2(this, memoModel, memoView));


    }




    @Override
    public void onStart(){
        super.onStart();
        memoView.start();
        memoModel.start();

        memoView.setDate(memoModel.getCurrentDate());
        memoView.setText("");
    }

    public void onClick(View v){
        //ここで具体的にComSubmitなど実行
        comList.get(v.getId()).execute();//id getId()で取得 このIDに等しいCommandを実行
    }


}
