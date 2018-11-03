package com.example.danishali.assignment01;

import java.text.SimpleDateFormat;
import java.util.Date;
// class definition
public class CustomItem {
    // constructor for the class
    CustomItem(String name, long time) {
// take a copy of the name and convert the time into a simple date format string
        this.name = name;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(time);
        date = sdf.format(d);
    }
    CustomItem(String name, String title) {
// take a copy of the name and convert the time into a simple date format string
        this.name = name;
        this.title = title;
    }
    CustomItem(String name) {
// take a copy of the name and convert the time into a simple date format string
        this.name = name;

    }
    // getter methods for both fields
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getTitle(){return title;}

    // private fields of the class
    private String name;
    private String date;
    private String title;

}