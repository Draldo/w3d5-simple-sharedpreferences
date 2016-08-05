package com.example.admin.simplesharedpreferences;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 8/5/2016.
 */
public class Student implements Parcelable {

    String name;
    String password;
    int age;
    double grade;


    protected Student(Parcel in) {
        name = in.readString();
        password = in.readString();
        age = in.readInt();
        grade = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeInt(age);
        dest.writeDouble(grade);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
