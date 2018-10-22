package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ItemsMenu;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by VinhCN on 4/7/2017.
 */

public class ListMenuAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<ItemsMenu> arrParent;
    HashMap<ItemsMenu, List<ItemsMenu>> mDataChild;
    //test
    SumManager manager = new SumManager();

    public ListMenuAdapter(Context context, ArrayList<ItemsMenu> arrParent, HashMap<ItemsMenu, List<ItemsMenu>> mDataChild) {
        this.context = context;
        this.arrParent = arrParent;
        this.mDataChild = mDataChild;
    }

    @Override
    public int getGroupCount() {
        return arrParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataChild.get(arrParent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrParent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataChild.get(arrParent.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_list_slider_menu_header, parent, false);
        TextView tvHeader = (TextView) convertView.findViewById(R.id.menu_name);
        Spanned textx = Html.fromHtml(arrParent.get(groupPosition).getMenuName());
        String xxx = textx.toString();
        String number = manager.getOnlyNumerics(xxx);
        tvHeader.setText(xxx.replace(number, ""));
        TextView tvNumberLableCountHeader = (TextView) convertView.findViewById(R.id.number_lable_count_header);
        if (arrParent.get(groupPosition).getMenuCount() > 0) {
            tvNumberLableCountHeader.setText(arrParent.get(groupPosition).getMenuCount() + "");
        } else {
            tvNumberLableCountHeader.setText(number + "");
        }
//
        if (tvNumberLableCountHeader.getText().toString().equals("") || tvNumberLableCountHeader.getText().toString().equals("0")) {
            tvNumberLableCountHeader.setVisibility(View.INVISIBLE);
        }
        ImageView imgHeader = (ImageView) convertView.findViewById(R.id.icon_menu);
        String urlNa = arrParent.get(groupPosition).getMenuCode();
        String NameCompare = arrParent.get(groupPosition).getMenuName();
        imgHeader.setImageResource(arrParent.get(groupPosition).getMenuIcon());
        if (!isExpanded){
            imgHeader.setImageResource(arrParent.get(groupPosition).getMenuIcon());
        }else {
            if (getChildrenCount(groupPosition)!=0){
                imgHeader.setImageResource(R.drawable.ic_play_arrow_down_white_24dp);
            }else {
                imgHeader.setImageResource(arrParent.get(groupPosition).getMenuIcon());
            }
        }
//        String urlWeb = arrParent.get(groupPosition).getUrlMenu();

        // expand and collaps listener
//        if (isExpanded) {
//            if (urlNa.equals("") && !urlWeb.contains("/group/mobile/tra-cuu/van-ban-den") &&
//                    !urlWeb.contains("/group/mobile/tra-cuu/van-ban-di") &&
//                    !urlWeb.contains("/group/mobile/tra-cuu/van-ban-noi-bo") &&
//                    !urlWeb.contains("/group/mobile/thong-ke/van-ban-den") &&
//                    !NameCompare.contains("TRANG CHỦ") &&
//                    !NameCompare.contains("LỊCH CÔNG TÁC") &&
//                    !NameCompare.contains("XỬ LÝ CÔNG VIỆC") &&
//                    !NameCompare.contains("TRA CỨU") &&
//                    !NameCompare.contains("THỐNG KÊ")) {
//                imgHeader.setImageResource(R.drawable.ic_play_arrow_down_white_24dp);
//                imgHeader.setVisibility(View.VISIBLE);
//            } else {
//                    imgHeader.setImageResource(arrParent.get(groupPosition).getMenuIcon());
//            }
//        } else {
//            imgHeader.setImageResource(arrParent.get(groupPosition).getMenuIcon());
//        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_list_slider_menu_child, parent, false);
        TextView tvChildText = (TextView) convertView.findViewById(R.id.menu_name_child);
        tvChildText.setText(((ItemsMenu) getChild(groupPosition, childPosition)).getMenuName());
        TextView tvNumberLableCount = (TextView) convertView.findViewById(R.id.number_lable_count);
        tvNumberLableCount.setText(((ItemsMenu) getChild(groupPosition, childPosition)).getMenuCount() + "");
        if (tvNumberLableCount.getText().toString().equals("0")) {
            tvNumberLableCount.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
