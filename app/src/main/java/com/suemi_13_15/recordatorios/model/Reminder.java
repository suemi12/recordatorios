package com.suemi_13_15.recordatorios.model;

import java.util.ArrayList;
import java.util.Date;

public class Reminder {

    private int id;
    private String description;
    private Date date;
    private String intervalRepeat;
    private char priority;
    private String location;
    private String periodicityAlarm;
    private Alarm alarm;
    private ArrayList<Resources> resources;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntervalRepeat() {
        return intervalRepeat;
    }

    public void setIntervalRepeat(String intervalRepeat) {
        this.intervalRepeat = intervalRepeat;
    }

    public char getPriority() {
        return priority;
    }

    public void setPriority(char priority) {
        this.priority = priority;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPeriodicityAlarm() {
        return periodicityAlarm;
    }

    public void setPeriodicityAlarm(String periodicityAlarm) {
        this.periodicityAlarm = periodicityAlarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public ArrayList<Resources> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resources> resources) {
        this.resources = resources;
    }
}
