package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;

import org.w3c.dom.Text;

import java.util.List;

public class InforAdapter extends BaseAdapter implements View.OnClickListener {

    List<InforAdapter.Items> object;
    Context context;

    public InforAdapter(List<Items> object, Context context) {
        this.object = object;
        this.context = context;
    }

    public InforAdapter() {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_infor_list, null);
        TextView tvMenuName = (TextView) convertView.findViewById(R.id.menu_name);
        TextView tvVerson = (TextView) convertView.findViewById(R.id.verson);
        ImageView imgArrow = (ImageView) convertView.findViewById(R.id.arrow);
        imgArrow.setVisibility(object.get(position).isShowVerson() ? View.GONE : View.VISIBLE);
        tvVerson.setVisibility(object.get(position).isShowVerson() ? View.VISIBLE : View.GONE);
        tvMenuName.setText(object.get(position).getMenuName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
//        int position = Integer.parseInt(v.getTag().toString());
//        switch (v.getId()) {
//            case R.id.item:
//                Toast.makeText(context, context.getString(R.string.building_function), Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//
//        }
    }

    public class Items {
        String menuName;
        boolean ShowVerson;

        public Items(String menuName, boolean showVerson) {
            this.menuName = menuName;
            ShowVerson = showVerson;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public boolean isShowVerson() {
            return ShowVerson;
        }

        public void setShowVerson(boolean showVerson) {
            ShowVerson = showVerson;
        }
    }


}