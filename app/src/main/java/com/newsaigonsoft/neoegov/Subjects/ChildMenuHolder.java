package com.newsaigonsoft.neoegov.Subjects;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import com.newsaigonsoft.neoegov.R;

/**
 * Created by Vinh on 12/4/2017.
 */

public class ChildMenuHolder extends ChildViewHolder {
    TextView tvChildText;
    TextView tvNumberLableCount;
    public LinearLayout mLnChild;

    public ChildMenuHolder(View itemView) {
        super(itemView);
        tvChildText = (TextView) itemView.findViewById(R.id.menu_name_child);
        tvNumberLableCount = (TextView) itemView.findViewById(R.id.number_lable_count);
        mLnChild = (LinearLayout) itemView.findViewById(R.id.slider_child);
    }

    public void setChildName(String menuName) {
        tvChildText.setText(menuName);
    }

    public void setChildCount(int menuCount) {
        tvNumberLableCount.setText(String.valueOf(menuCount));
        if (menuCount > 0) {
            tvNumberLableCount.setVisibility(View.VISIBLE);
        } else {
            tvNumberLableCount.setVisibility(View.GONE);
        }
    }
}
