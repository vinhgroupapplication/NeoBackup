package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.GsonObject.GsonStaComingList;
import com.newsaigonsoft.neoegov.MyInterface.MyInterface;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.StatisticalRows;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.R.id.statistical_department;
import static com.newsaigonsoft.neoegov.R.id.statistical_late_row;
import static com.newsaigonsoft.neoegov.R.id.statistical_total_row;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;

/**
 * Created by Vinh on 6/13/2017.
 */

public class StatisticalListAdapter extends BaseAdapter {
    //        implements View.OnClickListener
    private MyInterface listener;
    //    public StatisticalListAdapter(MyInterface listener) {
//        this.listener = listener;
//    }
    Context context;
    List<GsonStaComingList.DataBean>  object;
    String isScreen;

    // o day truyen lister bang OfficeActivity co nghia la truyen cai OfficeActivity vao, Myinterface se co gia tri bang voi OfficeActivity
    // tu do co the goi duoc method o OfficalActivity
    public StatisticalListAdapter(Context context, List<GsonStaComingList.DataBean> object, MyInterface listener, String isscreen) {
        this.context = context;
        this.object = object;
        this.listener = listener;
        this.isScreen = isscreen;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_statistical_row_list, null);
            mViewHolder = new ViewHolder(convertView, context);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvDepartment.setText(object.get(position).getOrganizationName());
        mViewHolder.tvTotal.setText(String.valueOf(object.get(position).getTotal()));
//        mViewHolder.tvLateTotal.setText(String.valueOf(object.get(position).getStatisticalLateTotal()));
        mViewHolder.tvTotal.setTag(position);
        mViewHolder.tvLateTotal.setTag(position);
        mViewHolder.lnDetailItem.setVisibility(object.get(position).isShow()? View.VISIBLE : View.GONE);
        mViewHolder.btnArrow.setImageResource(object.get(position).isShow() ? R.drawable.down_arrow_x1 : R.drawable.right_arrow_x1);
        mViewHolder.lnTopLayout.setTag(position);
        mViewHolder.tvPercent.setText(String.valueOf(object.get(position).getRateOnTime()) + "%");
        mViewHolder.tvTotalOutOfDate.setText(String.valueOf(object.get(position).getInProcess()));
        mViewHolder.tvTotalOutOfDate.setTag(position);
        mViewHolder.tvTotal_Processed.setText(String.valueOf(String.valueOf(object.get(position).getProcessed())));
        mViewHolder.tvTotal_Processed.setTag(position);
        mViewHolder.tvLate.setText(String.valueOf(object.get(position).getProcessedLate()));
        mViewHolder.tvLate.setTag(position);
        mViewHolder.tvOutOfDate.setText(String.valueOf(object.get(position).getInProcessLate()));
        mViewHolder.tvOutOfDate.setTag(position);
        mViewHolder.lnTopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.get(position).setShow(object.get(position).isShow() ? false : true);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    //    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id){
//            case statistical_department:
//                Toast.makeText(context, getStatisticalDepartment(), Toast.LENGTH_SHORT).show();
//                break;
//            case statistical_total_row:
//                break;
//            case statistical_late_row:
//                break;
//            default:
//                break;
//        }
//    }
    class ViewHolder {
        @BindView(statistical_department)
        TextView tvDepartment;
        @BindView(statistical_total_row)
        TextView tvTotal;
        @BindView(statistical_late_row)
        TextView tvLateTotal;
        @BindView(R.id.detail_item)
        LinearLayout lnDetailItem;
        @BindView(R.id.top_layout)
        LinearLayout lnTopLayout;
        @BindView(R.id.button_arrow)
        ImageView btnArrow;
        @BindView(R.id.total_processed)
        TextView tvTotal_Processed;
        @BindView(R.id.late)
        TextView tvLate;
        @BindView(R.id.total_out_of_date)
        TextView tvTotalOutOfDate;
        @BindView(R.id.out_of_date)
        TextView tvOutOfDate;
        @BindView(R.id.percent)
        TextView tvPercent;
        Context context;

        public ViewHolder(View view, Context mContext) {
            ButterKnife.bind(this, view);
            this.context = mContext;
        }

        @Optional
        @OnClick({statistical_late_row, statistical_total_row, R.id.total_processed, R.id.late, R.id.total_out_of_date, R.id.out_of_date})
        public void OnClick(View view) {
            int position = (int) view.getTag();
            switch (view.getId()) {
                case statistical_late_row:
//                    listener.eventGetHomeListDocument(object.get(position).getStatisticalLateTotal(), LIST_DOCUMENT_DEPARTMENT, object.get(position).getProcessedLateCode(), object.get(position).getOrganizationId());
                    break;
                case statistical_total_row:
                    listener.eventGetHomeListDocument(object.get(position).getTotal(), isScreen, object.get(position).getTotalCode(), object.get(position).getOrganizationId());
                    break;
                case R.id.total_processed:
                    listener.eventGetHomeListDocument(object.get(position).getProcessed(), isScreen, object.get(position).getProcessedCode(), object.get(position).getOrganizationId());
                    break;
                case R.id.late:
                    listener.eventGetHomeListDocument(object.get(position).getProcessedLate(), isScreen, object.get(position).getProcessedLateCode(), object.get(position).getOrganizationId());
                    break;
                case R.id.total_out_of_date:
                    listener.eventGetHomeListDocument(object.get(position).getInProcess(), isScreen, object.get(position).getInProcessCode(), object.get(position).getOrganizationId());
                    break;
                case R.id.out_of_date:
                    listener.eventGetHomeListDocument(object.get(position).getInProcessLate(), isScreen, object.get(position).getInProcessLateCode(), object.get(position).getOrganizationId());
                    break;
                default:
                    break;
            }


        }
    }
}
