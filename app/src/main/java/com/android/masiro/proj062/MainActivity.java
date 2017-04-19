package com.android.masiro.proj062;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    final int _REQUEST_MSG_CODE = 10;
    final int _REQUEST_MSG_CODE_SECOND = 100;
    ListView list;
    Button b3;
    EditText et;
    ArrayList<Data> info = new ArrayList<Data>(); //데이터 세트
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        b3 = (Button) findViewById(R.id.button3);
        et = (EditText) findViewById(R.id.editText);
        adapter = new RestAdapter(this, info);
        list.setAdapter(adapter);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                ((RestAdapter)list.getAdapter()).getFilter().filter(search) ;
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_of_set = number_of_set + 1;
                if (number_of_set % 2 == 1) {
                    b3.setText("삭제");
                    adapter.isCheckBoxState(true);
                    adapter.parameter = 1;
                } else {
                    b3.setText("선택");
                    adapter.setCheckBox(true);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("삭제하시겠습니까?")
                            .setPositiveButton("닫기", null)
                            .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (int i = info.size()-1; i >= 0; i--) {
                                        Data data = info.get(i);
                                        if (data.IsSelected()) {
                                            info.remove(data);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }

                                }
                            })
                            .show();
                    adapter.isCheckBoxState(false);
                    adapter.parameter = 0;
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void SetListView() {

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
                adapter.notifyDataSetChanged();
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
