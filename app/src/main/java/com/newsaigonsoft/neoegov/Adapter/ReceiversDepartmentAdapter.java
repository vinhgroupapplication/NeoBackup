package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.MyInterface.AdapterButtonClick;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ReceiversRow;

/**
 * Created by Vinh on 8/17/2017.
 */

public class ReceiversDepartmentAdapter extends BaseAdapter {
    Context context;
    List<ReceiversRow> object;
    AdapterButtonClick mBtnClickListener = null;
    boolean isDialog;

    public ReceiversDepartmentAdapter(Context context, List<ReceiversRow> object, boolean isdialog,  AdapterButtonClick listener) {
        this.context = context;
        this.object = object;
        this.isDialog = isdialog;
        this.mBtnClickListener = listener;
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
            if (isDialog){
                convertView = inflater.inflate(R.layout.custom_receivers_row_dialog, null);
            }else {
                convertView = inflater.inflate(R.layout.custom_receivers_row, null);
            }

            mViewHolder = new ViewHolder(convertView, object);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.btnRemove.setVisibility(object.get(position).isDialog() ? View.GONE : View.VISIBLE);
        mViewHolder.CbSelect.setVisibility(object.get(position).isDialog() ? View.VISIBLE : View.GONE);
        mViewHolder.cbMainReceiver.setVisibility(object.get(position).isDialog() ? View.INVISIBLE : View.VISIBLE);
        mViewHolder.tvPositionName.setText(object.get(position).isDialog() ? object.get(position).getRoleName() : "");
        mViewHolder.cbMainReceiver.setChecked(object.get(position).isMainChecked() ? true : false);
        mViewHolder.CbSelect.setChecked(object.get(position).isDefault() ? true : false);
        mViewHolder.tvPersonName.setText(object.get(position).getReceiverName());
        mViewHolder.tvPositionName.setText(object.get(position).getRoleName());
        mViewHolder.cbMainReceiver.setVisibility(object.get(position).isDialog() ? (mViewHolder.CbSelect.isChecked() ? View.VISIBLE : View.INVISIBLE) : View.VISIBLE);
        mViewHolder.btnRemove.setTag(position);
        mViewHolder.cbMainReceiver.setTag(position);
        mViewHolder.CbSelect.setTag(position);
        mViewHolder.tvPersonName.setTextColor(Color.parseColor(object.get(position).isMainChecked() ? "#FCA100" : "#444444"));


//        mViewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mBtnClickListener != null) {
//                    if (object.size() > (Integer) v.getTag()) {
//                        mBtnClickListener.onBtnRemoveClick((Integer) v.getTag());
//                    }
//                }
//            }
//        });
//        mViewHolder.CbSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mBtnClickListener != null) {
//                    mBtnClickListener.addClick((Integer) v.getTag());
//                }
//            }
//        });
//        mViewHolder.cbMainReceiver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBtnClickListener.mainCheck((Integer) v.getTag());
//            }
//        });
        //        if () {
//            if () {
//                mViewHolder.cbMainReceiver.setVisibility();
//            } else {
//                mViewHolder.cbMainReceiver.setVisibility();
//            }
//        }
//        if () {
//            mViewHolder.mRemoveButton.setVisibility();
//            mViewHolder.CbSelect.setVisibility();
//            mViewHolder.cbMainReceiver.setVisibility();
//            mViewHolder.tvPositionName.setText();
//        } else {
//            mViewHolder.mRemoveButton.setVisibility();
//            mViewHolder.CbSelect.setVisibility();
//            mViewHolder.cbMainReceiver.setVisibility();
//        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.persion_name)
        TextView tvPersonName;
        @BindView(R.id.position_name)
        TextView tvPositionName;
        @BindView(R.id.remove_button)
        LinearLayout btnRemove;
        @BindView(R.id.check_select)
        CheckBox CbSelect;
        @BindView(R.id.check_select_main_receiver)
        CheckBox cbMainReceiver;
        List<ReceiversRow> object;

        public ViewHolder(View view, List<ReceiversRow> Object) {
            ButterKnife.bind(this, view);
            this.object = Object;
        }

        @Optional
        @OnClick({R.id.remove_button, R.id.check_select, R.id.check_select_main_receiver})
        public void OnClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.remove_button:
                    if (mBtnClickListener != null) {
                        if (object.size() > position) {
                            mBtnClickListener.onBtnRemoveClick(position);
                        }
                    }
                    break;
                case R.id.check_select:
                    if (mBtnClickListener != null) {
                        mBtnClickListener.addClick(position);
                    }
                    break;
                case R.id.check_select_main_receiver:
                    mBtnClickListener.mainCheck(position);
                    tvPersonName.setTextColor(Color.parseColor(object.get(position).isMainChecked() ? "#FCA100" : "#444444"));
                    break;
                default:
                    break;
            }

        }
    }


}
