package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 12/4/2017.
 */

public class ChildMenu{
    String MenuName;
    int MenuIcon;
    int MenuPriority;
    String MenuCode;
    int MenuCount;

    public ChildMenu(String menuName, int menuIcon, int menuPriority, String menuCode, int menuCount) {
        MenuName = menuName;
        MenuIcon = menuIcon;
        MenuPriority = menuPriority;
        MenuCode = menuCode;
        MenuCount = menuCount;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
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
        return MenuCount;
    }

    public void setMenuCount(int menuCount) {
        MenuCount = menuCount;
    }
}
