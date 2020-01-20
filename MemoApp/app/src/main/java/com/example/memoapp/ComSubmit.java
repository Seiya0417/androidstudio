package com.example.memoapp;

public class ComSubmit extends MemoCommand {

    /*
    public ComSubmit(MemoModel model, MemoView view){
        super.model = model;
        super.view = view;
    }*/
    public ComSubmit(MemoModel2 model, MemoView2 view){
        super.model = model;
        super.view = view;
    }

    @Override
    public void execute(){
        //登録ボタン押したときの日時で登録
        String nowDate = model.getCurrentDate();

        //String nowDate = view.getDate(); //書き始めた時で保存
        String nowText = view.getText();

        model.submit(nowDate, nowText);
        view.setDate(model.getCurrentDate());
        view.setText("");
    }
}
