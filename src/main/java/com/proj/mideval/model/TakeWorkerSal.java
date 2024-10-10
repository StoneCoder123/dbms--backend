package com.proj.mideval.model;

import java.sql.Date;
import java.sql.Time;

public class TakeWorkerSal {
    private String workerID;
    private Date date;
    private Time time;
    private int bonus;

    // Constructors
    public TakeWorkerSal() {}

    public TakeWorkerSal(String workerID, Date date, Time time, int bonus) {
        this.workerID = workerID;
        this.date = date;
        this.time = time;
        this.bonus = bonus;
    }

    // Getters and Setters
    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
