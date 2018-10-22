package com.newsaigonsoft.neoegov.Fragment.StatisticalDPM;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Fragment.StatisticalDocComing.StatisticalDocComingFragment;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMRow;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CODE_STATISTICAL_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_IN_DUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_UN_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 11/24/2017.
 */

public class StatisticalDPMFm extends StatisticalDPMBase implements StatisticalDPMView {
    // department view
    @BindView(R.id.department_processed)
    TextView tvDpmProcessed;
    @BindView(R.id.department_unprocess)
    TextView tvDpmUnprocess;
    @BindView(R.id.department_process_ontime)
    TextView tvDpmProcessOnTime;
    @BindView(R.id.department_process_delays)
    TextView tvDpmProcessDelays;
    @BindView(R.id.department_process_indue)
    TextView tvDpmProcessIndue;
    @BindView(R.id.department_out_of_date)
    TextView tvDpmProcessOutOfDate;
    @BindView(R.id.department_process_total)
    TextView tvDpmProcessTotal;
    // person view
    @BindView(R.id.person_processed)
    TextView tvPsProcessed;
    @BindView(R.id.person_unprocess)
    TextView tvPsUnProcess;
    @BindView(R.id.person_process_ontime)
    TextView tvPsProcessOnTime;
    @BindView(R.id.person_process_delays)
    TextView tvPsProcessDelays;
    @BindView(R.id.person_process_indue)
    TextView tvPsProcessIndue;
    @BindView(R.id.person_out_of_date)
    TextView tvPsProcessOutOfDate;
    @BindView(R.id.start_date)
    TextView tvStartDate;
    @BindView(R.id.end_date)
    TextView tvEndDate;
    @BindView(R.id.layout_left_date)
    LinearLayout lnDateLeft;
    @BindView(R.id.layout_date_right)
    LinearLayout lnDateRight;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    private long mLastClickTime = System.currentTimeMillis();

