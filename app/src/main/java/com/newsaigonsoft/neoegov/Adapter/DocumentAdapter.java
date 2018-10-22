package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocument;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.w3c.dom.Text;

import pl.droidsonroids.gif.GifTextView;

/**
 * Created by VinhCN on 4/22/2017.
 */
public class DocumentAdapter extends BaseAdapter {
    Context context;
    List<GsonDocument.DataBean> object;
    SumManager manager = new SumManager();

    public DocumentAdapter(Context context, List<GsonDocument.DataBean> object) {
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
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_document, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.tvTitle.setText(Html.fromHtml("<font color=#000000>" + getTitleBeforeSpecical(String.valueOf(Html.fromHtml(object.get(position).getTitle()))) + "</font> <font color=#000000>" + getTitleAfterSpecical(String.valueOf(Html.fromHtml(object.get(position).getTitle()))) + "</font>"));
        viewHolder.tvSumary.setText(Html.fromHtml(object.get(position).getSummary()));
        viewHolder.tvDay.setText(Html.fromHtml(object.get(position).getReceiveDate()));
        viewHolder.imgVBMang.setVisibility(object.get(position).isOverNetwork() ? View.VISIBLE : View.GONE);
        viewHolder.tvAttitude.setText("Mới");
        viewHolder.tvAttitude.setVisibility(object.get(position).isNewX() ? View.VISIBLE : View.GONE);
        viewHolder.tvTitle.setTextColor(object.get(position).isNewX() ? Color.parseColor("#18b6ff") : Color.parseColor("#000000"));
        viewHolder.tvUrgency.setText(object.get(position).getUrgency());
        manager.setBGColorDrawable(viewHolder.tvAttitude, "#18b6ff");
        manager.setBGColorDrawable(viewHolder.tvUrgency, "#a657ff");
        viewHolder.tvNumberSymbol.setText("Số ký hiệu: " + object.get(position).getNumberSymbol());
        viewHolder.imgAttachIcon.setVisibility(!object.get(position).isHasAttachFile() ? View.GONE : View.VISIBLE);
        viewHolder.tvUrgency.setVisibility(object.get(position).getUrgency().length() == 0 ? View.GONE : View.VISIBLE);
//        //To change the stroke color
//        int width_px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, youStrokeWidth, getResources().getDisplayMetrics());
//        gd.setStroke(width_px, yourColor);
        switch (object.get(position).getStatus()) {
            case 1:
                viewHolder.tvStatusIcon.setText("Sắp quá hạn");
                viewHolder.tvStatusIcon.setVisibility(View.VISIBLE);
                manager.setBGColorDrawable(viewHolder.tvStatusIcon, "#ff9600");
                break;
            case 2:
                viewHolder.tvStatusIcon.setText("Quá hạn");
                viewHolder.tvStatusIcon.setVisibility(View.VISIBLE);
                manager.setBGColorDrawable(viewHolder.tvStatusIcon, "#ff6367");
                break;
            default:
                viewHolder.tvStatusIcon.setVisibility(View.GONE);
                break;

        }
        viewHolder.tvSumary.setTextColor(object.get(position).isViewed() ? Color.parseColor("#867f7f") : Color.parseColor("#000000"));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.title_document)
        TextView tvTitle;
        @BindView(R.id.sumary_document)
        TextView tvSumary;
        @BindView(R.id.day_document)
        TextView tvDay;
        @BindView(R.id.attitude)
        TextView tvAttitude;
        @BindView(R.id.urgency_icon)
        TextView tvUrgency;
        @BindView(R.id.vbmang_icon)
        GifTextView imgVBMang;
        @BindView(R.id.type_icon)
        TextView tvStatusIcon;
        @BindView(R.id.attach_icon)
        ImageView imgAttachIcon;
        @BindView(R.id.number_symbol)
        TextView tvNumberSymbol;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    String getTitleAfterSpecical(String title) {
        String titleAfterSpecical = title.substring(title.lastIndexOf(":") + 1);
        return titleAfterSpecical;
    }

    String getTitleBeforeSpecical(String title) {
        int iend = title.indexOf(":");
        String titleBeforeSpecical = title.substring(0, iend + 1);
        return titleBeforeSpecical;
    }


}
