package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

import com.newsaigonsoft.neoegov.Adapter.StatisticalDPMListADapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal.StatisticalDPMTotalFm;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_IN_DUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_UN_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_SCREEN;

/**
 * Created by Vinh on 11/27/2017.
 */

public class StatisticalDPMListFm extends StatisticalDPMListBase implements StatisticalDPMListView {
    @BindView(R.id.statistical_list)
    NonScrollListView lv;
    @BindView(R.id.layout_no_data)
    LinearLayout lnNoData;
    // init view header
//    @BindView(R.id.onTime)
//    TextView tvOnTime;
//    @BindView(R.id.out_of_date)
//    TextView tvOutOfDate;
//    @BindView(R.id.spn_select)
//    Spinner spnSelect;
//    @BindView(R.id.top_layout)
//    LinearLayout lnTopLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_dpm_list, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
//        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        mOfficalActivity.setLookupScreen(LOOKUP_STATISTICAL_LIST);
        mOfficalActivity.setActionBarIcon(R.drawable.close_white_x1);
        mStatisticalDPMListLogic = new StatisticalDPMListLogic(mOfficalActivity, this);
        initTab(view);
        setDataList(0);
        initSpinner();
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
                setDataList(selectedPage);

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


    private void setDataList(int position) {
//        mStatisticalDPMListHeader.tvOnTime.setVisibility(View.GONE);
        int Category = position + 1;
        mOfficalActivity.setCategory(Category);
        switch (getScreen()) {
            case DPM_PROCESS:
//                mStatisticalDPMListHeader.tvOnTime.setVisibility(View.VISIBLE);
                mStatisticalDPMListLogic.RequestList(Category, mOfficalActivity.getStatisticalDPMRow().getProcessedType(), mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
                //lv.setAdapter(getAdapterProcess(position));
                mOfficalActivity.titleActionbar.setText("Nhiệm vụ đã xử lý");
                break;
            case DPM_UN_PROCESS:
//                mStatisticalDPMListHeader.tvOnTime.setVisibility(View.VISIBLE);
//                mStatisticalDPMListHeader.tvOnTime.setText("Trong hạn");
//                mStatisticalDPMListHeader.tvOutOfDate.setText("Quá hạn");
                mStatisticalDPMListLogic.RequestList(Category, mOfficalActivity.getStatisticalDPMRow().getProcessType(), mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
                mOfficalActivity.titleActionbar.setText("Nhiệm vụ chưa xử lý");
                break;
            case DPM_ON_TIME:
//                mStatisticalDPMListHeader.tvOutOfDate.setText("Đúng hạn");
                mStatisticalDPMListLogic.RequestList(Category, mOfficalActivity.getStatisticalDPMRow().getProcessedOnTimeType(), mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
                mOfficalActivity.titleActionbar.setText("Nhiệm vụ đúng hạn");
                break;
            case DPM_DELAYS:
//                mStatisticalDPMListHeader.tvOutOfDate.setText("Trễ hạn");
                mStatisticalDPMListLogic.RequestList(Category, mOfficalActivity.getStatisticalDPMRow().getProcessedDemurrageType(), mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
                mOfficalActivity.titleActionbar.setText("Nhiệm vụ trễ hạn");
                break;
            case DPM_IN_DUE:
//                mStatisticalDPMListHeader.tvOutOfDate.setText("Trong hạn");
                mOfficalActivity.titleActionbar.setText("Nhiệm vụ trong hạn");
                mStatisticalDPMListLogic.RequestList(Category, mOfficalActivity.getStatisticalDPMRow().getProcessOnTimeType(), mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
                break;
            case DPM_OUT_OF_DATE:
                mOfficalActivity.titleActionbar.setText("Nhiệm vụ quá hạn");
//                mStatisticalDPMListHeader.tvOutOfDate.setText("Quá hạn");
                mStatisticalDPMListLogic.RequestList(Category, mOfficalActivity.getStatisticalDPMRow().getProcessDemurrageType(), mOfficalActivity.getStatisStartDate(), mOfficalActivity.getStatisEndDate());
                break;
        }

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

    private String getScreen() {
        Bundle b = getArguments();
        return b.getString(TAP_SCREEN);
    }

    @Override
    public String isCsreen() {
        return getScreen();
    }

    @Override
    public void setAdapterProcessed(StatisticalDPMListADapter adapterProcessed) {
        if (adapterProcessed.getCount() != 0) {
            lv.setAdapter(adapterProcessed);
            lnNoData.setVisibility(View.GONE);
//            mStatisticalDPMListHeader.lnTopLayout.setVisibility(View.VISIBLE);
        } else {
            lnNoData.setVisibility(View.VISIBLE);
//            mStatisticalDPMListHeader.lnTopLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setAdapterprocess(StatisticalDPMListADapter adapterProcess) {
        if (adapterProcess.getCount() != 0) {
            lnNoData.setVisibility(View.GONE);
//            mStatisticalDPMListHeader.lnTopLayout.setVisibility(View.VISIBLE);
            lv.setAdapter(adapterProcess);
        } else {
            lnNoData.setVisibility(View.VISIBLE);
//            mStatisticalDPMListHeader.lnTopLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void ToastError(String s) {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
        Log.d(TAG, s);
    }

    public void initSpinner() {
//        View header = mOfficalActivity.getLayoutInflater().inflate(R.layout.fragment_statistical_dpm_list_header, null);
//        lv.addHeaderView(header);
//        mStatisticalDPMListHeader = new StatisticalDPMListHeader(header, mOfficalActivity);
//        lv.setAdapter(null);
//        arrSelect = new ArrayList<>();
//        arrSelect.add("ĐƠN VỊ THỰC HIỆN");
//        arrSelect.add("NGƯỜI THEO DÕI");
//        adapterSelect = new ArrayAdapter(mOfficalActivity, R.layout.spinner_item, arrSelect);
//        mStatisticalDPMListHeader.spnSelect.setAdapter(adapterSelect);
//        mStatisticalDPMListHeader.spnSelect.setSelection(0);
//        mStatisticalDPMListHeader.spnSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        setDataList(0);
//                        break;
//                    case 1:
//                        setDataList(1);
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

//    private void initView(View view) {
//        tvOnTime = (TextView) view.findViewById(R.id.onTime);
//        tvOutOfDate = (TextView) view.findViewById(R.id.out_of_date);
//        lv = (ListView) view.findViewById(R.id.statistical_list);
//        spnSelect = (Spinner) view.findViewById(R.id.spn_select);
//        lnNoData = (LinearLayout) view.findViewById(R.id.layout_no_data);
//        lnTopLayout = (LinearLayout) view.findViewById(R.id.top_layout);
//    }
//
//    private StatisticalDPMListADapter getAdapterOutOfDate(int position) {
//        arrList = new ArrayList<>();
//        switch (position) {
//            case 0:
//                arrList.add(new StatisticalDPMListRow("Sở tư pháp", 0, 3));
//                arrList.add(new StatisticalDPMListRow("Sở xây dựng", 0, 1));
//                arrList.add(new StatisticalDPMListRow("Sở y tế", 0, 1));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 2", 0, 3));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 3", 0, 2));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 4", 0, 1));
//                break;
//            case 1:
//                arrList.add(new StatisticalDPMListRow("Lê Thị Hồng Loan", 0, 2));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Dương", 0, 2));
//                arrList.add(new StatisticalDPMListRow("Huỳnh Đức Thuận", 0, 3));
//                arrList.add(new StatisticalDPMListRow("Trần Hoàng Nam", 0, 1));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Bá Thái", 0, 1));
//                arrList.add(new StatisticalDPMListRow("Võ Tiết Cường", 0, 1));
//                break;
//        }
//        adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//        return adapter;
//    }
//
//    private StatisticalDPMListADapter getAdapterInDue(int position) {
//        arrList = new ArrayList<>();
//        switch (position) {
//            case 0:
//                arrList.add(new StatisticalDPMListRow("Sở tư pháp", 0, 20));
//                arrList.add(new StatisticalDPMListRow("Sở xây dựng", 0, 5));
//                arrList.add(new StatisticalDPMListRow("Sở y tế", 0, 7));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 2", 0, 9));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 3", 0, 1));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 4", 0, 3));
//                break;
//            case 1:
//                arrList.add(new StatisticalDPMListRow("Lê Thị Hồng Loan", 0, 15));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Dương", 0, 5));
//                arrList.add(new StatisticalDPMListRow("Huỳnh Đức Thuận", 0, 10));
//                arrList.add(new StatisticalDPMListRow("Trần Hoàng Nam", 0, 7));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Bá Thái", 0, 2));
//                arrList.add(new StatisticalDPMListRow("Võ Tiết Cường", 0, 1));
//                break;
//        }
//        adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//        return adapter;
//    }
//
//
//    private StatisticalDPMListADapter getAdapterOnTime(int position) {
//        arrList = new ArrayList<>();
//        switch (position) {
//            case 0:
//                arrList.add(new StatisticalDPMListRow("Sở tư pháp", 0, 10));
//                arrList.add(new StatisticalDPMListRow("Sở xây dựng", 0, 5));
//                arrList.add(new StatisticalDPMListRow("Sở y tế", 0, 4));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 2", 0, 7));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 3", 0, 5));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 4", 0, 5));
//                break;
//            case 1:
//                arrList.add(new StatisticalDPMListRow("Lê Thị Hồng Loan", 0, 10));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Dương", 0, 5));
//                arrList.add(new StatisticalDPMListRow("Huỳnh Đức Thuận", 0, 3));
//                arrList.add(new StatisticalDPMListRow("Trần Hoàng Nam", 0, 2));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Bá Thái", 0, 6));
//                arrList.add(new StatisticalDPMListRow("Võ Tiết Cường", 0, 4));
//                break;
//        }
//        adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//        return adapter;
//    }
//
//    private StatisticalDPMListADapter getAdapterProcess(int position) {
//        arrList = new ArrayList<>();
//        switch (position) {
//            case 0:
//                arrList.add(new StatisticalDPMListRow("Sở tư pháp", 10, 7));
//                arrList.add(new StatisticalDPMListRow("Sở xây dựng", 5, 2));
//                arrList.add(new StatisticalDPMListRow("Sở y tế", 3, 3));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 2", 7, 8));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 3", 5, 4));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 4", 5, 1));
//                adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//                break;
//            case 1:
//                arrList.add(new StatisticalDPMListRow("Lê Thị Hồng Loan", 10, 6));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Dương", 5, 3));
//                arrList.add(new StatisticalDPMListRow("Huỳnh Đức Thuận", 3, 1));
//                arrList.add(new StatisticalDPMListRow("Trần Hoàng Nam", 2, 1));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Bá Thái", 6, 5));
//                arrList.add(new StatisticalDPMListRow("Võ Tiết Cường", 4, 4));
//                adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//                break;
//        }
//
//        return adapter;
//    }
//
//    private StatisticalDPMListADapter getAdapterDelays(int position) {
//        arrList = new ArrayList<>();
//        switch (position) {
//            case 0:
//                arrList.add(new StatisticalDPMListRow("Sở tư pháp", 0, 7));
//                arrList.add(new StatisticalDPMListRow("Sở xây dựng", 0, 2));
//                arrList.add(new StatisticalDPMListRow("Sở y tế", 0, 3));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 2", 0, 8));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 3", 0, 4));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 4", 0, 1));
//                break;
//            case 1:
//                arrList.add(new StatisticalDPMListRow("Lê Thị Hồng Loan", 0, 6));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Dương", 0, 3));
//                arrList.add(new StatisticalDPMListRow("Huỳnh Đức Thuận", 0, 1));
//                arrList.add(new StatisticalDPMListRow("Trần Hoàng Nam", 0, 1));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Bá Thái", 0, 5));
//                arrList.add(new StatisticalDPMListRow("Võ Tiết Cường", 0, 4));
//                break;
//        }
//
//        adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//        return adapter;
//    }
//
//    private StatisticalDPMListADapter getAdapterNonProcess(int position) {
//        arrList = new ArrayList<>();
//        switch (position) {
//            case 0:
//                arrList.add(new StatisticalDPMListRow("Sở tư pháp", 20, 3));
//                arrList.add(new StatisticalDPMListRow("Sở xây dựng", 5, 1));
//                arrList.add(new StatisticalDPMListRow("Sở y tế", 7, 1));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 2", 9, 3));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 3", 1, 2));
//                arrList.add(new StatisticalDPMListRow("UBND Quận 4", 3, 1));
//                adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//                break;
//            case 1:
//                arrList.add(new StatisticalDPMListRow("Lê Thị Hồng Loan", 15, 2));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Dương", 5, 2));
//                arrList.add(new StatisticalDPMListRow("Huỳnh Đức Thuận", 10, 3));
//                arrList.add(new StatisticalDPMListRow("Trần Hoàng Nam", 7, 1));
//                arrList.add(new StatisticalDPMListRow("Nguyễn Bá Thái", 2, 1));
//                arrList.add(new StatisticalDPMListRow("Võ Tiết Cường", 1, 1));
//                adapter = new StatisticalDPMListADapter(mOfficalActivity, arrList, getScreen());
//                break;
//        }
//        return adapter;
//    }


    }
}
