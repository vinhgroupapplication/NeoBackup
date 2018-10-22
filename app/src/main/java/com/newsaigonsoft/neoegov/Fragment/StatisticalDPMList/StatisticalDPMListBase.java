package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMList;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.TabHost;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 3/8/2018.
 */

public class StatisticalDPMListBase extends Fragment {
    ArrayList<String> arrSelect;
    ArrayAdapter adapterSelect;
    StatisticalDPMListLogic mStatisticalDPMListLogic;
    OfficalActivity mOfficalActivity;
//    StatisticalDPMListHeader mStatisticalDPMListHeader;
    TabHost tabHost;
    List<String> mTabName;
}
