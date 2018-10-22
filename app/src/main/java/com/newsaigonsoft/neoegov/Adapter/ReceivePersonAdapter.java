package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.widget.CompoundButtonCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.MyInterface.AdapterButtonClick;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_14;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REPORT_RESUILT;

/**
 * Created by VinhCN on 5/3/2017.
 */

public class ReceivePersonAdapter extends BaseAdapter {
    Context context;
    ArrayList<ReceivePerson> object;
    ArrayList<ReceivePerson> objectTempolary;
    int TapForward;
    AdapterButtonClick mBtnClickListener = null;

    public ReceivePersonAdapter(Context context, ArrayList<ReceivePerson> object, ArrayList<ReceivePerson> objectTempolary, int tapForward, AdapterButtonClick listener) {
        this.context = context;
        this.object = object;
        this.objectTempolary = objectTempolary;
        this.TapForward = tapForward;
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
        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_process_person, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.tvPositionName.setText(object.get(position).getReceiveRoleName());
        viewHolder.cBox.setChecked(object.get(position).isDefault());
        viewHolder.tvReceiveRoomName.setText(object.get(position).getReceiveRoomName());
        viewHolder.tvReceivePersonName.setText(object.get(position).getReceivePersonName());
        viewHolder.cBox.setClickable(true);
        viewHolder.cbMainPerson.setChecked((object.get(position).isMainPerson() || objectTempolary.get(position).isMainPerson()) ? true : false);
        viewHolder.cbMainPerson.setVisibility(object.get(position).isDefault() ? View.VISIBLE : View.INVISIBLE);
        viewHolder.cBox.setTag(position);
        viewHolder.cbMainPerson.setTag(position);
        switch (TapForward) {
            case TAP_FORWARD_PERSON:
            case TAP_REPORT_RESUILT:
            case TAP_14:
                break;
            case TAP_FORWARD_RELEASE:
                viewHolder.cBox.setBackgroundResource(R.drawable.checkbox_green);
                viewHolder.cBox.setButtonDrawable(null);
                viewHolder.cBox.setWidth(35);
                viewHolder.cBox.setHeight(35);
                viewHolder.cbMainPerson.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
        viewHolder.cbMainPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.cbMainPerson.isChecked()) {
                    switch (TapForward) {
                        case TAP_14:
                            tap14CheckEvent(position);
                            break;
                        default:
                            object.get(position).setMainPerson(true);
                            objectTempolary.get(position).setMainPerson(true);
                            break;
                    }
                } else {
                    switch (TapForward) {
                        case TAP_FORWARD_RELEASE:
                            object.get(position).setMainPerson(true);
                            objectTempolary.get(position).setMainPerson(true);
                            break;
                        default:
                            object.get(position).setMainPerson(false);
                            objectTempolary.get(position).setMainPerson(false);
                            break;
                    }
                }
                notifyDataSetChanged();

            }
        });

        return convertView;
    }


    public void setCheckBoxColor(CheckBox checkBox, int checkedColor, int uncheckedColor) {
        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {checkedColor, uncheckedColor};
        CompoundButtonCompat.setButtonTintList(checkBox, new
                ColorStateList(states, colors));
    }

    private void tap14CheckEvent(int position) {
        for (int i = 0; i < object.size(); i++) {
            if (object.get(position).getReceivePersonID() == object.get(i).getReceivePersonID()) {
                object.get(i).setMainPerson(true);
                objectTempolary.get(i).setMainPerson(true);
            } else {
                object.get(i).setMainPerson(false);
                objectTempolary.get(i).setMainPerson(false);
            }
        }
    }


    class ViewHolder {
        @BindView(R.id.check_person)
        CheckBox cBox;
        @BindView(R.id.person_name)
        TextView tvReceivePersonName;
        @BindView(R.id.room_name)
        TextView tvReceiveRoomName;
        @BindView(R.id.position_name)
        TextView tvPositionName;
        @BindView(R.id.main_person)
        CheckBox cbMainPerson;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Optional
        @OnClick({R.id.check_person})
        public void onClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.check_person:
                    if (mBtnClickListener != null)
                        mBtnClickListener.mainCheck(position);
                    break;
            }
        }

    }


}
