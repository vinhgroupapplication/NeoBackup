package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainferSelectAdapter extends BaseAdapter {

    Context context;
    List<TrainferItems> object;

    public TrainferSelectAdapter(Context context, List<TrainferItems> object) {
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
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_dialog_select_add_trainfer, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvTitle.setText(object.get(position).getTitle());
        mViewHolder.imgSelect.setVisibility(object.get(position).isSelect() ? View.VISIBLE : View.INVISIBLE);
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.img_selected)
        ImageView imgSelect;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }

    public static class TrainferItems {
        String title;
        boolean Select;

        public TrainferItems(String title, boolean select) {
            this.title = title;
            Select = select;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isSelect() {
            return Select;
        }

        public void setSelect(boolean select) {
            Select = select;
        }
    }


}
