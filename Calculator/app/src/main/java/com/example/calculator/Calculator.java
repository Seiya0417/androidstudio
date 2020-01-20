package com.example.calculator;

import android.content.Context;
import android.widget.Toast;


public class Calculator {
    //計算結果を記録や各計算を実行する．
    private String results;
    private String number1;
    private String number2;
    private StringBuilder numBuilder;
    private Context context;
    private String calcType;
    private StringBuilder inputNum;

    //少数有効かの真偽値
    private boolean decimalPoint;

    //number1が少数であるかの情報
    private boolean decimalNum1;

    public Calculator(Context context){
        this.results = "0";
        this.context = context;
        this.calcType = "";
        this.number1 = "";
        this.number2 = "";
        this.numBuilder = new StringBuilder();

        this.inputNum = new StringBuilder();
        this.decimalPoint = false;
        this.decimalNum1 = false;
    }

    //小数点を有効化するメソッド  クリアボタンでfalseにする。
    public void onDecimalPoint(){
        this.decimalPoint = true;
    }

    //すでに小数点inputNumに格納されているか
    public boolean checkHasDecimal(){
        boolean isDP = false;
        if(this.inputNum.toString().contains(".")){
            isDP = true;
        }
        return isDP;
    }

    public boolean checkDecimalNum1(){
        return this.decimalNum1;
    }

    //演算子設定
    public void setCalcType(String type){// +, -. ×, ÷, %
        this.calcType = type;
        System.out.println("calcType: "+calcType);
    }

    //演算すべきものがないかチェック
    public boolean isCalcType(){
        return this.calcType.isEmpty();
    }

    //異なる演算子実行時に前の演算の実行のために使う
    public boolean equalsCalcType(String calcType){
        boolean isCheck = false;
        if(this.calcType.equals(calcType)){
            isCheck = true;
        }
        return isCheck;
    }

    public void clearDecimalPoint(){//小数点無効化
        this.decimalPoint = false;
    }

    //AC時に結果を初期化
    public void clearResults(){
        this.calcType = "";
        this.results = "0";

        //小数点無効化
        this.decimalPoint = false;
        this.decimalNum1 = false;
    }
    //getter 結果取得
    public String getResults(){
        return this.results;
    }

    //AC時numberを消すメソッド
    public void clearNum1(){
        this.number1 = "";
    }

    public void clearNum2(){
        this.number2 = "";
    }

    //clear時
    public void clearBuilder(){
        this.numBuilder.delete(0, numBuilder.length());
        this.inputNum.delete(0,inputNum.length());
    }

    public void clearSettingNumBuilder(){
        if(!number1.isEmpty() && !calcType.isEmpty()){
            numBuilder.append(number1+" "+calcType+" ");
        }
    }

    //builderの値をイコール時確認するため
    public String getBuilder(){
        return this.numBuilder.toString();
    }


    //inputNumの中身空かの真偽値を返す
    public boolean isInputNum(){
        boolean isTrue = false;
        if(this.inputNum.length()==0){
            isTrue = true;
        }
        return isTrue;
    }

    //数ボタン押したときnumber1がnullでないならnumber2に格納するためのチェック
    public boolean checkNullNumber1(){
        //return number1.equals("");
        return number1.isEmpty();
    }

    public boolean checkNullNumber2(){
        return number2.isEmpty();
    }