    @Optional
    @OnClick({R.id.layout_left_date, R.id.layout_date_right,
            R.id.department_process_total, R.id.department_processed,
            R.id.department_unprocess, R.id.department_process_ontime,
            R.id.department_process_delays, R.id.department_process_indue,
            R.id.department_out_of_date, R.id.person_processed,
            R.id.person_unprocess, R.id.person_process_ontime,
            R.id.person_process_delays, R.id.person_process_indue,
            R.id.person_out_of_date
    })
    public void onClick(View view) {
        long now = System.currentTimeMillis();
        if (now - mLastClickTime < 500) {
            return;
        }
        mLastClickTime = now;
        mOfficalActivity.setStatisStartDate(getStartTime());
        mOfficalActivity.setStatisEndDate(getEndTime());
        if (!mOfficalActivity.isNetworkAvailable(getActivity())) {
            mOfficalActivity.ShowErrorToast(getActivity());
            return;
        }
        if (mOfficalActivity.getStatisticalDPMRow() == null) {
            mOfficalActivity.ShowErrorToast(getActivity());
            return;
        }
        switch (view.getId()) {
            case R.id.layout_left_date:
                showTimeDialog(tvStartDate, tvEndDate, mOfficalActivity, true, tvStartDate.getText().toString());
//                showDatePickDialogSmallerThan(tvStartDate, tvEndDate, true, tvStartDate.getText().toString());
                break;
            case R.id.layout_date_right:
                showTimeDialog(tvStartDate, tvEndDate, mOfficalActivity, false, tvEndDate.getText().toString());
//                showDatePickDialogSmallerThan(tvStartDate, tvEndDate, false, tvEndDate.getText().toString());
                break;
            case R.id.department_process_total:
                mOfficalActivity.changeUIStatisticalDepartmentTotal();
                break;
            case R.id.department_processed:
                mOfficalActivity.changeUIStatisticalDpmList(DPM_PROCESS);
                break;
            case R.id.department_unprocess:
                mOfficalActivity.changeUIStatisticalDpmList(DPM_UN_PROCESS);
                break;
            case R.id.department_process_ontime:
                mOfficalActivity.changeUIStatisticalDpmList(DPM_ON_TIME);
                break;
            case R.id.department_process_delays:
                mOfficalActivity.changeUIStatisticalDpmList(DPM_DELAYS);
                break;
            case R.id.department_process_indue:
                mOfficalActivity.changeUIStatisticalDpmList(DPM_IN_DUE);
                break;
            case R.id.department_out_of_date:
                mOfficalActivity.changeUIStatisticalDpmList(DPM_OUT_OF_DATE);
                break;
            case R.id.person_processed:
            case R.id.person_unprocess:
            case R.id.person_process_ontime:
            case R.id.person_process_delays:
            case R.id.person_process_indue:
            case R.id.person_out_of_date:
                break;
            default:
                break;

        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical_department, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        progressBar.setIndeterminateDrawable(mOfficalActivity.getResources().getDrawable(R.drawable.progress_bar_360_to_0));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Drawable drawable = getResources().getDrawable(R.drawable.progress_bar_0_to_0);
                Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
                Rect bounds = indeterminateDrawable.getBounds();
                drawable.setBounds(bounds);
                progressBar.setIndeterminateDrawable(drawable);
            }
        }, 800);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        mOfficalActivity.setActionBarIcon(R.drawable.bar_back_x1);
        mOfficalActivity.setLookupScreen(CODE_STATISTICAL_DEPARTMENT);
        mStatisticalDPMLogic = new StatisticalDPMLogic(mOfficalActivity, this);
        setDateToDate(tvStartDate, tvEndDate);
        mStatisticalDPMLogic.RequestStatisticalDPM(tvStartDate.getText().toString(), tvEndDate.getText().toString());
        return view;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void setDateToDate(TextView start, TextView end) {
        if (mOfficalActivity.getStatisStartDate() != null && mOfficalActivity.getStatisEndDate() != null) {
            start.setText(mOfficalActivity.getStatisStartDate());
            end.setText(mOfficalActivity.getStatisEndDate());
        } else {
            Calendar mCalendar = Calendar.getInstance();
            start.setText("01/01/" + mCalendar.get(Calendar.YEAR));
            end.setText(mSimpleDateFormat.format(mCalendar.getTime()) + "");
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemCenterMenu = menu.findItem(R.id.center_menu);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void setStatistiCalView(StatisticalDPMRow mStatisticalDPMRow) {
        tvDpmProcessTotal.setText(String.valueOf(mStatisticalDPMRow.getTotal()));
        tvDpmProcessed.setText(String.valueOf(mStatisticalDPMRow.getProcessed()));
        tvDpmUnprocess.setText(String.valueOf(mStatisticalDPMRow.getProcess()));
        tvDpmProcessOnTime.setText(String.valueOf(mStatisticalDPMRow.getProcessedOnTime()));
        tvDpmProcessDelays.setText(String.valueOf(mStatisticalDPMRow.getProcessedDemurrage()));
        tvDpmProcessIndue.setText(String.valueOf(mStatisticalDPMRow.getProcessOnTime()));
        tvDpmProcessOutOfDate.setText(String.valueOf(mStatisticalDPMRow.getProcessDemurrage()));
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void showProgress() {
        mOfficalActivity.showProgressDialog(nULL_STRING, mOfficalActivity, DIALOG_CENTER, false);
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public String getStartTime() {
        return tvStartDate.getText().toString();
    }

    @Override
    public String getEndTime() {
        return tvEndDate.getText().toString();
    }

    @Override
    public void showToastError() {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
    }

    @Override
    public void mStatisticalDPMRow(StatisticalDPMRow mStatisticalDPMRow) {
        mOfficalActivity.setStatisticalDPMRow(mStatisticalDPMRow);
    }

    @Override
    public void ToastError(String s) {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
        Log.d(TAG, s);
    }


    public void showDatePickDialogSmallerThan(final TextView StartDate, final TextView EndDate, final boolean isLeft, final String dateBefore) {
        final Calendar mCalendar = Calendar.getInstance();
        if (mOfficalActivity.mDatePickerDialog == null) {
            mOfficalActivity.mDatePickerDialog = new DatePickerDialog(mOfficalActivity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCalendar.set(year, month, dayOfMonth);
                    if (isLeft) {
                        StartDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                    } else {
                        EndDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                    }
                    if (mOfficalActivity.getMilisecondDay(StartDate.getText().toString()) > mOfficalActivity.getMilisecondDay(EndDate.getText().toString())) {
                        mOfficalActivity.mDatePickerDialog = null;
                        mOfficalActivity.ShowErrorToast(mOfficalActivity, "Ngày bắt đầu không nhỏ hơn ngày kết thúc");
                        if (isLeft) {
                            StartDate.setText(dateBefore);
                        } else {
                            EndDate.setText(dateBefore);
                        }
                        return;
                    } else {
                        mOfficalActivity.mDatePickerDialog = null;
                    }
                    mStatisticalDPMLogic.RequestStatisticalDPM(StartDate.getText().toString(), EndDate.getText().toString());

                }
            }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            mOfficalActivity.mDatePickerDialog.show();
            mOfficalActivity.mDatePickerDialog.setCanceledOnTouchOutside(false);
        }
        mOfficalActivity.mDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    // Do Stuff
                    mOfficalActivity.mDatePickerDialog = null;
                }
            }
        });
        mOfficalActivity.mDatePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mOfficalActivity.mDatePickerDialog = null;
            }
        });
    }




    private void showTimeDialog(TextView tvStart, TextView tvEnd, Context context, boolean isLeft, String datebefore) {
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
                if (isLeft){
                    tvStart.setText(day.getDay() + "/" + Integer.parseInt(new SimpleDateFormat("MM").format(day.getCalendar().getTime())) + "/" + day.getYear());
                }else {
                    tvEnd.setText(day.getDay() + "/" + Integer.parseInt(new SimpleDateFormat("MM").format(day.getCalendar().getTime())) + "/" + day.getYear());
                }
                if (mOfficalActivity.getMilisecondDay(tvStart.getText().toString()) > mOfficalActivity.getMilisecondDay(tvEnd.getText().toString())){
                    mOfficalActivity.ShowErrorToast(mOfficalActivity, "Ngày bắt đầu không nhỏ hơn ngày kết thúc");
                    if (isLeft) {
                        tvStart.setText(datebefore);
                    } else {
                        tvEnd.setText(datebefore);
                    }
                    return;
                }
                mStatisticalDPMLogic.RequestStatisticalDPM(tvStart.getText().toString(), tvEnd.getText().toString());
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
//        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
    }


