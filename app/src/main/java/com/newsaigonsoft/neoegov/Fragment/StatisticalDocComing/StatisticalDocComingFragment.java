package com.newsaigonsoft.neoegov.Fragment.StatisticalDocComing;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisCMPersonAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisticalListAdapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComing;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComingList;
import com.newsaigonsoft.neoegov.GsonObject.GsonStatisPersonJoin;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.StatisticalFmRow;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.statistical_docDemurrage;
import static com.newsaigonsoft.neoegov.R.id.statistical_docNotProcess;
import static com.newsaigonsoft.neoegov.R.id.statistical_docProcessedOnTime;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CODE_STATISTICAL_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STA_LIST_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_CODE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.FORMATDATE;

/**
 * Created by Vinh on 6/12/2017.
 */

public class StatisticalDocComingFragment extends StatisticalDocComingBase implements StatisticalDocComingFmView {

    @BindView(R.id.statistical_list)
    NonScrollListView lv;
    @BindView(R.id.process_person_list)
    NonScrollListView lvPerson;
    @BindView(statistical_docDemurrage)
    TextView tvDocDemurrage;
    @BindView(R.id.statistical_total)
    TextView tvTotalDoc;
    @BindView(statistical_docNotProcess)
    TextView tvDocNotProcess;
    @BindView(statistical_docProcessedOnTime)
    TextView tvDocProcessedOnTime;
    @BindView(R.id.statistical_rateOnTime)
    TextView tvRateOnTime;
    @BindView(R.id.statistical_top_date)
    TextView tvDateTop;
    @BindView(R.id.statistical_down_date)
    TextView tvDateDown;
    @BindView(R.id.choose_any_layout)
    LinearLayout lnChooseAny;
    @BindView(R.id.choose_month_layout)
    LinearLayout lnChooseMonth;
    @BindView(R.id.begin_date)
    TextView tvBeginDate;
    @BindView(R.id.end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_choose_month)
    TextView tvMonth;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.blank_layout)
    LinearLayout lnDateBlank;
    @BindView(R.id.layout_month)
    LinearLayout lnMonth;
    @BindView(R.id.layout_quarter)
    LinearLayout lnQuarter;
    @BindView(R.id.tv_choose_quarter)
    TextView tvQuarter;
    @BindView(R.id.total_non_process)
    TextView tvTongChuaXuLy;
    @BindView(R.id.total_processed)
    TextView tvTongDaXuLy;
    @BindView(R.id.layout_department)
    LinearLayout lnDepartment;
    @BindView(R.id.layout_department_full)
    LinearLayout lnDepartmentFull;
    @BindView(R.id.layout_person_process)
    LinearLayout lnPersonProcess;
    @BindView(R.id.spinner_department)
    Spinner spnDepartment;


    @Optional
    @OnClick({R.id.statistical_total,R.id.tv_choose_quarter, R.id.tv_year, statistical_docProcessedOnTime, statistical_docNotProcess, statistical_docDemurrage, R.id.begin_date, R.id.end_date, R.id.tv_choose_month, R.id.total_non_process, R.id.total_processed})
    public void onClick(View v) {
        int id = v.getId();
        if (mOfficalActivity.isNetworkAvailable(mOfficalActivity)) {
            if (mDataBean != null) {
                switch (id) {
                    case R.id.statistical_total:
                        switch (getCodeMenu()) {
                            case CODE_STATISTICAL_COMING:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getTotal(), STA_DOC_PROCESS_ON_TIME_FULL, mDataBean.getTotalCode(), 0);
                                break;
                            case CODE_STATISTICAL_WORK_ARISE:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getTotal(), STA_DOC_PROCESS_ON_TIME_FULL_ARISE, mDataBean.getTotalCode(), 0);
                                break;
                        }
                        break;
                    // Đã xử lý tổng
                    case R.id.total_processed:
                        switch (getCodeMenu()) {
                            case CODE_STATISTICAL_COMING:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getProcessed(), STA_DOC_PROCESS_ON_TIME_FULL, mDataBean.getProcessedCode(), 0);
                                break;
                            case CODE_STATISTICAL_WORK_ARISE:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getProcessed(), STA_DOC_PROCESS_ON_TIME_FULL_ARISE, mDataBean.getProcessedCode(), 0);
                                break;
                        }
                        break;
                    // Đã xử lý trễ hạn
                    case statistical_docNotProcess:
                        switch (getCodeMenu()) {
                            case CODE_STATISTICAL_COMING:
                                mOfficalActivity.setLookupScreen(STA_DOC_NOT_PROCESS_FULL);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//                        mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mStatisticalRows.getDocNotProcess_Full(), STA_DOC_PROCESS_ON_TIME_FULL, mStatisticalRows.getDocNotProcessType_Full(), 0);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getInProcessOnTime(), STA_DOC_PROCESS_ON_TIME_FULL, mDataBean.getInProcessOnTimeCode(), 0);
                                break;
                            case CODE_STATISTICAL_WORK_ARISE:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getInProcessOnTime(), STA_DOC_PROCESS_ON_TIME_FULL_ARISE, mDataBean.getInProcessOnTimeCode(), 0);
                                break;
                        }

                        break;
                    // Chưa xử lý tổng
                    case R.id.total_non_process:
                        switch (getCodeMenu()) {
                            case CODE_STATISTICAL_COMING:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//                        mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mStatisticalRows.getDocProcessedOnTime_Full(), STA_DOC_PROCESS_ON_TIME_FULL, mStatisticalRows.getDocProcessedOnTimeType_Full(), 0);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getInProcess(), STA_DOC_PROCESS_ON_TIME_FULL, mDataBean.getInProcessCode(), 0);
                                break;
                            case CODE_STATISTICAL_WORK_ARISE:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getInProcess(), STA_DOC_PROCESS_ON_TIME_FULL_ARISE, mDataBean.getInProcessCode(), 0);
                                break;
                        }
                        break;
                    // Chưa xử lý quán hạn
                    case statistical_docDemurrage:
                        switch (getCodeMenu()) {
                            case CODE_STATISTICAL_COMING:
                                mOfficalActivity.setLookupScreen(STA_DOC_DEMURRAGE_FULL);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//                        mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mStatisticalRows.getDocDemurrage_Full(), STA_DOC_PROCESS_ON_TIME_FULL, mStatisticalRows.getDocDemurrageType_Full(), 0);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getInProcessLate(), STA_DOC_PROCESS_ON_TIME_FULL, mDataBean.getInProcessLateCode(), 0);
                                break;
                            case CODE_STATISTICAL_WORK_ARISE:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getInProcessLate(), STA_DOC_PROCESS_ON_TIME_FULL_ARISE, mDataBean.getInProcessLateCode(), 0);
                                break;
                        }
                        break;
                    case statistical_docProcessedOnTime:
                        switch (getCodeMenu()) {
                            case CODE_STATISTICAL_COMING:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
//                        mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mStatisticalRows.getDocProcessedOnTime_Full(), STA_DOC_PROCESS_ON_TIME_FULL, mStatisticalRows.getDocProcessedOnTimeType_Full(), 0);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getProcessedOnTime(), STA_DOC_PROCESS_ON_TIME_FULL, mDataBean.getProcessedOnTimeCode(), 0);
                                break;
                            case CODE_STATISTICAL_WORK_ARISE:
                                mOfficalActivity.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                                mOfficalActivity.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
                                mOfficalActivity.mOfficeLogic.eventGetHomeListDocument(mDataBean.getProcessedOnTime(), STA_DOC_PROCESS_ON_TIME_FULL_ARISE, mDataBean.getProcessedOnTimeCode(), 0);
                                break;
                        }
                        break;
                    case R.id.begin_date:
                        showTimeDialog(tvBeginDate, mOfficalActivity);
                        break;
                    case R.id.end_date:
                        showTimeDialog(tvEndDate, mOfficalActivity);
                        break;
                    case R.id.tv_choose_month:
                        showDialogSelect(tvMonth, mOfficalActivity.getString(R.string.month));
                        break;
                    case R.id.tv_year:
                        showDialogSelect(tvYear, mOfficalActivity.getString(R.string.year));
                        break;
                    case R.id.tv_choose_quarter:
                        showDialogSelect(tvYear, mOfficalActivity.getString(R.string.quarter));
                        break;
                    default:
                        break;
                }
            } else {
                mOfficalActivity.ShowErrorToast(mOfficalActivity, "Vui lòng tải lại trang");
            }
        } else {
            mOfficalActivity.ShowErrorToast(mOfficalActivity, "Không có kết nối");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        mStatisticalFmLogic = new StatisticalDocComingFmLogic(this, mOfficalActivity);
        initTab(view);
        setTimeInit();
        mStatisticalFmLogic.requestDPMList();
        beginActivites(mOfficalActivity.getString(R.string.month));
        mOfficalActivity.setLookupScreen(CODE_STATISTICAL_DEPARTMENT);
        mOfficalActivity.setActionBarIcon(R.drawable.bar_back_x1);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        spnDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!arrListDepartmentSearch.get(position).getReceiveRoomID().equals(nULL_STRING)) {
                    ReceiveRoomID = arrListDepartmentSearch.get(position).getReceiveRoomID();
                    Log.d(KeyManager.TAG, String.valueOf(ReceiveRoomID));
                } else {
                    ReceiveRoomID = "0";
                }
                mOfficalActivity.setOganizationID(Long.parseLong(ReceiveRoomID));
                mStatisticalFmLogic.requestPersonList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }


    public void showTimeDialog(TextView textView, Context context) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(calendar.getTime()));
        mOfficalActivity.dialogBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = mOfficalActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_time_choose, null);
        // title
        TextView tvCYear = (TextView) dialogView.findViewById(R.id.current_year);
        tvCYear.setText(String.valueOf(year));
        TextView tvCDate = (TextView) dialogView.findViewById(R.id.current_date);
        tvCDate.setText(mOfficalActivity.getNameOfDay(calendar.get(Calendar.DAY_OF_WEEK)) + ", " + day + " Tháng " + month);
        // Dismiss button
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.cancel);
        tvCancel.setOnClickListener(v -> mOfficalActivity.alertDialog.dismiss());
        /*com.applandeo.materialcalendarview.CalendarView*/
