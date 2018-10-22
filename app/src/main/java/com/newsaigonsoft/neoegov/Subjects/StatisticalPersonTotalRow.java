package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 11/28/2017.
 */

public class StatisticalPersonTotalRow {
    String Name;
    int OnTime;
    int Delays;
    int InDue;
    int OutOfDate;

    public StatisticalPersonTotalRow(String name, int onTime, int delays, int inDue, int outOfDate) {
        Name = name;
        OnTime = onTime;
        Delays = delays;
        InDue = inDue;
        OutOfDate = outOfDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getOnTime() {
        return OnTime;
    }

    public void setOnTime(int onTime) {
        OnTime = onTime;
    }

    public int getDelays() {
        return Delays;
    }

    public void setDelays(int delays) {
        Delays = delays;
    }

    public int getInDue() {
        return InDue;
    }

    public void setInDue(int inDue) {
        InDue = inDue;
    }

    public int getOutOfDate() {
        return OutOfDate;
    }

    public void setOutOfDate(int outOfDate) {
        OutOfDate = outOfDate;
    }
}
