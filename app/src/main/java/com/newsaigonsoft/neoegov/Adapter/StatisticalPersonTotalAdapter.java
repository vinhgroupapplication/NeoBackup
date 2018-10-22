package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.R.id.statistical_department;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.CLICK_TIME_INTERVAL;

/**
 * Created by Vinh on 11/28/2017.
 */

public class StatisticalPersonTotalAdapter extends BaseAdapter {

    Context context;
    List<StatisticalDPMRow> object;
    private long mLastClickTime = System.currentTimeMillis();

    public StatisticalPersonTotalAdapter(Context context, List<StatisticalDPMRow> object) {
        this.context = context;
        this.object = object;
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
            view = inflater.inflate(R.layout.custom_statistical_person_row, null);
            mViewHolder = new ViewHolder(view);
            view.setTag(mViewHolder);
        }
        mViewHolder.tvOnTime.setTag(i);
        mViewHolder.tvDelays.setTag(i);
        mViewHolder.tvIndue.setTag(i);
        mViewHolder.tvOutOfDate.setTag(i);
        mViewHolder.tvName.setText(object.get(i).getName());
        mViewHolder.tvTotal.setText(String.valueOf(object.get(i).getTotal()));
        mViewHolder.tvOnTime.setText(String.valueOf(object.get(i).getProcessedOnTime()));
        mViewHolder.tvDelays.setText(String.valueOf(object.get(i).getProcessedDemurrage()));
        mViewHolder.tvIndue.setText(String.valueOf(object.get(i).getProcessOnTime()));
        mViewHolder.tvOutOfDate.setText(String.valueOf(object.get(i).getProcessDemurrage()));
        mViewHolder.lnDetailItem.setVisibility(object.get(i).isShowDetail() ? View.VISIBLE : View.GONE);
        mViewHolder.btnArrow.setImageResource(object.get(i).isShowDetail() ? R.drawable.down_arrow_x1 : R.drawable.right_arrow_x1);
        mViewHolder.tvOnTime.setTag(i);
        mViewHolder.tvDelays.setTag(i);
        mViewHolder.tvIndue.setTag(i);
        mViewHolder.tvOutOfDate.setTag(i);
        mViewHolder.lnDetailItem.setTag(i);
        mViewHolder.btnArrow.setTag(i);
        mViewHolder.lnTopLayout.setTag(i);
        mViewHolder.tvTotal.setTag(i);
        mViewHolder.lnTopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.get(i).setShowDetail(object.get(i).isShowDetail() ? false : true);
                notifyDataSetChanged();
            }
        });
        return view;
    }


    class ViewHolder {
        @BindView(R.id.ps_row_name)
        TextView tvName;
        @BindView(R.id.st_ps_row_ontime)
        TextView tvOnTime;
        @BindView(R.id.st_ps_row_out_of_date)
        TextView tvOutOfDate;
        @BindView(R.id.st_ps_row_delays)
        TextView tvDelays;
        @BindView(R.id.st_ps_row_indue)
        TextView tvIndue;
        @BindView(R.id.button_arrow)
        ImageView btnArrow;
        @BindView(R.id.detail_item)
        LinearLayout lnDetailItem;
        @BindView(R.id.top_layout)
        LinearLayout lnTopLayout;
        @BindView(R.id.statistical_total_row)
        TextView  tvTotal;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Optional
        @OnClick({R.id.st_ps_row_ontime, R.id.st_ps_row_delays, R.id.st_ps_row_indue, R.id.st_ps_row_out_of_date})
        public void onClick(View view) {
            long now = System.currentTimeMillis();
            if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
                return;
            }
            mLastClickTime = now;
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.st_ps_row_ontime:
                    ((OfficalActivity) context).setLookupScreen(TAP_DPM_ONTIME);
                    ((OfficalActivity) context).mOfficeLogic.eventGetHomeListDocument(
                            SumManager.LimitPager
                            , TAP_DPM_ONTIME,
                            object.get(position).getProcessedOnTimeType(), object.get(position).getObjectId());
                    break;
                case R.id.st_ps_row_delays:
                    ((OfficalActivity) context).setLookupScreen(TAP_DPM_ONTIME);
                    ((OfficalActivity) context).mOfficeLogic.eventGetHomeListDocument(
                            SumManager.LimitPager
                            , TAP_DPM_ONTIME,
                            object.get(position).getProcessedDemurrageType(), object.get(position).getObjectId());
                    break;
                case R.id.st_ps_row_indue:
                    ((OfficalActivity) context).setLookupScreen(TAP_DPM_ONTIME);
                    ((OfficalActivity) context).mOfficeLogic.eventGetHomeListDocument(
                            SumManager.LimitPager
                            , TAP_DPM_ONTIME,
                            object.get(position).getProcessOnTimeType(), object.get(position).getObjectId());
                    break;
                case R.id.st_ps_row_out_of_date:
                    ((OfficalActivity) context).setLookupScreen(TAP_DPM_ONTIME);
                    ((OfficalActivity) context).mOfficeLogic.eventGetHomeListDocument(
                            SumManager.LimitPager
                            , TAP_DPM_ONTIME,
                            object.get(position).getProcessDemurrageType(), object.get(position).getObjectId());
                    break;
                default:
                    break;
            }
        }
    }
}
