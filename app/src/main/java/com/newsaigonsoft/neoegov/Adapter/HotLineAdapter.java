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
import com.newsaigonsoft.neoegov.Subjects.HotLineListRow;

/**
 * Created by Vinh on 10/31/2017.
 */

public class HotLineAdapter extends BaseAdapter {


    public HotLineAdapter(Context context, List<HotLineListRow> object) {
        this.context = context;
        this.object = object;
    }

    Context context;
    List<HotLineListRow> object;

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
            convertView = inflater.inflate(R.layout.custom_list_hot_line, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvPeopleReflect.setText(
                Html.fromHtml("<font color=#000000>" +
                        "Người phản ánh:" +
                        "</font> <font color=#0F89DB>" +
                        object.get(position).getPeopleReflect() +
                        "</font>")
                );
        mViewHolder.tvPhoneReflect.setText(
                Html.fromHtml("<font color=#000000>" +
                        "Số điện thoại phản ánh:" +
                        "</font> <font color=#0F89DB>" +
                        object.get(position).getPhoneReflect() +
                        "</font>")
         );
//        tvContent.setText(
//                Html.fromHtml("<font color=#000000>" +
//                        "Nội dung phản ánh:" +
//                        "</font> <font color=#0F89DB>" +
//                        object.get(position).getContent() +
//                        "</font>")
//                );
        mViewHolder.tvContent.setText(object.get(position).getContent());
        mViewHolder.tvReceiveDate.setText(object.get(position).getReceiveDate());
        return convertView;
    }
     class ViewHolder{
        @BindView(R.id.peopleReflect)
        TextView tvPeopleReflect;
        @BindView(R.id.phoneReflect)
        TextView tvPhoneReflect;
        @BindView(R.id.content)
        TextView tvContent;
        @BindView(R.id.receiveDate)
        TextView tvReceiveDate;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
