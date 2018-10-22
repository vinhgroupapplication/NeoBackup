package com.newsaigonsoft.neoegov.Fragment.ForwardTree;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.R;
import com.unnamed.b.atv.model.TreeNode;

import pl.droidsonroids.gif.GifTextView;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by VinhCN on 4/27/2017.
 */

public class TreeHolder extends TreeNode.BaseNodeViewHolder<TreeHolder.IconTreeItem> {
    ImageView img;
    TextView tvValue, tvYellowText;
    long mLastClickTime = System.currentTimeMillis();
    public boolean clicked = false;
    GifTextView iconStar;

    public TreeHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, final IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.custom_tree_forward, null, false);
        img = (ImageView) view.findViewById(R.id.icon_tree);
        tvValue = (TextView) view.findViewById(R.id.text_tree);
        tvYellowText = (TextView) view.findViewById(R.id.text_yellow);
        iconStar = (GifTextView) view.findViewById(R.id.ic_star);
        iconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    tvYellowText.setVisibility(View.VISIBLE);
                    clicked = true;
                } else {
                    tvYellowText.setVisibility(View.INVISIBLE);
                    clicked = false;
                }

            }
        });
        GifTextView giftStart = (GifTextView) view.findViewById(R.id.ic_star);
        img.setImageResource(value.icon);
        if (value.icon != 0) {
            img.setBackgroundResource(R.drawable.oval_border);
        }
        tvValue.setText(Html.fromHtml(value.getName()));
        if (value.isShowStar()) {
            giftStart.setVisibility(View.VISIBLE);
        } else {
            giftStart.setVisibility(View.GONE);
        }


        tvValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                if (now - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = now;
                showDialogSetting(value);
//                Toast.makeText(context, value.getName() + "\n" +
//                        value.getmPosition() + "\n" +
//                        value.getDepartment() + "\n" +
//                        value.getRequestProcessing() + "\n" +
//                        value.getProcessingDay() + "\n" +
//                        value.getReceivedDay()  , Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void showDialogSetting(IconTreeItem value) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        final Dialog mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_details_process);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imgCancel = (ImageView) mDialog.findViewById(R.id.diss_miss);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        TextView tvProccessPerson = (TextView) mDialog.findViewById(R.id.process_person);
        tvProccessPerson.setText(Html.fromHtml(value.getName()));
        TextView tvReciverDay = (TextView) mDialog.findViewById(R.id.reciver_day);
        tvReciverDay.setText(Html.fromHtml(value.getReceivedDay()));
        TextView tvProccessDay = (TextView) mDialog.findViewById(R.id.process_day);
        tvProccessDay.setText(Html.fromHtml(value.getProcessingDay()));
        TextView tvRequestProccess = (TextView) mDialog.findViewById(R.id.request_process);
        String RequestProcessing = String.valueOf(Html.fromHtml(value.getRequestProcessing()));
        tvRequestProccess.setText(Html.fromHtml(value.getRequestProcessing()));
        if (RequestProcessing.equals("N/A") || RequestProcessing.equals(nULL_STRING)) {
            tvRequestProccess.setText("--");
        }
        TextView tvDepartment = (TextView) mDialog.findViewById(R.id.department);
        tvDepartment.setText(Html.fromHtml(value.getDepartment()));
        TextView tvPositionEmployee = (TextView) mDialog.findViewById(R.id.position_staff);
        tvPositionEmployee.setText(Html.fromHtml(value.getmPosition()));
        mDialog.show();
    }

    //for toggle image on click ////
    public void toggle(boolean active) {
        Drawable imgID = img.getDrawable();
        Log.d("VinhCN: ", imgID + "");
        if (imgID != null) {
            img.setImageResource(active ? R.drawable.baseline_expand_more_24 : R.drawable.baseline_chevron_right_24);
        } else {
            img.setImageResource(active ? 0 : 0);
        }
    }

    public static class IconTreeItem {
        public int icon;
        public String Name;
        public String mPosition;
        public String Department;
        public String RequestProcessing;
        public String ProcessingDay;
        public String ReceivedDay;
        public boolean ShowStar;

        public IconTreeItem(int icon, String name, String mPosition, String department, String requestProcessing, String processingDay, String receivedDay, boolean showStar) {
            this.icon = icon;
            Name = name;
            this.mPosition = mPosition;
            Department = department;
            RequestProcessing = requestProcessing;
            ProcessingDay = processingDay;
            ReceivedDay = receivedDay;
            ShowStar = showStar;
        }

        public int getIcon() {
            return icon;
        }

        public String getName() {
            return Name;
        }

        public String getmPosition() {
            return mPosition;
        }

        public String getDepartment() {
            return Department;
        }

        public String getRequestProcessing() {
            return RequestProcessing;
        }

        public String getProcessingDay() {
            return ProcessingDay;
        }

        public String getReceivedDay() {
            return ReceivedDay;
        }

        public boolean isShowStar() {
            return ShowStar;
        }
    }
}


