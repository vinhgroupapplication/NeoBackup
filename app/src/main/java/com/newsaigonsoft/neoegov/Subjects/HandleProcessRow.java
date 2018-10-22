package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/16/2017.
 */

public class HandleProcessRow {
    boolean Default;
    long Id;
    String Name;

    public HandleProcessRow(boolean aDefault, long id, String name) {
        Default = aDefault;
        Id = id;
        Name = name;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
