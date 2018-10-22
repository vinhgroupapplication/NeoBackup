package com.newsaigonsoft.neoegov.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.R;

/**
 * Created by VinhCN on 4/21/2017.
 */

public class NumberPageAdapter extends RecyclerView.Adapter<NumberPageAdapter.MyViewHolder> {
    ArrayList<String> object;
    public NumberPageAdapter(ArrayList<String> object) {
        this.object = object;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView numberPage;

        public MyViewHolder(View view) {
            super(view);
            numberPage = (TextView) view.findViewById(R.id.number_page_button);
        }


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_number_page, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.numberPage.setText(object.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return object.size();
    }


}

