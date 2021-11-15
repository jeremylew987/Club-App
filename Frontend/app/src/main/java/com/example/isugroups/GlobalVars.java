package com.example.isugroups;

import android.app.Application;

import java.util.ArrayList;

public class GlobalVars extends Application {
    private static int CurUserID;

    public static int getCurUserID() {//for home page and finding clubs in
        return CurUserID;
    }

    public static void setCurUserID(String ID) {
        CurUserID = CurUserID;
    }

    private static String CurClubName;//for club details page

    public static String getCurClubName() {
        return CurClubName;
    }

    public static void setCurClubName(String name) {
        CurClubName = name;
    }

    private static ArrayList<String> Clubs = new ArrayList<String>();

    public static ArrayList<String> getClubs(){
        return Clubs;
    }
    public static void setClubs(String[] cList) {
        Clubs.clear();
        for(String a: cList)
            Clubs.add(a);
    }

    public static void addClub(String name){
        Clubs.add(name);
    }


}
