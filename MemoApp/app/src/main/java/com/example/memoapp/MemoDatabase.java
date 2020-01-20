package com.example.memoapp;

import android.content.Context;
import java.io.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

public class MemoDatabase {
    private Context context;
    private List<String> dates;
    private List<String> texts;
    private String filename;

    public MemoDatabase(Context context){
        this.context = context;
        this.dates = new ArrayList<>();
        this.texts = new ArrayList<>();
        //contextよりテキストファイルを指定する
        this.filename = context.getFilesDir().getPath()+ "/" + "memo.txt";


        //テキストファイルよりテキストと日時を取得
        //loadData();
        System.out.println("filename is "+this.filename);

    }

    public void start() {
        loadData();
    }

    private void loadData() {
        String rLine;
        int state = 0;
        String data = "";

        FileInputStream file;
        InputStreamReader isr;
        BufferedReader br;
        try {
            file = new FileInputStream(new File(filename));
            isr = new InputStreamReader(file, "UTF-8");
            br = new BufferedReader(isr);


            while(null != (rLine = br.readLine())) { //readLineでテキストファイルの１行を読み込み
                if (rLine.equals("<EOT>")) {
                    texts.add(data);
                    state = 0;
                } else if(state == 0) {
                    dates.add(parseDate(rLine)); //日付部分格納
                    state = 1;
                    data = ""; //前のループ時のdataの中身を消去
                } else if(state == 1) {
                    //読み込む前にテキストデータの形式を戻す
                    //Log.d("textdata rLine", rLine);
                    data += parseText(rLine)+"\n"; //テキストデータをdataに格納 改行
                } else{
                    Log.d("BAD Format", "bad rLine: "+rLine);
                }
            }

            br.close();
            isr.close();
            file.close(); //finally?

        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    //読み込みした日時の書式を整える処理を書く

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



    //読み込み時のテキストデータ形式戻す処理
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

    //書き込み処理
    private void appendDatesTexts(String date, String text) {
    //private void appendDatesTexts() {
        FileOutputStream fiout = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            System.out.println("append 実行");
            fiout = new FileOutputStream(new File(filename),true);
            osw = new OutputStreamWriter(fiout, "UTF-8"); //バイナリファイルで書き込み
            bw = new BufferedWriter(osw);  //バッファで一時堆積させてまとめて書き込み


            bw.write(writeFormatDate(date));//書き込み処理
            //改行
            bw.newLine();
            //テキストデータ
            bw.write(writeFormatText(text));
            bw.newLine();
            //終端記号
            bw.write("<EOT>");
            bw.newLine();
            bw.flush(); //書き込み close()されるとき必ずflush()されるのでは？
            osw.flush();
            fiout.flush();

            bw.close();
            osw.close();
            fiout.close();

        } catch (Exception e) {
            System.out.println("MemoDatabase-appendDatesTexts(): ERROR");
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


    //日ずけとテキストをデータベースに追加
    public void submit(String date, String text) {
        //System.out.println("ファイル名："+this.filename);

        if(!date.isEmpty() && (date.matches("2[0-1][0-9].{1}/[0-1][0-9]/[0-3][0-9] [0-2][0-9]:[0-5][0-9]:[0-5][0-9]"))) {
            if(!text.isEmpty()) {
                dates.add(date);
                texts.add(text);

                //確認
                System.out.println("dates: "+ dates);
                System.out.println("texts: "+ texts);

                //テキストファイルに記録
                appendDatesTexts(date, text);  //addしてる時点で引数のdate, textはリストに格納されているはず appendする必要ある？
                //appendDatesTexts(); //上書きとしてテキストファイル書き込み処理を行う

            } else{
                System.out.println("テキスト入力してないので登録できません");
            }
        }

    }

    public void search(String date, String text, List<String> dates, List<String> texts){
        //if(null == date || date.length()==0){
        if(date.isEmpty()){
            //if(text.isEmpty() || text.length()==0) {
            if(text.isEmpty()) {
                System.out.println("なんか入力しろや");
                return;
            }else {  //テキストのみで探す場合
                System.out.println("テキストのみで検索");
                searchByText(text, dates, texts);
            }

        } else {  //日時のみで探す場合
            //if(null==text || text.length()==0) {
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