//    private void initView(View view) {
//        // department view
//        tvDpmProcessTotal = (TextView) view.findViewById(R.id.department_process_total);
//        tvDpmProcessed = (TextView) view.findViewById(R.id.department_processed);
//        tvDpmUnprocess = (TextView) view.findViewById(R.id.department_unprocess);
//        tvDpmProcessOnTime = (TextView) view.findViewById(R.id.department_process_ontime);
//        tvDpmProcessDelays = (TextView) view.findViewById(R.id.department_process_delays);
//        tvDpmProcessIndue = (TextView) view.findViewById(R.id.department_process_indue);
//        tvDpmProcessOutOfDate = (TextView) view.findViewById(R.id.department_out_of_date);
//        lnDateLeft = (LinearLayout) view.findViewById(R.id.layout_left_date);
//        lnDateRight = (LinearLayout) view.findViewById(R.id.layout_date_right);
//        // person view
//        tvPsProcessed = (TextView) view.findViewById(R.id.person_processed);
//        tvPsUnProcess = (TextView) view.findViewById(R.id.person_unprocess);
//        tvPsProcessOnTime = (TextView) view.findViewById(R.id.person_process_ontime);
//        tvPsProcessDelays = (TextView) view.findViewById(R.id.person_process_delays);
//        tvPsProcessIndue = (TextView) view.findViewById(R.id.person_process_indue);
//        tvPsProcessOutOfDate = (TextView) view.findViewById(R.id.person_out_of_date);
//        tvStartDate = (TextView) view.findViewById(R.id.start_date);
//        tvEndDate = (TextView) view.findViewById(R.id.end_date);
//        //set onclick
//        tvDpmProcessTotal.setOnClickListener(this);
//        tvDpmProcessed.setOnClickListener(this);
//        tvDpmUnprocess.setOnClickListener(this);
//        tvDpmProcessOnTime.setOnClickListener(this);
//        tvDpmProcessDelays.setOnClickListener(this);
//        tvDpmProcessIndue.setOnClickListener(this);
//        tvDpmProcessOutOfDate.setOnClickListener(this);
//        tvPsProcessed.setOnClickListener(this);
//        tvPsUnProcess.setOnClickListener(this);
//        tvPsProcessOnTime.setOnClickListener(this);
//        tvPsProcessDelays.setOnClickListener(this);
//        tvPsProcessIndue.setOnClickListener(this);
//        tvPsProcessOutOfDate.setOnClickListener(this);
//        lnDateRight.setOnClickListener(this);
//        lnDateLeft.setOnClickListener(this);
////        tvStartDate.setOnClickListener(this);
////        tvEndDate.setOnClickListener(this);
//    }


}
