package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.newsaigonsoft.neoegov.Adapter.StatisticalDPMTotalAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisticalPersonTotalAdapter;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_TOTAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 11/24/2017.
 */

public class StatisticalDPMTotalFm extends StatisticalDPMBase implements StatisticalDPMTotalView {

    @BindView(R.id.total_department_list)
    ListView lvDpmTotal;
    @BindView(R.id.layout_department)
    LinearLayout lnDepartment;
    @BindView(R.id.no_connection)
    TextView tvNoData;
    @BindView(R.id.layout_no_data)
    LinearLayout lnNoData;

    public void HeaderClick(View view) {
        switch (view.getId()) {
            case R.id.department_processed:
            case R.id.department_unprocess:
            case R.id.department_party:
            case R.id.department_ontime:
            case R.id.department_delays:
            case R.id.department_indue:
            case R.id.department_out_of_date:
            case R.id.person_processed:
            case R.id.person_unprocess:
            case R.id.person_ontime:
            case R.id.person_delays:
            case R.id.person_indue:
            case R.id.person_out_of_date:
//                mOfficalActivity.setLookupScreen(TAP_DPM_ONTIME);
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(
//                        10
//                        ,TAP_DPM_ONTIME,
//                        2,0 );
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_fragment_total, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        mOfficalActivity.setActionBarIcon(R.drawable.close_white_x1);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        mOfficalActivity.setLookupScreen(LOOKUP_STATISTICAL_TOTAL);
        initView(view);
//        initSpinner();
        setNumberTotal();
        mStatisticalDPMTotalLogic = new StatisticalDPMTotalLogic(mOfficalActivity, this);
        initTab(view);
        mStatisticalDPMTotalLogic.RequestList(mOfficalActivity.getStatisticalDPMRow().getTotalType(), Category, mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
        return view;
    }

    private void initTab(View view) {
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();
        mTabName = new ArrayList<>();
        mTabName.add(mOfficalActivity.getString(R.string.don_vi_thuc_hien));
        mTabName.add(mOfficalActivity.getString(R.string.nguoi_theo_doi));
        for (int i = 0; i < mTabName.size(); i++) {
            TabHost.TabSpec mTabSpec;
            mTabSpec = tabHost.newTabSpec(mTabName.get(i));
            mTabSpec.setIndicator(createTabIndicator(mTabName.get(i)));
            mTabSpec.setContent(new FakeContent(mOfficalActivity));
            tabHost.addTab(mTabSpec);
        }
        tabHost.getTabWidget().setStripEnabled(false);
        tabHost.getTabWidget().setDividerDrawable(null);
        View vlBottom = tabHost.getTabWidget().getChildAt(0).findViewById(R.id.bottom_line);
        vlBottom.setBackgroundColor(Color.parseColor("#ffff4444"));
        TextView tvTitle = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tv_time_select);
        tvTitle.setTextColor(Color.parseColor("#ffff4444"));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int selectedPage = tabHost.getCurrentTab();
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    View vlBottom = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.bottom_line);
                    vlBottom.setBackgroundColor(Color.parseColor(i == selectedPage ? "#ffff4444" : "#00FFFFFF"));
                    TextView tvTitle = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tv_time_select);
                    tvTitle.setTextColor(Color.parseColor(i == selectedPage ? "#ffff4444" : "#777777"));
                }
                Category = selectedPage + 1;
                mOfficalActivity.setCategory(Category);
                mStatisticalDPMTotalLogic.RequestList(mOfficalActivity.getStatisticalDPMRow().getTotalType(), Category, mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
            }
        });
    }

    private View createTabIndicator(String title) {
        View view = LayoutInflater.from(mOfficalActivity).inflate(R.layout.custom_tab_statis_coming, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_time_select);
        tvTitle.setText(title);
        return view;
    }

    public class FakeContent implements TabHost.TabContentFactory {
        Context context;

        public FakeContent(Context context) {
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View view = new View(context);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }

    }

    private void setNumberTotal() {
        mStatisticalTotalHeader.tvPsProcessed.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessed()));
        mStatisticalTotalHeader.tvDpmProcessed.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessed()));
        mStatisticalTotalHeader.tvPsUnprocess.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcess()));
        mStatisticalTotalHeader.tvDpmUnprocess.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcess()));
        mStatisticalTotalHeader.tvPsOntime.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessedOnTime()));
        mStatisticalTotalHeader.tvDpmDelays.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessedDemurrage()));
        mStatisticalTotalHeader.tvPsIndue.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessedDemurrage()));
        mStatisticalTotalHeader.tvDpmIndue.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessOnTime()));
        mStatisticalTotalHeader.tvPsDelays.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessOnTime()));
        mStatisticalTotalHeader.tvDpmOntime.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessedOnTime()));
        mStatisticalTotalHeader.tvPsOutofDate.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessDemurrage()));
        mStatisticalTotalHeader.tvDpmOutofDate.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessDemurrage()));
        mStatisticalTotalHeader.tvDpmParty.setText(String.valueOf(mOfficalActivity.getStatisticalDPMRow().getProcessCoordinate()));
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
        MenuItem itemNotify = menu.findItem(R.id.menu_notify);
//        MenuItem itemConfig = menu.findItem(R.id.menu_config);
//        MenuItem itemHelp = menu.findItem(R.id.menu_help);
        MenuItem itemInfor = menu.findItem(R.id.menu_infor);
        itemNotify.setVisible(false);
