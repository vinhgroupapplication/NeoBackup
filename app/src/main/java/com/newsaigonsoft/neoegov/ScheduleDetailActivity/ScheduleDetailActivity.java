package com.newsaigonsoft.neoegov.ScheduleDetailActivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_DETAIL;

public class ScheduleDetailActivity extends SumManager {

    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.content)
    TextView tvContent;
    @BindView(R.id.date)
    TextView tvStartDate;
    @BindView(R.id.duration)
    TextView tvDuration;
    @BindView(R.id.processer)
    TextView tvProcesser;
    @BindView(R.id.status)
    TextView tvStatus;
    @BindView(R.id.place_register)
    TextView tvRegistryPlace;
    @BindView(R.id.main_person)
    TextView tvChairManName;
    @BindView(R.id.the_same_main_person)
    TextView tvChairManNameOther;
    @BindView(R.id.place)
    TextView tvPlaceName;
    @BindView(R.id.vehicle)
    TextView tvVehicle;
    @BindView(R.id.importance)
    CheckBox cbImportance;
    @BindView(R.id.allow_newspaper)
    CheckBox cbNewspapersAllow;
    @BindView(R.id.privacy)
    CheckBox cbConfidential;
    @BindView(R.id.invitation)
    TextView tvInvitation;
    @BindView(R.id.person_join)
    TextView tvParticipantsName;
    @BindView(R.id.person_join_out)
    TextView tvParticipantsNameOther;
    @BindView(R.id.metting_number)
    TextView tvTotalParticipants;
    @BindView(R.id.prepare)
    TextView tvPreparation;
    @BindView(R.id.allow_person)
    TextView tvUserApproved;
    @BindView(R.id.time_allow)
    TextView tvApprovedDate;
    @BindView(R.id.note)
    TextView tvNoteContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);
        initActiobar("Chi tiết lịch họp", true);
        initView();

    }

    private void initView() {
        tvTitle.setText(getDetail().getTitle());
        tvContent.setText(getDetail().getContent());
        tvStartDate.setText(getDateFromMiliSec(getDetail().getStartDate(), FORMATDATE));
        tvDuration.setText(String.valueOf(getDetail().getDurationHour()) + " Giờ " +  ":" + String.valueOf(getDetail().getDurationMinute()) + " Phút");
        tvProcesser.setText(getDetail().getRegister());
        setStatus(tvStatus);
        tvRegistryPlace.setText(getDetail().getRegistryPlace());
        tvChairManName.setText(getDetail().getChairManName());
        tvChairManNameOther.setText(getDetail().getChairManNameOther());
        tvPlaceName.setText(getDetail().getPlaceName());
        tvVehicle.setText(getDetail().getVehicle());
        cbImportance.setChecked(getDetail().isImportance());
        cbNewspapersAllow.setChecked(getDetail().isNewspapersAllow());
        cbConfidential.setChecked(getDetail().isConfidential());
        tvInvitation.setText(getDetail().getInvitation());
        tvParticipantsName.setText(getDetail().getParticipantsName());
        tvParticipantsNameOther.setText(getDetail().getParticipantsNameOther());
        tvTotalParticipants.setText(String.valueOf(getDetail().getTotalParticipants()));
        tvPreparation.setText(getDetail().getPreparation());
        tvUserApproved.setText(getDetail().getUserApproved());
        tvApprovedDate.setText("(" + getDateFromMiliSec(getDetail().getApprovedDate(), FORMATP_DATE_DOCUMENT_6_2) +")");
        tvNoteContent.setText(getDetail().getNoteContent());


    }

    private void setStatus(TextView tvStatus) {
        if (getDetail().getStatusName() != null) {
            if (getDetail().getStatusName().getStatusHour() != null) {
                tvStatus.setText(getDetail().getStatusName().getStatusHour().getStatus());
                if (getDetail().getStatusName().getStatusHour().getStatus().contains("Đã")) {
                    setBGColorDrawable(tvStatus, "#637F95");
                } else {
                    if (getDetail().getStatusName().getStatusHour().getStatus().contains("Đang")) {
                        setBGColorDrawable(tvStatus, "#64CF5B");
                    } else {
                        if (getDetail().getStatusName().getStatusHour().getStatus().contains("Sắp")) {
                            setBGColorDrawable(tvStatus, "#FFB129");
                        } else {
                            setBGColorDrawable(tvStatus, "#59BFFD");
                        }
                    }
                }


            }
        }
    }

    private GsonScheduleDay.DataBean getDetail() {
        return getGson().fromJson(getIntent().getStringExtra(SCHEDULE_DETAIL), GsonScheduleDay.DataBean.class);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.search_button).setVisible(false);
        menu.findItem(R.id.menu_infor).setVisible(false);
//        menu.findItem(R.id.menu_help).setVisible(false);
//        menu.findItem(R.id.menu_config).setVisible(false);
        menu.findItem(R.id.center_menu).setVisible(false);
        return true;
    }


}
