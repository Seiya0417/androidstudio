package com.example.memoapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CheckDel extends AppCompatDialogFragment {
    private Activity activity;
    private MenuDel del;

    public CheckDel(Activity activity, MenuDel del){
        this.activity = activity;
        this.del = del;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setMessage("表示中のメモを削除しますか？")
                .setPositiveButton("はい",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                del.delete();
                            }
                        })
                .setNegativeButton("いいえ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "削除がキャンセルされました。", Toast.LENGTH_SHORT).show();
                            }
                        })
                .create();
    }
}
