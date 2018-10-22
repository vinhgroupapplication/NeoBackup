package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.GsonObject.GsonReturnList;
import com.newsaigonsoft.neoegov.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinh on 8/8/2017.
 */

public class ReturnDocumentAdapter extends BaseAdapter {
    Context context;
    List<GsonReturnList.DataBean> object;

    public ReturnDocumentAdapter(Context context, List<GsonReturnList.DataBean> object) {
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
            convertView = inflater.inflate(R.layout.custom_return_document, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvName.setText(object.get(position).getReceiverName());
        mViewHolder.tvDepartment.setText(object.get(position).getOrganizationName());
        return convertView;
    }

     class ViewHolder{
        @BindView(R.id.name_return)
        TextView tvName;
        @BindView(R.id.department_return)
        TextView tvDepartment;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
