package com.ece.aurelien.androidproject.Match;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class Match implements Parcelable{
    private String teamA;
    private String teamB;
    private int resultA;
    private int resultB;
    private double latitude;
    private double longitude;
    private String dateTime;
    private int id;

    public Match(){}

    public Match (String teamAP, String teamBP, int resultAP, int resultBP, double latitudeP, double longitudeP, String dateTimeP){
        this.teamA = teamAP;
        this.teamB = teamBP;
        this.resultA = resultAP;
        this.resultB = resultBP;
        this.latitude = latitudeP;
        this.longitude = longitudeP;
        this.dateTime = dateTimeP;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getResultA() {
        return resultA;
    }

    public void setResultA(int resultA) {
        this.resultA = resultA;
    }

    public int getResultB() {
        return resultB;
    }

    public void setResultB(int resultB) {
        this.resultB = resultB;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
