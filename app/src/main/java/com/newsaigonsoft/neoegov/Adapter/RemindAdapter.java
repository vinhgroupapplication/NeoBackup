package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.RemindRow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by Vinh on 8/26/2017.
 */

public class RemindAdapter extends BaseAdapter {
    Context context;
    List<RemindRow> object;

    public RemindAdapter(Context context, List<RemindRow> object) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_remind_row, null);
            mViewHolder = new ViewHolder(convertView, object);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.cbSMS.setTag(position);
        mViewHolder.cbEmail.setTag(position);
        mViewHolder.cbSMS.setChecked(object.get(position).isSms() ? true : false);
        mViewHolder.cbEmail.setChecked(object.get(position).isEmail() ? true : false);
        mViewHolder.tvProcessPosition.setText(Html.fromHtml(object.get(position).getProcessPosition().replace("Đơn vị thực hiện:", "")));
        mViewHolder.tvContact.setText(object.get(position).getNumber() + "\n" + object.get(position).getEmailName());
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.cbSms)
        CheckBox cbSMS;
        @BindView(R.id.cbEmail)
        CheckBox cbEmail;
        @BindView(R.id.process_position)
        TextView tvProcessPosition;
        @BindView(R.id.remind_contact)
        TextView tvContact;
        List<RemindRow> object;

        public ViewHolder(View view, List<RemindRow> Object) {
            ButterKnife.bind(this, view);
            this.object = Object;
        }

        @Optional
        @OnClick({R.id.cbEmail, R.id.cbSms})
        public void OnClick(View view) {
            int position = (int) view.getTag();
            switch (view.getId()) {
                case R.id.cbEmail:
                    object.get(position).setEmail(object.get(position).isEmail() ? false : true);
                    break;
                case R.id.cbSms:
                    object.get(position).setSms(object.get(position).isSms() ? false : true);
                    break;
                default:
                    break;
            }

        }
    }
}
