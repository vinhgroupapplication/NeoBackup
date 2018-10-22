package com.newsaigonsoft.neoegov.SearchActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchInternal;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchLookup;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchProcess;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchSent;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchWorkArise;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECK_SREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_BOOTOM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TYPE_HOME_LIST_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

public class SeachActivity extends SearchBase implements SeachView {

    @BindView(R.id.seach_view_toolbar)
    EditText editSearchTop;
    @BindView(R.id.seach_back_button)
    ImageView imgButtonBack;
    @BindView(R.id.search_switch)
    SwitchCompat switchCompat;
    @BindView(R.id.empty_seach_layout)
    LinearLayout lnEmpty;
    @BindView(R.id.search_list)
    ListView lv;
    @BindView(R.id.layout_search_button)
    LinearLayout lnSearchButton;
    @BindView(R.id.search_button_start)
    TextView btnSearch;
    @BindView(R.id.tv_advance)
    TextView tvAdvance;
    @BindView(R.id.search_title)
    TextView tvTitle;


    public void headerClick(View view) {
        switch (view.getId()) {
            case R.id.search_coming_day_from_product:
//                showDataPickDialog(mSearchHeader.edtComingDayFromProduct, this);
                showTimeDialog(mSearchHeader.edtComingDayFromProduct, this);
                break;
            case R.id.search_coming_day_to_product:
//                showDataPickDialog(mSearchHeader.edtComingDayToProduct, this);
                showTimeDialog(mSearchHeader.edtComingDayToProduct, this);
                break;
            case R.id.search_coming_day_from_receive:
//                showDataPickDialog(mSearchHeader.edtComingDayFromReceive, this);
                showTimeDialog(mSearchHeader.edtComingDayFromReceive, this);
                break;
            case R.id.search_coming_day_to_receive:
//                showDataPickDialog(mSearchHeader.edtComingDaytoReceive, this);
                showTimeDialog(mSearchHeader.edtComingDaytoReceive, this);
                break;
            case R.id.search_sent_toCreateDate:
//                showDataPickDialog(mSearchHeader.edtSentToCreateDate, this);
                showTimeDialog(mSearchHeader.edtSentToCreateDate, this);
                break;
            case R.id.search_sent_fromCreateDate:
//                showDataPickDialog(mSearchHeader.edtSentFromCreateDate, this);
                showTimeDialog(mSearchHeader.edtSentFromCreateDate, this);
                break;
            case R.id.search_sent_fromPublishDate:
//                showDataPickDialog(mSearchHeader.edtSentFromPublishDate, this);
                showTimeDialog(mSearchHeader.edtSentFromPublishDate, this);
                break;
            case R.id.search_sent_toPublishDate:
//                showDataPickDialog(mSearchHeader.edtSentToPublishDate, this);
                showTimeDialog(mSearchHeader.edtSentToPublishDate, this);
                break;
            case R.id.search_internal_fromCreateDate:
//                showDataPickDialog(mSearchHeader.edtInternalFromCreateDate, this);
                showTimeDialog(mSearchHeader.edtInternalFromCreateDate, this);
                break;
            case R.id.search_internal_toCreateDate:
//                showDataPickDialog(mSearchHeader.edtInternalToCreateDate, this);
                showTimeDialog(mSearchHeader.edtInternalToCreateDate, this);
                break;
            case R.id.search_internal_fromSignDate:
//                showDataPickDialog(mSearchHeader.edtInternalFromSignDate, this);
                showTimeDialog(mSearchHeader.edtInternalFromSignDate, this);
                break;
            case R.id.search_internal_toSignDate:
//                showDataPickDialog(mSearchHeader.edtInternalToSignDate, this);
                showTimeDialog(mSearchHeader.edtInternalToSignDate, this);
                break;
            case R.id.search_from_day:
//                showDataPickDialog(mSearchHeader.edtFromDay, this);
                showTimeDialog(mSearchHeader.edtFromDay, this);
                break;
            case R.id.search_to_day:
//                showDataPickDialog(mSearchHeader.edtToDay, this);
                showTimeDialog(mSearchHeader.edtToDay, this);
                break;
            case R.id.search_work_arise_from_date:
                showTimeDialog(mSearchHeader.edtWorkAriseFromDate, this);
                break;
            case R.id.search_work_arise_to_date:
                showTimeDialog(mSearchHeader.edtWorkAriseToDate, this);
                break;
        }
    }

