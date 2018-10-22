package com.newsaigonsoft.neoegov.Fragment.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.ScheduleAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleDayAdapter;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.HomeLookupRow;
import com.newsaigonsoft.neoegov.Subjects.HotLineRow;
import com.newsaigonsoft.neoegov.Subjects.TaskRemind;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static android.graphics.Color.RED;
import static com.newsaigonsoft.neoegov.R.id.doc_Demurrage;
import static com.newsaigonsoft.neoegov.R.id.doc_docDemurrage_sent;
import static com.newsaigonsoft.neoegov.R.id.doc_not_process;
import static com.newsaigonsoft.neoegov.R.id.doc_not_process_sent;
import static com.newsaigonsoft.neoegov.R.id.doc_not_seen;
import static com.newsaigonsoft.neoegov.R.id.doc_not_seen_sent;
import static com.newsaigonsoft.neoegov.R.id.h_scroll_view;
import static com.newsaigonsoft.neoegov.R.id.hotline_processdemurrage;
import static com.newsaigonsoft.neoegov.R.id.hotline_processondue;
import static com.newsaigonsoft.neoegov.R.id.log_out;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.BROADCASTLISTENNER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOME_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_NAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RELOADSLIDER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.CLICK_TIME_INTERVAL;

/**
 * Created by Vinh on 6/1/2017.
 */

public class HomeFragment extends HomeFmBase implements HomeView {

    @BindView(R.id.doc_total)
    TextView tvTotalDoc;
    @BindView(doc_not_seen)
    TextView tvDocNotSeen;
    @BindView(doc_Demurrage)
    TextView tvDocDemurrage;
    @BindView(doc_not_process)
    TextView tvDocNotProcess;
    @BindView(R.id.doc_total_sent)
    TextView tvTotalDocSend;
    @BindView(doc_not_seen_sent)
    TextView tvDocNotSeenSend;
    @BindView(doc_docDemurrage_sent)
    TextView tvDocDemurrageSend;
    @BindView(doc_not_process_sent)
    TextView tvDocNotProcessSend;
    @BindView(R.id.statistical_coming_time)
    TextView tvComingTime;
    @BindView(R.id.statistical_sent_time)
    TextView tvSendTime;
    @BindView(R.id.day_schedule)
    TextView tvDaySchedule;
    @BindView(R.id.task_time)
    TextView tvTaskTime;
    @BindView(R.id.task_confirm_complete)
    TextView tvTaskComfirmComplete;
    @BindView(R.id.task_processing)
    TextView tvTaskProcessing;
    @BindView(R.id.task_in_of_date)
    TextView tvTaskInOfDate;
    @BindView(R.id.task_expiring_soon)
    TextView tvTaskExpiringSoon;
    @BindView(R.id.task_out_of_date)
    TextView tvTaskOutOfDate;
    @BindView(R.id.task_total)
    TextView tvTaskTotal;
    @BindView(R.id.lv_schedule)
    ListView lvSchedule;
    @BindView(R.id.schedule_layout)
    LinearLayout ScheduleLayout;
    @BindView(R.id.hotline_layout)
    LinearLayout HotLineLayout;
    @BindView(R.id.home_layout)
    RelativeLayout lnHome;
    @BindView(R.id.task_layout)
    LinearLayout mLayoutTask;
    @BindView(R.id.test_tap)
    ImageView imgTest;
    @BindView(R.id.hotline_process)
    TextView tvHotProcess;
    @BindView(hotline_processondue)
    TextView tvHotProcessondue;
    @BindView(hotline_processdemurrage)
    TextView tvHotProcessdemurrage;
    @BindView(R.id.scrollview)
    ScrollView svParent;
    @BindView(R.id.total_task_time)
    TextView tvTaskTimeTotal;
    @BindView(R.id.doc_not_process_remind)
    TextView tvChuaXuLy;
    @BindView(R.id.doc_not_near_of_date_remind)
    TextView tvSapQuaHan;
    @BindView(R.id.doc_out_of_date_remind)
    TextView tvQuaHan;
    @BindView(R.id.layout_recieve)
    LinearLayout lnRecieve;
    @BindView(R.id.layout_send)
    LinearLayout lnSend;
    @BindView(R.id.space_recieve)
    View vSpaceRecieve;
    @BindView(R.id.space_send)
    View vSpaceSend;
    @BindView(R.id.layout_remind)
    LinearLayout lnRemind;

