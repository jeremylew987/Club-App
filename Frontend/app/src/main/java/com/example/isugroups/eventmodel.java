package com.example.isugroups;

public class eventmodel {
    private String club;
    private String des;
    private String title;
    private String date;
    private String time;

    public eventmodel(String clubname, String description, String title, String date, String time) {
        this.club = clubname;
        this.des = description;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getClub() {
        return club;
    }
    public String getdescription() {
        return des;
    }
    public String gettitle() {
        return title;
    }
    public String getdate() {
        return date;
    }
    public String gettime() {
        return time;
    }

}
