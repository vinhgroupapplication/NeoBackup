package com.newsaigonsoft.neoegov.Subjects;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Vinh on 12/4/2017.
 */

public class HeaderMenu extends ExpandableGroup {
//    String MenuName;
    int MenuIcon;
    int MenuPriority;
    String MenuCode;
    int HeaderCount;
    boolean CheckColor;

    public HeaderMenu(String title, List items,  int menuIcon, int menuPriority, String menuCode, int menuCount, boolean checkColor) {
        super(title, items);
        MenuIcon = menuIcon;
        MenuPriority = menuPriority;
        MenuCode = menuCode;
        HeaderCount = menuCount;
        CheckColor = checkColor;
    }

    public int getHeaderCount() {
        return HeaderCount;
    }

    public void setHeaderCount(int headerCount) {
        HeaderCount = headerCount;
    }

    public boolean isCheckColor() {
        return CheckColor;
    }

    public void setCheckColor(boolean checkColor) {
        CheckColor = checkColor;
    }

    public int getMenuIcon() {
        return MenuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        MenuIcon = menuIcon;
    }

    public int getMenuPriority() {
        return MenuPriority;
    }

    public void setMenuPriority(int menuPriority) {
        MenuPriority = menuPriority;
    }

    public String getMenuCode() {
        return MenuCode;
    }

    public void setMenuCode(String menuCode) {
        MenuCode = menuCode;
    }

    public int getMenuCount() {
        return HeaderCount;
    }

    public void setMenuCount(int menuCount) {
        HeaderCount = menuCount;
    }
}
