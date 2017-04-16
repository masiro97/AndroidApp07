package com.android.masiro.proj062;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.os.UserHandle.readFromParcel;

/**
 * Created by haeyoung on 2017-04-06.
 */
//맛집이름, 전화, 메뉴매열(3개), 홈페이지, 등록일, 카테고리번
public class Data implements Parcelable{

    private String name;
    private String dial;
    private ArrayList<String> menu;
    private String homepage;
    private String date;
    private int category;
    public boolean selected;

    public Data(String name, String dial, ArrayList<String>menu, String homepage, String date, int category){
        this.name = name;
        this.dial = dial;
        this.menu = menu;
        this.homepage = homepage;
        this.date = date;
        this.category = category;
        this.selected = false;
    }
    public Data(String name){
        this.name = name;
        this.dial = "";
        this.menu = null;
        this.homepage = "";
        this.date = "";
        this.category = 0;
        this.selected = false;
    }

    public Data(Parcel src){
        readFromParcel(src);
    }
    public Data(){
        this.name = "null";
        this.dial = "null";
        this.menu = null;
        this.homepage = "null";
        this.date = "null";
        this.category = 0;
        this.selected = false;

    }
    public void setData(String name, String dial, ArrayList<String> menu, String homepage, String date, int category){

        this.name = name;
        this.dial = dial;
        this.menu = menu;
        this.homepage = homepage;
        this.date = date;
        this.category = category;
        this.selected = false;
    }

    public String getHome(){
        return this.homepage;
    }
    public String getDial(){
        return this.dial;
    }
    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date;
    }
    public int getCategory(){
        return this.category;
    }
    public ArrayList<String> getMenu(){
        return this.menu;
    }
    public boolean IsSelected(){return this.selected;}
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dial);
        dest.writeStringList(menu);
        dest.writeString(homepage);
        dest.writeString(date);
        dest.writeInt(category);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        dial = in.readString();
        menu = in.createStringArrayList();
        homepage = in.readString();
        date = in.readString();
        category = in.readInt();
    }
    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>(){
        public Data createFromParcel(Parcel src){
            return new Data(src);
        }

        public Data[] newArray(int size){
            return new Data[size];
        }

    };

}
