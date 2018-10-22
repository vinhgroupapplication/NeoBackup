package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 5/31/2017.
 */

public class PopupItemSetting {
    String SettingName;
    int SettingShow;

    public PopupItemSetting(String settingName, int settingShow) {
        SettingName = settingName;
        SettingShow = settingShow;
    }

    public String getSettingName() {
        return SettingName;
    }

    public void setSettingName(String settingName) {
        SettingName = settingName;
    }

    public int getSettingShow() {
        return SettingShow;
    }

    public void setSettingShow(int settingShow) {
        SettingShow = settingShow;
    }
}
