package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.MenuOtherRow;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Vinh on 10/9/2017.
 */

public class MenuOtherAdapter extends BaseAdapter {

    Context context;
    List<MenuOtherRow> object;

    public MenuOtherAdapter(Context context, List<MenuOtherRow> object) {
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_menu_other, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.imgMenu.setImageResource(object.get(position).getMenuImage());
        mViewHolder.tvMenuName.setText(object.get(position).getMenuName());
        return convertView;
    }

     class ViewHolder{
        @BindView(R.id.icon_menu)
        ImageView imgMenu;
        @BindView(R.id.menu_name)
        TextView tvMenuName;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
