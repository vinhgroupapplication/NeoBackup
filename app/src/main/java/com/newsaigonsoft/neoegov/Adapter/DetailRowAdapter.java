package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.DetailsRows;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by VinhCN on 4/26/2017.
 */

public class DetailRowAdapter extends BaseAdapter {
    Context context;
    List<DetailsRows> object;

    public DetailRowAdapter(Context context, List<DetailsRows> object) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_list_detail_row, null);
        ViewHolder mViewHolder = new ViewHolder(convertView);
        mViewHolder.tvValue.setText(object.get(position).getValue());
        if (!object.get(position).getStyle().equals(nULL_STRING)) {
            String ColorStyle = object.get(position).getStyle();
            try {
                mViewHolder.tvValue.setTextColor(Color.parseColor(ColorStyle));
            }catch (Exception ex){
                String message = ex.getMessage();
                Log.d(TAG, message);
            }

        }
        mViewHolder.tvTitle.setText(object.get(position).getTitle());
        if (object.get(position).getValue().contains(FALSE) || object.get(position).getValue().contains(TRUE)) {
            mViewHolder.tvValue.setVisibility(View.GONE);
            if (object.get(position).getValue().contains(FALSE)) {
                mViewHolder.mCheckBox.setChecked(false);
            } else {
                if (object.get(position).getValue().contains(TRUE)) {
                    mViewHolder.mCheckBox.setChecked(true);
                }
            }
            mViewHolder.mCheckBox.setClickable(false);
        } else {
            mViewHolder.mCheckBox.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.title_details)
        TextView tvTitle;
        @BindView(R.id.content_details)
        TextView tvValue;
        @BindView(R.id.check_box_detail)
        CheckBox mCheckBox;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