//        CalendarView calendarView = (CalendarView) dialogView.findViewById(R.id.calendarView);
        // set event
//        List<EventDay> events = new ArrayList<>();
//        events.add(new EventDay(calendar, R.drawable.sample_icon_3));
//        calendarView.setEvents(events);
        // show current month
//        calendarView.showCurrentMonthPage();
        //show dialog
        /* com.prolificinteractive.materialcalendarview.MaterialCalendarView*/
        mOfficalActivity.widget = (MaterialCalendarView) dialogView.findViewById(R.id.calendarView);
        mOfficalActivity.widget.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        mOfficalActivity.widget.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        mOfficalActivity.widget.setSelectedDate(calendar);
        // ok button
        TextView tvOk = (TextView) dialogView.findViewById(R.id.ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDay day = mOfficalActivity.widget.getSelectedDate();
                textView.setText(day.getDay() + "/" + Integer.parseInt(new SimpleDateFormat("MM").format(day.getCalendar().getTime())) + "/" + day.getYear());
                RequestAnyTime();
                mOfficalActivity.alertDialog.dismiss();
            }
        });
        mOfficalActivity.dialogBuilder.setView(dialogView);
        mOfficalActivity.alertDialog = mOfficalActivity.dialogBuilder.create();
        mOfficalActivity.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mOfficalActivity.alertDialog.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mOfficalActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        mOfficalActivity.alertDialog.getWindow().setLayout((width / 6) * 5, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    private void showDialogSelect(TextView tv, String tabName) {
        Calendar mCalendar = Calendar.getInstance();
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mOfficalActivity);
        LayoutInflater inflater = mOfficalActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_select_month, null);
        List<String> arrMonth = new ArrayList<>();
        DialogSelect dialogHolder = new DialogSelect(mOfficalActivity, dialogView, arrMonth, tv);
        switch (tabName) {
            case "Tháng":
                dialogHolder.tvTitle.setText("Chọn tháng");
                for (int i = 1; i <= 12; i++) {
                    arrMonth.add("Tháng " + i);
                }
                break;
            case "Năm":
                dialogHolder.tvTitle.setText("Chọn năm");
                for (int i = mCalendar.get(Calendar.YEAR) - 50; i < mCalendar.get(Calendar.YEAR) + 50; i++) {
                    arrMonth.add("Năm " + i);
                }
                break;
            case "Quý":
                dialogHolder.tvTitle.setText("Chọn quý");
                int i = 1;
                while (i <= 10) {
                    switch (i) {
                        case 1:
                            arrMonth.add(mOfficalActivity.getString(R.string.quarter) + " 1");
                            break;
                        case 4:
                            arrMonth.add(mOfficalActivity.getString(R.string.quarter) + " 2");
                            break;
                        case 7:
                            arrMonth.add(mOfficalActivity.getString(R.string.quarter) + " 3");
                            break;
                        case 10:
                            arrMonth.add(mOfficalActivity.getString(R.string.quarter) + " 4");
                            break;
                    }
                    i = i + 3;
                }
                break;
            case "Thời gian bất kỳ":
                break;
            default:
                break;

        }
        dialogHolder.lvMonth.setAdapter(new ArrayAdapter<String>(mOfficalActivity, android.R.layout.simple_list_item_1, arrMonth));
        dialogBuilder.setView(dialogView);
        mOfficalActivity.alertDialog = dialogBuilder.create();
        mOfficalActivity.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mOfficalActivity.alertDialog.show();
        if (!tabName.equals("Tháng")) {
            indexYear = arrMonth.indexOf("Năm " + mCalendar.get(Calendar.YEAR));
            if (dialogHolder.lvMonth.getFirstVisiblePosition() > indexYear || dialogHolder.lvMonth.getLastVisiblePosition() < indexYear)
                dialogHolder.lvMonth.setSelection(indexYear);
        }
    }

    class DialogSelect {
        Context context;
        List<String> arrMonth;
        TextView tvTime;

        public DialogSelect(Context context, View view, List<String> arrmonth, TextView textView) {
            this.context = context;
            this.arrMonth = arrmonth;
            tvTime = textView;
            ButterKnife.bind(this, view);
        }

        @BindView(R.id.lv_select_month)
        ListView lvMonth;
        @BindView(R.id.cancel_select)
        ImageView imgCancel;
        @BindView(R.id.title)
        TextView tvTitle;

        @Optional
        @OnClick({R.id.cancel_select})
        void OnClick(View view) {
            switch (view.getId()) {
                case R.id.cancel_select:
                    mOfficalActivity.alertDialog.dismiss();
                    break;
                default:
                    break;
            }
        }

        @OnItemClick({R.id.lv_select_month})
        void OnItemClick(AdapterView<?> parent, View view, int position, long id) {
            int s = tabHost.getCurrentTab();
            switch (s) {
                case 0:
                    if (arrMonth.get(position).contains("Năm")) {
                        tvYear.setText(arrMonth.get(position));
                    } else {
                        tvMonth.setText(arrMonth.get(position));
                    }
                    beginActivites("Tháng");
                    break;
                case 1:
                    if (arrMonth.get(position).contains("Năm")) {
                        tvYear.setText(arrMonth.get(position));
                    } else {
                        tvQuarter.setText(arrMonth.get(position));
                    }
                    beginActivites("Quý");
                    break;
                case 2:
                    String year = arrMonth.get(position);
                    tvYear.setText(year);
                    beginActivites("Năm");
                    break;
                default:
                    beginActivites("Thời gian bất kì");
                    break;
            }
            mOfficalActivity.alertDialog.dismiss();
        }
    }


    public static StatisticalDocComingFragment newInstance() {
        StatisticalDocComingFragment myFragment = new StatisticalDocComingFragment();
        return myFragment;
    }


    private void setTimeInit() {
        tvYear.setText("Năm " + Calendar.getInstance().get(Calendar.YEAR));
        tvMonth.setText("Tháng " + Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime())));
        setAnyDate();
        switch (Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()))) {
            case 1:
            case 2:
            case 3:
                tvQuarter.setText(mOfficalActivity.getString(R.string.quarter) + " 1");
                break;
            case 4:
            case 5:
            case 6:
                tvQuarter.setText(mOfficalActivity.getString(R.string.quarter) + " 2");
                break;
            case 7:
            case 8:
            case 9:
                tvQuarter.setText(mOfficalActivity.getString(R.string.quarter) + " 3");
                break;
            default:
                tvQuarter.setText(mOfficalActivity.getString(R.string.quarter) + " 4");
                break;
        }
        startQuarter = mOfficalActivity.mSimpleDateFormat.format(mOfficalActivity.getFirstDayOfQuarter(Calendar.getInstance().getTime()));
        endQuarter = mOfficalActivity.mSimpleDateFormat.format(mOfficalActivity.getLastDayOfQuarter(Calendar.getInstance().getTime()));
    }

    //         get 1 month add start
    private void setAnyDate() {
        Calendar mCalendar = Calendar.getInstance();
        String dateLast = new SimpleDateFormat(FORMATDATE).format(mCalendar.getTime()) + "";
        Calendar mCalendarFirstDay = Calendar.getInstance();
        mCalendarFirstDay.add(Calendar.MONTH, -1);
        String dateFirst = mOfficalActivity.mSimpleDateFormat.format(mCalendarFirstDay.getTime()) + "";
        Log.d(TAG, dateLast + " " + dateFirst);
        tvBeginDate.setText(dateFirst);
        tvEndDate.setText(dateLast);
    }
    //         get 1 month add start

    private void initTab(View view) {
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();
        mTabName = new ArrayList<>();
        mTabName.add(mOfficalActivity.getString(R.string.month));
        mTabName.add(mOfficalActivity.getString(R.string.quarter));
        mTabName.add(mOfficalActivity.getString(R.string.year));
        mTabName.add(mOfficalActivity.getString(R.string.anytime));
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
                beginActivites(s);
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    View vlBottom = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.bottom_line);
                    vlBottom.setBackgroundColor(Color.parseColor(i == selectedPage ? "#ffff4444" : "#00FFFFFF"));
                    TextView tvTitle = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tv_time_select);
                    tvTitle.setTextColor(Color.parseColor(i == selectedPage ? "#ffff4444" : "#777777"));
                }
            }
        });
    }


    private void beginActivites(String s) {
        ChooseTimeVisible(s);
        switch (s) {
            case "Tháng":
                RequestMonth();
                break;
            case "Quý":
                RequestQuarter();
                break;
            case "Năm":
                RequestYear();
                break;
            case "Thời gian bất kỳ":
                RequestAnyTime();
                break;
            default:
                break;
        }
    }

    private void RequestAnyTime() {
        mStatisticalFmLogic.Request(tvBeginDate.getText().toString(), tvEndDate.getText().toString());
    }

    private void RequestYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvYear.getText().toString())));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        String startYear = mOfficalActivity.mSimpleDateFormat.format(cal.getTime());
