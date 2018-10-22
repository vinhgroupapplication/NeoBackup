package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinh on 4/20/2018.
 */

public class ScheduleNewWeekAdapter extends BaseAdapter {


    Context context;
    List<GsonScheduleDay.DataBean> object;
    SumManager manager = new SumManager();

    public ScheduleNewWeekAdapter(Context context, List<GsonScheduleDay.DataBean> object) {
        this.context = context;
        this.object = object;
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ScheduleNewWeekAdapter.ViewHolder mViewHolder;
        if (view!=null){
            mViewHolder = (ScheduleNewWeekAdapter.ViewHolder) view.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_schedule_week, null);
            mViewHolder = new ScheduleNewWeekAdapter.ViewHolder(view);
            view.setTag(mViewHolder);
        }
        mViewHolder.tvStartDate.setText(object.get(position).getTimeTypeName());
        mViewHolder.tvContent.setText(object.get(position).getContent());
        mViewHolder.tvCharMan.setText(Html.fromHtml("<b>" +  "Người chủ trì: " + "</b>" + object.get(position).getChairManName()) );
        if (object.get(position).getPlaceName()!=null){
            mViewHolder.tvPlaceName.setText(Html.fromHtml("<b>" +  "Địa điểm: " + "</b>" + object.get(position).getPlaceName()) );
        }
        if (object.get(position).getStatusName()!=null){
            if (object.get(position).getStatusName().getStatusHour()!=null){
                mViewHolder.tvStatus.setVisibility(View.VISIBLE);
                mViewHolder.tvStatus.setText(Html.fromHtml(object.get(position).getStatusName().getStatusHour().getStatus()));
                if (object.get(position).getStatusName().getStatusHour().getStatus().contains("Đã")){
                    manager.setBGColorDrawable(mViewHolder.tvStatus, "#637F95");
                    manager.setBGColorDrawable(mViewHolder.tvDot, "#637F95");
                    mViewHolder.tvStartDate.setTextColor(Color.parseColor("#637F95"));
                }else {
                    if (object.get(position).getStatusName().getStatusHour().getStatus().contains("Đang")){
                        manager.setBGColorDrawable(mViewHolder.tvStatus, "#64CF5B");
                        manager.setBGColorDrawable(mViewHolder.tvDot, "#64CF5B");
                        mViewHolder.tvStartDate.setTextColor(Color.parseColor("#64CF5B"));
                    }else {
                        if (object.get(position).getStatusName().getStatusHour().getStatus().contains("Sắp")){
                            manager.setBGColorDrawable(mViewHolder.tvStatus, "#FFB129");
                            manager.setBGColorDrawable(mViewHolder.tvDot, "#FFB129");
                            mViewHolder.tvStartDate.setTextColor(Color.parseColor("#FFB129"));
                        }else{
                            manager.setBGColorDrawable(mViewHolder.tvStatus, "#59BFFD");
                            manager.setBGColorDrawable(mViewHolder.tvDot, "#59BFFD");
                            mViewHolder.tvStartDate.setTextColor(Color.parseColor("#59BFFD"));
                        }
                    }
                }
            }
        }
        return view;
    }

    class ViewHolder{
        @BindView(R.id.schedule_start_date)
        TextView tvStartDate;
        @BindView(R.id.schedule_content)
        TextView tvContent;
        @BindView(R.id.schedule_charman)
        TextView tvCharMan;
        @BindView(R.id.schedule_status)
        TextView tvStatus;
        @BindView(R.id.schedule_placeName)
        TextView tvPlaceName;
        @BindView(R.id.dot_)
        TextView tvDot;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }




}
