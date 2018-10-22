package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.graphics.Color;
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

public class ScheduleAdapter extends BaseAdapter {

    Context context;
    ArrayList<ScheduleRow> object;

    public ScheduleAdapter(Context context, ArrayList<ScheduleRow> object) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView!=null){
            mViewHolder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_schedule_list, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvStartDate.setText(object.get(position).getStartDate());
        mViewHolder.tvContent.setText(object.get(position).getContent());
        mViewHolder.tvCharMan.setText(Html.fromHtml("<b>" +  "Người chủ trì: " + "</b>" + object.get(position).getChairMan()) );
        mViewHolder.tvPlaceName.setText(Html.fromHtml("<b>" +  "Địa điểm: " + "</b>" + object.get(position).getPlaceName()) );
        mViewHolder.tvStatus.setText(Html.fromHtml(object.get(position).getStatus()));
        if (object.get(position).getStatus().contains("Đã")){
            mViewHolder.tvStatus.setBackgroundResource(R.drawable.schedule_rectangle_back_blue);
            mViewHolder.tvStartDate.setTextColor(Color.parseColor("#637F95"));
            mViewHolder.tvDot.setBackgroundResource(R.drawable.schedule_rectangle_back_blue);
        }else {
            if (object.get(position).getStatus().contains("Đang")){
                mViewHolder.tvStatus.setBackgroundResource(R.drawable.schedule_rectangle_green);
                mViewHolder.tvStartDate.setTextColor(Color.parseColor("#63D05B"));
                mViewHolder.tvDot.setBackgroundResource(R.drawable.schedule_rectangle_green);
            }else {
                if (object.get(position).getStatus().contains("Sắp")){
                    mViewHolder.tvStatus.setBackgroundResource(R.drawable.schedule_rectangle_yellow);
                    mViewHolder.tvStartDate.setTextColor(Color.parseColor("#FFB129"));
                    mViewHolder.tvDot.setBackgroundResource(R.drawable.schedule_rectangle_yellow);

                }else {
                    mViewHolder.tvStatus.setBackgroundResource(R.drawable.schedule_rectangle_blue);
                    mViewHolder.tvStartDate.setTextColor(Color.parseColor("#59BFFF"));
                    mViewHolder.tvDot.setBackgroundResource(R.drawable.schedule_rectangle_blue);
                }
            }
        }
        return convertView;
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
