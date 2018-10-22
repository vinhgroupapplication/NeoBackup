package com.newsaigonsoft.neoegov.Fragment.StatisticalDPM;

import android.app.Fragment;
import android.widget.TabHost;

import java.text.SimpleDateFormat;
import java.util.List;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORMATDATE;

/**
 * Created by Vinh on 3/8/2018.
 */

public class StatisticalDPMBase extends Fragment {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
    OfficalActivity mOfficalActivity;
    StatisticalDPMLogic mStatisticalDPMLogic;

}
