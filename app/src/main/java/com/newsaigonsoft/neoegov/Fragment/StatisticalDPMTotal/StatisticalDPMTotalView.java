package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal;

import com.newsaigonsoft.neoegov.Adapter.StatisticalDPMTotalAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisticalPersonTotalAdapter;

/**
 * Created by Vinh on 12/28/2017.
 */

public interface StatisticalDPMTotalView {

    void setListDPM(StatisticalDPMTotalAdapter adapter);

    void setListPs(StatisticalPersonTotalAdapter psAdapter);

    void closeProgress();

    void showProgress();

    void showToastError();
}
