package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //画面オブジェクトのTextViewを格納する変数の宣言
    private TextView tv_month;
    private TextView tv_day;
    private TextView tv_cashAmount;
    private TextView tv_restOfDays;
    private TextView tv_cashAmountPerDay;

    int month = 0;
    int day = 0;
    int cashAmount = 0;

    //各項目に値があるかどうかを示すフラグ
    boolean monthHasValue = false;
    boolean dayHasValue = false;
    boolean cashAmountHasValue = false;

    //入力を求める3つのプロセスをenumで定義
    private enum CheckProcess {
        Month,
        Day,
        CashAmount,
        End
    }

    //最初は月の入力から始める
    CheckProcess currentCheckProcess = CheckProcess.Month;

    //給料日は15日で固定とする
    final int payday = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //各画面オブジェクトを変数に格納する
        tv_month = (TextView) findViewById(R.id.textView1);
        tv_day = (TextView) findViewById(R.id.textView3);
        tv_cashAmount = (TextView) findViewById(R.id.textView6);
        tv_restOfDays = (TextView) findViewById(R.id.textView9);
        tv_cashAmountPerDay = (TextView) findViewById(R.id.textView12);

        //月から入力するようにToastで促す
        Toast.makeText(getApplicationContext(),"Enter Month.", Toast.LENGTH_SHORT).show();
    }

    //数字のボタンが押されたら対応する数字を引数にaddNumberを呼び出す
    public void onButton_1(View v){
        addNumber(1);
    }

    public void onButton_2(View v){
        addNumber(2);
    }

    public void onButton_3(View v){
        addNumber(3);
    }

    public void onButton_4(View v){
        addNumber(4);
    }

    public void onButton_5(View v){
        addNumber(5);
    }

    public void onButton_6(View v){
        addNumber(6);
    }

    public void onButton_7(View v){
        addNumber(7);
    }

    public void onButton_8(View v){
        addNumber(8);
    }

    public void onButton_9(View v){
        addNumber(9);
    }

    public void onButton_0(View v){
        addNumber(0);
    }

    public void onButtonNext_Enter(View v){
        toNext();
    }
    public void onButtonClearAll(View v){
        clearAll();
    }

    private void addNumber(int num){

        //現在のプロセスによってどこに入力するのかを分岐させる
        switch (currentCheckProcess) {
            case Month:
                addNumberToMonth(num);
                break;
            case Day:
                addNumberToDay(num);
                break;
            case CashAmount:
                addNumberToCashAmount(num);
                break;
        }
    }

    private void addNumberToMonth(int num) {

        if (!(monthHasValue)) {

            //0月はないので、いきなり0の入力はできない
            if (num == 0) {

                return;
            }

            month = num;
            monthHasValue = true;
        } else {
            month = (month * 10) + num;
        }

        //12を上回る数字は月として不正なデータ
        if (month > 12){
            clearCurrentProcess();
            return;
        }

        tv_month.setText(Integer.toString(month));
    }

    private void addNumberToDay(int num) {

        if (!(dayHasValue)) {

            //0日はないので、いきなり0の入力はできない
            if (num == 0) {

                return;
            }

            day = num;
            dayHasValue = true;
        } else {
            day = (day * 10) + num;
        }

        //月の日数に対して日にちが不正なデータの場合
        if (isValidDay(day) == false){
            clearCurrentProcess();
            return;
        }

        tv_day.setText(Integer.toString(day));
    }

    private void addNumberToCashAmount(int num) {

        if (!(cashAmountHasValue)){
            cashAmount = num;
            cashAmountHasValue = true;
        }else{
            cashAmount = (cashAmount * 10) + num;
        }

        tv_cashAmount.setText(Integer.toString(cashAmount));
    }

    //月の日数に対して日にちの入力が正しいかどうかを判断する関数
    private boolean isValidDay(int day) {

        int daysOfMonth = getDaysOfMonth();

        if (day > daysOfMonth) {
            return false;
        }

        return true;

    }

    //現在の入力プロセスをもう一度やり直すための関数
    private void clearCurrentProcess() {

        switch (currentCheckProcess) {
            case Month:
                month = 0;
                monthHasValue = false;
                tv_month.setText(R.string.tstr1);
                break;
            case Day:
                day = 0;
                dayHasValue = false;
                tv_day.setText(R.string.tstr3);
                break;
            case CashAmount:
                cashAmount = 0;
                cashAmountHasValue = false;
                tv_cashAmount.setText(R.string.tstr6);
                break;
        }

        Toast.makeText(getApplicationContext(), "TRY AGAIN.", Toast.LENGTH_SHORT).show();
    }

    private void clearAll(){

        month = 0;
        day = 0;
        cashAmount = 0;

        monthHasValue = false;
        dayHasValue = false;
        cashAmountHasValue = false;

        currentCheckProcess = CheckProcess.Month;

        tv_month.setText(R.string.tstr1);               //月をクリア
        tv_day.setText(R.string.tstr3);                 //日をクリア
        tv_cashAmount.setText(R.string.tstr6);         //残金をクリア
        tv_restOfDays.setText(R.string.tstr9);         //残り日数をクリア
        tv_cashAmountPerDay.setText(R.string.tstr12); //1日あたりをクリア

        //再入力を促す
        Toast.makeText(getApplicationContext(), "TRY AGAIN.", Toast.LENGTH_SHORT).show();
    }

    private void toNext() {

        if (monthHasValue && currentCheckProcess == CheckProcess.Month) {

            //日の入力に移る
            currentCheckProcess = CheckProcess.Day;
            Toast.makeText(getApplicationContext(), "Enter Day.", Toast.LENGTH_SHORT ).show();

        }else if (dayHasValue && currentCheckProcess == CheckProcess.Day){

            //残金の入力に移る
            currentCheckProcess = CheckProcess.CashAmount;
            Toast.makeText(getApplicationContext(), "Enter Cash Amount.", Toast.LENGTH_SHORT).show();

        }else if (cashAmountHasValue && currentCheckProcess == CheckProcess.CashAmount){

            //入力情報を元に結果を表示
            showResult();
        }
    }

    private void showResult(){

        int restOfDays; //次の給料日までの日数

        if (payday <= day) { //次回の給料日が来月の場合

            restOfDays = getDaysOfMonth() - day + payday;

        } else {              //次回の給料日が今月の場合

            restOfDays = payday - day;
        }

        int cashAmountPerDay = cashAmount / restOfDays;

        //残り日数、１日あたりの計算結果を画面に表示
        tv_restOfDays.setText(Integer.toString(restOfDays));
        tv_cashAmountPerDay.setText(Integer.toString(cashAmountPerDay));

        currentCheckProcess = CheckProcess.End;
    }

    //入力された月に対する日数を返す関数
    private int getDaysOfMonth() {

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }

        if (month == 2) {
            return 28;
        }

        return 30;
    }
}
