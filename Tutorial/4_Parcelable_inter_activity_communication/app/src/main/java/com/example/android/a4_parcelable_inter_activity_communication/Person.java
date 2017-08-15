package com.example.android.a4_parcelable_inter_activity_communication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by s on 15/8/17.
 */

public class Person implements Parcelable {

    String name;

    public Person() {
    }

    protected Person(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