    @OnItemClick(R.id.lv_schedule)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mHomeFmLogic.OpenDetail(position);
    }

    @Optional
    @OnClick({R.id.hotline_process, R.id.hotline_processdemurrage,
            R.id.hotline_processondue, R.id.task_confirm_complete,
            R.id.task_processing, R.id.task_in_of_date,
            R.id.task_expiring_soon, R.id.task_out_of_date,
            R.id.doc_not_seen, R.id.doc_not_process,
            R.id.doc_Demurrage, R.id.doc_not_seen_sent,
            R.id.doc_not_process_sent, R.id.doc_docDemurrage_sent,
            R.id.test_tap})
    public void onClick(View v) {
        int id = v.getId();
        if (!mOfficalActivity.isNetworkAvailable(mOfficalActivity)) {
            mOfficalActivity.ShowErrorToast(mOfficalActivity);
            return;
        }
        //tempory solution for fast tapping
        long now = System.currentTimeMillis();
        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return;
        }
        mLastClickTime = now;
        //tempory solution for fast tapping
        switch (id) {
            case R.id.hotline_process:
                mOfficalActivity.setLookupScreen(HOT_PROCESS);
                if (mHotLineRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHotLineRow.getProcess(), HOT_PROCESS,
                            mHotLineRow.getProcessType(), mHotLineRow.getOrganizationId());
                }
                break;
            case R.id.hotline_processdemurrage:
                mOfficalActivity.setLookupScreen(HOT_PROCESS_DEMURRAGE);
                if (mHotLineRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHotLineRow.getProcessDemurrage(), HOT_PROCESS_DEMURRAGE,
                            mHotLineRow.getProcessDemurrageType(), mHotLineRow.getOrganizationId());
                }
                break;
            case R.id.hotline_processondue:
                mOfficalActivity.setLookupScreen(HOT_PROCESS_ONDUE);
                if (mHotLineRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHotLineRow.getProcessOnDue(), HOT_PROCESS_ONDUE,
                            mHotLineRow.getProcessOnDueType(), mHotLineRow.getOrganizationId());
                }
                break;
            case R.id.task_confirm_complete:
                mOfficalActivity.setLookupScreen(TASK_REPORTED);
                if (mTaskRemind != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getReported(), TASK_REPORTED,
                            mTaskRemind.getReportedType(), 0);
                }
                break;
            case R.id.task_processing:
                mOfficalActivity.setLookupScreen(TASK_PROCESS);
                if (mTaskRemind != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcess(), TASK_PROCESS,
                            mTaskRemind.getProcessType(), 0);
                }
                break;
            case R.id.task_in_of_date:
                mOfficalActivity.setLookupScreen(PROCESS_ON_TIME);
                if (mTaskRemind != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcessOnDue(), PROCESS_ON_TIME,
                            mTaskRemind.getProcessOnDueType(), 0);
                }
                break;
            case R.id.task_expiring_soon:
                mOfficalActivity.setLookupScreen(TASK_PROCESS_NEAR_DEMURRAGE);
                if (mTaskRemind != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcessNearDemurrage(), TASK_PROCESS_NEAR_DEMURRAGE,
                            mTaskRemind.getProcessNearDemurrageType(), 0);
                }
                break;
            case R.id.task_out_of_date:
                mOfficalActivity.setLookupScreen(PROCESS_DEMURRAGE);
                if (mTaskRemind != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcessDemurrage(), PROCESS_DEMURRAGE,
                            mTaskRemind.getProcessDemurrageType(), 0);
                }
                break;
            case R.id.doc_not_seen:
                mOfficalActivity.setLookupScreen(DOC_NOT_SEEN_TYPE);
                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
                if (mHomeLookupRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotSeen_Coming(), DOC_NOT_SEEN_TYPE, mHomeLookupRow.getDocNotSeenType_Coming(), 0);
                }
                break;
            case R.id.doc_not_process:
                mOfficalActivity.setLookupScreen(DOC_NOT_PROCESS_TYPE);
                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
                if (mHomeLookupRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotProcess_Coming(), DOC_NOT_PROCESS_TYPE, mHomeLookupRow.getDocNotProcessType_Coming(), 0);
                }
                break;
            case R.id.doc_Demurrage:
                mOfficalActivity.setLookupScreen(DOC_DEMURRAGE_TYPE);
                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
                if (mHomeLookupRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocDemurrage_Coming(), DOC_DEMURRAGE_TYPE, mHomeLookupRow.getDocDemurrageType_Coming(), 0);
                }
                break;
            case R.id.doc_not_seen_sent:
                mOfficalActivity.setLookupScreen(DOC_NOT_SEEN_TYPE);
                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_SENT);
                if (mHomeLookupRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotSeen_Sent(), DOC_NOT_SEEN_TYPE, mHomeLookupRow.getDocNotSeenType_Sent(), 0);
                }
                break;
            case R.id.doc_not_process_sent:
                mOfficalActivity.setLookupScreen(DOC_NOT_PROCESS_TYPE);
                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_SENT);
                if (mHomeLookupRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotProcess_Sent(), DOC_NOT_PROCESS_TYPE, mHomeLookupRow.getDocNotProcessType_Sent(), 0);
                }
                break;
            case R.id.doc_docDemurrage_sent:
                mOfficalActivity.setLookupScreen(DOC_DEMURRAGE_TYPE);
                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_SENT);
                if (mHomeLookupRow != null) {
                    mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocDemurrage_Sent(), DOC_DEMURRAGE_TYPE, mHomeLookupRow.getDocDemurrageType_Sent(), 0);
                }
                break;
            case R.id.test_tap:
                Intent i = new Intent();
                i.putExtra(RELOADSLIDER, "NOTIFICATION_UP_DATE");
                i.setAction(BROADCASTLISTENNER);
                mOfficalActivity.sendBroadcast(i);
                break;
            default:
                break;
        }
    }


    private long mLastClickTime = System.currentTimeMillis();

    public static HomeFragment newInstance() {
        HomeFragment myFragment = new HomeFragment();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        mHomeFmLogic = new HomeFmLogic(this, mOfficalActivity);
        mOfficalActivity.mSetting_layout.setVisibility(View.GONE);
        mOfficalActivity.setLookupScreen(HOME_SCREEN);
        mOfficalActivity.setActionBarIcon(R.drawable.menu_icon_slider);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMainTitle());
        initTabSchedule(view);
        mHomeFmLogic.getOneMonth();
        mHomeFmLogic.setMsgAfterTrainfer(getActivity().getIntent().getStringExtra(FORWARD_RESUILD), getActivity().getIntent().getStringExtra(INPUT_COME_BACK), getActivity().getIntent().getStringExtra(DOCUMENT_NUMBER), getActivity().getIntent().getStringExtra(INPUT_NAME));
        setDataForHomeScreen();
        return view;
    }

    private void initTabSchedule(View view) {
        List<String> arrDate = new ArrayList<>();
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();
        // get width screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mOfficalActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        // get all days of month
//        Calendar start = Calendar.getInstance();
//        start.set(Calendar.DAY_OF_MONTH, 1);
//        Calendar end = Calendar.getInstance();
//        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
//        end.add(Calendar.DAY_OF_YEAR, 1);
        // get 7 days
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_YEAR, 7);
        while (start.before(end)) {
            TabHost.TabSpec tab = tabHost.newTabSpec(mOfficalActivity.mSimpleDateFormat.format(start.getTime()));
            arrDate.add(mOfficalActivity.mSimpleDateFormat.format(start.getTime()));
            tab.setIndicator(createTabIndicator(start, width));
            tab.setContent(new EmptyTabFactory());
            tabHost.addTab(tab);
            start.add(Calendar.DAY_OF_MONTH, 1);
        }
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setFocusableInTouchMode(true);
        }
        final Calendar crDate = Calendar.getInstance();
        tabHost.setCurrentTabByTag(mOfficalActivity.mSimpleDateFormat.format(crDate.getTime()));
        final int defaultPosition = tabHost.getCurrentTab();
        final TextView tv = (TextView) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(R.id.tv_date_bottom);
        View redline = (View) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(R.id.red_line);
        redline.setVisibility(View.VISIBLE);
        tv.setTextColor(Color.RED);
        tabHost.getTabWidget().setStripEnabled(false);
        tabHost.getTabWidget().setDividerDrawable(null);
        mHomeFmLogic.requestSchedule(mOfficalActivity.mSimpleDateFormat.format(crDate.getTime()));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int selectedPage = tabHost.getCurrentTab();
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//                    View v = tabHost.getTabWidget().getChildAt(i);
//                    v.setBackgroundResource(R.drawable.tabs);
//                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
//                    tv.setTextColor(getResources().getColor(R.color.white));
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tv_date_bottom);
                    View redline = (View) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.red_line);
                    tv.setTextColor(selectedPage == i ? Color.RED : Color.BLACK);
                    redline.setVisibility(selectedPage == i ? View.VISIBLE : View.INVISIBLE);
                    if (selectedPage == i) {
                        tv.setTextColor(Color.WHITE);
                        tv.setBackgroundResource(R.drawable.red_circle_tabhost);
                    } else {
                        if (i == defaultPosition) {
                            tv.setTextColor(Color.RED);
                            tv.setBackgroundResource(R.drawable.white_circle_tabhost);
                        } else {
                            tv.setTextColor(Color.BLACK);
                            tv.setBackgroundResource(R.drawable.white_circle_tabhost);
                        }

                    }


