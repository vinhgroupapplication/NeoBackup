package com.newsaigonsoft.neoegov.Fragment.Document;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Adapter.HotLineAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity.urlNA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_ARISE_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECK_SREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_BOOTOM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_NAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NUMBERIC_SHOW_LIST_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REQUEST_STATIS_CODE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TYPE_HOME_LIST_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.getLimitPager;

/**
 * Created by VinhCN on 4/20/2017.
 */

public class DocumentFragment extends DocumentFmBase implements DocumentFmView {

    @BindView(R.id.button_next)
    TextView btnNext;
    @BindView(R.id.pre_button)
    TextView btnPre;
    @BindView(R.id.double_pre)
    TextView btnLastPre;
    @BindView(R.id.double_next)
    TextView btnLastNext;
    @BindView(R.id.spinner_number_page)
    Spinner spnNumberPage;
    @BindView(R.id.document_spinner_row)
    Spinner spnNumberRow;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.list_van_ban)
    public ListView lv;
    @BindView(R.id.pager_bar)
    LinearLayout PagerBar;
    @BindView(R.id.layout_try_again)
    LinearLayout mLayoutTryAgain;
    @BindView(R.id.button_try_again)
    Button btnTryAgain;
    @BindView(R.id.notify_forward)
    TextView tvNotify_Forward;
    @BindView(R.id.no_connection)
    TextView tvNo_Connection;

    @Optional
    @OnClick({R.id.button_try_again, R.id.pre_button, R.id.button_next, R.id.double_pre, R.id.double_next})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_try_again:
                if (PagerBar.isShown()) {
                    mOfficalActivity.mOfficeLogic.eventChangeToDocument(mOfficalActivity.positionPage + 1, getLimitPager() + "", urlNA, false);
                } else {
                    mOfficalActivity.eventReadJsonNumberOfTotal();
                }
                break;
            case R.id.pre_button:
                if (mOfficalActivity.positionPage != 0) {
                    mOfficalActivity.positionPage--;
                    spnNumberPage.setSelection(mOfficalActivity.positionPage);
                }
                break;
            case R.id.button_next:
                if (mOfficalActivity.positionPage < (numberOfPager - 1)) {
                    mOfficalActivity.positionPage++;
                    spnNumberPage.setSelection(mOfficalActivity.positionPage);
                }
                break;
            case R.id.double_pre:
                spnNumberPage.setSelection(0);
                break;
            case R.id.double_next:
                spnNumberPage.setSelection(numberOfPager - 1);
                break;
            default:
                break;

        }
    }


    public static DocumentFragment newInstance() {
        DocumentFragment myFragment = new DocumentFragment();
        return myFragment;
    }

    public void visibleSwipeLayout(boolean isVisible) {
        if (mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setRefreshing(isVisible);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        getStringTrainfer();
        mDocumentFmLogic = new DocumentFmLogic(this, mOfficalActivity, mOfficalActivity.getScreenNameInSide());
        mOfficalActivity.mSetting_layout.setVisibility(View.GONE);
        mOfficalActivity.positionPage = 1;
        mOfficalActivity.mOfficeLogic.initNewArrayList();
        ReferenceView(view);
        visiblePagerBar(false);
        mOfficalActivity.setActionBarIcon(R.drawable.bar_back_x1);
        mDocumentFmLogic.startEvent(mOfficalActivity.getTypeHomeListDocument(), mOfficalActivity.positionPage);
        mOfficalActivity.showSoftwareKeyboard(false, mOfficalActivity);
        if (mOfficalActivity.mDatePickerDialog != null && mOfficalActivity.mDatePickerDialog.isShowing()) {
            mOfficalActivity.mDatePickerDialog.dismiss();
        }
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.center_dialog), getResources().getColor(R.color.end_dialog));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mOfficalActivity.isLoading()){
                    mOfficalActivity.LockListDocument();
                    mOfficalActivity.positionPage = 1;
                    mDocumentFmLogic.startSrollList(mOfficalActivity.positionPage, mOfficalActivity.mOfficeLogic.arrDocumentLookUp, mOfficalActivity.NumberOfPage);
                }else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    lv.setEnabled(true);
                }
            }
        });

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!view.canScrollList(View.SCROLL_AXIS_VERTICAL) && scrollState == SCROLL_STATE_IDLE) {
                    if (!mOfficalActivity.isLoading()) {
                        mOfficalActivity.LockListDocument();
                        mOfficalActivity.showProgressDialog(nULL_STRING, getActivity(), DIALOG_BOOTOM, true);
                        mOfficalActivity.positionPage++;
                        mDocumentFmLogic.startSrollList(mOfficalActivity.positionPage, mOfficalActivity.mOfficeLogic.arrDocumentLookUp, mOfficalActivity.NumberOfPage);
                    }else {
                        mOfficalActivity.closeProgress();
                        lv.setEnabled(true);
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, firstVisibleItem + " " + visibleItemCount + totalItemCount);
//                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
//                }
                if (firstVisibleItem == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else mSwipeRefreshLayout.setEnabled(false);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), DetailForwardActivity.class);
                intent.putExtra(IS_SCREEN, mOfficalActivity.getScreenNameInSide());

                String DepartmentPosition = "";
                long Id = 0;
                int Statistic = 0;
                intent.putExtra(TYPE_HOME_LIST_DOCUMENT, mOfficalActivity.getTypeHomeListDocument());
                intent.putExtra(TITLE_ACTION_BAR, mOfficalActivity.titleActionbar.getText());
                switch (mOfficalActivity.getScreenNameInSide()) {
                    case SEARCH_FRAGMENT_TAG:
                    case DOCUMENT_FRAGMENT_TAG:
                        // hide notification forward
                        mOfficalActivity.mOfficeLogic.getArrDocument().get(position).setViewed(true);
                        mDocumentFmLogic.putIntentDocument(intent, position, mOfficalActivity.mOfficeLogic.getArrDocument(), mOfficalActivity.positionPage, mOfficalActivity.getLookupScreen());
                        break;
                    case LOOKUP_COMING:
                    case LOOKUP_SENT:
                    case LOOKUP_INTERNAL:
                    case DOC_NOT_SEEN_TYPE:
                    case DOC_NOT_PROCESS_TYPE:
                    case DOC_DEMURRAGE_TYPE:
                    case STA_DOC_PROCESS_ON_TIME_FULL:
                    case STA_DOC_NOT_PROCESS_FULL:
                    case STA_DOC_DEMURRAGE_FULL:
                    case LIST_DOCUMENT_DEPARTMENT:
                    case TASK_REPORTED:
                    case TASK_PROCESS:
                    case PROCESS_ON_TIME:
                    case TASK_PROCESS_NEAR_DEMURRAGE:
                    case PROCESS_DEMURRAGE:
                        // statistical department
                    case TAP_DPM_DELAYS:
                    case TAP_DPM_INDUE:
                    case TAP_DPM_ONTIME:
                    case TAP_DPM_OUT_OF_DATE:
                    case WORK_ARISE:
                    case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                        Id = mOfficalActivity.mOfficeLogic.getArrDocumentLookUp().get(position).getDocReceiptId();
                        Statistic = mOfficalActivity.getStatisticType();
                        DepartmentPosition = mOfficalActivity.mOfficeLogic.getArrDocumentLookUp().get(position).getBriefContent();
                        mDocumentFmLogic.putIntentLookup(intent, Id, Statistic, DepartmentPosition, mOfficalActivity.getLookupScreen());
                        break;
                    case HOT_PROCESS:
                    case HOT_PROCESS_DEMURRAGE:
                    case HOT_PROCESS_ONDUE:
                        Id = mOfficalActivity.mOfficeLogic.getArrHotLineList().get(position).getMessageReflectId();
                        mDocumentFmLogic.putIntentLookup(intent, Id, Statistic, DepartmentPosition, mOfficalActivity.getLookupScreen());
                        break;
                    default:
                        break;

                }
            }

        });


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemSearchButton = menu.findItem(R.id.search_button);
        MenuItem itemCenterMenu = menu.findItem(R.id.center_menu);
        itemCenterMenu.setVisible(false);
        itemSearchButton.setVisible(false);
        switch (mOfficalActivity.getScreenNameInSide()) {
            case SEARCH_FRAGMENT_TAG:
            case DOCUMENT_FRAGMENT_TAG:
            case LOOKUP_COMING:
            case LOOKUP_SENT:
            case LOOKUP_INTERNAL:
            case WORK_ARISE:
                itemSearchButton.setVisible(true);
                break;
            case DOC_NOT_SEEN_TYPE:
            case DOC_NOT_PROCESS_TYPE:
            case DOC_DEMURRAGE_TYPE:
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
            case TASK_REPORTED:
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
                itemSearchButton.setVisible(false);
                break;
        }
        super.onPrepareOptionsMenu(menu);
    }


