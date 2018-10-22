package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.GsonObject.GsonStatisPersonJoin;
import com.newsaigonsoft.neoegov.MyInterface.MyInterface;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.OtherActivity.OtherActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;

public class StatisCMPersonAdapter extends BaseAdapter {

    Context context;
    List<GsonStatisPersonJoin.DataBean> object;
    private MyInterface listener;
    String isScreen;

    public StatisCMPersonAdapter(Context context, List<GsonStatisPersonJoin.DataBean> object,MyInterface listener, String iscreen) {
        this.context = context;
        this.object = object;
        this.listener = listener;
        this.isScreen = iscreen;

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
            convertView = inflater.inflate(R.layout.custom_person_list_statis_coming, null);
            mViewHolder = new ViewHolder(convertView, context, object);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvPeopleName.setText(object.get(position).getProcesser());
        mViewHolder.tvTotal.setText(String.valueOf(object.get(position).getTotal()));
        mViewHolder.tvTotal.setTag(position);
        mViewHolder.tvNotProcess.setText(String.valueOf(object.get(position).getInProcess()));
        mViewHolder.tvNotProcess.setTag(position);
        mViewHolder.tvOutOfDate.setText(String.valueOf(object.get(position).getProcessedLate()));
        mViewHolder.tvOutOfDate.setTag(position);
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.statistical_person)
        TextView tvPeopleName;
        @BindView(R.id.statistical_total_row)
        TextView tvTotal;
        @BindView(R.id.not_process)
        TextView tvNotProcess;
        @BindView(R.id.out_of_date)
        TextView tvOutOfDate;
        Context mContext;
        List<GsonStatisPersonJoin.DataBean> object;

        public ViewHolder(View view, Context context, List<GsonStatisPersonJoin.DataBean> object) {
            this.mContext = context;
            this.object = object;
            ButterKnife.bind(this, view);

        }

        @Optional
        @OnClick({R.id.statistical_total_row, R.id.not_process, R.id.out_of_date})
        public void onClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.statistical_total_row:
                    listener.eventGetHomeListDocument(object.get(position).getTotal(), isScreen, object.get(position).getTotalCode(), 0, object.get(position).getProcesserId());
                    break;
                case R.id.not_process:
                    listener.eventGetHomeListDocument(object.get(position).getInProcess(), isScreen, object.get(position).getInProcessCode(), 0, object.get(position).getProcesserId());
                    break;
                case R.id.out_of_date:
                    listener.eventGetHomeListDocument(object.get(position).getProcessedLate(), isScreen, object.get(position).getProcessedLateCode(), 0, object.get(position).getProcesserId());
                    break;
                default:
                    break;
            }
        }


    }


}
