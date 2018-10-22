package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.GsonObject.GsonNotify;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.NotifyRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.newsaigonsoft.neoegov.Subjects.SumManager.FORMATP_DATE_DOCUMENT_6_1;

public class NotifyAdapter extends BaseAdapter {
    SumManager manager = new SumManager();
    Context context;
    List<GsonNotify> object;

    public NotifyAdapter(Context context, List<GsonNotify> object) {
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
        ViewHolder mViewHolder;
        if (convertView!=null){
            mViewHolder = (ViewHolder) convertView.getTag();
        }else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_notify, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvSender.setText(object.get(position).getSender());
        mViewHolder.tvBody.setText(object.get(position).getBody());
        mViewHolder.tvTime.setText(manager.getDateFromMiliSec(object.get(position).getDateSent(), FORMATP_DATE_DOCUMENT_6_1));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.notify_sender)
        TextView tvSender;
        @BindView(R.id.notify_body)
        TextView tvBody;
        @BindView(R.id.notify_time)
        TextView tvTime;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
