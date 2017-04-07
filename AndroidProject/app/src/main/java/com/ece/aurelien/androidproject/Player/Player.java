package com.ece.aurelien.androidproject.Player;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class Player implements Parcelable{
    private String name;
    private String firstName;
    private int number;
    private String teamName;
    public Player(){}
    public Player(String nameStr, String fnameStr, int numberInt,String teamName) {
        this.name = nameStr;
        this.firstName = fnameStr;
        this.number = numberInt;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
