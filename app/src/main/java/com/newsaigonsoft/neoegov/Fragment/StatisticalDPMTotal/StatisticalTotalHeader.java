package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;

/**
 * Created by Vinh on 3/8/2018.
 */

public class StatisticalTotalHeader {
    Context mContext;
    @BindView(R.id.department_processed)
    TextView tvDpmProcessed;
    @BindView(R.id.department_unprocess)
    TextView tvDpmUnprocess;
    @BindView(R.id.department_ontime)
    TextView tvDpmOntime;
    @BindView(R.id.department_indue)
    TextView tvDpmIndue;
    @BindView(R.id.department_delays)
    TextView tvDpmDelays;
    @BindView(R.id.department_out_of_date)
    TextView tvDpmOutofDate;
    @BindView(R.id.department_party)
    TextView tvDpmParty;
    @BindView(R.id.person_processed)
    TextView tvPsProcessed;
    @BindView(R.id.person_unprocess)
    TextView tvPsUnprocess;
    @BindView(R.id.person_ontime)
    TextView tvPsOntime;
    @BindView(R.id.person_delays)
    TextView tvPsIndue;
    @BindView(R.id.person_indue)
    TextView tvPsDelays;
    @BindView(R.id.person_out_of_date)
    TextView tvPsOutofDate;
    @BindView(R.id.header_person)
    LinearLayout lnTopPerson;
    @BindView(R.id.top_layout)
    LinearLayout lnTopLayout;
    @BindView(R.id.select_layout)
    LinearLayout lnSelectLayout;
    @BindView(R.id.spn_select)
    Spinner spnSelect;

    public StatisticalTotalHeader(View view, Context context) {
        mContext = context;
        ButterKnife.bind(this, view);
    }

    @Optional
    @OnClick({R.id.department_processed, R.id.department_unprocess,
            R.id.department_party, R.id.department_ontime,
            R.id.department_delays, R.id.department_indue,
            R.id.department_out_of_date, R.id.person_processed,
            R.id.person_unprocess, R.id.person_ontime,
            R.id.person_delays, R.id.person_indue,
            R.id.person_out_of_date
    })
    public void onHeaderClicked(View view) {
        ((OfficalActivity) mContext).StatisticalTotalClick(view);
    }


}
