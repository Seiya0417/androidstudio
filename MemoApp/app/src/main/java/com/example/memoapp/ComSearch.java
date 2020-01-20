package com.example.memoapp;

public class ComSearch extends MemoCommand {

    /*
    public ComSearch(MemoModel model, MemoView view){
        super.model = model;
        super.view = view;
    }*/

    public ComSearch(MemoModel2 model, MemoView2 view){
        super.model = model;
        super.view = view;
    }

    @Override
    public void execute(){
        //search実行時必ずclearResults()でmIndex=0に戻す
        model.clearResult();


        model.search(view.getDate(), view.getText());

        view.setDate(model.getFocusedDate());
        view.setText(model.getFocusedText());
    }
}
