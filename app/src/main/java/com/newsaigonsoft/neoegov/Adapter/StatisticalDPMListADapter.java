package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMListRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_IN_DUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_UN_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;

/**
 * Created by Vinh on 11/27/2017.
 */

public class StatisticalDPMListADapter extends BaseAdapter {
    Context context;
    List<StatisticalDPMListRow> object;
    String isCscreen;
    SumManager manager = new SumManager();

    public StatisticalDPMListADapter(Context context, List<StatisticalDPMListRow> object, String isCscreen) {
        this.context = context;
        this.object = object;
        this.isCscreen = isCscreen;
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if (view != null) {
            mViewHolder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_statistical_dpm_list, null);
            mViewHolder = new ViewHolder(view, isCscreen, context, object);
            view.setTag(mViewHolder);
        }
        mViewHolder.tvDpmName.setText(String.valueOf(object.get(i).getName()));
        mViewHolder.tvOutOfDate.setText(String.valueOf(object.get(i).getProcesseDemurrage()));
        mViewHolder.tvOnTime.setText(String.valueOf(object.get(i).getProcesseOnTime()));
        mViewHolder.tvOnTime.setTag(i);
        mViewHolder.tvOutOfDate.setTag(i);
        switch (isCscreen) {
            case DPM_PROCESS:
                mViewHolder.tvCenterLine.setVisibility(View.VISIBLE);
                mViewHolder.lnOnTime.setVisibility(View.VISIBLE);
                mViewHolder.lnOutOfDate.setVisibility(View.VISIBLE);
                break;
            case DPM_UN_PROCESS:
                mViewHolder.tvCenterLine.setVisibility(View.VISIBLE);
                mViewHolder.lnOnTime.setVisibility(View.VISIBLE);
                manager.setBGColorDrawable(mViewHolder.tvOnTime, "#46C3FD");
                manager.setBGColorDrawable(mViewHolder.tvOutOfDate, "#FFA140");
                mViewHolder.tvTitleOnTime.setText("Trong hạn");
                mViewHolder.tvOutOfDateTitle.setText("Quá hạn");
                mViewHolder.lnOutOfDate.setVisibility(View.VISIBLE);

                break;
            case DPM_ON_TIME:
                mViewHolder.tvCenterLine.setVisibility(View.GONE);
                mViewHolder.lnOutOfDate.setVisibility(View.GONE);
                mViewHolder.lnOnTime.setVisibility(View.VISIBLE);
//                mViewHolder.tvOnTime.setGravity(Gravity.RIGHT);
                break;
            case DPM_DELAYS:
                mViewHolder.tvCenterLine.setVisibility(View.GONE);
                mViewHolder.lnOutOfDate.setVisibility(View.VISIBLE);
                mViewHolder.lnOnTime.setVisibility(View.GONE);
//                mViewHolder.tvOutOfDate.setGravity(Gravity.RIGHT);
                break;
            case DPM_IN_DUE:
                mViewHolder.tvCenterLine.setVisibility(View.GONE);
                mViewHolder.lnOutOfDate.setVisibility(View.GONE);
                mViewHolder.lnOnTime.setVisibility(View.VISIBLE);
                manager.setBGColorDrawable(mViewHolder.tvOnTime, "#46C3FD");
                manager.setBGColorDrawable(mViewHolder.tvOutOfDate, "#FFA140");
                mViewHolder.tvTitleOnTime.setText("Trong hạn");
                mViewHolder.tvOutOfDateTitle.setText("Quá hạn");
//                mViewHolder.tvOnTime.setGravity(Gravity.RIGHT);
                break;
            case DPM_OUT_OF_DATE:
                mViewHolder.tvCenterLine.setVisibility(View.GONE);
                mViewHolder.lnOutOfDate.setVisibility(View.VISIBLE);
                mViewHolder.lnOnTime.setVisibility(View.GONE);
                manager.setBGColorDrawable(mViewHolder.tvOnTime, "#46C3FD");
                manager.setBGColorDrawable(mViewHolder.tvOutOfDate, "#FFA140");
                mViewHolder.tvTitleOnTime.setText("Trong hạn");
                mViewHolder.tvOutOfDateTitle.setText("Quá hạn");
//                mViewHolder.tvOutOfDate.setGravity(Gravity.RIGHT);
                break;
        }
        return view;
    }


    class ViewHolder {
        @BindView(R.id.statistical_department)
        TextView tvDpmName;
        @BindView(R.id.statistical_late_row)
        TextView tvOutOfDate;
        @BindView(R.id.row_on_time)
        TextView tvOnTime;
        @BindView(R.id.ontime_layout)
        LinearLayout lnOnTime;
        @BindView(R.id.out_of_date_layout)
        LinearLayout lnOutOfDate;
        @BindView(R.id.center_line)
        TextView tvCenterLine;
        @BindView(R.id.title_ontime)
        TextView tvTitleOnTime;
        @BindView(R.id.out_of_date_title)
        TextView tvOutOfDateTitle;
        String isScreen;
        Context mContext;
        List<StatisticalDPMListRow> object;

        public ViewHolder(View view, String Screen, Context context, List<StatisticalDPMListRow> Object) {
            ButterKnife.bind(this, view);
            this.isScreen = Screen;
            this.mContext = context;
            this.object = Object;
        }

        @Optional
        @OnClick({R.id.row_on_time, R.id.statistical_late_row})
        public void onClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.row_on_time:
                    switch (isScreen) {
                        case DPM_PROCESS:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_ONTIME);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_ONTIME,
                                    object.get(position).getProcesseOnTimeType(), object.get(position).getObjectId());
                            break;
                        case DPM_UN_PROCESS:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_INDUE);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_INDUE,
                                    object.get(position).getProcesseOnTimeType(), object.get(position).getObjectId());
                            break;
                        case DPM_ON_TIME:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_ONTIME);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_ONTIME,
                                    object.get(position).getProcesseOnTimeType(), object.get(position).getObjectId());
                            break;
                        case DPM_IN_DUE:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_INDUE);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_INDUE,
                                    object.get(position).getProcesseOnTimeType(), object.get(position).getObjectId());
                            break;
                    }
                    break;
                case R.id.statistical_late_row:
                    switch (isScreen) {
                        case DPM_PROCESS:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_DELAYS);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_DELAYS,
                                    object.get(position).getProcesseDemurrageType(), object.get(position).getObjectId());
                            break;
                        case DPM_UN_PROCESS:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_OUT_OF_DATE);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_OUT_OF_DATE,
                                    object.get(position).getProcesseDemurrageType(), object.get(position).getObjectId());
                            break;
