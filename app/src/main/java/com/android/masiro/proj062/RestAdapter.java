package com.android.masiro.proj062;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by haeyoung on 2017-04-13.
 */

public class RestAdapter extends BaseAdapter {

    private boolean mCheckBoxState = false;
    ArrayList<Data> arr = new ArrayList<Data>();
    //ArrayList<Boolean> pos =  new ArrayList<Boolean>();
    Context context;
    CheckBox cbox;
    public int NAME_ASC = 0;
    public int KIND_ASC = 1;
    final int CHICKEN = 1;
    final int PIZZA = 2;
    final int HAMBURGER = 3;
    final int COFFEE = 4;
    final int DESERT = 5;

    public RestAdapter(Context context, ArrayList<Data> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {return arr.size();}

    @Override
    public Object getItem(int position) {return arr.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //화면을 구성하는 메소드
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }

        TextView t1 = (TextView) convertView.findViewById(R.id.textView);
        TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        cbox = (CheckBox) convertView.findViewById(R.id.checkBox);
        final Data data = arr.get(position);
        if (mCheckBoxState) {
            cbox.setVisibility(CheckBox.VISIBLE);
            cbox.setChecked(false);
        } else {
            cbox.setVisibility(CheckBox.INVISIBLE);
            if (cbox.isChecked()) data.selected = true;
            else data.selected = false;
        }

        t1.setText(data.getName());
        t2.setText(data.getDial());
        if (data.getCategory() == CHICKEN) img.setImageResource(R.drawable.chicken);
        else if (data.getCategory() == PIZZA) img.setImageResource(R.drawable.pizza);
        else if (data.getCategory() == HAMBURGER) img.setImageResource(R.drawable.hamburger);
        else if (data.getCategory() == COFFEE) img.setImageResource(R.drawable.coffee);
        else if (data.getCategory() == DESERT) img.setImageResource(R.drawable.desert);
        else img.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }

    Comparator<Data> nameAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    Comparator<Data> kindAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.getCategory() - o2.getCategory();
        }
    };

    public void setSort(int sortType) {
        if (sortType == NAME_ASC) {
            Collections.sort(arr, nameAsc);
            this.notifyDataSetChanged();
        } else if (sortType == KIND_ASC) {
            Collections.sort(arr, kindAsc);
            this.notifyDataSetChanged();
        }
    }

    public void setCheckBoxState(boolean pState) {
        mCheckBoxState = pState;
        notifyDataSetChanged();
    }
}
