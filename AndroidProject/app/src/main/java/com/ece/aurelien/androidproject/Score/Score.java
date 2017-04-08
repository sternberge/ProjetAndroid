package com.ece.aurelien.androidproject.Score;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by win8 on 08/04/2017.
 */

public class Score implements Parcelable {
    private String match;
    private String player;
    private int point,decisive,rebound,counter,interception,minuteplay;
    public Score(){}
    public Score(String matchStr, String playerStr, int pointInt,int decisiveInt,int reboundInt,int counterInt,int interception,int minuteplayInt) {
        this.match = matchStr;
        this.player = playerStr;
        this.point = pointInt;
        this.decisive = decisiveInt;
        this.rebound = reboundInt;
        this.counter = counterInt;
        this.minuteplay = minuteplayInt;
        this.interception= interception;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getDecisive() {return decisive;}

    public void setDecisive(int decisive) {this.decisive = decisive;}

    public int getRebound() {return rebound;}

    public void setRebound(int rebound) {this.rebound = rebound;}

    public int getCounter() {return counter;}

    public void setCounter(int counter) {this.counter = counter;}

    public int getMinuteplay() {return minuteplay;}

    public void setMinuteplay(int minuteplay) {this.minuteplay = minuteplay;}

    public int getInterception() {return interception;}

    public void setInterception(int interception) {this.interception = interception;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
