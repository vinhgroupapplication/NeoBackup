package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.MyInterface.OpenFileItemClick;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACK_URL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BASE_64;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DIGITAL_SIGNATURE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FILE_ENTRY_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_STATUS;

/**
 * Created by Vinh on 9/23/2017.
 */

public class FeedBackAdapter extends BaseAdapter {

    Context context;
    List<FeedBackRow> object;
    private OpenFileItemClick listener;
    ModuleRow ModuleRow;

    public FeedBackAdapter(Context context, List<FeedBackRow> object, OpenFileItemClick listener, ModuleRow moduleRow) {
        this.context = context;
        this.object = object;
        this.listener = listener;
        this.ModuleRow = moduleRow;
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
            convertView = inflater.inflate(R.layout.custom_feed_back, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvContent.setText(Html.fromHtml("<font color=#000000>" +
                "Nội dung:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getContent() +
                "</font>"));
        mViewHolder.tvOrganizationName.setText(Html.fromHtml("<font color=#000000>" +
                "Đơn vị:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getOrganizationName() +
                "</font>"));
        mViewHolder.tvUserName.setText(Html.fromHtml("<font color=#000000>" +
                "Người góp ý:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getUserName() +
                "</font>"));
        mViewHolder.tvCreateDate.setText(Html.fromHtml("<font color=#000000>" +
                "Ngày tạo:" +
                "</font> <font color=#0F89DB>" +
                object.get(position).getCreateDate() +
                "</font>"));
        final ArrayList<AttachFile> arrFileAttach = new ArrayList<>();
        try {
            for (int i = 0; i < object.get(position).getmArrayAttachFile().length(); i++) {
                JSONObject mObjectAttach = object.get(position).getmArrayAttachFile().getJSONObject(i);
                String FileBase64 = mObjectAttach.getString(BASE_64);
                String Name = mObjectAttach.getString(NAME);
                String Type = mObjectAttach.getString(NEOTYPE);
                String url = mObjectAttach.getString(ATTACK_URL);
                boolean digitalSignature = false;
                long fileEntryId = 0;
                String status = "";
                if (!mObjectAttach.isNull(DIGITAL_SIGNATURE) && !mObjectAttach.isNull(FILE_ENTRY_ID)
                        && !mObjectAttach.isNull(SCHEDULE_STATUS)
                        ) {
                    digitalSignature = mObjectAttach.getBoolean(DIGITAL_SIGNATURE);
                    fileEntryId = mObjectAttach.getLong(FILE_ENTRY_ID);
                    status = mObjectAttach.getString(SCHEDULE_STATUS);

                }
                arrFileAttach.add(new AttachFile(Name, Type, url, FileBase64, digitalSignature, fileEntryId,status, ModuleRow));
            }
            FileAttachAdapter adapter = new FileAttachAdapter(context, arrFileAttach);
            mViewHolder.lvAttachFile.setAdapter(adapter);
            mViewHolder.lvAttachFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listener.checkRunTimePermission(position, arrFileAttach, context);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }

     class ViewHolder{
        @BindView(R.id.content)
        TextView tvContent;
        @BindView(R.id.organization_name)
        TextView tvOrganizationName;
        @BindView(R.id.user_name)
        TextView tvUserName;
        @BindView(R.id.create_date)
        TextView tvCreateDate;
        @BindView(R.id.list_attach_doc_connect)
        NonScrollListView lvAttachFile;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