    //符号の変換メソッドsetSign
    public void setSign(CalcView view){
        String sign = "-";
        if(!checkNullNumber1()&&checkNullNumber2()&& isCalcType()){
            Toast.makeText(this.context, "演算子を指定してから数値を入力してください。", Toast.LENGTH_SHORT).show();
        }else {
            //空でない
            if (!isInputNum()) {
                String tmp = inputNum.toString(); //inputNum内の数を一時取り出す

                inputNum.delete(0, inputNum.length());
                numBuilder.delete(0,numBuilder.length());
                if(tmp.contains("-")){ //-を含んでいるならそれを取り除く
                    tmp = tmp.replace("-", "");
                    inputNum.append(tmp); //数値

                    if (!number1.isEmpty() && !calcType.isEmpty()) {
                        this.numBuilder.append(number1 + " " + calcType + " " + inputNum.toString());
                    } else {
                        this.numBuilder.append(inputNum.toString());
                    }
                } else{
                    inputNum.append(sign); //-
                    inputNum.append(tmp); //数値

                    if (!number1.isEmpty() && !calcType.isEmpty()) {
                        this.numBuilder.append(number1 + " " + calcType + " " + "(" + inputNum.toString() + ")");
                    } else {
                        this.numBuilder.append(inputNum.toString());
                    }
                }
                /*
                //「-」から「＋」に置き換えるとき「ー」を消す操作
                if (tmp.contains("-")) {
                    sign = ""; //
                    tmp = tmp.replace("-", "");
                }
                inputNum.delete(0, inputNum.length());
                inputNum.append(sign); //- or ""(正)
                inputNum.append(tmp); //数値

                //表示用のnumBuilderの操作
                numBuilder.delete(0, numBuilder.length());
                if (!number1.isEmpty() && !calcType.isEmpty()) {
                    this.numBuilder.append(number1 + " " + calcType + " " + "(" + inputNum.toString() + ")");
                } else {
                    this.numBuilder.append(inputNum.toString());
                }*/

            }
            else {//空なら
                inputNum.append(sign);
                if (!number1.isEmpty() && !calcType.isEmpty()) {
                    this.numBuilder.delete(0, numBuilder.length());
                    this.numBuilder.append(number1 + " " + calcType + " " + "(" + inputNum.toString());
                } else {
                    this.numBuilder.append(sign);
                }

                /*
                inputNum.append(sign);
                numBuilder.append(sign);
                 */
            }
            System.out.println("numBuilder: " + numBuilder);
            view.setText(numBuilder.toString());
        }
    }

    //Builderにappendするメソッド 表示はBuilderの中身としてここで請け負う
    public void getSetNumber(String number, CalcView view){
        if(!checkNullNumber1()&&checkNullNumber2()&& isCalcType()){
            Toast.makeText(this.context, "演算子を指定してから数値を入力してください。", Toast.LENGTH_SHORT).show();
        } else {
            //inputNumの中身が0でその後に小数点以外の数値を入力した場合
            if(this.inputNum.toString().equals("0") && !number.equals(".")){
                this.inputNum.delete(0, inputNum.length());
                this.numBuilder.delete(0, numBuilder.length());

                this.inputNum.append(number);

                //deleteしたとき、もしnumber1と演算子が空でないなら
                if(!number1.isEmpty() && !calcType.isEmpty()){
                    this.numBuilder.append(number1 + " "+ calcType +" " + number);
                } else {
                    this.numBuilder.append(number);
                }
            } //「-」入力時のチェック
            else if(this.inputNum.toString().equals("-0") && !number.equals(".")){ //-0のinputNumのとき、「.」以外入力したとき
                this.inputNum.delete(0, inputNum.length());
                this.numBuilder.delete(0, numBuilder.length());

                this.inputNum.append("-");
                this.inputNum.append(number);

                //deleteしたとき、もしnumber1と演算子が空でないなら
                if(!number1.isEmpty() && !calcType.isEmpty()){
                    this.numBuilder.append(number1 + " "+ calcType +" " + "(-"+number+")");
                } else {
                    this.numBuilder.append("-");
                    this.numBuilder.append(number);
                }
            }else if(isInputNum() && number.equals(".")){ //数値入力ないときに「.」を入力したとき ( number==「.」)
                this.inputNum.append("0"); //0を付け足して
                this.inputNum.append(number);
                this.numBuilder.append("0");
                this.numBuilder.append(number);
            }//「-」入力後「.」の入力は「-0.」 となるように
            else if(inputNum.toString().equals("-") && number.equals(".")){ //入力「-」でnumberが「.」のとき
                this.inputNum.append("0"); //0を付け足して
                this.inputNum.append(number); //少数点

                this.numBuilder.delete(0, numBuilder.length());
                if(!number1.isEmpty() && !calcType.isEmpty()){
                    this.numBuilder.append(number1 + " "+ calcType +" " + "("+this.inputNum.toString());
                }else {
                    this.numBuilder.append("-0");
                    this.numBuilder.append(number);
                }
            }
            else if(numBuilder.toString().contains("(")){ //numBuilderが「(」を含んでいるとき　（「0.」→「+/-」を押したとき）
                this.inputNum.append(number); //数をinputNumに格納

                numBuilder.delete(0, numBuilder.length());
                numBuilder.append(number1 + " "+calcType+" ("+ inputNum.toString() +")");
            }
            else {
                this.inputNum.append(number);
                System.out.println("getSetNumber: " + number);
                numBuilder.append(number);
            }
            System.out.println("numBuilder: " + numBuilder);
            view.setText(numBuilder.toString());
        }
    }

