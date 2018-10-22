package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Action.Cancel.CancelActivity;
import com.newsaigonsoft.neoegov.MyInterface.AllCheck;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.CancelListRow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by Vinh on 9/12/2017.
 */

public class CancelListAdapter extends BaseAdapter {
    Context context;
    List<CancelListRow> object;
    AllCheck mAllCheck;

    public CancelListAdapter(Context context, List<CancelListRow> object, AllCheck mAllCheck) {
        this.context = context;
        this.object = object;
        this.mAllCheck = mAllCheck;
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
        final ViewHolder mViewHolder;
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_cancel_list, null);
            mViewHolder = new ViewHolder(convertView, object);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.cbSelect.setTag(position);
        mViewHolder.cbSelect.setChecked(object.get(position).isDefault() ? true : false);
        mViewHolder.tvName.setText(object.get(position).getProcesser());
        mViewHolder.tvRoomName.setText(object.get(position).getOrganizationName());
        return convertView;
    }


     class ViewHolder {
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.room_name)
        TextView tvRoomName;
        @BindView(R.id.check_select)
        CheckBox cbSelect;
        List<CancelListRow> object;

        public ViewHolder(View view, List<CancelListRow> Object) {
            ButterKnife.bind(this, view);
            object = Object;
        }

        @Optional
        @OnClick({R.id.check_select})
        public void OnClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.check_select:
                    if (object.get(position).isDefault()) {
                        cbSelect.setChecked(false);
                        object.get(position).setDefault(false);
                        if (object.size() == 1) {
                            mAllCheck.AllCheck(false);
                        }
                    } else {
                        cbSelect.setChecked(true);
                        object.get(position).setDefault(true);
                        if (object.size() == 1) {
                            mAllCheck.AllCheck(true);
                        }
                    }
                    ((CancelActivity) context).setCheckAllList();
                    break;
                default:
                    break;
            }

        }

    }
}
