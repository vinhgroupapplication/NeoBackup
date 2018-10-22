package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ScheduleMonthRow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class ScheduleMonthAdapter extends BaseAdapter {

    Context context;
    List<ScheduleMonthRow> object;

    public ScheduleMonthAdapter(Context context, List<ScheduleMonthRow> object) {
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
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_schedule_month, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvDateName.setText("Lịch ngày " + object.get(position).getDate().substring(0, 5));



        ScheduleNewWeekAdapter adapter = new ScheduleNewWeekAdapter(context, object.get(position).getArrData());



        mViewHolder.lv.setAdapter(adapter);
        mViewHolder.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                GsonScheduleDay.DataBean mBean = object.get(position).getArrData().get(i);
                ((OfficalActivity) context).OpenDetailSchedule(mBean);
            }
        });




        mViewHolder.lv.setVisibility(object.get(position).isShowList() ? View.VISIBLE : View.GONE);
        mViewHolder.lnARow.setVisibility(object.get(position).getArrData().size() != 0 ? View.VISIBLE : View.GONE);
        mViewHolder.imgIcon.setImageResource(object.get(position).isShowList() ? R.drawable.wall_clock_green_x1 : R.drawable.wall_clock_x1);
        mViewHolder.tvDateName.setTextColor(object.get(position).isShowList() ? context.getResources().getColor(R.color.text_green) : context.getResources().getColor(R.color.recycle));
        mViewHolder.imgArrow.setImageResource(object.get(position).isShowList() ? R.drawable.down_arrow_x1 : R.drawable.right_arrow_x1);
        mViewHolder.rltHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!object.get(position).isShowList()){
                    for (int i = 0; i < object.size(); i++){
                        if (i==position){
                            object.get(i).setShowList(true);
                        }else {
                            object.get(i).setShowList(false);
                        }
                    }
                }else {
                    object.get(position).setShowList(!object.get(position).isShowList());
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }





    class ViewHolder {
        @BindView(R.id.date_name)
        TextView tvDateName;
        @BindView(R.id.lv)
        NonScrollListView lv;
        @BindView(R.id.a_row)
        LinearLayout lnARow;
        @BindView(R.id.arrow)
        ImageView imgArrow;
        @BindView(R.id.icon_monday)
        ImageView imgIcon;
        @BindView(R.id.header_schedule_month)
        RelativeLayout rltHeader;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


//        @Optional
//        @OnClick({R.id.cbEmail, R.id.cbSms})
//        public void OnClick(View view) {
//            int position = (int) view.getTag();
//            switch (view.getId()) {
//                case R.id.cbEmail:
//                    break;
//                case R.id.cbSms:
//                    break;
//                default:
//                    break;
//            }
//
//        }
    }
}
