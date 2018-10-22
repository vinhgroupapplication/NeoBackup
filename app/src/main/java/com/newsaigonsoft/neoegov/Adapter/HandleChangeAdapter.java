package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.HandleChangeRow;

/**
 * Created by Vinh on 9/25/2017.
 */

public class HandleChangeAdapter extends BaseAdapter {
    Context context;
    List<HandleChangeRow> object;

    public HandleChangeAdapter(Context context, List<HandleChangeRow> object) {
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
            convertView = inflater.inflate(R.layout.custom_handle_change, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvChangeDate.setText(Html.fromHtml(object.get(position).getChangeDate()));
        mViewHolder.tvBeforeChange.setText(Html.fromHtml(object.get(position).getHandleWayBefore()));
        mViewHolder.tvReason.setText(Html.fromHtml(object.get(position).getReason()));
        mViewHolder.tvUserChange.setText(Html.fromHtml(object.get(position).getUserChange()));
        mViewHolder.tvAfterChange.setText(Html.fromHtml(object.get(position).getHandleWayAfter()));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.change_day)
        TextView tvChangeDate;
        @BindView(R.id.before_change)
        TextView tvBeforeChange;
        @BindView(R.id.reason)
        TextView tvReason;
        @BindView(R.id.user_change)
        TextView tvUserChange;
        @BindView(R.id.after_change)
        TextView tvAfterChange;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
