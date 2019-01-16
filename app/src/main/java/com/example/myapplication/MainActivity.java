package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv3;
    private TextView tv6;
    private TextView tv9;
    private TextView tv12;

    int month = 0;
    int day = 0;
    int cashAmount = 0;

    boolean monthHasValue = false;
    boolean dayHasValue = false;
    boolean cashAmountHasValue = false;

    private enum CheckProcess {
        Month,
        Day,
        CashAmount
    }

    CheckProcess currentCheckProcess = CheckProcess.Month;

    boolean missInfo = false;

    int daysOfMonth = 0;
    int restOfDays = 0;

    final int payday = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv9 = (TextView) findViewById(R.id.textView9);
        tv12 = (TextView) findViewById(R.id.textView12);

        Toast.makeText(getApplicationContext(),"Enter Month.", Toast.LENGTH_SHORT).show();
    }

    public void button1Clicked(View v){
        addNumber(1);
    }

    public void button2Clicked(View v){
        addNumber(2);
    }

    public void button3Clicked(View v){
        addNumber(3);
    }

    public void button4Clicked(View v){
        addNumber(4);
    }

    public void button5Clicked(View v){
        addNumber(5);
    }

    public void button6Clicked(View v){
        addNumber(6);
    }

    public void button7Clicked(View v){
        addNumber(7);
    }

    public void button8Clicked(View v){
        addNumber(8);
    }

    public void button9Clicked(View v){
        addNumber(9);
    }

    public void button11Clicked(View v){
        addNumber(0);
    }

    public void button10Clicked(View v){
        toNext();
    }
    public void button12Clicked(View v){
        clearAll();
    }

    public void addNumber(int num){

        if (currentCheckProcess == CheckProcess.Month) {

            if (!(monthHasValue)) {
                month = num;
                monthHasValue = true;
            } else {
                month = (month * 10) + num;
            }

            if (month > 12){
                missInfo = true;
            }
        }else if (currentCheckProcess == CheckProcess.Day){

            if (!(dayHasValue)){
                day = num;
                dayHasValue = true;
            }else{
                day = (day * 10) + num;
            }

            if (day > 31){
                missInfo = true;
            }

        }else if (currentCheckProcess == CheckProcess.CashAmount){

            if (!(cashAmountHasValue)){
                cashAmount = num;
                cashAmountHasValue = true;
            }else{
                cashAmount = (cashAmount * 10) + num;
            }
        }

        if (missInfo) {
            clearAll();
            missInfo = false;
        }else {

            switch (currentCheckProcess) {
                case Month:
                    tv1.setText(Integer.toString(month));
                    break;
                case Day:
                    tv3.setText(Integer.toString(day));
                    break;
                case CashAmount:
                    tv6.setText(Integer.toString(cashAmount));
                    break;
            }
        }
    }

    public void clearAll(){

        month = 0;
        day = 0;
        cashAmount = 0;
        monthHasValue = false;
        dayHasValue = false;
        cashAmountHasValue = false;
        currentCheckProcess = CheckProcess.Month;

        boolean missInfo = false;

        daysOfMonth = 0;
        restOfDays = 0;

        tv1.setText(R.string.tstr1);
        tv3.setText(R.string.tstr3);
        tv6.setText(R.string.tstr6);
        tv9.setText(R.string.tstr9);
        tv12.setText(R.string.tstr12);

        Toast.makeText(getApplicationContext(), "TRY AGAIN.", Toast.LENGTH_SHORT).show();

    }

    private void toNext() {

        if (monthHasValue && currentCheckProcess == CheckProcess.Month) {

            currentCheckProcess = CheckProcess.Day;
            Toast.makeText(getApplicationContext(), "Enter Day.", Toast.LENGTH_SHORT ).show();

        }else if (dayHasValue && currentCheckProcess == CheckProcess.Day){

            currentCheckProcess = CheckProcess.CashAmount;
            Toast.makeText(getApplicationContext(), "Enter Cash Amount.", Toast.LENGTH_SHORT).show();

        }else if (cashAmountHasValue && currentCheckProcess == CheckProcess.CashAmount){

            showResult();
        }
    }

    public void showResult(){

        daysOfMonth = getDaysOfMonth();

        if (payday <= day) //次回の給料日が来月の場合
            restOfDays = daysOfMonth - (day - 1) + (payday - 1);
        else //次回の給料日が今月の場合
            restOfDays = (payday - 1) - (day - 1);

        int cashAmountPerDay = cashAmount / restOfDays;

        tv9.setText(Integer.toString(restOfDays));
        tv12.setText(Integer.toString(cashAmountPerDay));
    }

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
