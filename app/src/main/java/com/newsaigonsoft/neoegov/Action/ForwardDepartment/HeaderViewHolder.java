package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class HeaderViewHolder {
    Context mContext;
    @BindView(R.id.process_content)
    EditText edtContent;
    @BindView(R.id.send_mail_cb)
    CheckBox cbEmail;
    @BindView(R.id.khan_cb)
    CheckBox cbUrgent;
    @BindView(R.id.thuong_khan_cb)
    CheckBox cbSupUrgent;
    @BindView(R.id.check_all)
    CheckBox cbCheckAllDepartment;
    @BindView(R.id.process_name)
    TextView tvProcessDay;

    @BindView(R.id.choose_file)
    TextView tvChooseFile;
    @BindView(R.id.attach_list)
    NonScrollListView lvAttach;


    public HeaderViewHolder(View view, Context context) {
        mContext = context;
        ButterKnife.bind(this, view);
    }

    @Optional
    @OnClick({ R.id.check_all, R.id.choose_file})
    public void onHeaderClicked(View view) {
        ((InputForwardDepartmentActivity) mContext).onHeaderClicked(view);
    }


}
