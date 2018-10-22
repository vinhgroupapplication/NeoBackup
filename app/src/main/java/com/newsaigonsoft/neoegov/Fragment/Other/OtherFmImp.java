package com.newsaigonsoft.neoegov.Fragment.Other;

import org.json.JSONObject;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.Subjects.MenuOtherRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface OtherFmImp {
    void initMenu(ArrayList<MenuOtherRow> arrMenu, JSONObject mOther);
}
