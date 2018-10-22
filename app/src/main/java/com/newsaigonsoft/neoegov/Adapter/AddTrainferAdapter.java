package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AddTrainferRow;

/**
 * Created by Vinh on 8/28/2017.
 */

public class AddTrainferAdapter extends BaseAdapter {
    Context context;
    List<AddTrainferRow> object;

    public AddTrainferAdapter(Context context, List<AddTrainferRow> object) {
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
            convertView = inflater.inflate(R.layout.custom_add_trainfer, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvPeopleName.setText(object.get(position).getTrainferPeplo());
        mViewHolder.tvTime.setText(object.get(position).getTime());
        mViewHolder.tvContent.setText(object.get(position).getContent());
        FileAttachAdapter adapter = new FileAttachAdapter(context, object.get(position).getAttackFile());
        mViewHolder.lvFileAttach.setAdapter(adapter);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.trainfer_people)
        TextView tvPeopleName;
        @BindView(R.id.trainfer_time)
        TextView tvTime;
        @BindView(R.id.content_trainfer)
        TextView tvContent;
        @BindView(R.id.file_list)
        NonScrollListView lvFileAttach;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}
