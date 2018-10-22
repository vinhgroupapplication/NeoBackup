package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.newsaigonsoft.neoegov.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogMenuDetailAdapter extends BaseAdapter {
    List<ItemMenu> object;
    Context context;

    public DialogMenuDetailAdapter(List<ItemMenu> object, Context context) {
        this.object = object;
        this.context = context;
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
            convertView = inflater.inflate(R.layout.custom_dialog_menu_details, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvTitle.setText(object.get(position).getName());
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.icon)
        ImageView imgIcon;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }


    public class ItemMenu {


        /**
         * id : 10065
         * showChooseHandleWay : false
         * default : false
         * name : Trình lãnh đạo cơ quan
         * showChooseDueDate : false
         * type : 13
         */

        private int id;
        private boolean showChooseHandleWay;
        @SerializedName("default")
        private boolean defaultX;
        private String name;
        private boolean showChooseDueDate;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isShowChooseHandleWay() {
            return showChooseHandleWay;
        }

        public void setShowChooseHandleWay(boolean showChooseHandleWay) {
            this.showChooseHandleWay = showChooseHandleWay;
        }

        public boolean isDefaultX() {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isShowChooseDueDate() {
            return showChooseDueDate;
        }

        public void setShowChooseDueDate(boolean showChooseDueDate) {
            this.showChooseDueDate = showChooseDueDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}
