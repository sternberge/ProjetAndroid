package com.ece.aurelien.androidproject.Team;

/**
 * Created by Aurélien on 01/04/2017.
 */

public class Team {
    private String name;
    private String location;

    public Team(String nameP, String locationP){
        this.name = nameP;
        this.location =  locationP;
    }

    public String getName(){
        return this.name;
    }

    public String getLocation(){
        return this.location;
    }

    public void setName(String nameP){
        this.name =  nameP;
    }

    public void setLocation(String locationP){
        this.location = locationP;
    }
}
