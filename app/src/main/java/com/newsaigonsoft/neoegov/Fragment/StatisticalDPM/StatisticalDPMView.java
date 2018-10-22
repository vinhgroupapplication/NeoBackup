package com.newsaigonsoft.neoegov.Fragment.StatisticalDPM;

import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMRow;

/**
 * Created by Vinh on 12/26/2017.
 */

public interface StatisticalDPMView {

    void setStatistiCalView(StatisticalDPMRow mStatisticalDPMRow);

    void showProgress();

    void closeProgress();

    String getStartTime();

    String getEndTime();

    void showToastError();

    void mStatisticalDPMRow(StatisticalDPMRow mStatisticalDPMRow);

    void ToastError(String s);
}
