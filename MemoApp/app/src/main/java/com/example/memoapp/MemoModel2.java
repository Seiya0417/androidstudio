package com.example.memoapp;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MemoModel2 {
    private Context context;
    //private MemoDatabaseSQL database;
    private MemoDatabaseSQL2 database;
    private List<String> mDateResults; //検索結果の日付けのデータを格納
    private List<String> mTextResults; //検索結果のテキストデータ格納
    private int mIndex; //参照中のインデクス


    public MemoModel2(Context context){
        this.context = context;
        this.mIndex = 0;
    }

    public void start() {//リストのインスタンス化
        this.mDateResults = new ArrayList<>();
        this.mTextResults = new ArrayList<>();

        //this.database = new MemoDatabaseSQL(context);
        this.database = new MemoDatabaseSQL2(context);
        database.start();
    }


    //データベースに日時とデータを登録
    public void submit(String date, String text){
        //前のmDateResultsなどの検索結果あるなら消してmIndex=0に戻す
        clearResult();
        database.submit(date, text);

    }

    public void search(String date, String text){
        //前のmDateResultsなどの検索結果あるなら消してmIndex=0に戻す
        clearResult();

        database.search(date, text, mDateResults, mTextResults);


        //検索結果0件ならmIndex = -1
        if(mDateResults.isEmpty()) {
            mIndex = -1;
            Toast.makeText(this.context, "検索内容の候補となるメモがありません。", Toast.LENGTH_SHORT).show();
        }
    }


    //MenuDelで検索結果表示中かをチェックするために使う
    public boolean delCheck(){
        boolean check = false;
        if(mIndex>=0 && !mDateResults.isEmpty()){
            check = true;
        }
        return check;
    }

    public void delete(){
        //データを消す　mDateReultsのmIndexが示すデータを消去
        if(mIndex>=0 && !mDateResults.isEmpty()){
            database.delete(mDateResults.get(mIndex),mTextResults.get(mIndex));

            mDateResults.remove(mDateResults.indexOf(mDateResults.get(mIndex)));
            mTextResults.remove(mTextResults.indexOf(mTextResults.get(mIndex)));
            if(mIndex >= mDateResults.size()){
                mIndex = mDateResults.size() - 1;
            }
        }
    }

    public void clearResult(){//検索結果を破棄
        if(!mDateResults.isEmpty()) {
            mDateResults.clear();
            mTextResults.clear();
        }
        if(mIndex != 0)  //mIndex戻す
            mIndex = 0;
    }



    public String getFocusedDate(){//mIndexが示す日時とテキストを返す
        String focDate;

        if(mIndex < 0 || mDateResults.isEmpty()){ //mIndex < 0なら上の条件満たすはず
            System.out.println("mDateResults is empty");
            focDate = getCurrentDate();
        } else {
            focDate = mDateResults.get(mIndex);
        }
        return focDate;
    }

    public String getFocusedText(){
        String focText;
        if(mIndex < 0 || mTextResults.isEmpty()){
            System.out.println("mTextResults is empty");
            focText = "";
        } else {
            focText = mTextResults.get(mIndex);
        }
        return focText;
    }

    //mIndexを変更する　めもがあればTrue返す
    public boolean movePrevious(){
        //データベースアクセスして一つindexずらす
        int s = mDateResults.size();
        if(s<=0){
            mIndex = -1; //検索結果が0件であること示す。
            return false;
        } else if(mIndex <= 0) {
            return false;
        } else {//添字0より前にはデータはない。
            mIndex--;
            return true;
        }
    }


    public boolean moveCurrent(){
        int s = mDateResults.size();
        if(s<=0){
            mIndex = -1; //検索結果が0件であること示す。
            return false;
        } else {
            //mDateResultsの中で現在の日付と最も近い日付を探す
            int p = 0; //日時近いと予想しているdateの添字
            long nowTime = getLongDate(getCurrentDate()); //現在日時のlong値取得
            int min = difValue(nowTime, getLongDate(mDateResults.get(0))); //差分取得
            for(int i=1; i<s; i++){
                int dif = difValue(nowTime, getLongDate(mDateResults.get(i)));
                if(dif < min){
                    p = i;
                    min = dif;
                }
            }
            mIndex = p;
            return true;
        }
    }

    //差分求める 第一引数はnowDate固定すること
    private int difValue(long time1, long time2){
        int t = (int)(time1-time2);
        if(t<0){
            throw new IllegalArgumentException("時間の差分が負です");
        }
        return t;
    }



    public boolean moveNext(){//検索結果（mTextResults）の現在示すmIndexの次にテキストあるか あれば添字移動
        int s = mDateResults.size();
        if(s<=0){
            mIndex = -1; //検索結果が0件であること示す。
            return false;
        }
        if(mIndex < 0){//検索結果が0件なら正に戻す
            mIndex = 0;
        }
        else{
            mIndex++;
            if(mIndex >= s) {//サイズ超えたら次の日付はないのでfalse
                mIndex = s - 1;
                return false;
            }
        }
        return true;
    }


    //現在の日時を返す
    public String getCurrentDate(){
        return getFormatDate(Calendar.getInstance().getTime());
    }

    private String getFormatDate(Date time){//書式を整えて文字列として日時を取得
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //空白入れた
        return sdf.format(time.getTime());
    }

    //入力日時をlong値に変換 比較のために使う
    private long getLongDate(String date){
        String dt = date.replace("/", "").replace(":", "").replace(" ", "");
        return Long.parseLong(dt);
    }
}
