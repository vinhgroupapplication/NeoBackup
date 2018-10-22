package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
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
import com.newsaigonsoft.neoegov.Subjects.ChangeHandleRow;

/**
 * Created by Vinh on 8/9/2017.
 */

public class ChangeHandlerAdapter extends BaseAdapter {

    Context context;
    List<ChangeHandleRow> object;

    public ChangeHandlerAdapter(Context context, List<ChangeHandleRow> object) {
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
            convertView = inflater.inflate(R.layout.custom_list_change_handle, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.mCheckBoxHandle.setChecked(object.get(position).isDefault());
        mViewHolder.tvNameHandle.setText(object.get(position).getName());
        mViewHolder.mCheckBoxHandle.setClickable(false);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.check_change_handle)
        CheckBox mCheckBoxHandle;
        @BindView(R.id.handle_name)
        TextView tvNameHandle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
