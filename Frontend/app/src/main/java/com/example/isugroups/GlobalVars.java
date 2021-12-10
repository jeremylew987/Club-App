package com.example.isugroups;

import android.app.Application;

import java.util.ArrayList;

public class GlobalVars extends Application {
    private static String CurUserID;

    public static String getCurUserID() {//for home page and finding clubs in
        return CurUserID;
    }

    public static void setCurUserID(String ID) {
        CurUserID = ID;
    }

    public static String userPassphrase;

    public static String getUserPassphrase() {return userPassphrase;}

    public static void setUserPassphrase(String passphrase){userPassphrase = passphrase;}

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

    private static int CurPage;

    public static int getCurPage(){return CurPage;}

    public static void setCurPage(int p){CurPage = p;}


    private static String prevPage;
    public static String getPrevPage(){return prevPage;}

    public static void setPrevPage(String p){prevPage = p;}

    private static String curEventTitle;

    public static String getCurEventTitle(){return curEventTitle;}

    public static void setCurEventTitle(String curEventTitle_){curEventTitle = curEventTitle_;}

}
