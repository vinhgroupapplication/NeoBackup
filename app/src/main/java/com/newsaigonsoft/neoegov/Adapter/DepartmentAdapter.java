package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Vinh on 9/6/2017.
 */

public class DepartmentAdapter extends ArrayAdapter {

    public DepartmentAdapter(Context theContext, ArrayList<String> objects, int theLayoutResId) {
        super(theContext, theLayoutResId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
//        int count = super.getCount();
        //display last item
        return super.getCount();
    }
}
