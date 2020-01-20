package com.example.memoapp;

public class ComClear extends MemoCommand {

    /*
    public ComClear(MemoModel model, MemoView view){
        super.model = model;
        super.view = view;
    }*/

    public ComClear(MemoModel2 model, MemoView2 view) {
        super.model = model;
        super.view = view;
    }


        @Override
    public void execute(){
        model.clearResult();
        //現在の日時に更新
        view.setDate(model.getCurrentDate()); //現在の日時
        view.setText(""); //空白テキストに更新
    }
}
