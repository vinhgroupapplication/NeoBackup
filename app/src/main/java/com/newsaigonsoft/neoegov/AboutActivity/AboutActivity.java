package com.newsaigonsoft.neoegov.AboutActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;

public class AboutActivity extends SumManager {

    @BindView(R.id.web_site)
    TextView tvWebSite;


    @Optional
    @OnClick({R.id.web_site})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.web_site:
                String url = "http://www.newsaigonsoft.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initActiobar("Về NewSaiGonSoft", true);

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
