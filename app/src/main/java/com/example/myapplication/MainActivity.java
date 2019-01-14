package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int x = 0;
    int y = 0;
    int z = 0;
    boolean xHasValue = false;
    boolean yHasValue = false;
    boolean zHasValue = false;
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

            if (!(xHasValue)) {
                x = num;
                xHasValue = true;
            } else {
                x = (x * 10) + num;
            }

            if (x > 12){
                missInfo = true;
            }
        }else if (checkProcess == 2){

            if (!(yHasValue)){
                y = num;
                yHasValue = true;
            }else{
                y = (y * 10) + num;
            }

            if (y > 31){
                missInfo = true;
            }

        }else if (checkProcess == 3){

            if (!(zHasValue)){
                z = num;
                zHasValue = true;
            }else{
                z = (z * 10) + num;
            }
        }

        if (missInfo) {
            clearAll();
            missInfo = false;
        }else {

            if (checkProcess == 1) {
                TextView tv1 = (TextView) findViewById(R.id.textView1);
                tv1.setText(Integer.toString(x));
            } else if (checkProcess == 2) {
                TextView tv3 = (TextView) findViewById(R.id.textView3);
                tv3.setText(Integer.toString(y));
            } else if (checkProcess == 3) {
                TextView tv6 = (TextView) findViewById(R.id.textView6);
                tv6.setText(Integer.toString(z));

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

        if (xHasValue && checkProcess == 1) {
            checkProcess = 2;
            Toast.makeText(getApplicationContext(), "Enter Day.", Toast.LENGTH_SHORT ).show();
        }else if (yHasValue && checkProcess == 2){
            checkProcess = 3;
            Toast.makeText(getApplicationContext(), "Enter Cash Amount.", Toast.LENGTH_SHORT).show();
        }else if (zHasValue && checkProcess == 3){
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
        x = 0;
        y = 0;
        z = 0;
        xHasValue = false;
        yHasValue = false;
        zHasValue = false;
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
        if (x == 1 || x == 3 || x == 5 || x == 7 || x == 8 || x == 10 || x == 12)
            daysOfMonth = 31;
        else if (x == 2)
            daysOfMonth = 28;
        else
            daysOfMonth = 30;

        if (y >= 15)//翌月まであるパターン
            restOfDays = daysOfMonth - (y - 1) + 14;
        else//今月で締めるパターン
            restOfDays = 14 - (y - 1);

        TextView tv9 = (TextView) findViewById(R.id.textView9);
        tv9.setText(Integer.toString(restOfDays));
        TextView tv12 = (TextView) findViewById(R.id.textView12);
        tv12.setText(Integer.toString(z/restOfDays));


    }
}