    //number1の値をイコール時確認するため
    public String getNumber1(){
        return this.number1;
    }


    public void setNumber1(CalcView view){
        String num = inputNum.toString();
        if(num.equals("-")){//-だけでは演算できない
            Toast.makeText(this.context, "「-」だけの数は演算できません. 0とします。", Toast.LENGTH_SHORT).show();
            num = "0";
        }
        if (num.endsWith(".")) {
            //例 「12.」、「0.」など小数点で終わっている場合、0を追加する　→「12.0」, 「0.0」
            num = num + "0";
        }
        this.number1 = num;
        inputNum.delete(0, inputNum.length());
        numBuilder.delete(0, numBuilder.length()); //初期化

        //number1格納時、格納する数が少数ならdecimalNum1をtrueにする.
        if(decimalPoint){
            decimalNum1 = true;
        }

        //演算子も含めた表示
        numBuilder.append(this.number1 + " " + this.calcType + " ");
        view.setText(numBuilder.toString());
        System.out.println("number1: " + number1);
    }

    public void setNumber2(CalcView view){
        String num = inputNum.toString();

        if(num.equals("-")){//-だけでは演算できない
            Toast.makeText(this.context, "「-」だけの数は演算できません. 0として演算実行。", Toast.LENGTH_SHORT).show();
            num = "0";
        }

        if (num.endsWith(".")) {
            //例 「12.」、「0.」など小数点で終わっている場合、0を追加する　→「12.0」, 「0.0」
            num = num + "0";
        }
        this.number2 = num;
        inputNum.delete(0, inputNum.length());

        numBuilder.delete(0, numBuilder.length()); //初期化

        if (num.contains("-")) {//負の数なら
            numBuilder.append(this.number1 + " " + this.calcType + " " + "(" + this.number2 + ")");
        } else {
            numBuilder.append(this.number1 + " " + this.calcType + " " + this.number2);
        }
        view.setText(numBuilder.toString());
        System.out.println("number2: " + number2);
    }

    //演算子を追加してbuilderに表示する results表示から演算子入力に対応 number2の入力されていないときのview表示に対応
    public void setBuilder(CalcView view){
        numBuilder.delete(0,numBuilder.length());
        numBuilder.append(number1+" "+calcType+" ");
        view.setText(numBuilder.toString());
    }

    //「＝」ボタン　あるいは　演算子ボタン実行時
    public void answerCalc(){
        //calcTypeの一致する演算実行
        if(!calcType.isEmpty()){
            if(calcType.equals("+")){//全角注意
                calcAdd();
                calcType = "";
            } else if(calcType.equals("-")){
                calcSubtract();
                calcType = "";
            } else if(calcType.equals("×")) {
                calcMultiply();
                calcType = "";
            } else if(calcType.equals("÷")){
                calcDivision();
                calcType = "";
            } else if(calcType.equals("%")){
                calcSurplus();
                calcType = "";
            } else{
                throw new IllegalArgumentException("ERROR: calcType is not match.");
            }
        }
    }

    //文字列数値変換メソッド
    private Double parseDouble(String num){
        if(num.isEmpty())
            throw new IllegalArgumentException("number null : parseInt");
        return Double.parseDouble(num);
    }

    //calc演算メソッド内で答えansの値に対して適切な型(Long, Int, Double)に設定しその文字列を返すメソッド
    private String getFormatNumber(Double number){
        if(!this.decimalPoint){//小数点有効状態でないとき
            if(number>2147483647||number<-2147483647){//Longでないと表せないとき Longは容量食うので可能ならば使わない
                Long l = number.longValue();
                return l.toString();
            }else{
                Integer i = number.intValue();
                return i.toString();
            }
        }else{//小数点有効なら
            return number.toString();
        }
    }

    //足し算
    private void calcAdd(){
        Double ans;
        if(!this.number2.equals("")) {
            ans = parseDouble(number1) + parseDouble(number2);
            System.out.printf("number1 + number2 = results: %s + %s = %f\n",number1, number2, ans);

            //演算結果を格納 intなどの型の問題はgetFormatNumberが担う
            this.results = getFormatNumber(ans);
            //Toast.makeText(this.context, this.number1+" + "+this.number2+" = "+this.results,Toast.LENGTH_SHORT).show();

            number1 = this.results;
            if(decimalPoint){//少数なら
                decimalNum1 = true;
            }
            number2 = "";

            //追加 「number1 + ""」の状態へ
            numBuilder.delete(0, numBuilder.length());
            numBuilder.append(number1);
            System.out.println("numBuilder: "+numBuilder);
        }
    }

