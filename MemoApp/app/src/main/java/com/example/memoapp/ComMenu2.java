package com.example.memoapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

public class ComMenu2 extends MemoCommand{
    private AppCompatActivity activity;

    //インスタンスを保持
    private static HashMap<String, MemoCommand> mComlist;


    public ComMenu2(AppCompatActivity activity, MemoModel2 model, MemoView2 view){
        this.activity = activity;

        mComlist = new HashMap<>();
       mComlist.put("削除", new MenuDel(activity, model, view));
        mComlist.put("ヘルプ", new MenuHelp(activity));
    }

    public static class MemoDialog2 extends AppCompatDialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final String[] items = {"削除", "ヘルプ"};
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle("メニュー")
                    .setItems(items,
                            new DialogInterface.OnClickListener() {  //whichに一致するクラスを実行
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //一致するメニュー実行
                                    if(mComlist.containsKey(items[which]))
                                        mComlist.get(items[which]).execute();
                                }

                            }
                    ).create();
        }
    }

    @Override
    public void execute(){
        AppCompatDialogFragment dialog = new MemoDialog2();
        dialog.show(activity.getSupportFragmentManager(), "dialog_button");
    }
}
