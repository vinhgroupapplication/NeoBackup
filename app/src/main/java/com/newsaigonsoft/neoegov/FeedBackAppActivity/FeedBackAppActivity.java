package com.newsaigonsoft.neoegov.FeedBackAppActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;

public class FeedBackAppActivity extends SumManager implements FeedBackAppView {

    FeedBackAppLogic mFeedBackAppLogic = new FeedBackAppLogic(this, this);

    @Optional
    @OnClick({R.id.button_forward, R.id.cancel_forward})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_forward:
            case R.id.cancel_forward:
                ShowErrorToast(this, getString(R.string.building_function));
                break;
            default:
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_app);
        ButterKnife.bind(this);
        initActiobar("Góp ý kiến", true);
        initView();

    }

    private void initView() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.bar_back_x1);
    }
}
