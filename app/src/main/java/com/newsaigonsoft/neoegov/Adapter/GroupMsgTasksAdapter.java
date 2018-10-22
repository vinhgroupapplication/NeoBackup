package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;
import com.newsaigonsoft.neoegov.Subjects.MessageTasksRow;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_SMS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNITS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SEND_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SEND_SMS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SENT_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SENT_SMS;

/**
 * Created by Vinh on 10/4/2017.
 */

public class GroupMsgTasksAdapter extends BaseAdapter {
    Context context;
    List<GroupMsgTasksRow> object;
    ArrayList<MessageTasksRow> arrMsgTasks;

    public GroupMsgTasksAdapter(Context context, List<GroupMsgTasksRow> object) {
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
        arrMsgTasks = new ArrayList<>();
        ViewHolder mViewHolder;
        if (convertView!=null){
            mViewHolder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_group_message_task, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvTitle.setText(object.get(position).getGroupName());
        try {
            JSONArray mJsonArray = object.get(position).getmJsonObject().getJSONArray(EXECUTION_UNITS);
            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                boolean sendSMS = mJsonObject.getBoolean(SEND_SMS);
                boolean sendEmail = mJsonObject.getBoolean(SEND_EMAIL);
                boolean sentSMS = mJsonObject.getBoolean(SENT_SMS);
                boolean sentEmail = mJsonObject.getBoolean(SENT_EMAIL);
                String executionUnit = mJsonObject.getString(EXECUTION_UNIT);
                String contentSMS = mJsonObject.getString(CONTENT_SMS);
                String contentEmail = mJsonObject.getString(CONTENT_EMAIL);
                arrMsgTasks.add(new MessageTasksRow(sendSMS, sendEmail, sentSMS, sentEmail, executionUnit, contentSMS, contentEmail));
            }
            MsgTaskAdapter adapter = new MsgTaskAdapter(context, arrMsgTasks);
            mViewHolder.lvMsgTasks.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }

     class ViewHolder{
        @BindView(R.id.group_message_title)
        TextView tvTitle;
        @BindView(R.id.list_group_message_tasks)
        NonScrollListView lvMsgTasks;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
