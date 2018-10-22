package com.newsaigonsoft.neoegov.Fragment.StatisticalDocSent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;

import butterknife.ButterKnife;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_SEND;

/**
 * Created by Vinh on 3/22/2018.
 */

public class StatisticalDocSentFm extends StatisticalDocSentFmBase implements StatisticalDocSentView {

    public static StatisticalDocSentFm newInstance() {
        StatisticalDocSentFm myFragment = new StatisticalDocSentFm();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_internal, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        mStatisticalDocSentLogic = new StatisticalDocSentLogic(mOfficalActivity, this);
        ButterKnife.bind(this,view);
        mOfficalActivity.setLookupScreen(LOOKUP_STATISTICAL_SEND);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemSearchButton = menu.findItem(R.id.search_button);
        MenuItem itemCenterMenu = menu.findItem(R.id.center_menu);
        itemSearchButton.setVisible(false);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }


}
