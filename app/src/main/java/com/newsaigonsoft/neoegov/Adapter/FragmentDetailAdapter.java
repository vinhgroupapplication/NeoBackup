package com.newsaigonsoft.neoegov.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;

import java.util.List;

/**
 * Created by Vinh on 10/16/2017.
 */

public class FragmentDetailAdapter extends FragmentStatePagerAdapter {
    String isScreen;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    List<Fragment> mFragmentList;

    public FragmentDetailAdapter(FragmentManager fm, String isScreen, List<Fragment> mFragmentList) {
        super(fm);
        this.isScreen = isScreen;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("isScreen ", isScreen);
        //        Fragment mFragment = null;
//        switch (isScreen) {
//            case TASK_REPORTED:
//            case TASK_PROCESS:
//            case TASK_PROCESS_ON_TIME:
//            case TASK_PROCESS_NEAR_DEMURRAGE:
//            case TASK_PROCESS_DEMURRAGE:
//                switch (position) {
//                    case 0:
//                        mFragment = DetailFragment.newInstance("CHI TIẾT CÔNG VIỆC");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    case 1:
//                        mFragment = MessageTaskFragment.newInstance("NHẮC NHỞ");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    case 2:
//                        mFragment = ContentTaskFragment.newInstance("TRAO ĐỔI");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    case 3:
//                        mFragment = ReportTaskFragment.newInstance("BÁO CÁO");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    default:
//                        break;
//                }
//                break;
//            case STA_DOC_PROCESS_ON_TIME_FULL:
//            case STA_DOC_NOT_PROCESS_FULL:
//            case STA_DOC_DEMURRAGE_FULL:
//            case LIST_DOCUMENT_DEPARTMENT:
//            case LOOKUP_COMING:
//            case LOOKUP_SENT:
//            case LOOKUP_INTERNAL:
//            case DOC_NOT_SEEN_TYPE:
//            case DOC_NOT_PROCESS_TYPE:
//            case DOC_DEMURRAGE_TYPE:
//            case NOTIFY_SCREEN:
//            default:
//                switch (position) {
//                    case 0:
//                        mFragment = DetailFragment.newInstance("CHI TIẾT CÔNG VIỆC");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    case 1:
//                        mFragment = ForwardFragment.newInstance("LUÂN CHUYỂN");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    case 2:
//                        mFragment = FeedBackFragment.newInstance("GÓP Ý");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    case 3:
//                        mFragment = OtherFragment.newInstance("KHÁC");
//                        registeredFragments.put(position, mFragment);
//                        return mFragment;
//                    default:
//                        break;
//                }
//                break;
//        }
//        return null;
        registeredFragments.put(position, mFragmentList.get(position));
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