//                    case DPM_ON_TIME:
//                        ((OfficalActivity) context).setLookupScreen(TAP_DPM_ONTIME);
//                        ((OfficalActivity) context).mOfficeLogic.eventGetHomeListDocument(
//                                SumManager.LimitPager
//                                , TAP_DPM_ONTIME,
//                                object.get(position).getProcesseDemurrageType(), object.get(position).getObjectId());
//                        break;
                        //                    case DPM_IN_DUE:
//                        ((OfficalActivity) context).setLookupScreen(TAP_DPM_INDUE);
//                        ((OfficalActivity) context).mOfficeLogic.eventGetHomeListDocument(
//                                SumManager.LimitPager
//                                , TAP_DPM_INDUE,
//                                object.get(position).getProcesseDemurrageType(), object.get(position).getObjectId());
//                        break;
                        case DPM_DELAYS:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_DELAYS);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_DELAYS,
                                    object.get(position).getProcesseDemurrageType(), object.get(position).getObjectId());
                            break;
                        case DPM_OUT_OF_DATE:
                            ((OfficalActivity) mContext).setLookupScreen(TAP_DPM_OUT_OF_DATE);
                            ((OfficalActivity) mContext).mOfficeLogic.eventGetHomeListDocument(
                                    SumManager.LimitPager
                                    , TAP_DPM_OUT_OF_DATE,
                                    object.get(position).getProcesseDemurrageType(), object.get(position).getObjectId());
                            break;
                    }
                    break;
            }
        }


    }
}
