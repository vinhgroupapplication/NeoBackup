package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ChildMenu;
import com.newsaigonsoft.neoegov.Subjects.ChildMenuHolder;
import com.newsaigonsoft.neoegov.Subjects.HeaderHolder;
import com.newsaigonsoft.neoegov.Subjects.HeaderMenu;

/**
 * Created by Vinh on 12/4/2017.
 */

public class SlideMenuAdapter extends ExpandableRecyclerViewAdapter<HeaderHolder, ChildMenuHolder> {
    // stop multiclick
//        mRecyclerView.setMotionEventSplittingEnabled(false);
    Context context;
    List<? extends ExpandableGroup> Group;
    //tempory sulution command out for future
//    private static final long CLICK_TIME_INTERVAL = 300;
//    private long mLastClickTime = System.currentTimeMillis();

    public SlideMenuAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.context = context;
        this.Group = groups;
    }

    @Override
    public HeaderHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_slider_menu_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public ChildMenuHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_slider_menu_child, parent, false);
        return new ChildMenuHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ChildMenuHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final ChildMenu child = (ChildMenu) group.getItems().get(childIndex);
        holder.setChildName(child.getMenuName());
        holder.setChildCount(child.getMenuCount());
        holder.mLnChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tempory sulution command out for future
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return;
//                }
//                mLastClickTime = now;
                ((OfficalActivity) context).SliderChildClick(child.getMenuCode());
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(HeaderHolder holder, int flatPosition, ExpandableGroup group) {
        final HeaderMenu healder = (HeaderMenu) group;
        holder.setHeaderName(healder.getTitle());
        if (isGroupExpanded(flatPosition) && group.getItemCount() != 0) {
            holder.setHeaderIcon(R.drawable.ic_play_arrow_down_white_24dp, group.getItemCount());
        } else {
            holder.setHeaderIcon(healder.getMenuIcon(), group.getItemCount());
        }
        holder.setHeaderCount(healder.getMenuCount(), healder.getTitle());
    }

//    @Override
//    public void onGroupExpanded(int positionStart, int itemCount) {
//        int headerPosition = positionStart - 1;
//        int groupIndex = 0;
//        notifyItemChanged(headerPosition);
//        if (itemCount > 0) {
//            notifyItemRangeInserted(positionStart, itemCount);
//            groupIndex = expandableList.getUnflattenedPosition(positionStart).groupPos;
////            Toast.makeText(context, "" + groupIndex, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "" + getGroups().get(groupIndex).getTitle(), Toast.LENGTH_SHORT).show();
//        } else {
//            notifyItemRangeInserted(positionStart, itemCount);
//            groupIndex = expandableList.getUnflattenedPosition(positionStart - 1).groupPos;
////            Toast.makeText(context, "" + (groupIndex - 1), Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "" + getGroups().get(groupIndex).getTitle(), Toast.LENGTH_SHORT).show();
//        }
//    }


    @Override
    public boolean onGroupClick(int flatPos) {
        //tempory sulution command out for future
//        long now = System.currentTimeMillis();
//        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//        } else {
//            mLastClickTime = now;
        int groupIndex = expandableList.getUnflattenedPosition(flatPos).groupPos;
        HeaderMenu mHolder = (HeaderMenu) Group.get(groupIndex);
        ((OfficalActivity) context).SliderGroupClick(mHolder.getMenuCode());
//        }
        return super.onGroupClick(flatPos);
    }

}
