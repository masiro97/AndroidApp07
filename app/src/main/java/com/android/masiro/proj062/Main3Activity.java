package com.android.masiro.proj062;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t6,t8,t10;
    ImageView img;
    ImageButton ib1,ib2;
    Data data;
    final int CHICKEN = 1;
    final int PIZZA = 2;
    final int HAMBURGER = 3;
    final int COFFEE = 4;
    final int DESERT =5;

    public void OnButton(View v){
        if(v.getId() == R.id.imageButton){

            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/" +data.getDial()));
            startActivity(intent);


        }
        else if(v.getId() == R.id.imageButton2){

            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://" + data.getHome()));
            startActivity(intent);
        }
        else{
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t1 = (TextView)findViewById(R.id.textView1);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
        t4 = (TextView)findViewById(R.id.textView4);
        t6 = (TextView)findViewById(R.id.textView6);
        t8 = (TextView)findViewById(R.id.textView8);
        t10 = (TextView)findViewById(R.id.textView10);
        img = (ImageView)findViewById(R.id.imageView);
        ib1 = (ImageButton)findViewById(R.id.imageButton);
        ib2 = (ImageButton)findViewById(R.id.imageButton2);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("Rmsg");

        t1.setText(data.getName().toString());
        t2.setText(data.getMenu().get(0).toString());
        t3.setText(data.getMenu().get(1).toString());
        t4.setText(data.getMenu().get(2).toString());
        t6.setText(data.getDial().toString());
        t8.setText(data.getHome().toString());
        t10.setText(data.getDate().toString());
        SetImage(data.getCategory());

    }

    public void SetImage(int c){
        if(c == CHICKEN) img.setImageResource(R.drawable.chicken);
        else if(c == PIZZA) img.setImageResource(R.drawable.pizza);
        else if(c == HAMBURGER) img.setImageResource(R.drawable.hamburger);
        else if(c == COFFEE) img.setImageResource(R.drawable.coffee);
        else if(c == DESERT) img.setImageResource(R.drawable.desert);
        else img.setImageResource(R.mipmap.ic_launcher);
    }
}
