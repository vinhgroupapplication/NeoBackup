package com.newsaigonsoft.neoegov.Fragment.Home;

import android.app.Fragment;
import android.widget.TabHost;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.HomeLookupRow;
import com.newsaigonsoft.neoegov.Subjects.HotLineRow;
import com.newsaigonsoft.neoegov.Subjects.TaskRemind;

/**
 * Created by Vinh on 3/6/2018.
 */

public class HomeFmBase extends Fragment {
    OfficalActivity mOfficalActivity;
    TaskRemind mTaskRemind;
    HomeFmLogic mHomeFmLogic;
    HomeLookupRow mHomeLookupRow;
    HotLineRow mHotLineRow;
    TabHost tabHost;
}
