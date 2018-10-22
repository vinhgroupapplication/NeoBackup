package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;

/**
 * Created by Vinh on 3/2/2018.
 */

public class FooterViewHolder {
    Context mContext;

    @BindView(R.id.list_Handle) NonScrollListView lvHandle;
    @BindView(R.id.number_day_repulations) EditText edtProcessDays;
    @BindView(R.id.repulation_day) TextView edtExpirationDate;
    @BindView(R.id.allow_extenstion) CheckBox cbAllowExtension;
    @BindView(R.id.regulations_deadline) CheckBox cbRegulationDeadline;
    @BindView(R.id.layout_handle_way) LinearLayout lnChooseHandleWay;
    @BindView(R.id.layout_due_date) LinearLayout lnChooseDueDate;

    public FooterViewHolder(View view, Context context) {
        mContext = context;
        ButterKnife.bind(this, view);
    }

    @Optional
    @OnClick({R.id.repulation_day, R.id.regulations_deadline})
    public void onHeaderClicked(View view) {
        ((InputForwardDepartmentActivity) mContext).onHeaderClicked(view);
    }
}
