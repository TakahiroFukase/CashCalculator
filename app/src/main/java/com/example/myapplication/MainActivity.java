package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int day = 0;
    int month = 0;
    int cashAmount = 0;
    boolean dayHasValue = false;
    boolean monthHasValue = false;
    boolean cashAmountHasValue = false;

    int checkProcess = 1;

    boolean missInfo = false;

    int daysOfMonth = 0;
    int restOfDays = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"Enter Month.", Toast.LENGTH_SHORT).show();
    }

    public void addNumber(int num){

        if (checkProcess == 1) {

            if (!(dayHasValue)) {
                day = num;
                dayHasValue = true;
            } else {
                day = (day * 10) + num;
            }

            if (day > 12){
                missInfo = true;
            }
        }else if (checkProcess == 2){

            if (!(monthHasValue)){
                month = num;
                monthHasValue = true;
            }else{
                month = (month * 10) + num;
            }

            if (month > 31){
                missInfo = true;
            }

        }else if (checkProcess == 3){

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

            if (checkProcess == 1) {
                TextView tv1 = (TextView) findViewById(R.id.textView1);
                tv1.setText(Integer.toString(day));
            } else if (checkProcess == 2) {
                TextView tv3 = (TextView) findViewById(R.id.textView3);
                tv3.setText(Integer.toString(month));
            } else if (checkProcess == 3) {
                TextView tv6 = (TextView) findViewById(R.id.textView6);
                tv6.setText(Integer.toString(cashAmount));

            }
        }
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

    public void button10Clicked(View v){

        if (dayHasValue && checkProcess == 1) {
            checkProcess = 2;
            Toast.makeText(getApplicationContext(), "Enter Day.", Toast.LENGTH_SHORT ).show();
        }else if (monthHasValue && checkProcess == 2){
            checkProcess = 3;
            Toast.makeText(getApplicationContext(), "Enter Cash Amount.", Toast.LENGTH_SHORT).show();
        }else if (cashAmountHasValue && checkProcess == 3){
            calculate();
        }

    }
    public void button11Clicked(View v){
        addNumber(0);
    }
    public void button12Clicked(View v){
        clearAll();
    }


    public void clearAll(){
        day = 0;
        month = 0;
        cashAmount = 0;
        dayHasValue = false;
        monthHasValue = false;
        cashAmountHasValue = false;
        checkProcess = 1;

        boolean missInfo = false;

        daysOfMonth = 0;
        restOfDays = 0;



        TextView tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setText(R.string.tstr1);
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        tv3.setText(R.string.tstr3);
        TextView tv6 = (TextView) findViewById(R.id.textView6);
        tv6.setText(R.string.tstr6);
        TextView tv9 = (TextView) findViewById(R.id.textView9);
        tv9.setText(R.string.tstr9);
        TextView tv12 = (TextView) findViewById(R.id.textView12);
        tv12.setText(R.string.tstr12);



        Toast.makeText(getApplicationContext(), "TRY AGAIN.", Toast.LENGTH_SHORT).show();


    }

    public void calculate(){
        if (day == 1 || day == 3 || day == 5 || day == 7 || day == 8 || day == 10 || day == 12)
            daysOfMonth = 31;
        else if (day == 2)
            daysOfMonth = 28;
        else
            daysOfMonth = 30;

        if (month >= 15)//翌月まであるパターン
            restOfDays = daysOfMonth - (month - 1) + 14;
        else//今月で締めるパターン
            restOfDays = 14 - (month - 1);

        TextView tv9 = (TextView) findViewById(R.id.textView9);
        tv9.setText(Integer.toString(restOfDays));
        TextView tv12 = (TextView) findViewById(R.id.textView12);
        tv12.setText(Integer.toString(cashAmount /restOfDays));


    }
}
