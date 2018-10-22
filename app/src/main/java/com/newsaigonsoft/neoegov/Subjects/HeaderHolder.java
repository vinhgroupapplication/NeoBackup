package com.newsaigonsoft.neoegov.Subjects;

import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import com.newsaigonsoft.neoegov.R;

/**
 * Created by Vinh on 12/4/2017.
 */

public class HeaderHolder extends GroupViewHolder {
    TextView tvHeader;
    ImageView imgHeader;
    int count;
    TextView tvNumberLableCountHeader;
    LinearLayout mBackground;
    SumManager manager = new SumManager();

    public HeaderHolder(View itemView) {
        super(itemView);
        tvHeader = (TextView) itemView.findViewById(R.id.menu_name);
        imgHeader = (ImageView) itemView.findViewById(R.id.icon_menu);
        tvNumberLableCountHeader = (TextView) itemView.findViewById(R.id.number_lable_count_header);
        mBackground = (LinearLayout) itemView.findViewById(R.id.linear_item_header);
    }


    public void setBackground(boolean isBackground){
        if (isBackground){
            mBackground.setBackgroundColor(Color.DKGRAY);
        }else {
            mBackground.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    @Override
    public void expand() {
        if (count != 0) {
            imgHeader.setImageResource(R.drawable.ic_play_arrow_down_white_24dp);
        }
        super.expand();
    }

    @Override
    public void collapse() {
        if (count != 0) {
            imgHeader.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        }
        super.collapse();
    }

    public void setHeaderName(String title) {
        Spanned textx = Html.fromHtml(title);
        String xxx = textx.toString();
        String number = manager.getOnlyNumerics(xxx);
        tvHeader.setText(xxx.replace(number, ""));
    }

    public void setHeaderIcon(int icon, int itemCount) {
//        if (itemCount != 0) {
        imgHeader.setImageResource(icon);
//        }
        count = itemCount;

    }

    public void setHeaderCount(int menuCount, String title) {
        if (menuCount > 0) {
            tvNumberLableCountHeader.setText(String.valueOf(menuCount));
        } else {
            tvNumberLableCountHeader.setText(manager.getOnlyNumerics(title));
        }
        if (tvNumberLableCountHeader.getText().toString().equals("") || tvNumberLableCountHeader.getText().toString().equals("0")) {
            tvNumberLableCountHeader.setVisibility(View.INVISIBLE);
        }else {
            tvNumberLableCountHeader.setVisibility(View.VISIBLE);
        }

    }
}
