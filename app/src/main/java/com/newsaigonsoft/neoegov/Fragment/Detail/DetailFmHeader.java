package com.newsaigonsoft.neoegov.Fragment.Detail;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.R;

/**
 * Created by Vinh on 3/15/2018.
 */

public class DetailFmHeader {
    @BindView(R.id.list_view_attach_file)
    ListView lvFileAttach;
    @BindView(R.id.show_more)
    TextView tvShowMore;
    Context Context;

    public DetailFmHeader(View view, Context context) {
        ButterKnife.bind(this,view);
        Context = context;
    }

    @Optional
    @OnClick({R.id.show_more})
    public void OnClickShowMore(View view){
        switch (view.getId()){
            case R.id.show_more:
                ((DetailForwardActivity) Context).showMoreDetailClick();
                break;
        }
    }
}
