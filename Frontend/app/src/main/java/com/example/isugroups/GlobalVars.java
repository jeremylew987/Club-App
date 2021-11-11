package com.example.isugroups;

import android.app.Application;

import java.util.ArrayList;

public class GlobalVars extends Application {
    private int CurUserID;

    public int getCurUserID() {//for home page and finding clubs in
        return CurUserID;
    }

    public void setCurUserID(String ID) {
        this.CurUserID = CurUserID;
    }

    private String CurClubName;//for club details page

    public String getCurClubName() {
        return CurClubName;
    }

    public void setCurClubName(String name) {
        this.CurClubName = name;
    }

    private ArrayList<String> Clubs = new ArrayList<String>();

    public ArrayList<String> getClubs(){
        return Clubs;
    }
    public void setClubs(String[] cList) {
        Clubs.clear();
        for(String a: cList)
            Clubs.add(a);
    }

    public void addClub(String name){
        Clubs.add(name);
    }


}
