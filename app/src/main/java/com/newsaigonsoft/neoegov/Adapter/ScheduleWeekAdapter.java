package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ScheduleRow;

/**
 * Created by Vinh on 7/12/2017.
 */

public class ScheduleWeekAdapter extends BaseAdapter {

    Context context;
    ArrayList<ScheduleRow> object;

    public ScheduleWeekAdapter(Context context, ArrayList<ScheduleRow> object) {
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
        ViewHolder mViewHolder;
        if (view!=null){
            mViewHolder = (ViewHolder) view.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_schedule_week_old, null);
            mViewHolder = new ViewHolder(view);
            view.setTag(mViewHolder);
        }
        mViewHolder.tvStartDate.setText(object.get(position).getStartDate() +  " giờ");
        mViewHolder.tvContent.setText(object.get(position).getContent());
        mViewHolder.tvCharMan.setText(Html.fromHtml("<b>" +  "Người chủ trì: " + "</b>" + object.get(position).getChairMan()) );
        mViewHolder.tvPlaceName.setText(Html.fromHtml("<b>" +  "Địa điểm: " + "</b>" + object.get(position).getPlaceName()) );
        mViewHolder.tvStatus.setText(Html.fromHtml(object.get(position).getStatus()));
        if (!object.get(position).getStatus().contains("Đã họp")){
            mViewHolder.tvStatus.setBackgroundResource(R.drawable.cornor_button_green);
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

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
