package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 10/9/2017.
 */

public class MenuOtherRow {

    String CaseFuntion;
    int MenuImage;
    String MenuName;

    public MenuOtherRow(int menuImage, String menuName, String functionName) {
        MenuImage = menuImage;
        MenuName = menuName;
        CaseFuntion = functionName;
    }

    public String getCaseFuntion() {
        return CaseFuntion;
    }

    public void setCaseFuntion(String caseFuntion) {
        CaseFuntion = caseFuntion;
    }

    public int getMenuImage() {
        return MenuImage;
    }

    public void setMenuImage(int menuImage) {
        MenuImage = menuImage;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }
}
