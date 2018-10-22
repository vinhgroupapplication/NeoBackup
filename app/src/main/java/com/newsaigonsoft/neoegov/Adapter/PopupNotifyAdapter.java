package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.NotifyRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 8/14/2017.
 */

public class PopupNotifyAdapter extends BaseAdapter {
    SumManager manager = new SumManager();
    Context context;
    List<NotifyRow> object;

    public PopupNotifyAdapter(Context context, List<NotifyRow> object) {
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
            convertView = inflater.inflate(R.layout.custom_notify_row, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.mLayoutNotifyNull.setVisibility(object.get(position).isShowNull() ? View.VISIBLE : View.GONE);
        mViewHolder.mLayoutNotifyNotNull.setVisibility(object.get(position).isShowNull() ? View.GONE : View.VISIBLE);
        mViewHolder.tvContent.setText(object.get(position).isShowNull() ? "" : object.get(position).getContent());
        mViewHolder.tvNotifyMessage.setText(!manager.isNetworkAvailable(context) ? "Không có kết nối" : "Không có thông báo");
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.layout_notify_not_null)
        LinearLayout mLayoutNotifyNotNull;
        @BindView(R.id.layout_notify_null)
        LinearLayout mLayoutNotifyNull;
        @BindView(R.id.notify_content)
        TextView tvContent;
        @BindView(R.id.notify_message)
        TextView tvNotifyMessage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
