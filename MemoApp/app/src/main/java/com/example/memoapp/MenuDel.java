package com.example.memoapp;


import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MenuDel extends MemoCommand {

    private AppCompatActivity activity;

    public MenuDel(AppCompatActivity activity, MemoModel2 model, MemoView2 view){
        this.activity = activity;
        this.model = model;
        this.view = view;
    }


    public void delete(){
        //はいなら削除
        //modelに削除依頼
        model.delete();
        view.setDate(model.getFocusedDate());
        view.setText(model.getFocusedText());
    }

    public void execute(){
        //削除するかダイアログ表示
        //はいなら削除
        if(model.delCheck()) {
            AppCompatDialogFragment dialog = new CheckDel(activity, this);
            dialog.show(activity.getSupportFragmentManager(), "dialog_basic");
        }else{
            Toast.makeText(this.activity, "削除対象の検索結果の候補となるメモがありません。", Toast.LENGTH_SHORT).show();
        }

    }
}
