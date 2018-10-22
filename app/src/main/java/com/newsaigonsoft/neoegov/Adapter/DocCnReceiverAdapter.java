package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocReceiptConnects;
import com.newsaigonsoft.neoegov.MyInterface.OpenFileItemClick;
import com.newsaigonsoft.neoegov.OtherActivity.OtherActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.DocConnectionReciverRow;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACK_URL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BASE_64;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DIGITAL_SIGNATURE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FILE_ENTRY_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_STATUS;

/**
 * Created by Vinh on 10/2/2017.
 */

public class DocCnReceiverAdapter extends BaseAdapter {
    Context context;
    List<GsonDocReceiptConnects> object;
    private OpenFileItemClick listener;

    public DocCnReceiverAdapter(Context context, List<GsonDocReceiptConnects> object, OpenFileItemClick listener) {
        this.context = context;
        this.object = object;
        this.listener = listener;
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
            convertView = inflater.inflate(R.layout.custom_document_connnect, null);
            mViewHolder = new ViewHolder(convertView, context);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.imgUnLink.setTag(position);
        mViewHolder.tvDocumentType.setText(
                Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Số ký hiệu gốc:" +
//                        "</font> <font color=#0F89DB>" +
                        object.get(position).getNumberOfSymbol()
//                                +
//                        "</font>"
                )
        );
        mViewHolder.tvPublishCategory.setText(object.get(position).getDocNumberFull());
        mViewHolder.tvBriefContentFull.setText(
                Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Ngày ban hành:" +
//                        "</font> <font color=#000000>" +
                        object.get(position).getPublishDate()
//                                +
//                        "</font>"
                )
        );
        mViewHolder.tvDocNumberFull.setText(
                Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Loại văn bản:" +
//                        "</font> <font color=#000000>" +
                        object.get(position).getDocumentType()
//                                +
//                        "</font>"
                )
        );
        mViewHolder.tvNumberOfSymbol.setText(
                Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Cơ quan ban hành:" +
//                        "</font> <font color=#000000>" +
                        object.get(position).getPublishCategory()
//                                +
//                        "</font>"
                )
        );
        mViewHolder.tvBriefContent.setText(
                Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Trích yếu:" +
//                        "</font> <font color=#000000>" +
                        object.get(position).getBriefContent()
//                        +
//                        "</font>"
                )
        );
        mViewHolder.tvPublishDate.setText(Html.fromHtml(object.get(position).getBriefContentFull()));

        mViewHolder.tvConnecter.setText(Html.fromHtml(object.get(position).getConnecter()));
//        final ArrayList<AttachFile> arrFileAttach = new ArrayList<>();
//        try {
//            for (int i = 0; i < object.get(position).getmArrayAttachFile().length(); i++) {
//                JSONObject mObjectAttach = object.get(position).getmArrayAttachFile().getJSONObject(i);
//                String FileBase64 = mObjectAttach.getString(BASE_64);
//                String Name = mObjectAttach.getString(NAME);
//                String Type = mObjectAttach.getString(NEOTYPE);
//                String url = mObjectAttach.getString(ATTACK_URL);
//                boolean digitalSignature = false;
//                long fileEntryId = 0;
//                String status = "";
//                if (!mObjectAttach.isNull(DIGITAL_SIGNATURE) && !mObjectAttach.isNull(FILE_ENTRY_ID)
//                        && !mObjectAttach.isNull(SCHEDULE_STATUS)
//                        ) {
//                    digitalSignature = mObjectAttach.getBoolean(DIGITAL_SIGNATURE);
//                    fileEntryId = mObjectAttach.getLong(FILE_ENTRY_ID);
//                    status = mObjectAttach.getString(SCHEDULE_STATUS);
//
//                }
//                arrFileAttach.add(new AttachFile(Name, Type, url, FileBase64, digitalSignature, fileEntryId, status));
//            }
//            FileAttachAdapter adapter = new FileAttachAdapter(context, arrFileAttach);
//            mViewHolder.lvAttachFile.setAdapter(adapter);
//            mViewHolder.lvAttachFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    listener.checkRunTimePermission(position, arrFileAttach, context);
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return convertView;
    }

    class ViewHolder {
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
        @BindView(R.id.connecter)
        TextView tvConnecter;
        @BindView(R.id.list_attach_doc_connect)
        NonScrollListView lvAttachFile;
        @BindView(R.id.unlink)
        ImageView imgUnLink;
        Context mContext;

        //
        public ViewHolder(View view, Context context) {
            ButterKnife.bind(this, view);
            this.mContext = context;
        }

        @Optional
        @OnClick({R.id.unlink})
        void OnClick(View view) {
            int position = Integer.parseInt(view.getTag().toString());
            switch (view.getId()) {
                case R.id.unlink:
                    ((OtherActivity) mContext).showDialogUnLink(position);
                    break;
                default:
                    break;
            }

        }
    }
}