//    public void eventSearch(int positionSpinner, boolean visiblePagerBar) {
//        if (!visiblePagerBar) {
//            visiblePagerBar(visiblePagerBar);
//        }
//        if (!isClickSearch) {
//            mOfficalActivity.upDateSearchView(positionSpinner, false);
//            isClickSearch = true;
//        } else {
//            mOfficalActivity.upDateSearchView(positionSpinner, true);
//        }
//    }

    // set number row for list document
    private void setListNumberRow() {
        isFirstLoading = false;
        for (int i = 1; i <= 50; i++) {
            arrNumberRow.add(i + "");
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrNumberRow);
        spnNumberRow.setAdapter(adapter);
        String strPotistionSeekBar = mOfficalActivity.getDefaults(NUMBERIC_SHOW_LIST_DOCUMENT, getActivity());
        if (strPotistionSeekBar == null) {
            strPotistionSeekBar = "20";
        }
        int position = arrNumberRow.indexOf(strPotistionSeekBar);
        spnNumberRow.setSelection(position);
        spnNumberRow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isFirstLoading) {
                    isFirstLoading = true;
                } else {
                    mOfficalActivity.setDefaults(NUMBERIC_SHOW_LIST_DOCUMENT, spnNumberRow.getSelectedItem().toString(), getActivity());
                    mOfficalActivity.setLimitPager(Integer.parseInt(spnNumberRow.getSelectedItem().toString()));
//                    mOfficalActivity.reFreshListDocumentByRowSpinner();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // set number row for list document
    private void getStringTrainfer() {
        mOfficalActivity.setScreenNameInSide(getArguments().getString(CHECK_SREEN));
//        urlNA = getArguments().getString(KEYURLNA);
    }


    public void setAdapterListVanPham(DocumentAdapter adapter) {
        if (adapter != null && lv != null) {
            lv.setAdapter(adapter);
            if (mOfficalActivity.getIntent().getExtras() != null) {
                mDocumentFmLogic.setMsgAfterTrainfer(mOfficalActivity.CSREENCOMEBACK, getActivity().getIntent().getStringExtra(FORWARD_RESUILD), getActivity().getIntent().getStringExtra(INPUT_COME_BACK), getActivity().getIntent().getStringExtra(DOCUMENT_NUMBER), getActivity().getIntent().getStringExtra(INPUT_NAME));
            }
        }
//        if (mOfficalActivity.CREENCOMEBACK != null && !mOfficalActivity.CREENCOMEBACK.equals(nULL_STRING)) {
//            String Resuilt = getActivity().getIntent().getStringExtra(FORWARD_RESUILD);
//            if (Resuilt != null) {
//                if (Resuilt.equals(FALSE)) {
//                    switch (getActivity().getIntent().getStringExtra(INPUT_COME_BACK)) {
//                        case CHANGE_HANDLE:
//                            visibleNotifyForward(true, "Yêu cầu thay đổi thất bại.");
//                            break;
//                        case CANCEL_SCREEN:
//                            visibleNotifyForward(true, "Yêu cầu rút lại thất bại.");
//                            break;
//                        case RETURN_SCREEN:
//                            visibleNotifyForward(true, "Yêu cầu trả lại thất bại.");
//                            break;
//                        case FEED_BACK:
//                            visibleNotifyForward(true, "Góp ý thất bại.");
//                            break;
//                        case EXTEND_SCREEN:
//                            visibleNotifyForward(true, "Gia hạn thất bại.");
//                            break;
//                        default:
//                            visibleNotifyForward(true, getString(R.string.chuyen_that_bai) + " " + Html.fromHtml(getActivity().getIntent().getStringExtra(DOCUMENT_NUMBER)) + ".");
//                            break;
//                    }
//                    tvNotify_Forward.setTextColor(Color.parseColor("#ff0400"));
//                } else {
//                    switch (getActivity().getIntent().getStringExtra(INPUT_COME_BACK)) {
//                        case CHANGE_HANDLE:
//                            visibleNotifyForward(true, "Yêu cầu thay đổi thành công.");
//                            break;
//                        case CANCEL_SCREEN:
//                            visibleNotifyForward(true, "Yêu cầu rút lại thành công.");
//                            break;
//                        case RETURN_SCREEN:
//                            visibleNotifyForward(true, "Yêu cầu trả lại thành công.");
//                            break;
//                        case FEED_BACK:
//                            visibleNotifyForward(true, "Góp ý thành công.");
//                            break;
//                        case EXTEND_SCREEN:
//                            visibleNotifyForward(true, "Gia hạn thành công.");
//                            break;
//                        default:
//                            visibleNotifyForward(true, getString(R.string.chuyen_thanh_cong) + " " + Html.fromHtml(getActivity().getIntent().getStringExtra(DOCUMENT_NUMBER)) + ".");
//                            break;
//                    }
//                    tvNotify_Forward.setTextColor(Color.parseColor("#0008ff"));
//                }
//            } else {
//                visibleNotifyForward(true, getString(R.string.da_luu_vao_hop_thu_di) + " " + Html.fromHtml(getActivity().getIntent().getStringExtra(DOCUMENT_NUMBER)) + " sẽ lưu vào hàng đợi để gửi lại.");
//                tvNotify_Forward.setTextColor(Color.parseColor("#ff0400"));
//            }
//            mOfficalActivity.CREENCOMEBACK = nULL_STRING;
//        }
    }

    public void setAdapterListDocumentLookup(DocumentLookupAdapter mAdapter) {
        if (mAdapter != null && lv != null) {
            lv.setAdapter(mAdapter);
            if (mOfficalActivity.CSREENCOMEBACK != null && !mOfficalActivity.CSREENCOMEBACK.equals(nULL_STRING)) {
                mDocumentFmLogic.setMsgAferTask(mOfficalActivity.CSREENCOMEBACK, getActivity().getIntent().getStringExtra(FORWARD_RESUILD), getActivity().getIntent().getStringExtra(INPUT_COME_BACK));
            }
        }
    }

    public void ReferenceView(View view) {
        tvNotify_Forward.setVisibility(View.GONE);
        btnLastPre.setText("<<");
        btnLastNext.setText(">>");
        btnNext.setText(">");
        btnPre.setText("<");
    }


    public void visiblePagerBar(boolean visible) {
        if (visible) {
            PagerBar.setVisibility(View.VISIBLE);
        } else {
            PagerBar.setVisibility(View.GONE);
        }
    }


    public void showEmpty(boolean isShow) {
        if (isShow) {
            switch (mOfficalActivity.getScreenNameInSide()) {
                case SEARCH_FRAGMENT_TAG:
                case DOCUMENT_FRAGMENT_TAG:
                    tvNo_Connection.setText(getString(R.string.khong_co_cong_viec_nao));
                    break;
                case LOOKUP_COMING:
                case LOOKUP_SENT:
                case LOOKUP_INTERNAL:
                case DOC_NOT_PROCESS_TYPE:
                case DOC_NOT_SEEN_TYPE:
                case DOC_DEMURRAGE_TYPE:
                case STA_DOC_PROCESS_ON_TIME_FULL:
                case STA_DOC_NOT_PROCESS_FULL:
                case STA_DOC_DEMURRAGE_FULL:
                case LIST_DOCUMENT_DEPARTMENT:
                    tvNo_Connection.setText(getString(R.string.khong_co_van_ban_nao));
                    break;
                case TASK_REPORTED:
                case TASK_PROCESS:
                case PROCESS_ON_TIME:
                case TASK_PROCESS_NEAR_DEMURRAGE:
                case PROCESS_DEMURRAGE:
                case TAP_DPM_DELAYS:
                case TAP_DPM_INDUE:
                case TAP_DPM_ONTIME:
                case TAP_DPM_OUT_OF_DATE:
                    tvNo_Connection.setText(getString(R.string.khong_co_nhiem_vu_nao));
                    break;
                default:
                    break;
            }
            visibleNotConnection(true);
            btnTryAgain.setVisibility(View.INVISIBLE);
        } else {
            visibleNotConnection(false);
        }

    }

    public void visibleNotConnection(boolean visible) {
        if (tvNo_Connection != null && mLayoutTryAgain != null && btnTryAgain != null) {
            if (visible) {
                if (mOfficalActivity.isNetworkAvailable(getActivity())) {
                    tvNo_Connection.setText("Không có văn bản");
                } else {
                    tvNo_Connection.setText("Không có kết nối");
                }
                mLayoutTryAgain.setVisibility(View.VISIBLE);
            } else {
                mLayoutTryAgain.setVisibility(View.GONE);
            }
            btnTryAgain.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void eventSearch(int positionSpinner, boolean visiblePagerBar) {
        if (!visiblePagerBar) {
            visiblePagerBar(visiblePagerBar);
        }
        if (!isClickSearch) {
            mOfficalActivity.mOfficeLogic.upDateSearchView(positionSpinner, false);
            isClickSearch = true;
        } else {
            mOfficalActivity.mOfficeLogic.upDateSearchView(positionSpinner, true);
        }
    }

    @Override
    public void getListSQLDocument(int positionPage, String urlNA) {
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        mOfficalActivity.mOfficeLogic.getListDocumentSQLite(positionPage - 1, urlNA);
    }

    @Override
    public void eventGetListSQL(String s) {
        mOfficalActivity.mOfficeLogic.getListDocumentLookupComing(mOfficalActivity.positionPage - 1);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
    }

    @Override
    public void eventChangeToLookup(String s) {
        mOfficalActivity.mOfficeLogic.eventChangeToLookupComing(mOfficalActivity.positionPage, true);
        mOfficalActivity.setActionBarIcon(R.drawable.bar_back_x1);
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
    }

    @Override
    public void eventChangePageDocument() {
        mOfficalActivity.mOfficeLogic.eventChangeToDocument(mOfficalActivity.positionPage, getLimitPager() + nULL_STRING, urlNA, true);
    }

    @Override
    public void eventChangePageLookupComing() {
        mOfficalActivity.mOfficeLogic.eventChangeToLookupComing(mOfficalActivity.positionPage, true);
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void setRedMsgColor() {
        tvNotify_Forward.setTextColor(Color.parseColor("#ff0400"));
    }

    @Override
    public void setNullCreenComeBack() {
        mOfficalActivity.CSREENCOMEBACK = nULL_STRING;
    }

    @Override
    public void setBlueMsg() {
        tvNotify_Forward.setTextColor(Color.parseColor("#0008ff"));
    }

    @Override
    public void visibleNotifyForward(boolean visible, String msg) {
        if (visible) {
            tvNotify_Forward.setVisibility(View.VISIBLE);
            tvNotify_Forward.setText(msg);
        } else {
            tvNotify_Forward.setVisibility(View.GONE);
        }
    }

    @Override
    public void startMyIntent(Intent intent) {
        visibleNotifyForward(false, nULL_STRING);
        getActivity().startActivityForResult(intent, REQUEST_STATIS_CODE);
//        getActivity().startActivity(intent);
    }

    @Override
    public void changePageLookup() {
//        if (mOfficalActivity.mOfficeLogic.arrDocumentLookUp.size() >= mOfficalActivity.NumberOfPage) {
//            closeProgress();
//        } else {
        eventChangePageLookupComing();
//        }
    }

    @Override
    public Intent getIntentFromAcitivity() {
        return getActivity().getIntent();
    }

    public void setAdapterListHotLine(HotLineAdapter adapterHotLine) {
        lv.setAdapter(adapterHotLine);
    }

    public void showSimPleMessage(Intent data) {
        if (data != null) {
            String CSREENCOMEBACK = data.getStringExtra(COME_BACK_FROM_SCREEN);
            String resuilt = data.getStringExtra(FORWARD_RESUILD);
            String inputComeBack = data.getStringExtra(INPUT_COME_BACK);
            if (CSREENCOMEBACK != null && !CSREENCOMEBACK.equals(nULL_STRING)) {
                mDocumentFmLogic.setMsgAferTask(CSREENCOMEBACK, resuilt, inputComeBack);
            }
        }
    }

    //    public void upDatePagerNumber() {
//        int NumberOfPage = mOfficalActivity.NumberOfPage;
//        int numberOfPagerEven = NumberOfPage / getLimitPager();
//        int numberOfPagerRedundant = NumberOfPage % getLimitPager();
//        arrListNumberPage = new ArrayList<Integer>();
//        if (numberOfPagerRedundant > 0) {
//            numberOfPager = numberOfPagerEven + 1;
//        } else {
//            numberOfPager = numberOfPagerEven;
//        }
//        if (numberOfPager > 0) {
//            visiblePagerBar(true);
//        } else {
//            visiblePagerBar(false);
//        }
//        if (NumberOfPage > 0) {
//            visibleNotConnection(false);
//        } else {
//            if (NumberOfPage == 0) {
//                if (!mOfficalActivity.isNetworkAvailable(getActivity())) {
//                    visibleNotConnection(true);
//                } else {
//                    mOfficalActivity.showDialogUpdateError(getString(R.string.khong_co_van_ban_nao), getActivity());
//                }
//            }
//        }
//        for (int i = 0; i < numberOfPager; i++) {
//            arrListNumberPage.add(i + 1);
//        }
////        Integer[] items = new Integer[numberOfPager];
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrListNumberPage);
//        spnNumberPage.setAdapter(adapter);
//
//    }
//    public void ReferenceView(View view) {
//        PagerBar = (LinearLayout) view.findViewById(R.id.pager_bar);
//        spnNumberPage = (Spinner) view.findViewById(R.id.spinner_number_page);
//        mLayoutTryAgain = (LinearLayout) view.findViewById(R.id.layout_try_again);
//        btnPre = (TextView) view.findViewById(R.id.pre_button);
//        btnNext = (TextView) view.findViewById(R.id.button_next);
//        btnLastPre = (TextView) view.findViewById(R.id.double_pre);
//        btnLastNext = (TextView) view.findViewById(R.id.double_next);
//        lv = (ListView) view.findViewById(R.id.list_van_ban);
//        btnTryAgain = (Button) view.findViewById(R.id.button_try_again);
//        spnNumberRow = (Spinner) view.findViewById(R.id.document_spinner_row);
//        tvNotify_Forward = (TextView) view.findViewById(R.id.notify_forward);
//        tvNo_Connection = (TextView) view.findViewById(R.id.no_connection);
//        tvNotify_Forward.setVisibility(View.GONE);
//        btnLastPre.setText("<<");
//        btnLastNext.setText(">>");
//        btnNext.setText(">");
//        btnPre.setText("<");
//        btnLastPre.setOnClickListener(this);
//        btnLastNext.setOnClickListener(this);
//        btnNext.setOnClickListener(this);
//        btnPre.setOnClickListener(this);
//        btnTryAgain.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.button_try_again:
//                if (PagerBar.isShown()) {
//                    mOfficalActivity.mOfficeLogic.eventChangeToDocument(mOfficalActivity.positionPage + 1, getLimitPager() + "", urlNA, false);
//                } else {
//                    mOfficalActivity.eventReadJsonNumberOfTotal();
//                }
//                break;
//            case R.id.pre_button:
//                if (mOfficalActivity.positionPage != 0) {
//                    mOfficalActivity.positionPage--;
//                    spnNumberPage.setSelection(mOfficalActivity.positionPage);
//                }
//                break;
//            case R.id.button_next:
//                if (mOfficalActivity.positionPage < (numberOfPager - 1)) {
//                    mOfficalActivity.positionPage++;
//                    spnNumberPage.setSelection(mOfficalActivity.positionPage);
//                }
//                break;
//            case R.id.double_pre:
//                spnNumberPage.setSelection(0);
//                break;
//            case R.id.double_next:
//                spnNumberPage.setSelection(numberOfPager - 1);
//                break;
//            default:
//                break;
//
//        }
//    }


}