//set date to last day of 2014
        cal.set(Calendar.YEAR, Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvYear.getText().toString())));
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31);
        String endYear = mOfficalActivity.mSimpleDateFormat.format(cal.getTime());
        Log.d(TAG, startYear + endYear);
        mStatisticalFmLogic.Request(startYear, endYear);
    }

    private void RequestQuarter() {
        Calendar quarter = Calendar.getInstance();
        Date date = null;
        switch (Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvQuarter.getText().toString()))) {
            case 1:
                quarter.set(Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvYear.getText().toString())), 1, 1);
                date = quarter.getTime();
                break;
            case 2:
                quarter.set(Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvYear.getText().toString())), 4, 1);
                date = quarter.getTime();
                break;
            case 3:
                quarter.set(Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvYear.getText().toString())), 7, 1);
                date = quarter.getTime();
                break;
            case 4:
                quarter.set(Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvYear.getText().toString())), 10, 1);
                date = quarter.getTime();
                break;
            default:
                break;

        }
        startQuarter = mOfficalActivity.mSimpleDateFormat.format(mOfficalActivity.getFirstDayOfQuarter(date));
        endQuarter = mOfficalActivity.mSimpleDateFormat.format(mOfficalActivity.getLastDayOfQuarter(date));
        mStatisticalFmLogic.Request(startQuarter, endQuarter);
    }

    private void RequestMonth() {
        String finalStartDate = "01/" + (mOfficalActivity.getOnlyNumerics(tvMonth.getText().toString()).length() == 1 ? ("0" + mOfficalActivity.getOnlyNumerics(tvMonth.getText().toString())) : mOfficalActivity.getOnlyNumerics(tvMonth.getText().toString())) + "/" + mOfficalActivity.getOnlyNumerics(tvYear.getText().toString());
        // next month add start
        int month = Integer.parseInt(mOfficalActivity.getOnlyNumerics(tvMonth.getText().toString())) + 1;
        month = (month == 13) ? 1 : month;
        String strMonth = String.valueOf(month).length() == 1 ? ("0" + month) : String.valueOf(month);
        String finalEndDate = "01/" + strMonth + "/" + mOfficalActivity.getOnlyNumerics(tvYear.getText().toString());
        // next month add end
        mStatisticalFmLogic.Request(finalStartDate, finalEndDate);
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

    @Override
    public void showProgress() {
        mOfficalActivity.showProgressDialog("", getActivity(), DIALOG_CENTER, true);
    }

    @Override
    public void setViewStatistical(GsonStaComing.DataBean mBean, String dateFirst, String dateLast) {
        tvTotalDoc.setText(String.valueOf(mBean.getTotal()));
        tvRateOnTime.setText(String.valueOf(mBean.getRateOnTime()) + "%");
        tvTongChuaXuLy.setText(String.valueOf(mBean.getInProcess()));
        tvTongDaXuLy.setText(String.valueOf(mBean.getProcessed()));
        tvDocDemurrage.setText(String.valueOf(mBean.getInProcessLate()));
        tvDocNotProcess.setText(String.valueOf(mBean.getInProcessOnTime()));
        tvDocProcessedOnTime.setText(String.valueOf(mBean.getProcessedOnTime()));
        mOfficalActivity.setDateFirst(dateFirst);
        mOfficalActivity.setDateLast(dateLast);
        mDataBean = mBean;
        showLayoutFull(false);
    }


    @Override
    public void setListDepartment(List<GsonStaComingList.DataBean> arr, String dateFirstX, String dateLastX) {
        switch (getCodeMenu()) {
            case CODE_STATISTICAL_COMING:
                adapter = new StatisticalListAdapter(mOfficalActivity, arr, mOfficalActivity.mOfficeLogic, LIST_DOCUMENT_DEPARTMENT);
                break;
            case CODE_STATISTICAL_WORK_ARISE:
                adapter = new StatisticalListAdapter(mOfficalActivity, arr, mOfficalActivity.mOfficeLogic, STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                break;
            default:
                break;
        }
        lv.setAdapter(adapter);
        tvDateDown.setText("(" + dateFirstX + " - " + dateLastX + ")");
        mOfficalActivity.setDateFirst(dateFirstX);
        mOfficalActivity.setDateLast(dateLastX);
        showLayoutDepartment(false);
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void setStatisticalRow(StatisticalFmRow StatisticalRows) {
        mStatisticalRows = StatisticalRows;
    }

    @Override
    public void ToastError(String s) {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
        Log.d(TAG, s);
    }

    @Override
    public void setListPerson(List<GsonStatisPersonJoin.DataBean> arr) {
        switch (getCodeMenu()) {
            case CODE_STATISTICAL_COMING:
                lvPerson.setAdapter(new StatisCMPersonAdapter(mOfficalActivity, arr, mOfficalActivity.mOfficeLogic, LIST_DOCUMENT_DEPARTMENT));
                break;
            case CODE_STATISTICAL_WORK_ARISE:
                lvPerson.setAdapter(new StatisCMPersonAdapter(mOfficalActivity, arr, mOfficalActivity.mOfficeLogic, STA_DOC_PROCESS_ON_TIME_FULL_ARISE));
                break;
            default:
                break;
        }
    }

    @Override
    public void showLayoutDepartment(boolean b) {
        lnDepartment.setVisibility(b ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showLayoutFull(boolean b) {
        lnDepartmentFull.setVisibility(b ? View.GONE : View.VISIBLE);
    }

    @Override
    public void hideLayoutPersonJoin(boolean b) {
        lnPersonProcess.setVisibility(b ? View.GONE : View.VISIBLE);
    }

    @Override
    public String getCodeMenu() {
        return getArguments().getString(TAP_CODE);
    }

    @Override
    public void getSrrListDepartmentSearch(ArrayList<ReceivePerson> arr) {
        arrListDepartmentSearch = arr;
    }

    @Override
    public void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList<String> arrNameDepartment) {
        spnDepartment.setAdapter(adapter);
    }

    @Override
    public void showErrorToast() {
        mOfficalActivity.ShowErrorToast(mOfficalActivity, "Không tải được danh sách phòng ban");
    }

    @Override
    public String getOrganizationId() {
        return ReceiveRoomID;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemCenterMenu = menu.findItem(R.id.center_menu);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void ChooseTimeVisible(String s) {
        switch (s) {
            case "Tháng":
                lnChooseMonth.setVisibility(View.VISIBLE);
                lnChooseAny.setVisibility(View.GONE);
                lnMonth.setVisibility(View.VISIBLE);
                lnDateBlank.setVisibility(View.GONE);
                lnQuarter.setVisibility(View.GONE);
                break;
            case "Thời gian bất kỳ":
                lnChooseAny.setVisibility(View.VISIBLE);
                lnChooseMonth.setVisibility(View.GONE);
                break;
            case "Năm":
                lnChooseMonth.setVisibility(View.VISIBLE);
                lnChooseAny.setVisibility(View.GONE);
                lnDateBlank.setVisibility(View.VISIBLE);
                lnMonth.setVisibility(View.GONE);
                lnQuarter.setVisibility(View.GONE);
                break;
            case "Quý":
                lnChooseMonth.setVisibility(View.VISIBLE);
                lnChooseAny.setVisibility(View.GONE);
                lnDateBlank.setVisibility(View.GONE);
                lnMonth.setVisibility(View.GONE);
                lnQuarter.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

//    class StatisticalDPMFm extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String mRequest = addJsonRequestStatistical(TYPE_STATISTICAL_DEPARTMENT);
////            return mOfficalActivity.makePostRequest(params[0], mRequest, mOfficalActivity.getUserid(), mOfficalActivity.getPass());
//            return mOfficalActivity.eventPostRequest(mOfficalActivity.getModuleInfor(MODULE_LOOKUP_DOCUMENT,mOfficalActivity), mRequest, mOfficalActivity.getUserid(), mOfficalActivity.getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            rowsArrayList = new ArrayList<StatisticalRows>();
//            Log.d(TAG, s);
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mObject = mJsonArray.getJSONObject(i);
//                    String organizationName = mObject.getString(ORGANIZATION_NAME);
//                    int total = mObject.getInt(DOC_TOTAL);
//                    int docDemurrage = mObject.getInt(DOC_DEMURRAGE);
//                    int docNotProcessType = mObject.getInt(DOC_NOT_PROCESS_TYPE);
//                    int docProcessedOnTimeType = mObject.getInt(DOC_PROSESSED_ON_TIME_TYPE);
//                    int docDemurrageType = mObject.getInt(DOC_DEMURRAGE_TYPE);
//                    int totalDocType = mObject.getInt(TOTAL_DOC_TYPE);
//                    long organizationId = mObject.getLong(ORGANIZATION_ID);
//                    rowsArrayList.add(new StatisticalRows(organizationName, total, docDemurrage,docNotProcessType,docProcessedOnTimeType,
//                            docDemurrageType, totalDocType, organizationId ));
//                }
//                StatisticalListAdapter adapter = new StatisticalListAdapter(mOfficalActivity, rowsArrayList, mOfficalActivity);
//                lv.setAdapter(adapter);
//                mOfficalActivity.setListViewHeightBasedOnChildren(lv);
//                tvDateDown.setText("(" + dateFirst + " - " + dateLast + ")");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            mOfficalActivity.closeProgressDialog();
//            super.onPostExecute(s);
//        }
//    }

//    private String addJsonRequestStatistical(String TYPE) {
//        JSONObject mJsonObject = new JSONObject();
//        // get 1 month add start
//        Calendar mCalendar = Calendar.getInstance();
//        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
//        dateLast = mSimpleDateFormat.format(mCalendar.getTime()) + "";
//        Calendar mCalendarFirstDay = Calendar.getInstance();
//        mCalendarFirstDay.add(Calendar.MONTH, -1);
//        dateFirst = mSimpleDateFormat.format(mCalendarFirstDay.getTime()) + "";
//        Log.d(TAG, dateLast + " " + dateFirst);
//        // get 1 month add start
//        try {
//            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//            mJsonObject.put(NEOTYPE, TYPE);
//            JSONObject mJsonObjectData = new JSONObject();
//            mJsonObjectData.put(FROM_DATE, mOfficalActivity.getMilisecondDay(dateFirst));
//            mJsonObjectData.put(TO_DATE, mOfficalActivity.getMilisecondDay(dateLast));
//            mJsonObject.put(DATA, mJsonObjectData.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mJsonObject.toString();
//    }
//
//    private void initView(View view) {
//        lv = (ListView) view.findViewById(R.id.statistical_list);
//        tvDocDemurrage = (TextView) view.findViewById(statistical_docDemurrage);
//        tvTotalDoc = (TextView) view.findViewById(R.id.statistical_total);
//        tvDocNotProcess = (TextView) view.findViewById(statistical_docNotProcess);
//        tvDocProcessedOnTime = (TextView) view.findViewById(statistical_docProcessedOnTime);
//        tvRateOnTime = (TextView) view.findViewById(R.id.statistical_rateOnTime);
//        tvDateTop = (TextView) view.findViewById(R.id.statistical_top_date);
//        tvDateDown = (TextView) view.findViewById(R.id.statistical_down_date);
//        tvDocProcessedOnTime.setOnClickListener(this);
//        tvDocNotProcess.setOnClickListener(this);
//        tvDocDemurrage.setOnClickListener(this);
//    }
}
