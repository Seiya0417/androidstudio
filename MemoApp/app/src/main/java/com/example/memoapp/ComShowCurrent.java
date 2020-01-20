package com.example.memoapp;

public class ComShowCurrent extends MemoCommand {
    /*
    public ComShowCurrent(MemoModel model, MemoView view){
        super.model = model;
        super.view = view;
    }*/
    public ComShowCurrent(MemoModel2 model, MemoView2 view){
        super.model = model;
        super.view = view;
    }
    @Override
    public void execute(){
        if(model.moveCurrent()){
            String curDate = model.getFocusedDate();
            String curText = model.getFocusedText();
            view.setDate(curDate);
            view.setText(curText);
        }

    }
}
