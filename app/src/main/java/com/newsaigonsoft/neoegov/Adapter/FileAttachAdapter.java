package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FILE_ENTRY_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DIGITAL_SIGNATURE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOC;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCX;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PDF;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PPT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PPTX;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.XLSX;

/**
 * Created by VinhCN on 4/25/2017.
 */

public class FileAttachAdapter extends BaseAdapter {
    Context context;
    ArrayList<AttachFile> object;
    SumManager manager = new SumManager();

    public FileAttachAdapter(Context context, ArrayList<AttachFile> object) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView!=null){
            mViewHolder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_file_attach_row, null);
            mViewHolder = new ViewHolder(convertView, object);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvNameAttach.setText(object.get(position).getFileName());
        // get extension file
        int i = object.get(position).getFileName().lastIndexOf('.');
        String extension = object.get(position).getFileName().substring(i + 1);
//        switch (object.get(position).getFileTyPe()) {
        switch (extension) {
            case PDF:
                mViewHolder.imgIconAttach.setImageResource(R.drawable.pdfdoc_2x);
                break;
            case DOC:
                mViewHolder.imgIconAttach.setImageResource(R.drawable.icon_word);
                break;
            case DOCX:
                mViewHolder.imgIconAttach.setImageResource(R.drawable.wordx);
                break;
            case XLSX:
                mViewHolder.imgIconAttach.setImageResource(R.drawable.excel);
                break;
            case PPT:
            case PPTX:
                mViewHolder.imgIconAttach.setImageResource(R.drawable.power_point);
                break;
            default:
                mViewHolder.imgIconAttach.setImageResource(R.drawable.attach_file_icon);
                break;
        }
        mViewHolder.imgRemove.setVisibility(object.get(position).getFileOnlyName() == null ? View.GONE : View.VISIBLE);
        if (object.get(position).isDigitalSignature()) {
            mViewHolder.btnSignature.setVisibility(View.VISIBLE);
            if (object.get(position).getStatus().equals("")) {
                mViewHolder.tvStatusSignature.setVisibility(View.GONE);
            } else {
                mViewHolder.tvStatusSignature.setVisibility(View.VISIBLE);
                mViewHolder.tvStatusSignature.setText("Đã ký số");
            }
        } else {
            mViewHolder.btnSignature.setVisibility(View.GONE);
            mViewHolder.tvStatusSignature.setVisibility(View.GONE);
        }
        mViewHolder.btnSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long FileType = object.get(position).getFileEntryId();
                Log.d("asddas", FileType + "");
                AttachFile mAttachFile = new AttachFile(
                        object.get(position).getFileName(),
                        object.get(position).getFileTyPe(),
                        object.get(position).getFileURL(),
                        object.get(position).getBase64Code(),
                        object.get(position).isDigitalSignature(),
                        object.get(position).getFileEntryId(),
                        object.get(position).getStatus(),
                        object.get(position).getModuleRow()
                        );
                manager.showProgressDialog("", context, DIALOG_CENTER, true);
                new DigitalSignatureRequest().execute(mAttachFile);
            }
        });
        mViewHolder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class DigitalSignatureRequest extends AsyncTask<AttachFile, Integer, String> {

        @Override
        protected String doInBackground(AttachFile... params) {
            manager.getInforAccountFromShareReferenced(context);
            String UserName = manager.getUserid();
            String Pass = manager.getPass();
            String Link = manager.getModuleInfor(MODULE_PROCESSING_WORKING, context).getServerUrl();
            long FileType = params[0].getFileEntryId();
            Log.d("VinhCNLog: ", Link + UserName + Pass + FileType);
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
                mJsonObject.put(NEOTYPE, TYPE_DIGITAL_SIGNATURE);
                JSONObject mData = new JSONObject();
                mData.put(FILE_ENTRY_ID, params[0].getFileEntryId());
                mJsonObject.put(DATA, mData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return manager.eventPostRequest(manager.getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), UserName, Pass);
        }


        @Override
        protected void onPostExecute(String s) {
            Log.d("VinhCNLog: ", s);
            try {
                JSONObject mJsonObject = new JSONObject(s);
                boolean Resuilt = mJsonObject.getBoolean(DATA);
                if (Resuilt) {
                    manager.showDialogUpdateError("Ký số thành công", context);
                } else {
                    manager.showDialogUpdateError("Ký số thất bại", context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            manager.closeProgressDialog();
            super.onPostExecute(s);
        }
    }

     class ViewHolder{
        @BindView(R.id.attach_name)
        TextView tvNameAttach;
        @BindView(R.id.icon_attach_file)
        ImageView imgIconAttach;
        @BindView(R.id.remove)
        ImageView imgRemove;
        @BindView(R.id.btn_signature)
        TextView btnSignature;
        @BindView(R.id.status_signature)
        TextView tvStatusSignature;
        public ViewHolder(View view,ArrayList<AttachFile> Object ) {
            ButterKnife.bind(this,view);
        }
    }
}
