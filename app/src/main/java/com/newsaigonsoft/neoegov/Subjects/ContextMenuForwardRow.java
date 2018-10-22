package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/9/2017.
 */

public class ContextMenuForwardRow {
    int ID;
    boolean mDefault;
    String Name;
    int mType;

    public ContextMenuForwardRow(int ID, boolean mDefault, String name, int mType) {
        this.ID = ID;
        this.mDefault = mDefault;
        Name = name;
        this.mType = mType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean ismDefault() {
        return mDefault;
    }

    public void setmDefault(boolean mDefault) {
        this.mDefault = mDefault;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