//                    View tabView = tabHost.getCurrentTabView();
//                    int scrollPos = tabView.getLeft() - (mHorizontalScrollView.getWidth() - tabView.getWidth()) / 2;
//                    mHorizontalScrollView.smoothScrollTo(scrollPos, 0);
                }
                mOfficalActivity.showProgressDialog("", getActivity(), DIALOG_CENTER, true);
                mHomeFmLogic.requestSchedule(s);


            }
        });
        svParent.smoothScrollBy(0, 0);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setFocusableInTouchMode(false);
        }

    }

    private View createTabIndicator(Calendar calendar, int width) {
        View view = LayoutInflater.from(mOfficalActivity).inflate(R.layout.custom_tab, null);
        TextView tvBottom = (TextView) view.findViewById(R.id.tv_date_bottom);
        TextView tvTop = (TextView) view.findViewById(R.id.tv_date_top);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvTop.getLayoutParams();
        params.width = (width / 6);
        tvTop.setLayoutParams(params);
        tvBottom.setText(String.valueOf(calendar.get(Calendar.DATE)));
        tvTop.setText(mOfficalActivity.getNameOfDay(calendar.get(Calendar.DAY_OF_WEEK)));
        return view;
    }

    private class EmptyTabFactory implements TabHost.TabContentFactory {

        @Override
        public View createTabContent(String tag) {
            return new View(mOfficalActivity);
        }

    }


    public void setDataForHomeScreen() {
        mOfficalActivity.showProgressDialog("", getActivity(), DIALOG_CENTER, true);
        if (mOfficalActivity.isNetworkAvailable(getActivity())) {
            mHomeFmLogic.RequestData();
        } else {
            mHomeFmLogic.setDataForHomeScreenOfLine();
        }

    }


    @Override
    public void setDataForHomeScreenComing(int total, int docNotProcess, int docNotSeen, int docDemurrage) {
        tvTotalDoc.setText("Tổng " + String.valueOf(total) + " văn bản");
        tvDocNotProcess.setText(String.valueOf(docNotProcess));
        tvDocNotSeen.setText(String.valueOf(docNotSeen));
        tvDocDemurrage.setText(String.valueOf(docDemurrage));
    }

    @Override
    public void setDaySchedule(String mday) {
        tvDaySchedule.setText(mday);
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void setDataForHomeScreenSent(int total, int docNotProcess, int docNotSeen, int docDemurrage) {
        tvTotalDocSend.setText("Tổng " + String.valueOf(total) + " văn bản");
        tvDocNotProcessSend.setText(String.valueOf(docNotProcess));
        tvDocNotSeenSend.setText(String.valueOf(docNotSeen));
        tvDocDemurrageSend.setText(String.valueOf(docDemurrage));
    }

    @Override
    public void setComingTime(String msg) {
        tvComingTime.setText(msg);
    }

    @Override
    public void setSentTime(String s) {
        tvSendTime.setText(s);
    }

    @Override
    public void setTaskTime(String s) {
        tvTaskTime.setText(s);
    }

    @Override
    public void showScheduleLayout() {
        ScheduleLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideScheduleLayout() {
        ScheduleLayout.setVisibility(View.GONE);
    }

    @Override
    public void setAdapterSchedule(ScheduleAdapter adapter) {
        lvSchedule.setAdapter(adapter);
    }



    @Override
    public void showLayoutTask() {
        mLayoutTask.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLayoutTask() {
        mLayoutTask.setVisibility(View.GONE);
    }

    @Override
    public void setTaskHomeNumber(String TaskTotal, String TaskProcess, String TaskProcessOnDue, String TaskProcessNearDemurrage, String TaskProcessDemurrage, String reported) {
        tvTaskTotal.setText(TaskTotal);
        tvTaskProcessing.setText(TaskProcess);
        tvTaskInOfDate.setText(TaskProcessOnDue);
        tvTaskExpiringSoon.setText(TaskProcessNearDemurrage);
        tvTaskOutOfDate.setText(TaskProcessDemurrage);
        tvTaskComfirmComplete.setText(reported);
    }

    @Override
    public void setTaskRemind(TaskRemind TaskRemind) {
        mTaskRemind = TaskRemind;
    }

    @Override
    public void setHomeLookupRow(HomeLookupRow HomeLookupRow) {
        mHomeLookupRow = HomeLookupRow;
    }

    @Override
    public void getViewAndHotLineData(HotLineRow HotLineRow) {
        HotLineLayout.setVisibility(View.VISIBLE);
        mHotLineRow = HotLineRow;
        tvHotProcess.setText(mHotLineRow.getProcess() + "");
        tvHotProcessdemurrage.setText(mHotLineRow.getProcessDemurrage() + "");
        tvHotProcessondue.setText(mHotLineRow.getProcessOnDue() + "");
    }

    @Override
    public void setNullCreenComeBack() {
        mOfficalActivity.CSREENCOMEBACK = nULL_STRING;
    }

    @Override
    public void showSnackBar(String msgSnackBar) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), msgSnackBar, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Intent getIntentFromActivity() {
        return getActivity().getIntent();
    }

    @Override
    public void ToastError(String s) {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
    }

    @Override
    public void setAdapterScheduleDay(ScheduleDayAdapter adapter) {
        lvSchedule.setAdapter(adapter);
    }

    @Override
    public void setTaskTotalTime(String s) {
        tvTaskTimeTotal.setText(s);
    }

    @Override
    public void setRemindPersonView(int inProcess, int preLate, int late) {
        tvChuaXuLy.setText(String.valueOf(inProcess));
        tvSapQuaHan.setText(String.valueOf(preLate));
        tvQuaHan.setText(String.valueOf(late));

    }

    @Override
    public void setVisibleRecieveTable(int i) {
        lnRecieve.setVisibility(i);
        vSpaceRecieve.setVisibility(i);
    }

    @Override
    public void setVisibleSendTable(int i) {
        lnSend.setVisibility(i);
        vSpaceSend.setVisibility(i);
    }

    @Override
    public void setVisibleHotLineTable(int i) {
        HotLineLayout.setVisibility(i);
    }

    @Override
    public void setVisibleHomeTask(int i) {
        mLayoutTask.setVisibility(i);
    }

    @Override
    public void setVisibleTimeHomeTask(int i) {
        tvTaskTimeTotal.setVisibility(i);
    }

    @Override
    public void setVisibleRemind(int i) {
        lnRemind.setVisibility(i);
    }

    @Override
    public void setVisibleScheduleLayout(int i) {
        ScheduleLayout.setVisibility(i);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


//    private void initView(View view) {
//        lnHome = (LinearLayout) view.findViewById(R.id.home_layout);
//        tvTotalDoc = (TextView) view.findViewById(R.id.doc_total);
//        tvDocNotProcess = (TextView) view.findViewById(doc_not_process);
//        tvDocNotSeen = (TextView) view.findViewById(doc_not_seen);
//        tvDocDemurrage = (TextView) view.findViewById(doc_Demurrage);
//        tvTotalDocSend = (TextView) view.findViewById(R.id.doc_total_sent);
//        tvDocNotProcessSend = (TextView) view.findViewById(doc_not_process_sent);
//        tvDocDemurrageSend = (TextView) view.findViewById(doc_docDemurrage_sent);
//        tvDocNotSeenSend = (TextView) view.findViewById(doc_not_seen_sent);
//        lvSchedule = (ListView) view.findViewById(R.id.lv_schedule);
//        tvComingTime = (TextView) view.findViewById(R.id.statistical_coming_time);
//        tvSendTime = (TextView) view.findViewById(R.id.statistical_sent_time);
//        tvDaySchedule = (TextView) view.findViewById(R.id.day_schedule);
//        tvTaskComfirmComplete = (TextView) view.findViewById(R.id.task_confirm_complete);
//        tvTaskProcessing = (TextView) view.findViewById(R.id.task_processing);
//        tvTaskInOfDate = (TextView) view.findViewById(R.id.task_in_of_date);
//        tvTaskExpiringSoon = (TextView) view.findViewById(R.id.task_expiring_soon);
//        tvTaskOutOfDate = (TextView) view.findViewById(R.id.task_out_of_date);
//        ScheduleLayout = (LinearLayout) view.findViewById(R.id.schedule_layout);
//        tvTaskTotal = (TextView) view.findViewById(R.id.task_total);
//        mLayoutTask = (LinearLayout) view.findViewById(R.id.task_layout);
//        tvTaskTime = (TextView) view.findViewById(R.id.task_time);
//        tvHotProcess = (TextView) view.findViewById(R.id.hotline_process);
//        HotLineLayout = (LinearLayout) view.findViewById(R.id.hotline_layout);
//        imgTest = (ImageView) view.findViewById(R.id.test_tap);
//        imgTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.putExtra(RELOADSLIDER, "NOTIFICATION_UP_DATE");
//                i.setAction(BROADCASTLISTENNER);
//                mOfficalActivity.sendBroadcast(i);
//            }
//        });
//        tvHotProcess.setOnClickListener(this);
//        tvHotProcessdemurrage.setOnClickListener(this);
//        tvHotProcessondue.setOnClickListener(this);
//        tvTaskComfirmComplete.setOnClickListener(this);
//        tvTaskProcessing.setOnClickListener(this);
//        tvTaskInOfDate.setOnClickListener(this);
//        tvTaskExpiringSoon.setOnClickListener(this);
//        tvTaskOutOfDate.setOnClickListener(this);
//        tvDocNotSeen.setOnClickListener(this);
//        tvDocNotProcess.setOnClickListener(this);
//        tvDocDemurrage.setOnClickListener(this);
//        tvDocNotProcessSend.setOnClickListener(this);
//        tvDocDemurrageSend.setOnClickListener(this);
//        tvDocNotSeenSend.setOnClickListener(this);
//    }
//public void onClick(View v) {
//    int id = v.getId();
//    if (!mOfficalActivity.isNetworkAvailable(mOfficalActivity)) {
//        mOfficalActivity.ShowErrorToast(mOfficalActivity);
//        return;
//    }
//    //tempory solution for fast tapping
//    long now = System.currentTimeMillis();
//    if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//        return;
//    }
//    mLastClickTime = now;
//    //tempory solution for fast tapping
//    switch (id) {
//        case R.id.hotline_process:
//            mOfficalActivity.setLookupScreen(HOT_PROCESS);
//            if (mHotLineRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHotLineRow.getProcess(), HOT_PROCESS,
//                        mHotLineRow.getProcessType(), mHotLineRow.getOrganizationId());
//            }
//            break;
//        case R.id.hotline_processdemurrage:
//            mOfficalActivity.setLookupScreen(HOT_PROCESS_DEMURRAGE);
//            if (mHotLineRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHotLineRow.getProcessDemurrage(), HOT_PROCESS_DEMURRAGE,
//                        mHotLineRow.getProcessDemurrageType(), mHotLineRow.getOrganizationId());
//            }
//            break;
//        case R.id.hotline_processondue:
//            mOfficalActivity.setLookupScreen(HOT_PROCESS_ONDUE);
//            if (mHotLineRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHotLineRow.getProcessOnDue(), HOT_PROCESS_ONDUE,
//                        mHotLineRow.getProcessOnDueType(), mHotLineRow.getOrganizationId());
//            }
//            break;
//        case R.id.task_confirm_complete:
//            mOfficalActivity.setLookupScreen(TASK_REPORTED);
//            if (mTaskRemind != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getReported(), TASK_REPORTED,
//                        mTaskRemind.getReportedType(), 0);
//            }
//            break;
//        case R.id.task_processing:
//            mOfficalActivity.setLookupScreen(TASK_PROCESS);
//            if (mTaskRemind != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcess(), TASK_PROCESS,
//                        mTaskRemind.getProcessType(), 0);
//            }
//            break;
//        case R.id.task_in_of_date:
//            mOfficalActivity.setLookupScreen(PROCESS_ON_TIME);
//            if (mTaskRemind != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcessOnDue(), PROCESS_ON_TIME,
//                        mTaskRemind.getProcessOnDueType(), 0);
//            }
//            break;
//        case R.id.task_expiring_soon:
//            mOfficalActivity.setLookupScreen(TASK_PROCESS_NEAR_DEMURRAGE);
//            if (mTaskRemind != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcessNearDemurrage(), TASK_PROCESS_NEAR_DEMURRAGE,
//                        mTaskRemind.getProcessNearDemurrageType(), 0);
//            }
//            break;
//        case R.id.task_out_of_date:
//            mOfficalActivity.setLookupScreen(PROCESS_DEMURRAGE);
//            if (mTaskRemind != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mTaskRemind.getProcessDemurrage(), PROCESS_DEMURRAGE,
//                        mTaskRemind.getProcessDemurrageType(), 0);
//            }
//            break;
//        case R.id.doc_not_seen:
//            mOfficalActivity.setLookupScreen(DOC_NOT_SEEN_TYPE);
//            mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//            if (mHomeLookupRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotSeen_Coming(), DOC_NOT_SEEN_TYPE, mHomeLookupRow.getDocNotSeenType_Coming(), 0);
//            }
//            break;
//        case R.id.doc_not_process:
//            mOfficalActivity.setLookupScreen(DOC_NOT_PROCESS_TYPE);
//            mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//            if (mHomeLookupRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotProcess_Coming(), DOC_NOT_PROCESS_TYPE, mHomeLookupRow.getDocNotProcessType_Coming(), 0);
//            }
//            break;
//        case R.id.doc_Demurrage:
//            mOfficalActivity.setLookupScreen(DOC_DEMURRAGE_TYPE);
//            mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//            if (mHomeLookupRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocDemurrage_Coming(), DOC_DEMURRAGE_TYPE, mHomeLookupRow.getDocDemurrageType_Coming(), 0);
//            }
//            break;
//        case R.id.doc_not_seen_sent:
//            mOfficalActivity.setLookupScreen(DOC_NOT_SEEN_TYPE);
//            mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_SENT);
//            if (mHomeLookupRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotSeen_Sent(), DOC_NOT_SEEN_TYPE, mHomeLookupRow.getDocNotSeenType_Sent(), 0);
//            }
//            break;
//        case R.id.doc_not_process_sent:
//            mOfficalActivity.setLookupScreen(DOC_NOT_PROCESS_TYPE);
//            mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_SENT);
//            if (mHomeLookupRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocNotProcess_Sent(), DOC_NOT_PROCESS_TYPE, mHomeLookupRow.getDocNotProcessType_Sent(), 0);
//            }
//            break;
//        case R.id.doc_docDemurrage_sent:
//            mOfficalActivity.setLookupScreen(DOC_DEMURRAGE_TYPE);
//            mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_SENT);
//            if (mHomeLookupRow != null) {
//                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mHomeLookupRow.getDocDemurrage_Sent(), DOC_DEMURRAGE_TYPE, mHomeLookupRow.getDocDemurrageType_Sent(), 0);
//            }
//            break;
//        default:
//            break;
//    }
}
