package com.newsaigonsoft.neoegov.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.PopupItemSetting;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NUMBERIC_SHOW_LIST_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REQUEST_KEEP_LOGIN;

/**
 * Created by Vinh on 5/31/2017.
 */

public class SettingAdapter extends BaseAdapter {

    Context context;
    ArrayList<PopupItemSetting> object;
    SumManager sumManager = new SumManager();

    public SettingAdapter(SumManager manager) {
        this.sumManager = manager;
    }

    public SettingAdapter(Context context, ArrayList<PopupItemSetting> object) {
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.popup_setting, null);
            mViewHolder= new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.tvTitle.setText(object.get(position).getSettingName());
        if (object.get(position).getSettingShow() == 1) {
            mViewHolder.LnSeekBar.setVisibility(View.VISIBLE);
            SeekBar mSeekBar = (SeekBar) convertView.findViewById(R.id.seek_bar_row_visible);
            final TextView tvProcessValue = (TextView) convertView.findViewById(R.id.process_value_seekbar);
            String strPotistionSeekBar = sumManager.getDefaults(NUMBERIC_SHOW_LIST_DOCUMENT, context);
            if (strPotistionSeekBar==null){
                strPotistionSeekBar = "20";
            }
            tvProcessValue.setText(strPotistionSeekBar);
            int potistionSeekBar = Integer.parseInt(strPotistionSeekBar);
            mSeekBar.setProgress(potistionSeekBar);
            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tvProcessValue.setText(progress + "");
                    sumManager.setDefaults(NUMBERIC_SHOW_LIST_DOCUMENT, tvProcessValue.getText().toString(), context);
                    sumManager.setLimitPager(progress);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } else {
            mViewHolder.LnChooseLoading.setVisibility(View.GONE);
            mViewHolder.tvTitle.setVisibility(View.GONE);
            String TimeForKeepLogin = sumManager.getDefaults(REQUEST_KEEP_LOGIN, context);
            if (TimeForKeepLogin == null){
                TimeForKeepLogin = "60000";
            }
            int TimeForRequestLogin = Integer.parseInt(TimeForKeepLogin);
            switch (TimeForRequestLogin){
                case 60000:
                    mViewHolder.tvOneMinutes.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    mViewHolder.tvOneMinutes.setTextColor(Color.WHITE);
                    mViewHolder.tvTwoeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    mViewHolder.tvTwoeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    mViewHolder.tvThreeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    mViewHolder.tvThreeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    break;
                case 120000:
                    mViewHolder.tvTwoeMinutes.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    mViewHolder.tvTwoeMinutes.setTextColor(Color.WHITE);
                    mViewHolder.tvOneMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    mViewHolder.tvOneMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    mViewHolder.tvThreeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    mViewHolder.tvThreeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    break;
                case 180000:
                    mViewHolder.tvThreeMinutes.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    mViewHolder.tvThreeMinutes.setTextColor(Color.WHITE);
                    mViewHolder.tvTwoeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    mViewHolder.tvTwoeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    mViewHolder.tvOneMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    mViewHolder.tvOneMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    break;
                default:
                    break;
            }
        }

        return convertView;
    }



     class ViewHolder{
        @BindView(R.id.layout_seek_bar)
        LinearLayout LnSeekBar;
        @BindView(R.id.layout_loading_choose)
        LinearLayout LnChooseLoading;
        @BindView(R.id.setting_name)
        TextView tvTitle;
        @BindView(R.id.one_minutes)
        TextView tvOneMinutes;
        @BindView(R.id.two_minutes)
        TextView tvTwoeMinutes;
        @BindView(R.id.three_minutes)
        TextView tvThreeMinutes;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }

         @Optional
         @OnClick({R.id.one_minutes, R.id.two_minutes, R.id.three_minutes})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.one_minutes:
                    tvOneMinutes.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    tvOneMinutes.setTextColor(Color.WHITE);
                    tvTwoeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    tvTwoeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    tvThreeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    tvThreeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    sumManager.setDefaults(REQUEST_KEEP_LOGIN, 60 * 1000 * 1 + "", context);
                    break;
                case R.id.two_minutes:
                    tvTwoeMinutes.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    tvTwoeMinutes.setTextColor(Color.WHITE);
                    tvOneMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    tvOneMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    tvThreeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    tvThreeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    sumManager.setDefaults(REQUEST_KEEP_LOGIN, 60 * 1000 * 2 + "", context);
                    break;
                case R.id.three_minutes:
                    tvThreeMinutes.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    tvThreeMinutes.setTextColor(Color.WHITE);
                    tvTwoeMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    tvTwoeMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    tvOneMinutes.setBackgroundResource(R.drawable.setting_loading_bar);
                    tvOneMinutes.setTextColor(ContextCompat.getColor(context, R.color.colorButtonLoadingSetting));
                    sumManager.setDefaults(REQUEST_KEEP_LOGIN, 60 * 1000 * 3 + "", context);
                    break;
                default:
                    break;
            }
        }
    }
}
