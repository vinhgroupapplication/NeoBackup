package com.newsaigonsoft.neoegov.NotifyAcitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.newsaigonsoft.neoegov.Adapter.NotifyAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.GsonObject.GsonNotify;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import me.leolin.shortcutbadger.ShortcutBadger;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class NotifyActivity extends NotifyBase implements NotifyView {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.empty_seach_layout)
    LinearLayout lnEmpty;
    SQLite mSqLiteNotify;
    NotifyLogic notifyLogic = new NotifyLogic(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
        initActiobar(getIntent().getStringExtra(TITLE_ACTION_BAR), true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        mSqLiteNotify = new SQLite(this, NOTIFY_SQL, null, 1);
        notifyLogic.getListNotify(mSqLiteNotify);
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
    public void setListNotify(List<GsonNotify> arr) {
        arrayListNotify = arr;
        lv.setAdapter(new NotifyAdapter(this, arrayListNotify));
        lnEmpty.setVisibility(arrayListNotify.size() == 0 ? View.VISIBLE : View.GONE);
    }

    @OnItemClick(R.id.lv)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailForwardActivity.class);
        notifyLogic.openDetails(intent, position);
    }
}