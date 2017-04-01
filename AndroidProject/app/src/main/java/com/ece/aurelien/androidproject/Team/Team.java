package com.ece.aurelien.androidproject.Team;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class Team implements Parcelable{
    private String name;
    private String location;
    private int classement;

    public Team(){

    }

    public Team(String nameP, String locationP,int classementP){
        this.name = nameP;
        this.location =  locationP;
        this.classement = classementP;
    }

    public String getName(){
        return this.name;
    }

    public String getLocation(){
        return this.location;
    }

    public int getClassement(){
        return this.classement;
    }

    public void setName(String nameP){
        this.name =  nameP;
    }

    public void setLocation(String locationP){
        this.location = locationP;
    }

    public void setClassement(int classementP){
        this.classement = classementP;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
