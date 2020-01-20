package com.example.memoapp;

public class ComShowNext extends MemoCommand {
    /*
    public ComShowNext(MemoModel model, MemoView view){
        super.model = model;
        super.view = view;
    }*/

    public ComShowNext(MemoModel2 model, MemoView2 view){
        super.model = model;
        super.view = view;
    }

    @Override
    public void execute(){
        if(model.moveNext()){
            String nexDate = model.getFocusedDate();
            String nexText = model.getFocusedText();
            view.setDate(nexDate);
            view.setText(nexText);
        }

    }
}
