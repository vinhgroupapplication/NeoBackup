package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.newsaigonsoft.neoegov.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class GuideMenuAdapter extends BaseAdapter implements View.OnClickListener {

    List<Items> object;
    Context context;

    public GuideMenuAdapter(List<Items> object, Context context) {
        this.object = object;
        this.context = context;
    }

    public GuideMenuAdapter() {
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
        convertView = inflater.inflate(R.layout.custom_guide_list, null);
        TextView tvMenuName = (TextView) convertView.findViewById(R.id.menu_name);
        RelativeLayout rltItems = (RelativeLayout) convertView.findViewById(R.id.item);
        tvMenuName.setText(object.get(position).getMenuName());
        tvMenuName.setTag(position);
        rltItems.setTag(position);
        rltItems.setOnClickListener(this);
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

        public Items(String menuName) {
            this.menuName = menuName;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }
    }


}
