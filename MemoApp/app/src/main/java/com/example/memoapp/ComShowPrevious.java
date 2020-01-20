package com.example.memoapp;

public class ComShowPrevious extends MemoCommand{
    /*
    public ComShowPrevious(MemoModel model, MemoView view){
        super.model = model;
        super.view = view;
    }*/

    public ComShowPrevious(MemoModel2 model, MemoView2 view){
        super.model = model;
        super.view = view;
    }

    @Override
    public void execute(){
        if(model.movePrevious()){
            String preDate = model.getFocusedDate();
            String preText = model.getFocusedText();
            view.setDate(preDate);
            view.setText(preText);
        }

    }
}