    //引き算
    private void calcSubtract(){
        Double ans;
        if(!this.number2.equals("")) {
            ans = parseDouble(number1) - parseDouble(number2);
            System.out.printf("number1 - number2 = results: %s - %s = %f\n",number1, number2, ans);

            this.results = getFormatNumber(ans);
            //Toast.makeText(this.context, this.number1+" - "+this.number2+" = "+this.results,Toast.LENGTH_SHORT).show();

            number1 = this.results;
            if(decimalPoint){//少数なら
                decimalNum1 = true;
            }
            number2 = "";

            numBuilder.delete(0, numBuilder.length());
            numBuilder.append(number1);
            System.out.println("numBuilder: "+numBuilder);
        }
    }

    //掛け算
    private void calcMultiply(){
        Double ans;
        if(!this.number2.equals("")) {
            ans = parseDouble(number1) * parseDouble(number2);
            System.out.printf("number1 * number2 = results: %s × %s = %f\n",number1, number2, ans);

            this.results = getFormatNumber(ans);
            //Toast.makeText(this.context, this.number1+" × "+this.number2+" = "+this.results,Toast.LENGTH_SHORT).show();

            number1 = this.results;
            if(decimalPoint){//少数なら
                decimalNum1 = true;
            }
            number2 = "";

            numBuilder.delete(0, numBuilder.length());
            numBuilder.append(number1);
            System.out.println("numBuilder: "+numBuilder);
        }
    }

    //除算
    private void calcDivision(){
        Double ans;
        if(!this.number2.equals("")) {
            if(this.number2.equals("0")||this.number2.matches("0.0*")){
                //0の除算
                Toast.makeText(this.context, "エラー",Toast.LENGTH_SHORT).show();
                number2 = "";

                numBuilder.delete(0, numBuilder.length());
                numBuilder.append(number1);
                System.out.println("numBuilder: "+numBuilder);
            }else {
                ans = parseDouble(number1) / parseDouble(number2);
                System.out.printf("number1 / number2 = results: %s / %s = %f\n", number1, number2, ans);

                //割ったときに小数点以下が０でないなら小数点有効化
                if(!ans.toString().endsWith(".0")){
                    this.decimalPoint = true;
                }
                this.results = getFormatNumber(ans);
                //Toast.makeText(this.context, this.number1 + " ÷ " + this.number2 + " = " + this.results, Toast.LENGTH_SHORT).show();

                number1 = this.results;
                if(decimalPoint){//少数なら
                    decimalNum1 = true;
                }
                number2 = "";

                numBuilder.delete(0, numBuilder.length());
                numBuilder.append(number1);
                System.out.println("numBuilder: " + numBuilder);
            }
        }
    }

    //剰余算
    private void calcSurplus() {
        Double ans;
        if(!this.number2.equals("")) {
            if(this.number2.equals("0")){
                //0の剰余
                Toast.makeText(this.context, "エラー: 0で除算はできません。",Toast.LENGTH_SHORT).show();
                number2 = "";

                numBuilder.delete(0, numBuilder.length());
                numBuilder.append(number1);
                System.out.println("numBuilder: "+numBuilder);
            }else {
                //剰余算 各数値は正の整数でなければならない
                if(this.number1.contains(".") || this.number2.contains(".")||this.number1.contains("-")|| this.number2.contains("-")){
                    Toast.makeText(this.context, "エラー: 剰余算は正の整数で行ってください。",Toast.LENGTH_SHORT).show();
                    number2 = "";

                    numBuilder.delete(0, numBuilder.length());
                    numBuilder.append(number1);
                    System.out.println("numBuilder: "+numBuilder);
                }else {
                    ans = parseDouble(number1) % parseDouble(number2);
                    System.out.printf("number1 - number2 = results: %s ％ %s = %f\n", number1, number2, ans);

                    this.results = getFormatNumber(ans);
                    //Toast.makeText(this.context, this.number1 + " ％ " + this.number2 + " = " + this.results, Toast.LENGTH_SHORT).show();

                    number1 = this.results;
                    number2 = "";

                    numBuilder.delete(0, numBuilder.length());
                    numBuilder.append(number1);
                    System.out.println("numBuilder: " + numBuilder);
                }
            }
        }
    }



}
