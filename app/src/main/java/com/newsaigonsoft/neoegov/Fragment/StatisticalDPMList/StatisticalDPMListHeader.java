package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMList;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinh on 5/28/2018.
 */

public class StatisticalDPMListHeader {
    Context mContext;
    @BindView(R.id.onTime)
    TextView tvOnTime;
    @BindView(R.id.out_of_date)
    TextView tvOutOfDate;
    @BindView(R.id.spn_select)
    Spinner spnSelect;
    @BindView(R.id.top_layout)
    LinearLayout lnTopLayout;

    public StatisticalDPMListHeader(View view, Context mContext) {
        this.mContext = mContext;
        ButterKnife.bind(this, view);
    }
}
