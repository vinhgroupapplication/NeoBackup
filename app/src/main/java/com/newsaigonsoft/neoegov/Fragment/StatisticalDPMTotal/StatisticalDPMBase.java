package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;

/**
 * Created by Vinh on 3/8/2018.
 */

public class StatisticalDPMBase extends Fragment {
    ArrayList<String> arrSelect;
    OfficalActivity mOfficalActivity;
    ArrayAdapter adapter;
    int Category = 1;
    StatisticalDPMTotalLogic mStatisticalDPMTotalLogic;
    StatisticalTotalHeader mStatisticalTotalHeader;
    TabHost tabHost;
    List<String> mTabName;
}
