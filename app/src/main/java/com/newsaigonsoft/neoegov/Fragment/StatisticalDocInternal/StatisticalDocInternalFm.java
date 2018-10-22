package com.newsaigonsoft.neoegov.Fragment.StatisticalDocInternal;

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

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_INTERNAL;

/**
 * Created by Vinh on 3/22/2018.
 */

public class StatisticalDocInternalFm extends StatisticalDocInternalBase implements StatisticalDocInternalView{

    public static StatisticalDocInternalFm newInstance() {
        StatisticalDocInternalFm myFragment = new StatisticalDocInternalFm();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_send, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        mStatisticalDocInternalLogic = new StatisticalDocInternalLogic(mOfficalActivity, this);
        ButterKnife.bind(this,view);
        mOfficalActivity.setLookupScreen(LOOKUP_STATISTICAL_INTERNAL);
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
