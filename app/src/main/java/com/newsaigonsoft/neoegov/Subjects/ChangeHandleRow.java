package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/9/2017.
 */

public class ChangeHandleRow {
    boolean Default;
    String Name;
    long ID;

    public ChangeHandleRow(boolean aDefault, String name, long ID) {
        Default = aDefault;
        Name = name;
        this.ID = ID;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
