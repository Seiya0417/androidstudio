package com.example.memoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MenuHelp extends MemoCommand {

    private AppCompatActivity activity;

    public MenuHelp(AppCompatActivity activity){
        this.activity = activity;
    }

    public static class Help extends AppCompatDialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle("ヘルプ")
                    .setMessage("1. 登録\nテキストを入力すれば、メモを登録できます。記録される日時は登録ボタンを押した日時です。" +
                            "テキストを入力しなければ登録ボタンを押しても記録されません。\n\n" +
                            "2. クリア\n登録前のテキスト、検索結果を消すことができます。\n\n" +
                            "3. 検索\n日時またはテキストを指定することで、保存したメモを取得できます。\n\n" +
                            "4. 前へ、最新、次へ\nそれぞれ適切な検索結果を表示します。\n\n" +
                            "5. 削除\n検索結果で現在表示中のメモを消去します。")
                    .setNeutralButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .create();
        }
    }

    public void execute() {
        AppCompatDialogFragment dialog = new Help();
        dialog.show(activity.getSupportFragmentManager(), "dialog_basic");
    }
}
