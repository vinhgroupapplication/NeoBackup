package com.newsaigonsoft.neoegov.Fragment.StatisticalDocComing;

import android.app.Fragment;
import android.widget.TabHost;

import com.newsaigonsoft.neoegov.Adapter.StatisticalListAdapter;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComing;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.StatisticalFmRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 3/8/2018.
 */

public class StatisticalDocComingBase extends Fragment {
    StatisticalDocComingFmLogic mStatisticalFmLogic;
    StatisticalFmRow mStatisticalRows;
    OfficalActivity mOfficalActivity;
    GsonStaComing.DataBean mDataBean;
    TabHost tabHost;
    List<String> mTabName;
    StatisticalListAdapter adapter;
    int indexYear;
    String startQuarter, endQuarter;
    ArrayList<ReceivePerson> arrListDepartmentSearch;
    String ReceiveRoomID = "0";


}