//        itemConfig.setVisible(false);
//        itemHelp.setVisible(false);
        itemInfor.setVisible(false);
        itemSearchButton.setVisible(false);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void initView(View view) {
        View header = mOfficalActivity.getLayoutInflater().inflate(R.layout.fragment_statistical_fragment_total_header, null);
        lvDpmTotal.addHeaderView(header);
        mStatisticalTotalHeader = new StatisticalTotalHeader(header, mOfficalActivity);
        lvDpmTotal.setAdapter(null);
    }

    @Override
    public void setListDPM(StatisticalDPMTotalAdapter adapter) {
        if (adapter.getCount() != 0) {
            mStatisticalTotalHeader.lnTopLayout.setVisibility(View.VISIBLE);
//            mStatisticalTotalHeader.lnSelectLayout.setVisibility(View.VISIBLE);
            lvDpmTotal.setAdapter(adapter);
            lnNoData.setVisibility(View.GONE);
            mStatisticalTotalHeader.lnTopPerson.setVisibility(View.GONE);
        } else {
            DisibleList();
        }

    }

    public void DisibleList() {
        if (mOfficalActivity.isNetworkAvailable(getActivity())) {
            tvNoData.setText("Không có nhiệm vụ");
        } else {
            tvNoData.setText("Không có kết nối");
        }
        mStatisticalTotalHeader.lnTopLayout.setVisibility(View.GONE);
        mStatisticalTotalHeader.lnSelectLayout.setVisibility(View.GONE);
        lnNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListPs(StatisticalPersonTotalAdapter psAdapter) {
        if (psAdapter.getCount() != 0) {
            mStatisticalTotalHeader.lnTopLayout.setVisibility(View.VISIBLE);
//            mStatisticalTotalHeader.lnSelectLayout.setVisibility(View.VISIBLE);
            lvDpmTotal.setAdapter(psAdapter);
            lnNoData.setVisibility(View.GONE);
            mStatisticalTotalHeader.lnTopPerson.setVisibility(View.VISIBLE);
            mStatisticalTotalHeader.lnTopLayout.setVisibility(View.GONE);
        } else {
            DisibleList();
        }
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void showProgress() {
        mOfficalActivity.showProgressDialog(nULL_STRING, mOfficalActivity, DIALOG_CENTER, false);
    }

    @Override
    public void showToastError() {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
    }

//    private void initSpinner() {
//        arrSelect = new ArrayList<>();
//        arrSelect.add("ĐƠN VỊ THỰC HIỆN");
//        arrSelect.add("NGƯỜI THEO DÕI");
//        adapter = new ArrayAdapter(mOfficalActivity, R.layout.spinner_item, arrSelect);
//        mStatisticalTotalHeader.spnSelect.setAdapter(adapter);
//        mStatisticalTotalHeader.spnSelect.setSelection(0);
//        mStatisticalTotalHeader.spnSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        mStatisticalTotalHeader.lnTopPerson.setVisibility(View.GONE);
//                        mStatisticalTotalHeader.lnTopLayout.setVisibility(View.VISIBLE);
//                        break;
//                    case 1:
//                        mStatisticalTotalHeader.lnTopLayout.setVisibility(View.GONE);
//                        mStatisticalTotalHeader.lnTopPerson.setVisibility(View.VISIBLE);
//                        break;
//                    default:
//                        break;
//                }
//                Category = i + 1;
//                mOfficalActivity.setCategory(Category);
//                mStatisticalDPMTotalLogic.RequestList(mOfficalActivity.getStatisticalDPMRow().getTotalType(), Category, mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

//    private void initView(View view) {
//        View header = mOfficalActivity.getLayoutInflater().inflate(R.layout.fragment_statistical_fragment_total_header, null);
//        lvDpmTotal.addHeaderView(header);
//        mStatisticalTotalHeader = new StatisticalTotalHeader(header, mOfficalActivity);
//        lvDpmTotal.setAdapter(null);
//        lnTopPerson = (LinearLayout) header.findViewById(R.id.header_person);
//        spnSelect = (Spinner) header.findViewById(R.id.spn_select);
//        tvDpmProcessed = (TextView) header.findViewById(R.id.department_processed);
//        tvDpmUnprocess = (TextView) header.findViewById(R.id.department_unprocess);
//        tvDpmOntime = (TextView) header.findViewById(R.id.department_ontime);
//        tvDpmDelays = (TextView) header.findViewById(R.id.department_delays);
//        tvDpmIndue = (TextView) header.findViewById(R.id.department_indue);
//        tvDpmOutofDate = (TextView) header.findViewById(R.id.department_out_of_date);
//        tvDpmParty = (TextView) header.findViewById(R.id.department_party);
//        tvPsProcessed = (TextView) header.findViewById(R.id.person_processed);
//        tvPsUnprocess = (TextView) header.findViewById(R.id.person_unprocess);
//        tvPsOntime = (TextView) header.findViewById(R.id.person_ontime);
//        tvPsIndue = (TextView) header.findViewById(R.id.person_delays);
//        tvPsDelays = (TextView) header.findViewById(R.id.person_indue);
//        tvPsOutofDate = (TextView) header.findViewById(R.id.person_out_of_date);
//        lnTopLayout = (LinearLayout) header.findViewById(R.id.top_layout);
//        lnSelectLayout = (LinearLayout) header.findViewById(R.id.select_layout);
//
//        tvDpmProcessed.setOnClickListener(this);
//        tvDpmUnprocess.setOnClickListener(this);
//        tvDpmOntime.setOnClickListener(this);
//        tvDpmDelays.setOnClickListener(this);
//        tvDpmIndue.setOnClickListener(this);
//        tvDpmOutofDate.setOnClickListener(this);
//        tvDpmParty.setOnClickListener(this);
//        tvPsProcessed.setOnClickListener(this);
//        tvPsUnprocess.setOnClickListener(this);
//        tvPsOntime.setOnClickListener(this);
//        tvPsIndue.setOnClickListener(this);
//        tvPsDelays.setOnClickListener(this);
//        tvPsOutofDate.setOnClickListener(this);
//    }


}
