package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.MessageTasksRow;

/**
 * Created by Vinh on 10/3/2017.
 */

public class MsgTaskAdapter extends BaseAdapter {

    Context context;
    List<MessageTasksRow> object;

    public MsgTaskAdapter(Context context, List<MessageTasksRow> object) {
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
            convertView = inflater.inflate(R.layout.custom_msg_tasks, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.cbsendSMS.setChecked(object.get(position).isSendSMS());
        mViewHolder.cbsendEmail.setChecked(object.get(position).isSendEmail());
        mViewHolder.cbsendSMS.setChecked(object.get(position).isSendSMS());
        mViewHolder.cbsentSMS.setChecked(object.get(position).isSentSMS());
        mViewHolder.cbsentEmail.setChecked(object.get(position).isSentEmail());
        mViewHolder.tvExecutionUnit.setText(Html.fromHtml("<font color=#000000>" +
                "Đơn vị thực hiện:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getExecutionUnit() +
                "</font>"));
        mViewHolder.tvContentSMS.setText(Html.fromHtml("<font color=#000000>" +
                "Nội dung gửi SMS:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getContentSMS() +
                "</font>"));
        mViewHolder.tvContentEmail.setText(Html.fromHtml("<font color=#000000>" +
                "Nội dung gửi email:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getContentEmail() +
                "</font>"));
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.send_sms)
        CheckBox cbsendSMS;
        @BindView(R.id.send_email)
        CheckBox cbsendEmail;
        @BindView(R.id.sent_sms)
        CheckBox cbsentSMS;
        @BindView(R.id.sent_email)
        CheckBox cbsentEmail;
        @BindView(R.id.execution_unit)
        TextView tvExecutionUnit;
        @BindView(R.id.content_sms)
        TextView tvContentSMS;
        @BindView(R.id.content_email)
        TextView tvContentEmail;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
