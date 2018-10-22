package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMList;

import com.newsaigonsoft.neoegov.Adapter.StatisticalDPMListADapter;

/**
 * Created by Vinh on 12/29/2017.
 */

public interface StatisticalDPMListView {
    String isCsreen();

    void setAdapterProcessed(StatisticalDPMListADapter adapterProcessed);

    void setAdapterprocess(StatisticalDPMListADapter adapterProcess);

    void closeProgress();

    void ToastError(String s);
}
