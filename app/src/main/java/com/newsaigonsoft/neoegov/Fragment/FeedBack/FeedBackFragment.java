package com.newsaigonsoft.neoegov.Fragment.FeedBack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.Adapter.FeedBackAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

/**
 * Created by Vinh on 10/7/2017.
 */

public class FeedBackFragment extends FeedBackFMBase implements FeedBackFmView {
    @BindView(R.id.list_feed_back)
    ListView lv;
    @BindView(R.id.no_feed_back)
    LinearLayout lnNoFeedBack;

    public static FeedBackFragment newInstance(String TitleActionbar) {
        FeedBackFragment myFragment = new FeedBackFragment();
        Bundle args = new Bundle();
        args.putString("Title", TitleActionbar);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        title = getArguments().getString("Title");
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this, view);
        mFeedBackFmLogic = new FeedBackFmLogic(this);
//        mDetailForwardActivity.titleActionbar.setText(title);
//        mDetailForwardActivity.changeListView(2);
        return view;
    }



    public void setAdapterFeedBack(List<FeedBackRow> arrFeedBack, ModuleRow moduleRow) {
        mFeedBackFmLogic.setFeedBack(arrFeedBack, moduleRow);
    }
    @Override
    public void showNoFeedBack() {
        lnNoFeedBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListFeedBack(List<FeedBackRow> arrFeedBack, ModuleRow moduleRow) {
        FeedBackAdapter feedBackAdapter = new FeedBackAdapter(mDetailForwardActivity, arrFeedBack, mDetailForwardActivity, moduleRow);
        lv.setAdapter(feedBackAdapter);
    }
//    private void initView(View v) {
//        lv = (ListView) v.findViewById(R.id.list_feed_back);
//        lnNoFeedBack = (LinearLayout) v.findViewById(R.id.no_feed_back);
//    }
}
