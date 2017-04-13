package com.android.masiro.proj062;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText e,e2,e3,e4,e5,e6;
    int category = 0;
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    String currentDate = CurDateFormat.format(date); //나중에 바꾸기
    final int CHICKEN = 1;
    final int PIZZA = 2;
    final int HAMBURGER = 3;
    final int COFFEE = 4;
    final int DESERT =5;

    public void OnButton(View v){
        if(v.getId() == R.id.button) {
            View dlgview = getLayoutInflater().inflate(R.layout.choiceimage,null);
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            final RadioButton r1,r2,r3,r4,r5;
            r1 = (RadioButton)dlgview.findViewById(R.id.radioButton);
            r2 = (RadioButton)dlgview.findViewById(R.id.radioButton2);
            r3 = (RadioButton)dlgview.findViewById(R.id.radioButton3);
            r4 = (RadioButton)dlgview.findViewById(R.id.radioButton4);
            r5 = (RadioButton)dlgview.findViewById(R.id.radioButton5);

            dlg.setTitle("맛집 종류")
                    .setView(dlgview)
                    .setPositiveButton("닫기",null)
                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(r1.isChecked()) category = CHICKEN;
                            else if(r2.isChecked()) category = PIZZA;
                            else if(r3.isChecked()) category = HAMBURGER;
                            else if(r4.isChecked()) category = COFFEE;
                            else if(r5.isChecked()) category = DESERT;
                            else category = 0;
                        }
                    })
                    .show();
        }
        else if(v.getId() == R.id.button2){

            String name = e.getText().toString();
            String dial = e5.getText().toString();
            String homepage = e6.getText().toString();
            String date = currentDate;
            ArrayList<String> menu = new ArrayList<String>();
            menu.add(e2.getText().toString());
            menu.add(e3.getText().toString());
            menu.add(e4.getText().toString());
            Data data = new Data(name,dial,menu,homepage,date,category);

            Intent intent = new Intent();
            intent.putExtra("msg",data);
            setResult(RESULT_OK,intent);
            finish();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);
        e4 = (EditText)findViewById(R.id.editText4);
        e5 = (EditText)findViewById(R.id.editText5);
        e6 = (EditText)findViewById(R.id.editText6);

    }

}
