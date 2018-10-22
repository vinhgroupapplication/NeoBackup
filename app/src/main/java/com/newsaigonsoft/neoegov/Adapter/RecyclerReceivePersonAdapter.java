package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;

/**
 * Created by Vinh on 10/6/2017.
 */

public class RecyclerReceivePersonAdapter extends RecyclerView.Adapter<RecyclerReceivePersonAdapter.RecyclerViewHolder> {
    Context context;
    List<ReceivePerson> object;

    public RecyclerReceivePersonAdapter(Context context, List<ReceivePerson> object) {
        this.context = context;
        this.object = object;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_process_person, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.tvPositionName.setText(object.get(position).getReceiveRoleName());
        holder.cBox.setChecked(object.get(position).isDefault());
        holder.tvReceiveRoomName.setText(object.get(position).getReceiveRoomName());
        holder.tvReceivePersonName.setText(object.get(position).getReceivePersonName());
        holder.cbMainPerson.setChecked(object.get(position).isMainPerson() ? true : false);
        holder.cbMainPerson.setVisibility(object.get(position).isDefault() ? View.VISIBLE : View.INVISIBLE);
        holder.cbMainPerson.setTag(position);
        holder.cBox.setTag(position);
    }

    @Override
    public int getItemCount() {
        return object.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
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

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Optional
        @OnClick({R.id.main_person, R.id.check_person})
        public void OnClick(View itemView) {
            int position = Integer.parseInt(itemView.getTag().toString());
            switch (itemView.getId()) {
                case R.id.main_person:
                    cbMainPerson.setChecked(object.get(position).isMainPerson() ? false : true);
                    object.get(position).setMainPerson(cbMainPerson.isChecked() ? true : false);
                    break;
                case R.id.check_person:
                    boolean isChoose = cBox.isChecked();
                    object.get(position).setDefault(isChoose ? true : false);
                    cbMainPerson.setVisibility(object.get(position).isDefault() ? View.VISIBLE : View.INVISIBLE);
                    cbMainPerson.setChecked(object.get(position).isDefault() ? true : false);
                    break;
                default:
                    break;
            }
        }
    }
}
