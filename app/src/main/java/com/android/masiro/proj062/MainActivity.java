package com.android.masiro.proj062;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final int _REQUEST_MSG_CODE = 10;
    final int _REQUEST_MSG_CODE_SECOND = 100;
    Button b3,b4;
    ListView list;
    ArrayList<Data> info = new ArrayList<Data>(); //데이터 세트
    ArrayList<Boolean> position = new ArrayList<Boolean>();
    RestAdapter adapter; //Custom Adapter
    Data dataset; //현재 데이터
    int number_of_set = 0;

    public void OnButton(View v) {

        if (v.getId() == R.id.button) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(intent, _REQUEST_MSG_CODE);
        } else if (v.getId() == R.id.button1) {
            //이름순
            adapter.setSort(adapter.NAME_ASC);
        } else if (v.getId() == R.id.button2) {
            //종류별
            adapter.setSort(adapter.KIND_ASC);
        }
        else if(v.getId() == R.id.button3){
            number_of_set++;
            if (number_of_set % 2 == 1) {
                b3.setText("삭제"); adapter.setCheckBoxState(true);
            } else {
                b3.setText("선택"); adapter.setCheckBoxState(false);
            }
        }

        else if(v.getId() == R.id.button4){
            ArrayList<Data> temp = adapter.arr;
            for (int i = 0; i < temp.size(); i++) {
                Data data = temp.get(i);
                if (data.selected == true) info.remove(data);
            }
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        adapter = new RestAdapter(this, info);
        list.setAdapter(adapter);
        info.add(new Data("d"));
        info.add(new Data("d2"));
        info.add(new Data("d3"));
        info.add(new Data("d4"));

    }

    public void SetListView() {

        adapter.notifyDataSetChanged();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("Rmsg", info.get(position));
                startActivityForResult(intent, _REQUEST_MSG_CODE_SECOND);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _REQUEST_MSG_CODE) {
            if (resultCode == RESULT_OK) {
                dataset = data.getParcelableExtra("msg");
                info.add(dataset);
                SetListView();
            }
        }

        if (requestCode == _REQUEST_MSG_CODE_SECOND) {
            if (resultCode == RESULT_OK) {
                //3번째 액티비티에서 받기
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
