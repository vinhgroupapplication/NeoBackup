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
import com.newsaigonsoft.neoegov.Subjects.HandleProcessRow;

/**
 * Created by Vinh on 8/16/2017.
 */

public class HandleProcessAdapter extends BaseAdapter {
    Context context;
    List<HandleProcessRow> object;

    public HandleProcessAdapter(Context context, List<HandleProcessRow> object) {
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
            convertView = inflater.inflate(R.layout.custom_process_person, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvNameHandle.setText(object.get(position).getName());
        mViewHolder.cbCheck.setChecked(object.get(position).isDefault() ? true : false);
        mViewHolder.cbCheck.setEnabled(false);
        mViewHolder.tvOther.setVisibility(View.GONE);
        return convertView;
    }
     class ViewHolder{
        @BindView(R.id.person_name)
        TextView tvNameHandle;
        @BindView(R.id.check_person)
        CheckBox cbCheck;
        @BindView(R.id.room_name)
        TextView tvOther;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
