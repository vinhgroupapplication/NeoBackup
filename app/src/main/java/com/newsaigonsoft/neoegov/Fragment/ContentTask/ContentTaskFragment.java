package com.newsaigonsoft.neoegov.Fragment.ContentTask;

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

import com.newsaigonsoft.neoegov.Adapter.ContentTasksAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ContentTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_ADD_TRAINFER;

/**
 * Created by Vinh on 10/9/2017.
 */

public class ContentTaskFragment extends ContentTaskFMBase implements ContentTaskFmView {
    @BindView(R.id.no_content_task)
    LinearLayout lnNoContentTasks;
    @BindView(R.id.list_content_task)
    ListView lvContentTasks;

    @Optional
    @OnClick({R.id.forward_button})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.forward_button:
                mDetailForwardActivity.startAddTransfer(TAP_ADD_TRAINFER);
                break;
        }
    }



    public static ContentTaskFragment newInstance(String TitleActionbar) {
        ContentTaskFragment myFragment = new ContentTaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_content_task, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this, view);
        mContentTaskFmLogin = new ContentTaskFmLogic(this);
        return view;
    }

    public void setAdapterContentTask(List<ContentTasksRow> arrDocConnect, ModuleRow moduleRow) {
        mContentTaskFmLogin.setAdapterContent(arrDocConnect, moduleRow);
    }

    @Override
    public void showLnNoConTentTask() {
        lnNoContentTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListContent(List<ContentTasksRow> arrDocConnect, ModuleRow moduleRow) {
        ContentTasksAdapter adapter = new ContentTasksAdapter(mDetailForwardActivity, arrDocConnect, mDetailForwardActivity, moduleRow);
        lvContentTasks.setAdapter(adapter);
    }

//    public void setAdapterContentTask(List<ContentTasksRow> arrDocConnect) {
//        if (arrDocConnect.size() == 0) {
//            lnNoContentTasks.setVisibility(View.VISIBLE);
//        } else {
//            ContentTasksAdapter adapter = new ContentTasksAdapter(mDetailForwardActivity, arrDocConnect, mDetailForwardActivity);
//            lvContentTasks.setAdapter(adapter);
//        }
//    }

}
