package com.newsaigonsoft.neoegov.Fragment.Other;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.Adapter.MenuOtherAdapter;
import com.newsaigonsoft.neoegov.Subjects.MenuOtherRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface OtherFmView {
    void setListOther(MenuOtherAdapter adapter, ArrayList<MenuOtherRow> arrMenu);
}
