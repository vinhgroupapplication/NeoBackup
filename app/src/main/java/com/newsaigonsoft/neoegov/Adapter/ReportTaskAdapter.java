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
import com.newsaigonsoft.neoegov.MyInterface.OpenFileItemClick;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACK_URL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BASE_64;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DIGITAL_SIGNATURE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FILE_ENTRY_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_STATUS;

/**
 * Created by Vinh on 10/4/2017.
 */

public class ReportTaskAdapter extends BaseAdapter {
    Context context;
    List<ReportTasksRow> object;
    private OpenFileItemClick listener;
    ModuleRow ModuleRow;

    public ReportTaskAdapter(Context context, List<ReportTasksRow> object, OpenFileItemClick listener, ModuleRow moduleRow) {
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
            convertView = inflater.inflate(R.layout.custom_document_connnect, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvDocumentType.setText(
                Html.fromHtml("<font color=#000000>" +
                        "Đơn vị:" +
                        "</font> <font color=#0F89DB>" +
                        object.get(position).getOrganizationName() +
                        "</font>"));
        mViewHolder.tvPublishCategory.setText(
                Html.fromHtml("<font color=#000000>" +
                        "Số văn bản báo cáo:" +
                        "</font> <font color=#0F89DB>" +
                        object.get(position).getDocNumberReport() +
                        "</font>"));
        mViewHolder.tvBriefContentFull.setText(
                Html.fromHtml("<font color=#000000>" +
                        "Ngày báo cáo:" +
                        "</font> <font color=#0F89DB>" +
                        object.get(position).getDateReport() +
                        "</font>"));
        mViewHolder.tvDocNumberFull.setText(
                Html.fromHtml("<font color=#000000>" +
                        "Nội dung báo cáo:" +
                        "</font> <font color=#0F89DB>" +
                        object.get(position).getContentReport() +
                        "</font>"));
        mViewHolder.tvNumberOfSymbol.setVisibility(View.GONE);
        mViewHolder.tvBriefContent.setVisibility(View.GONE);
        mViewHolder.tvPublishDate.setVisibility(View.GONE);
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
                arrFileAttach.add(new AttachFile(Name, Type, url, FileBase64, digitalSignature, fileEntryId, status, ModuleRow));
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

    static class ViewHolder {
        @BindView(R.id.documentType)
        TextView tvDocumentType;
        @BindView(R.id.publishCategory)
        TextView tvPublishCategory;
        @BindView(R.id.briefContentFull)
        TextView tvBriefContentFull;
        @BindView(R.id.docNumberFull)
        TextView tvDocNumberFull;
        @BindView(R.id.numberOfSymbol)
        TextView tvNumberOfSymbol;
        @BindView(R.id.briefContent)
        TextView tvBriefContent;
        @BindView(R.id.publishDate)
        TextView tvPublishDate;
        @BindView(R.id.list_attach_doc_connect)
        NonScrollListView lvAttachFile;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