    @Optional
    @OnClick({R.id.seach_back_button, R.id.search_button_start})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.seach_back_button:
                onBackPressed();
                break;
            case R.id.search_button_start:
                searchStart();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        ButterKnife.bind(this);
        mSearchLogic = new SearchLogic(this, this, getIntent().getStringExtra(CHECK_SREEN));
        initActiobarSearch();
        initView();
        mSearchLogic.eventGetDepartmentSearchList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(SeachActivity.this, DetailForwardActivity.class);
                intent.putExtra(IS_SCREEN, getIntent().getStringExtra(IS_SCREEN));
                String DepartmentPosition = "";
                long Id;
                int Statistic;
                intent.putExtra(TYPE_HOME_LIST_DOCUMENT, getIntent().getStringExtra(TYPE_HOME_LIST_DOCUMENT));
                intent.putExtra(TITLE_ACTION_BAR, tvTitle.getText().toString());
                switch (getIntent().getStringExtra(IS_SCREEN)) {
                    case SEARCH_FRAGMENT_TAG:
                    case DOCUMENT_FRAGMENT_TAG:
                        // hide notification forward
                        mSearchLogic.getArrDocument().get(position).setViewed(true);
                        mSearchLogic.putIntentDocument(intent, position, mSearchLogic.getArrDocument(), getPositionPage(), getIntent().getStringExtra(IS_SCREEN));
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
                        mSearchLogic.putIntentLookup(intent, mSearchLogic.getArrDocumentLookUp().get(position).getDocReceiptId(), getIntent().getIntExtra(STATISTIC_TYPE, 0), mSearchLogic.getArrDocumentLookUp().get(position).getBriefContent(), getIntent().getStringExtra(CHECK_SREEN));
                        break;
                    case HOT_PROCESS:
                    case HOT_PROCESS_DEMURRAGE:
                    case HOT_PROCESS_ONDUE:
                        break;
                    default:
                        break;

                }
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!view.canScrollList(View.SCROLL_AXIS_VERTICAL) && scrollState == SCROLL_STATE_IDLE) {
                    if (mSearchHeader.lnSearchComing.isShown() || mSearchHeader.lnSearchInternal.isShown()
                            || mSearchHeader.lnSearchProcess.isShown() || mSearchHeader.lnSearchSent.isShown()
                            || mSearchHeader.lnSearchWorkArise.isShown()
                            ) {
                    } else {
                        showProgressDialog(nULL_STRING, SeachActivity.this, DIALOG_BOOTOM, true);
                        isScroll = true;
                        searchStart();
                    }

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


        mSearchHeader.spnDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!arrListDepartmentSearch.get(position).getReceiveRoomID().equals(nULL_STRING)) {
                    ReceiveRoomID = arrListDepartmentSearch.get(position).getReceiveRoomID();
                    Log.d(KeyManager.TAG, String.valueOf(ReceiveRoomID));
                } else {
                    ReceiveRoomID = nULL_STRING;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mSearchHeader.spnWorkAriseDepartMent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!arrListDepartmentSearch.get(position).getReceiveRoomID().equals(nULL_STRING)) {
                    ReceiveRoomID = arrListDepartmentSearch.get(position).getReceiveRoomID();
                    Log.d(KeyManager.TAG, String.valueOf(ReceiveRoomID));
                } else {
                    ReceiveRoomID = nULL_STRING;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showDetailSearch(b);
            }
        });

        editSearchTop.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                             if (!switchCompat.isChecked()) {
                                                                 searchStart();
                                                             }
                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {

                                                         }
                                                     });

        mSearchHeader.edtComingTypeDocument.addTextChangedListener(new

                                                                           TextWatcher() {
                                                                               @Override
                                                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                               }

                                                                               @Override
                                                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                                   if (s.length() != 0)
                                                                                       mSearchLogic.requestJsonDocumentType(mSearchHeader.edtComingTypeDocument.getText().toString());
                                                                               }

                                                                               @Override
                                                                               public void afterTextChanged(Editable s) {

                                                                               }
                                                                           });

        mSearchHeader.edtComingTypeDocument.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSeachTypeDocument.setTypeDocumentId(arrayTypeDocumentID.get(position));
            }
        });

        mSearchHeader.edtSentDocTypeId.addTextChangedListener(new

                                                                      TextWatcher() {
                                                                          @Override
                                                                          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                          }

                                                                          @Override
                                                                          public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                              if (s.length() != 0)
                                                                                  if (mSearchHeader.lnSearchComing.isShown() || mSearchHeader.lnSearchInternal.isShown()
                                                                                          || mSearchHeader.lnSearchProcess.isShown() || mSearchHeader.lnSearchSent.isShown()) {
                                                                                      mSearchLogic.requestJsonDocumentType(mSearchHeader.edtSentDocTypeId.getText().toString());
                                                                                  }

                                                                          }

                                                                          @Override
                                                                          public void afterTextChanged(Editable s) {

                                                                          }
                                                                      });

        mSearchHeader.edtSentDocTypeId.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSeachTypeDocument.setTypeDocumentId(arrayTypeDocumentID.get(position));
            }
        });

        mSearchHeader.edtInternalDocTypeId.addTextChangedListener(new

                                                                          TextWatcher() {
                                                                              @Override
                                                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                              }

                                                                              @Override
                                                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                                  if (s.length() != 0 && (before == 0 || before == 1))
                                                                                      if (mSearchHeader.lnSearchComing.isShown() || mSearchHeader.lnSearchInternal.isShown()
                                                                                              || mSearchHeader.lnSearchProcess.isShown() || mSearchHeader.lnSearchSent.isShown()) {
                                                                                          mSearchLogic.requestJsonDocumentType(mSearchHeader.edtInternalDocTypeId.getText().toString());
                                                                                      }

                                                                              }

                                                                              @Override
                                                                              public void afterTextChanged(Editable s) {

                                                                              }
                                                                          });

        mSearchHeader.edtInternalDocTypeId.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSeachTypeDocument.setTypeDocumentId(arrayTypeDocumentID.get(position));
            }
        });
    }

    private void searchStart() {
        showProgressDialog("", this, DIALOG_CENTER, true);
        if (isScroll) {
            setPositionPage(getPositionPage() + 1);
        } else {
            mSearchLogic.getArrDocumentLookUp().clear();
            mSearchLogic.getArrDocument().clear();
            setPositionPage(1);
        }
        switch (getIntent().getStringExtra(CHECK_SREEN)) {
            case SEARCH_COMING:
                mSearchLogic.searchSimpleComing(getRequestComing());
                break;
            case SEARCH_SENT:
                mSearchHeader.edtSentDocTypeId.setText(String.valueOf(0));
                mSearchLogic.searchSimpleSent(getRequestSend());
                break;
            case SEARCH_INTERNAL:
                mSearchLogic.searchSimpleInternal(getRequestInternal());
                break;
            case SEARCH_WORK_ARISE:
                mSearchLogic.searchSimpleWorkArise(getRequestWorkArise());
                break;
            default:
                mSearchLogic.searchSimpleProcess(getRequestProcess());
                break;
        }
        isScroll = false;
    }


    GsonSearchWorkArise.DataBean getRequestWorkArise() {
        return new GsonSearchWorkArise.DataBean(
                getLimitPager(),
                getPositionPage(),
                isAdvande(),
                editSearchTop.getText().toString(),
                mSearchHeader.edtWorkAriseCode.getText().toString(),
                mSearchHeader.edtWorkAriseName.getText().toString(),
                mSearchHeader.edtWorkAriseContent.getText().toString(),
                ReceiveRoomID,
                getMilisecondDay(mSearchHeader.edtWorkAriseFromDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtWorkAriseToDate.getText().toString().trim())
        );
    }


    GsonSearchProcess.DataBean getRequestProcess() {
        GsonSearchProcess.DataBean dataBean = new GsonSearchProcess.DataBean(
                urlNA,
                getLimitPager(),
                getPositionPage(),
                isAdvande(),
                editSearchTop.getText().toString(),
                ReceiveRoomID,
                getMilisecondDay(mSearchHeader.edtFromDay.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtToDay.getText().toString().trim()),
                0,
                0,
                0,
                0,
                mSearchHeader.edtSearchContent.getText().toString());
        return dataBean;
    }


    GsonSearchLookup.DataBean getRequestComing() {
        GsonSearchLookup.DataBean mLookup = new GsonSearchLookup.DataBean(
                getLimitPager(),
                getPositionPage(),
                isAdvande(),
                editSearchTop.getText().toString().trim(),
                mSearchHeader.edtComingNumberReceive.getText().toString().trim(),
                mSearchHeader.edtComingNumberOfSymbol.getText().toString().trim(),
                mSeachTypeDocument.getTypeDocumentId(),
                mSearchHeader.edtComingAbstract.getText().toString(),
                getMilisecondDay(mSearchHeader.edtComingDayFromProduct.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtComingDayToProduct.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtComingDayFromReceive.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtComingDaytoReceive.getText().toString().trim()));
        return mLookup;
    }

    GsonSearchSent.DataBean getRequestSend() {
        GsonSearchSent.DataBean mSent = new GsonSearchSent.DataBean(getLimitPager(),
                getPositionPage(),
                isAdvande(),
                editSearchTop.getText().toString(),
                mSearchHeader.edtSentPublishNumber.getText().toString().trim(),
                Long.parseLong(mSearchHeader.edtSentDocTypeId.getText().toString().trim()),
                mSearchHeader.edtSentBriefContent.getText().toString(),
                getMilisecondDay(mSearchHeader.edtSentFromPublishDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtSentToPublishDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtSentFromCreateDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtSentToCreateDate.getText().toString().trim()));
        return mSent;
    }

    GsonSearchInternal.DataBean getRequestInternal() {
        GsonSearchInternal.DataBean mInternal = new GsonSearchInternal.DataBean(
                getLimitPager(),
                getPositionPage(),
                isAdvande(),
                editSearchTop.getText().toString(),
                mSearchHeader.edtInternalDocNumber.getText().toString().trim(),
                mSearchHeader.edtInternalDocTypeId.getText().toString().trim(),
                mSearchHeader.edtInternalBriefContent.getText().toString().trim(),
                getMilisecondDay(mSearchHeader.edtInternalFromSignDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtInternalToSignDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtInternalFromCreateDate.getText().toString().trim()),
                getMilisecondDay(mSearchHeader.edtInternalToCreateDate.getText().toString().trim()));
        return mInternal;
    }

    boolean isAdvande() {
        if (!switchCompat.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    private void initView() {
        View header = getLayoutInflater().inflate(R.layout.header_search_list, null);
        lv.addHeaderView(header);
        mSearchHeader = new SearchHeader(header, this);
        lv.setAdapter(null);
    }

    public void showDetailSearch(boolean b) {
        lnSearchButton.setVisibility(b ? View.VISIBLE : View.GONE);
        lnEmpty.setVisibility(b ? View.GONE : View.VISIBLE);
        clearForm((ViewGroup) findViewById(R.id.search_root));
        lv.setAdapter(null);
        tvAdvance.setTextColor(Color.parseColor(b ? "#9F9F9F" : "#d7d7ce"));
        mSearchLogic.setSearchView(getIntent().getStringExtra(CHECK_SREEN), b);
        mSearchHeader.spnDepartment.setSelection(0);
    }

    private void initActiobarSearch() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar_seach);
        tvTitle.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(nULL_STRING);
        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.search_button).setVisible(false);
        return true;
    }


    @Override
    public void showComing(boolean b) {
        if (b) {
            lnEmpty.setVisibility(View.GONE);
            mSearchHeader.lnSearchComing.setVisibility(View.VISIBLE);
        } else {
            lnEmpty.setVisibility(View.VISIBLE);
            mSearchHeader.lnSearchComing.setVisibility(View.GONE);
        }

    }

    @Override
    public void showSent(boolean b) {
        if (b) {
            lnEmpty.setVisibility(View.GONE);
            mSearchHeader.lnSearchSent.setVisibility(View.VISIBLE);
        } else {
            lnEmpty.setVisibility(View.VISIBLE);
            mSearchHeader.lnSearchSent.setVisibility(View.GONE);
        }
    }

    @Override
    public void showInternal(boolean b) {
        if (b) {
            lnEmpty.setVisibility(View.GONE);
            mSearchHeader.lnSearchInternal.setVisibility(View.VISIBLE);
        } else {
            lnEmpty.setVisibility(View.VISIBLE);
            mSearchHeader.lnSearchInternal.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProcess(boolean b) {
        if (b) {
            lnEmpty.setVisibility(View.GONE);
            mSearchHeader.lnSearchProcess.setVisibility(View.VISIBLE);
        } else {
            lnEmpty.setVisibility(View.VISIBLE);
            mSearchHeader.lnSearchProcess.setVisibility(View.GONE);
        }
    }

    @Override
    public void showWorkArise(boolean b) {
        if (b) {
            lnEmpty.setVisibility(View.GONE);
            mSearchHeader.lnSearchWorkArise.setVisibility(View.VISIBLE);
        } else {
            lnEmpty.setVisibility(View.VISIBLE);
            mSearchHeader.lnSearchWorkArise.setVisibility(View.GONE);
        }
    }

    @Override
    public void getSrrListDepartmentSearch(ArrayList<ReceivePerson> arrDepartmentSearch) {
        arrListDepartmentSearch = arrDepartmentSearch;
    }

    @Override
    public void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList<String> arrNameDepartment) {
        mSearchHeader.spnDepartment.setAdapter(adapter);
        mSearchHeader.spnWorkAriseDepartMent.setAdapter(adapter);
    }

    @Override
    public void showErrorToast() {
        ShowErrorToast(this);
    }

    @Override
    public void setArrTypeDocumentID(ArrayList<Long> mArrayTypeDocumentID) {
        arrayTypeDocumentID = mArrayTypeDocumentID;
    }

    @Override
    public void setAdapterEdtComing(ArrayAdapter adapter) {
        mSearchHeader.edtComingTypeDocument.setAdapter(adapter);
        mSearchHeader.edtComingTypeDocument.showDropDown();
    }

    @Override
    public void setAdapterEdtSent(ArrayAdapter adapter) {
        mSearchHeader.edtSentDocTypeId.setAdapter(adapter);
        mSearchHeader.edtSentDocTypeId.showDropDown();
    }

    @Override
    public void setAdapterEdtInternal(ArrayAdapter adapter) {
        mSearchHeader.edtInternalDocTypeId.setAdapter(adapter);
        mSearchHeader.edtInternalDocTypeId.showDropDown();
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void ToastError(String s) {
        Log.d(KeyManager.TAG, s);
        ShowErrorToast(this);
    }

    @Override
    public void setListProcess(DocumentAdapter adapterSearch) {
        lv.setAdapter(adapterSearch);
    }

    @Override
    public void hideEmpty(int Visible) {
        lnEmpty.setVisibility(Visible);
    }

    @Override
    public void setListComing(DocumentLookupAdapter adapterDocumentLookup) {
        lv.setAdapter(adapterDocumentLookup);
    }

    @Override
    public void turnOffSearchAdvance(boolean b) {
        switchCompat.setChecked(b);
    }

    @Override
    public boolean getAfterSearch() {
        return isAfterSearch();
    }

    @Override
    public void startIntent(Intent intent) {
        startActivity(intent);
    }


}
