package com.newsaigonsoft.neoegov.Fragment.ForwardTree;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.unnamed.b.atv.view.AndroidTreeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.R;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;

/**
 * Created by VinhCN on 4/25/2017.
 */
public class ForwardFragment extends ForwardFmBase implements ForwardView {

    @BindView(R.id.containerView)
    RelativeLayout mRelativeLayout;

    public static ForwardFragment newInstance(String TitleActionbar) {
        ForwardFragment myFragment = new ForwardFragment();
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
        view = inflater.inflate(R.layout.fragment_forward, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this, view);
        mForwardLogic = new ForwardLogic(this, mDetailForwardActivity, mDetailForwardActivity.getmInforTrainfer());
        mForwardLogic.requestDataForwardTree();
        return view;
    }

    public void SetViewList(AndroidTreeView treeView) {
        mRelativeLayout.addView(treeView.getView());
    }

    @Override
    public void SetTreeList(AndroidTreeView tView, String forward) {
        mRelativeLayout.addView(tView.getView());
    }

    @Override
    public void showProgress() {
        mDetailForwardActivity.showProgressDialog("", mDetailForwardActivity, DIALOG_CENTER, true);
    }

    @Override
    public void closeProgress() {
        mDetailForwardActivity.closeProgressDialog();
    }

    @Override
    public void ToastError(String s) {
        mDetailForwardActivity.ShowErrorToast(mDetailForwardActivity);
        Log.d(TAG, s);
    }
//    private void initView(View view) {
//        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.containerView);
//    }
// visible fail connection
//public void visibleLayoutTryAgainForward(boolean visible) {
//    if (visible) {
//        layoutTryAgain.setVisibility(View.VISIBLE);
//    } else {
//        layoutTryAgain.setVisibility(View.GONE);
//    }
//}
}
