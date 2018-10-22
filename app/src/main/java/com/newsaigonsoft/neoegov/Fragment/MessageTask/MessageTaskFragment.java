package com.newsaigonsoft.neoegov.Fragment.MessageTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.Adapter.GroupMsgTasksAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REMIND_TASK;

/**
 * Created by Vinh on 10/9/2017.
 */

public class MessageTaskFragment extends MessageTaskBase implements MessageTaskFmView {
    @BindView(R.id.list_remind)
    ListView lvMsgTasks;
    @BindView(R.id.no_remind)
    LinearLayout lnNoMsgTasks;

    @Optional
    @OnClick({R.id.forward_button})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.forward_button:
                mDetailForwardActivity.startAddTransfer(TAP_REMIND_TASK);
                break;
            default:
                break;
        }
    }

    public static MessageTaskFragment newInstance(String TitleActionbar) {
        MessageTaskFragment myFragment = new MessageTaskFragment();
        Bundle args = new Bundle();
        args.putString("Title", TitleActionbar);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        title = getArguments().getString("Title");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_task, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this, view);
        messageTaskFmLogic = new MessageTaskFmLogic(this);
//        mDetailForwardActivity.titleActionbar.setText(title);
//        mDetailForwardActivity.changeListView(3);
        return view;
    }

    public void setAdapterMsgGroupTask(List<GroupMsgTasksRow> arrDocConnect) {
        messageTaskFmLogic.setAdapterMsgGroupTask(arrDocConnect);
    }

    @Override
    public void showNoMsgTask() {
        lnNoMsgTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListMsgTask(List<GroupMsgTasksRow> arrDocConnect) {
        GroupMsgTasksAdapter adapter = new GroupMsgTasksAdapter(mDetailForwardActivity, arrDocConnect);
        lvMsgTasks.setAdapter(adapter);
    }


}
