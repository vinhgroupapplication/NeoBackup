package com.newsaigonsoft.neoegov.AppInforActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.AboutActivity.AboutActivity;
import com.newsaigonsoft.neoegov.Adapter.GuideMenuAdapter;
import com.newsaigonsoft.neoegov.Adapter.InforAdapter;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Optional;

public class AppInforActivity extends SumManager implements AppInforView {

    AppInforLogic mAppInforLogic = new AppInforLogic(this, this);

    @BindView(R.id.lv)
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_infor);
        ButterKnife.bind(this);
        initActiobar("Thông tin ứng dụng", true);
        initView();
        mAppInforLogic.getListInfor();

    }

    private void initView() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.bar_back_x1);
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


    @Optional
    @OnItemClick({R.id.lv})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
            case 1:
//                Toast.makeText(AppInforActivity.this, getString(R.string.building_function), Toast.LENGTH_SHORT).show();
//                break;
            case 2:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }

    }

    @Override
    public void setListInfor(InforAdapter menuAdapter) {
        lv.setAdapter(menuAdapter);
    }
}
