package com.newsaigonsoft.neoegov.Fragment.Other;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.Adapter.MenuOtherAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.OtherActivity.OtherActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.MenuOtherRow;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OHTER_FUNCTION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

/**
 * Created by Vinh on 9/23/2017.
 */

public class OtherFragment extends OtherFmBase implements OtherFmView {
    @BindView(R.id.list_menu)
    ListView lvMenu;

    public static OtherFragment newInstance(String TitleActionbar) {
        OtherFragment myFragment = new OtherFragment();
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
        view = inflater.inflate(R.layout.fragment_other, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this,view);
        mOtherFmLogic = new OtherFmLogic(this, mDetailForwardActivity);
//        mOtherFmLogic.initMenu(arrMenu);
//        mDetailForwardActivity.changeListView(6);
//        mDetailForwardActivity.titleActionbar.setText(title);
        return view;
    }

    @Override
    public void setListOther(MenuOtherAdapter adapter, final ArrayList<MenuOtherRow> arrMenus) {
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mDetailForwardActivity, OtherActivity.class);
                intent.putExtra(OHTER_FUNCTION, arrMenus.get(position).getCaseFuntion());
                intent.putExtra(TITLE_ACTION_BAR, arrMenus.get(position).getMenuName());
                startActivity(intent);
            }
        });
    }

    public void CheckShowMenu(JSONObject mOther) {
        mOtherFmLogic.initMenu(arrMenu, mOther);
    }
}
