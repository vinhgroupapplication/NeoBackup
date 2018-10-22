package com.newsaigonsoft.neoegov.SupportActivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupportActivity extends SumManager {

    @BindView(R.id.linear_parent_app_bar)
    LinearLayout lnParentAppBar;
    @BindView(R.id.title_actionbar)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ButterKnife.bind(this);
        initActiobar("Hỗ trợ", true);
        initView();
    }

    private void initView() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_clear_black_36);
        lnParentAppBar.setBackgroundColor(getResources().getColor(android.R.color.white));
        tvTitle.setTextColor(getResources().getColor(android.R.color.black));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.search_button).setVisible(false);
        menu.findItem(R.id.menu_infor).setVisible(false);
//        menu.findItem(R.id.menu_help).setVisible(false);
//        menu.findItem(R.id.menu_config).setVisible(false);
        menu.findItem(R.id.center_menu).setVisible(false);
        return true;
    }

}
