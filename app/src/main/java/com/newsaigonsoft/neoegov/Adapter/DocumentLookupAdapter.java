package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocumentLookUp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 7/5/2017.
 */

public class DocumentLookupAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemsDocumentLookUp> object;


    public DocumentLookupAdapter(Context context, ArrayList<ItemsDocumentLookUp> object) {
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
        boolean isTaskScreen = false;
        ViewHolder mViewHolder;
        if (convertView!=null){
            mViewHolder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_document_lookup, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        switch (object.get(position).getIsScreen()) {
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LOOKUP_COMING:
            case WORK_ARISE:
            case TYPE_HOME_LIST_DOCUMENT_COMING:
                if (object.get(position).getReceiveDate()!=null && object.get(position).getReceiveDate()!=nULL_STRING){
                    mViewHolder.tvReceiverDate.setVisibility(View.VISIBLE);
                    mViewHolder.tvReceiverDate.setText(object.get(position).getReceiveDate());
                }
                mViewHolder.tvProcessPosition.setVisibility(View.GONE);
                mViewHolder.tvTitleDocument.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Số ký hiệu gốc:" +
//                        "</font> <font color=#ff0000>" +
                        object.get(position).getNumberOfSymbol()
//                                +
//                        "</font>"
                ));
                mViewHolder.tvNumBerComing.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Số đến:" +
//                        "</font> <font color=#0000FF>" +
                        object.get(position).getDocNumberFull()
//                                +
//                        "</font>"
                ));
                break;
            case LOOKUP_SENT:
            case TYPE_HOME_LIST_DOCUMENT_SENT:
                mViewHolder.tvProcessPosition.setVisibility(View.GONE);
                mViewHolder.tvTitleDocument.setVisibility(View.GONE);
                mViewHolder.tvNumBerComing.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Số phát hành:" +
//                        "</font> <font color=#0000FF>" +
                        object.get(position).getDocNumberFull()
//                                +
//                        "</font>"
                ));
                if (object.get(position).getReceiveDate()!=null && object.get(position).getReceiveDate()!=nULL_STRING){
                    mViewHolder.tvReceiverDate.setVisibility(View.VISIBLE);
                    mViewHolder.tvReceiverDate.setText(object.get(position).getReceiveDate());
                }
                String content = object.get(position).getBriefContent().toString();
                Log.d(TAG, content);
                break;
            case LOOKUP_INTERNAL:
                if (object.get(position).getReceiveDate()!=null && object.get(position).getReceiveDate()!=nULL_STRING){
                    mViewHolder.tvReceiverDate.setVisibility(View.VISIBLE);
                    mViewHolder.tvReceiverDate.setText(object.get(position).getReceiveDate());
                }
                mViewHolder.tvProcessPosition.setVisibility(View.GONE);
                mViewHolder.tvTitleDocument.setVisibility(View.GONE);
                mViewHolder.tvNumBerComing.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Số văn bản:" +
//                        "</font> <font color=#0000FF>" +
                        object.get(position).getDocNumberFull()
//                                +
//                        "</font>"
                ));
                break;
            default:
                if (object.get(position).getReceiveDate()!=null && object.get(position).getReceiveDate()!=nULL_STRING){
                    mViewHolder.tvReceiverDate.setVisibility(View.VISIBLE);
                    mViewHolder.tvReceiverDate.setText(object.get(position).getReceiveDate());
                }
                mViewHolder.tvProcessPosition.setVisibility(View.VISIBLE);
                mViewHolder.tvNumBerComing.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Số phát hành:" +
//                        "</font> <font color=#0000FF>" +
                        object.get(position).getNumberOfSymbol()
//                                +
//                        "</font>"
                ));
                mViewHolder.tvTitleDocument.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Người chỉ đạo:" +
//                        "</font> <font color=#000000>" +
                        object.get(position).getDocNumberFull()
//                                +
//                        "</font>"
                ));
                mViewHolder.tvProcessPosition.setText(Html.fromHtml(
//                        "<font color=#000000>" +
//                        "Đơn vị thực hiện:" +
//                        "</font> <font color=#000000>" +
                        object.get(position).getBriefContent()
//                                +
//                        "</font>"
                ));
                isTaskScreen = true;
                break;
        }
        mViewHolder.tvSumaryDocument.setText(isTaskScreen ? Html.fromHtml(object.get(position).getIsScreen().toString()) : Html.fromHtml(object.get(position).getBriefContent().toString()));
        return convertView;
    }

     class ViewHolder{
        @BindView(R.id.number_coming)
        TextView tvNumBerComing;
        @BindView(R.id.sumary_document)
        TextView tvSumaryDocument;
        @BindView(R.id.title_document)
        TextView tvTitleDocument;
        @BindView(R.id.process_position)
        TextView tvProcessPosition;
        @BindView(R.id.day_document)
        TextView tvReceiverDate;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
