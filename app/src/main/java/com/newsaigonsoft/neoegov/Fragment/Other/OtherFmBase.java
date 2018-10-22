package com.newsaigonsoft.neoegov.Fragment.Other;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.Subjects.MenuOtherRow;

/**
 * Created by Vinh on 3/7/2018.
 */

public class OtherFmBase extends Fragment {
    View view;
    DetailForwardActivity mDetailForwardActivity;
    ArrayList<MenuOtherRow> arrMenu;
    String title;
    OtherFmLogic mOtherFmLogic;
}
