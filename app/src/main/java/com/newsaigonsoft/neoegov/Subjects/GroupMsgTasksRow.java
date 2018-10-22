package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONObject;

/**
 * Created by Vinh on 10/4/2017.
 */

public class GroupMsgTasksRow {
    String GroupName;
    JSONObject mJsonObject;

    public GroupMsgTasksRow(String groupName, JSONObject mJsonObject) {
        GroupName = groupName;
        this.mJsonObject = mJsonObject;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public JSONObject getmJsonObject() {
        return mJsonObject;
    }

    public void setmJsonObject(JSONObject mJsonObject) {
        this.mJsonObject = mJsonObject;
    }
}
