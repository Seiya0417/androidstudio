package com.example.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//ヘルパーを使う
public class MemoDatabaseSQL {
    private Context context;
    private List<String> dates;
    private List<String> texts;


    private Helper helper = null;


    public MemoDatabaseSQL(Context context){
        this.context = context;
        this.dates = new ArrayList<>();
        this.texts = new ArrayList<>();

        helper = new Helper(context);
    }

    public void start() {
        loadData();
    }


    private void loadData() {
        SQLiteDatabase db = null;
        try{
            db = helper.getReadableDatabase();
            Toast.makeText(this.context, "Database接続　読み込み開始。",Toast.LENGTH_SHORT).show();

            //読み込み dates textsにデータ格納
            String[] cols = {"date", "text"};
            Cursor cs = db.query("datas", cols,null, null, null, null, null);

            boolean isCur = cs.moveToFirst(); //参照を最初に

            while(isCur){
                Log.d("load data",""+cs.getInt(cs.getColumnIndex("date")));
                String date = cs.getString(cs.getColumnIndex("date"));
                String text = cs.getString(cs.getColumnIndex("text"));
                dates.add(parseDate(date));
                texts.add(parseText(text));

                isCur = cs.moveToNext(); //つぎのデータへ ないならfalse
            }

            Toast.makeText(this.context, "Database 読み込み完了", Toast.LENGTH_SHORT).show();

        }finally {
            db.close();
        }
    }

    //読み込み時のデータ形式戻す処理
    private String parseDate(String date) {
        String fm = date;
        if(date.isEmpty())//nullありえるか？
            fm = "";
        else if (!date.matches("2[0-1][0-9][0-9][0-1][0-9][0-3][0-9] [0-2][0-9],[0-5][0-9],[0-5][0-9]")){
            throw new IllegalArgumentException("not matches regex: MemoDatabase-parseDate() : ERROR");
        }
        else {
            String year = fm.substring(0,4);
            String month = fm.substring(4,6);
            String day = fm.substring(6,8);
            String hour = fm.substring(9,11);
            String minite = fm.substring(12,14);
            String second = fm.substring(15,17);
            fm = String.format("%s/%s/%s %s:%s:%s",year,month,day,hour,minite,second);
        }
        return fm;
    }

    private String parseText(String text) {
        String fm = text;
        if (fm.isEmpty()) {
            fm = "";
        } else {
            if (fm.contains("&lt;")) {//「&lt;」を「<」, 「&gt;」を「>」に戻す
                fm = fm.replace("&lt;", "<");
            }
            if (fm.contains("&gt;")) {
                fm = fm.replace("&gt;", ">");
            }
        }
        return fm;
    }


    public void submit(String date, String text){
        dates.add(date);
        texts.add(text);

        //Sqlite 保存
        SQLiteDatabase db = null;
        try{
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("date", writeFormatDate(date));
            cv.put("text", writeFormatText(text));
            db.insert("datas", null, cv);
            Toast.makeText(this.context, "メモを登録できました。",Toast.LENGTH_SHORT).show();

        }finally {
            db.close();
        }
    }

    //テキストファイルに格納するときの日時の形式整えるメソッド
    private String writeFormatDate(String date){
        String dt = date.replace("/", "").replace(":", ",");
        return dt;
    }
    //テキストファイルに格納するときのテキストの形式整えるメソッド
    private String writeFormatText(String text){
        if(text.isEmpty())
            return "";
        String tx = text.replace("<", "&lt;").replace(">", "&gt;");
        return tx;
    }

    public void delete(String date, String text){
        //データを消す
    }

    public void search(String date, String text, List<String> dates, List<String> texts){
        if(date.isEmpty()){
            if(text.isEmpty()) {
                System.out.println("なんか入力しろや");
                return;
            }else {  //テキストのみで探す場合
                System.out.println("テキストのみで検索");
                searchByText(text, dates, texts);
            }

        } else {  //日時のみで探す場合
            if(text.isEmpty()) {
                System.out.println("日時のみで検索");
                searchByDate(date, dates, texts);
            }else {  //日時とテキストで検索
                System.out.println("日時またはテキストで検索");
                searchByDateText(date, text, dates, texts);
            }
        }



    }


    private void searchByDate(String date, List<String> dates, List<String> texts){

        int sizeArray = this.dates.size();
        for (int i=0; i<sizeArray; i++) {
            if(this.dates.get(i).contains(date)){
                dates.add(this.dates.get(i));
                texts.add(this.texts.get(i));
            }
        }

    }

    private void searchByText(String text, List<String> dates, List<String> texts){
        int sizeArray = this.texts.size();
        for(int i=0; i<sizeArray; i++){
            if(this.texts.get(i).contains(text)){
                dates.add(this.dates.get(i));
                texts.add(this.texts.get(i));
            }
        }
    }

    private void searchByDateText(String date, String text, List<String> dates, List<String> texts){
        int sizeArray = this.texts.size();
        for(int i=0; i<sizeArray; i++){
            if(this.texts.get(i).contains(date) || this.texts.get(i).contains(text)){
                dates.add(this.dates.get(i));
                texts.add(this.texts.get(i));
            }
        }
    }
}
