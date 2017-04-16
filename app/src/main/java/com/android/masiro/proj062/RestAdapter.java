package com.android.masiro.proj062;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by haeyoung on 2017-04-13.
 */


public class RestAdapter extends BaseAdapter implements Filterable{

    private boolean mCheckBoxState = false;
    ArrayList<Data> arr = new ArrayList<Data>();
    ArrayList<Data> filtered = arr;
    Filter listFilter;
    Context context;
    CheckBox cbox;
    public int NAME_ASC = 0;
    public int KIND_ASC = 1;
    final int CHICKEN = 1;
    final int PIZZA = 2;
    final int HAMBURGER = 3;
    final int COFFEE = 4;
    final int DESERT = 5;
    int parameter = 0;

    public RestAdapter(Context context, ArrayList<Data> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {return filtered.size();}

    @Override
    public Object getItem(int position) {return filtered.get(position);}

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
        cbox = (CheckBox)convertView.findViewById(R.id.checkBox);

        final Data data = filtered.get(position);
        if(parameter == 0){
            cbox.setChecked(false);
            data.selected = false;
        }
        if(mCheckBoxState){
            if(cbox.isChecked()) data.selected = true;
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

    public void setCheckBox(boolean pState) {
        mCheckBoxState = pState;
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        if(listFilter == null) listFilter =  new ListFilter();
        return listFilter;
    }

    private class ListFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint ==null || constraint.length() ==0){
                results.values = arr;
                results.count = arr.size();
            }
            else{
                ArrayList<Data> itemList = new ArrayList<Data>();

                for(Data data : arr){
                    if(data.getName().toUpperCase().contains(constraint.toString().toUpperCase()))
                        itemList.add(data);
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered = (ArrayList<Data>)results.values;
            if(results.count >0) notifyDataSetChanged();
            else notifyDataSetInvalidated();
        }
    }
}
