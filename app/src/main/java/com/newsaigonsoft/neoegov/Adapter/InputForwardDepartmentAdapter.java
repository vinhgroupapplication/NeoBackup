package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Action.ForwardDepartment.InputForwardDepartmentActivity;
import com.newsaigonsoft.neoegov.MyInterface.AdapterButtonClick;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.InputForwardDeparmentRow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by Vinh on 8/9/2017.
 */

public class InputForwardDepartmentAdapter extends BaseAdapter {
    Context context;
    List<InputForwardDeparmentRow> object;
    AdapterButtonClick mBtnClickListener = null;

    public InputForwardDepartmentAdapter(Context context, List<InputForwardDeparmentRow> object, AdapterButtonClick listener) {
        this.context = context;
        this.object = object;
        mBtnClickListener = listener;
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
        if (convertView!=null){
            mViewHolder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_input_forward_department, null);
            mViewHolder = new ViewHolder(convertView, object);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.mCheckBox.setTag(position);
        mViewHolder.btnAdd.setTag(position);
        mViewHolder.mCheckBox.setChecked(object.get(position).isDefault() ? true : false);
        mViewHolder.tvRoomName.setText(object.get(position).getOrganizationName());
        mViewHolder.lvTrue.setAdapter(object.get(position).getAdapterTrue());
//        mViewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mBtnClickListener != null)
//                    mBtnClickListener.AddDialog((Integer) v.getTag());
//            }
//        });
        //        mViewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (object.get(position).isDefault()) {
//                    object.get(position).setDefault(false);
//                } else {
//                    object.get(position).setDefault(true);
//                }
//            }
//        });
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.check_department_room)
        CheckBox mCheckBox;
        @BindView(R.id.button_add)
        ImageView btnAdd;
        @BindView(R.id.room_name)
        TextView tvRoomName;
        @BindView(R.id.list_receivers)
        ListView lvTrue;
        List<InputForwardDeparmentRow> object;

        public ViewHolder(View view, List<InputForwardDeparmentRow> Object) {
            ButterKnife.bind(this, view);
            this.object = Object;
        }

        @Optional
        @OnClick({R.id.check_department_room, R.id.button_add})
        public void OnClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.check_department_room:
                    object.get(position).setDefault(object.get(position).isDefault() ? false : true);
                    ((InputForwardDepartmentActivity) context).setAllChecking();
                    break;
                case R.id.button_add:
                    if (mBtnClickListener != null)
                        mBtnClickListener.AddDialog(position);
                    break;
                default:
                    break;
            }
        }


    }

}



