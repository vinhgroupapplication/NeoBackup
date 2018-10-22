package com.newsaigonsoft.neoegov.GuideActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.Adapter.GuideMenuAdapter;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends SumManager implements GuideView {

    GuideLogic mGuideLogic = new GuideLogic(this, this);

    @BindView(R.id.lv_menu)
    ListView lv;
    @BindView(R.id.search_view)
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initActiobar("Hướng dẫn sử dụng", true);
        initView();
        mGuideLogic.getListMenu();
        initSearchView();
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mGuideLogic.filterName(newText);
                return true;
            }
        });
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

    @Override
    public void setAdapterList(GuideMenuAdapter menuAdapter) {
        lv.setAdapter(menuAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GuideActivity.this, getString(R.string.building_function), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
