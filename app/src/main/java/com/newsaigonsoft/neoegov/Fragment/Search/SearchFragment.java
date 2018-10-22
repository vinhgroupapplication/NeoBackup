package com.newsaigonsoft.neoegov.Fragment.Search;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.SearchRow;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECK_SREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.CLICK_TIME_INTERVAL;

/**
 * Created by Vinh on 5/6/2017.
 */

public class SearchFragment extends SearchFmBase implements SearchFmView {
    @BindView(R.id.back_button_search_form)
    ImageView btnBack;
    @BindView(R.id.search_from_day)
    EditText edtFromDay;
    @BindView(R.id.search_to_day)
    EditText edtToDay;
    @BindView(R.id.search_content)
    EditText edtSearchContent;
    @BindView(R.id.search_character)
    EditText edtSearchTop;
    @BindView(R.id.search_button_form)
    ImageView btnSearch;
    @BindView(R.id.search_switch)
    SwitchCompat switchCompat;
    @BindView(R.id.search_spinner)
    Spinner spnDepartment;
    @BindView(R.id.search_process_working)
    LinearLayout mLayoutSearchProcess;
    @BindView(R.id.search_lookup_coming)
    LinearLayout mLayoutSearchComing;
    @BindView(R.id.search_lookup_sent)
    LinearLayout mLayoutSearchSent;
    @BindView(R.id.search_lookup_internal)
    LinearLayout mLayoutSearchInternal;
    @BindView(R.id.layout_search_top)
    LinearLayout mLinearSearchTop;
    // documentComing view
    @BindView(R.id.search_coming_number_receive)
    EditText edtComingNumberReceive;
    @BindView(R.id.search_coming_number_of_symblol)
    EditText edtComingNumberOfSymbol;
    @BindView(R.id.search_coming_day_to_product)
    EditText edtComingDayToProduct;
    @BindView(R.id.search_coming_day_from_product)
    EditText edtComingDayFromProduct;
    @BindView(R.id.search_coming_day_from_receive)
    EditText edtComingDayFromReceive;
    @BindView(R.id.search_coming_day_to_receive)
    EditText edtComingDaytoReceive;
    @BindView(R.id.search_coming_abstract)
    EditText edtComingAbstract;
    // documentSent view
    @BindView(R.id.search_sent_number_publish)
    EditText edtSentPublishNumber;
    @BindView(R.id.search_sent_briefContent)
    EditText edtSentBriefContent;
    @BindView(R.id.search_sent_fromPublishDate)
    EditText edtSentFromPublishDate;
    @BindView(R.id.search_sent_toPublishDate)
    EditText edtSentToPublishDate;
    @BindView(R.id.search_sent_fromCreateDate)
    EditText edtSentFromCreateDate;
    @BindView(R.id.search_sent_toCreateDate)
    EditText edtSentToCreateDate;
    //documentInternal view
    @BindView(R.id.search_internal_docNumber)
    EditText edtInternalDocNumber;
    @BindView(R.id.search_internal_briefContent)
    EditText edtInternalBriefContent;
    @BindView(R.id.search_internal_fromSignDate)
    EditText edtInternalFromSignDate;
    @BindView(R.id.search_internal_toSignDate)
    EditText edtInternalToSignDate;
    @BindView(R.id.search_internal_fromCreateDate)
    EditText edtInternalFromCreateDate;
    @BindView(R.id.search_internal_toCreateDate)
    EditText edtInternalToCreateDate;
    @BindView(R.id.search_coming_type)
    AutoCompleteTextView edtComingTypeDocument;
    @BindView(R.id.search_sent_docTypeId)
    AutoCompleteTextView edtSentDocTypeId;
    @BindView(R.id.search_internal_docTypeId)
    AutoCompleteTextView edtInternalDocTypeId;
    @BindView(R.id.notify_search)
    TextView tvNotifySearchError;

    private long mLastClickTime = System.currentTimeMillis();

    @Optional
    @OnClick({R.id.back_button_search_form, R.id.search_button_form})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_search_form:
                // it will call back to document screen
                ((OfficalActivity) getActivity()).changeUISearch(true);
                break;
            case R.id.search_button_form:
                long now = System.currentTimeMillis();
                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
                    return;
                }
                mLastClickTime = now;
                searchStart();
                break;
        }
        mOfficalActivity.showSoftwareKeyboard(false, mOfficalActivity);
    }



    @Optional
    @OnTouch({R.id.search_coming_day_from_product, R.id.search_coming_day_to_product, R.id.search_coming_day_from_receive,
            R.id.search_coming_day_to_receive, R.id.search_sent_toCreateDate, R.id.search_sent_fromCreateDate, R.id.search_sent_fromPublishDate,
            R.id.search_sent_toPublishDate, R.id.search_internal_fromCreateDate, R.id.search_internal_toCreateDate,
            R.id.search_internal_fromSignDate, R.id.search_internal_toSignDate, R.id.search_from_day, R.id.search_to_day
    })
    public boolean onTouch(View v, MotionEvent event) {
        if (clicked) {
            return false;
        }
        clicked = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switchCompat.setClickable(true);
                clicked = false;
            }
        }, 500);
        switch (v.getId()) {
            case R.id.search_coming_day_from_product:
//                mOfficalActivity.showDataPickDialog(edtComingDayFromProduct, getActivity());
                mOfficalActivity.showTimeDialog(edtComingDayFromProduct, getActivity());
                break;
            case R.id.search_coming_day_to_product:
                mOfficalActivity.showTimeDialog(edtComingDayToProduct, getActivity());
                break;
            case R.id.search_coming_day_from_receive:
//                mOfficalActivity.showDataPickDialog(edtComingDayFromReceive, getActivity());
                mOfficalActivity.showTimeDialog(edtComingDayFromReceive, getActivity());
                break;
            case R.id.search_coming_day_to_receive:
//                mOfficalActivity.showDataPickDialog(edtComingDaytoReceive, getActivity());
                mOfficalActivity.showTimeDialog(edtComingDaytoReceive, getActivity());
                break;
            case R.id.search_sent_toCreateDate:
//                mOfficalActivity.showDataPickDialog(edtSentToCreateDate, getActivity());
                mOfficalActivity.showTimeDialog(edtSentToCreateDate, getActivity());
                break;
            case R.id.search_sent_fromCreateDate:
//                mOfficalActivity.showDataPickDialog(edtSentFromCreateDate, getActivity());
                mOfficalActivity.showTimeDialog(edtSentFromCreateDate, getActivity());
                break;
            case R.id.search_sent_fromPublishDate:
//                mOfficalActivity.showDataPickDialog(edtSentFromPublishDate, getActivity());
                mOfficalActivity.showTimeDialog(edtSentFromPublishDate, getActivity());
                break;
            case R.id.search_sent_toPublishDate:
//                mOfficalActivity.showDataPickDialog(edtSentToPublishDate, getActivity());
                mOfficalActivity.showTimeDialog(edtSentToPublishDate, getActivity());
                break;
            case R.id.search_internal_fromCreateDate:
//                mOfficalActivity.showDataPickDialog(edtInternalFromCreateDate, getActivity());
                mOfficalActivity.showTimeDialog(edtInternalFromCreateDate, getActivity());
                break;
            case R.id.search_internal_toCreateDate:
//                mOfficalActivity.showDataPickDialog(edtInternalToCreateDate, getActivity());
                mOfficalActivity.showTimeDialog(edtInternalToCreateDate, getActivity());
                break;
            case R.id.search_internal_fromSignDate:
//                mOfficalActivity.showDataPickDialog(edtInternalFromSignDate, getActivity());
                mOfficalActivity.showTimeDialog(edtInternalFromSignDate, getActivity());
                break;
            case R.id.search_internal_toSignDate:
//                mOfficalActivity.showDataPickDialog(edtInternalToSignDate, getActivity());
                mOfficalActivity.showTimeDialog(edtInternalToSignDate, getActivity());
                break;
            case R.id.search_from_day:
//                mOfficalActivity.showDataPickDialog(edtFromDay, getActivity());
                mOfficalActivity.showTimeDialog(edtFromDay, getActivity());
                break;
            case R.id.search_to_day:
//                mOfficalActivity.showDataPickDialog(edtToDay, getActivity());
                mOfficalActivity.showTimeDialog(edtToDay, getActivity());
                break;
        }
        switchCompat.setClickable(false);
        return false;
    }

    public static SearchFragment newInstance() {
        SearchFragment myFragment = new SearchFragment();
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        initView();
        setTimeDefault();
        getStringIntent();
        mSearchFmLogic = new SearchFmLogic(this, mOfficalActivity, screenNameInSide);
        mSearchFmLogic.eventGetDepartmentSearchList();
//        mSearchFmLogic.setSearchView();
        showDetailSearch(false);
        switchCompat.setChecked(false);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showDetailSearch(b);
            }
        });

        edtComingTypeDocument.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0)
                    mSearchFmLogic.requestJsonDocumentType(edtComingTypeDocument.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        edtComingTypeDocument.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSeachTypeDocument.setTypeDocumentId(arrayTypeDocumentID.get(position));
//                Toast.makeText(getActivity(), arrayTypeDocumentID.get(position) + "", Toast.LENGTH_SHORT).show();
            }
        });

        edtSentDocTypeId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0)
                    mSearchFmLogic.requestJsonDocumentType(edtSentDocTypeId.getText().toString());
//                new ReadJsonDocumentType().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mOfficalActivity.getLink() + URL_CENTER_6_1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtSentDocTypeId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSeachTypeDocument.setTypeDocumentId(arrayTypeDocumentID.get(position));
            }
        });

        edtInternalDocTypeId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                new ReadJsonDocumentType().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mOfficalActivity.getLink() + URL_CENTER_6_1);
                if (s.length() != 0)
                    mSearchFmLogic.requestJsonDocumentType(edtInternalDocTypeId.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtInternalDocTypeId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSeachTypeDocument.setTypeDocumentId(arrayTypeDocumentID.get(position));
            }
        });

        return view;
    }

    @Override
    public void enableSearchComing() {
        mLayoutSearchComing.setVisibility(View.VISIBLE);
        mLayoutSearchProcess.setVisibility(View.GONE);
        mLayoutSearchSent.setVisibility(View.GONE);
        mLayoutSearchInternal.setVisibility(View.GONE);
    }

    public void showDetailSearch(boolean b) {
        if (b) {
            edtSearchTop.setVisibility(View.INVISIBLE);
            edtSearchTop.setText(nULL_STRING);
//            mLinearSearchTop.setVisibility(View.GONE);
            mSearchFmLogic.setSearchView();
        } else {
            disibleInputSearchTop(true);
            edtInternalBriefContent.setText(nULL_STRING);
            edtInternalDocTypeId.setText(nULL_STRING);
            edtInternalToSignDate.setText(nULL_STRING);
            edtInternalFromSignDate.setText(nULL_STRING);
            edtInternalToCreateDate.setText(nULL_STRING);
            edtInternalFromCreateDate.setText(nULL_STRING);
            edtInternalDocNumber.setText(nULL_STRING);
            edtSentBriefContent.setText(nULL_STRING);
            edtSentDocTypeId.setText(nULL_STRING);
            edtSentToPublishDate.setText(nULL_STRING);
            edtSentToPublishDate.setText(nULL_STRING);
            edtSentFromPublishDate.setText(nULL_STRING);
            edtSentToCreateDate.setText(nULL_STRING);
            edtSentFromCreateDate.setText(nULL_STRING);
            edtSentPublishNumber.setText(nULL_STRING);
            edtComingAbstract.setText(nULL_STRING);
            edtComingTypeDocument.setText(nULL_STRING);
            edtComingDaytoReceive.setText(nULL_STRING);
            edtComingDayFromReceive.setText(nULL_STRING);
            edtComingDayToProduct.setText(nULL_STRING);
            edtComingDayFromProduct.setText(nULL_STRING);
            edtComingNumberOfSymbol.setText(nULL_STRING);
            edtComingNumberReceive.setText(nULL_STRING);
            edtSearchContent.setText(nULL_STRING);
            spnDepartment.setSelection(0);
            edtToDay.setText(nULL_STRING);
            edtFromDay.setText(nULL_STRING);
            tvNotifySearchError.setVisibility(View.GONE);
            edtSearchTop.setVisibility(View.VISIBLE);
            mLayoutSearchComing.setVisibility(View.GONE);
            mLayoutSearchProcess.setVisibility(View.GONE);
            mLayoutSearchSent.setVisibility(View.GONE);
            mLayoutSearchInternal.setVisibility(View.GONE);
            ReceiveRoomID = nULL_STRING;
        }
    }


    @Override
    public void enableSearchSent() {
        mLayoutSearchComing.setVisibility(View.GONE);
        mLayoutSearchProcess.setVisibility(View.GONE);
        mLayoutSearchSent.setVisibility(View.VISIBLE);
        mLayoutSearchInternal.setVisibility(View.GONE);
    }

    @Override
    public void enableSearchInternal() {
        mLayoutSearchComing.setVisibility(View.GONE);
        mLayoutSearchProcess.setVisibility(View.GONE);
        mLayoutSearchSent.setVisibility(View.GONE);
        mLayoutSearchInternal.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableSearchProcess() {
        mLayoutSearchComing.setVisibility(View.GONE);
        mLayoutSearchProcess.setVisibility(View.VISIBLE);
        mLayoutSearchSent.setVisibility(View.GONE);
        mLayoutSearchInternal.setVisibility(View.GONE);
        spnDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!mArrListDepartmentSearch.get(position).getReceiveRoomID().equals(nULL_STRING)) {
                    ReceiveRoomID = mArrListDepartmentSearch.get(position).getReceiveRoomID();
                    Log.d("VinhCN: ", ReceiveRoomID + "");
                } else {
                    ReceiveRoomID = nULL_STRING;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void setAdapterEdtComing(ArrayAdapter adapter) {
        edtComingTypeDocument.setAdapter(adapter);
        edtComingTypeDocument.showDropDown();
    }

    @Override
    public void setAdapterEdtSent(ArrayAdapter adapter) {
        edtSentDocTypeId.setAdapter(adapter);
        edtSentDocTypeId.showDropDown();
    }

    @Override
    public void setAdapterEdtInternal(ArrayAdapter adapter) {
        edtInternalDocTypeId.setAdapter(adapter);
        edtInternalDocTypeId.showDropDown();
    }

    @Override
    public void setArrTypeDocumentID(ArrayList<Long> mArrayTypeDocumentID) {
        arrayTypeDocumentID = mArrayTypeDocumentID;
    }

    @Override
    public void showNotifySearchError() {
        tvNotifySearchError.setVisibility(View.VISIBLE);
    }

    @Override
    public void setMsgNotifySearchError(String s) {
        tvNotifySearchError.setText(s);
    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void hideNotifySearchError() {
        if (tvNotifySearchError.isShown()) {
            tvNotifySearchError.setVisibility(View.GONE);
        }
    }

    @Override
    public void startEvent(SearchRow mSearchRow) {
        mOfficalActivity.mOfficeLogic.eventSearchStart(mSearchRow);
    }

    @Override
    public void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList<String> arrNameDepartment) {
        spnDepartment.setAdapter(adapter);
    }

    @Override
    public void showToast() {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
    }

    @Override
    public void getSrrListDepartmentSearch(ArrayList<ReceivePerson> arrListDepartmentSearch) {
        mArrListDepartmentSearch = arrListDepartmentSearch;
    }

    @Override
    public void ToastError(String s) {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
    }


    private void setTimeDefault() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        mCalendar.set(Calendar.DAY_OF_MONTH, mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        edtComingDayFromReceive.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//        edtSentFromCreateDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//        edtInternalFromCreateDate.setText(mSimpleDateFormat.format(mCalendar.getTime()))
//        edtComingDaytoReceive.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//        edtSentToCreateDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//        edtInternalToCreateDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        edtComingDayFromProduct.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtComingDayFromProduct, getActivity());
//                return false;
//            }
//        });
//        edtComingDayToProduct.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtComingDayToProduct, getActivity());
//                return false;
//            }
//        });
//        edtComingDayFromReceive.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtComingDayFromReceive, getActivity());
//                return false;
//            }
//        });
//
//        edtComingDaytoReceive.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtComingDaytoReceive, getActivity());
//                return false;
//            }
//        });

//        edtSentToCreateDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtSentToCreateDate, getActivity());
//                return false;
//            }
//        });

//        edtSentFromCreateDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtSentFromCreateDate, getActivity());
//                return false;
//            }
//        });

//        edtSentFromPublishDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtSentFromPublishDate, getActivity());
//                return false;
//            }
//        });

//        edtSentToPublishDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtSentToPublishDate, getActivity());
//                return false;
//            }
//        });
//
//        edtInternalFromCreateDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtInternalFromCreateDate, getActivity());
//                return false;
//            }
//        });
//        edtInternalToCreateDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtInternalToCreateDate, getActivity());
//                return false;
//            }
//        });
//
//        edtInternalFromSignDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtInternalFromSignDate, getActivity());
//                return false;
//            }
//        });
//
//        edtInternalToSignDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtInternalToSignDate, getActivity());
//                return false;
//            }
//        });
//
//        edtFromDay.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtFromDay, getActivity());
//                return false;
//            }
//        });
//        edtToDay.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return true;
//                }
//                mLastClickTime = now;
//                mOfficalActivity.showDataPickDialog(edtToDay, getActivity());
//                return false;
//            }
//        });
    }

    private void getStringIntent() {
        screenNameInSide = getArguments().getString(CHECK_SREEN);
    }


    private void initView() {
        tvNotifySearchError.setVisibility(View.GONE);
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
        itemSearchButton.setVisible(false);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }


    public void disibleInputSearchTop(boolean isDisible) {
        edtSearchTop.setFocusableInTouchMode(isDisible);
        edtSearchTop.setFocusable(isDisible);
    }

    // create json and start search here
    public void searchStart() {
        disibleInputSearchTop(false);
        mOfficalActivity.showProgressDialog(nULL_STRING, getActivity(), DIALOG_CENTER, false);
        if (edtComingTypeDocument.getText().toString().equals(nULL_STRING) && edtSentDocTypeId.getText().toString().equals("")
                && edtInternalDocTypeId.getText().toString().equals(nULL_STRING)) {
            mSeachTypeDocument.setTypeDocumentId(0);
        }
        String strSearchTop = edtSearchTop.getText().toString().trim();
        switch (screenNameInSide) {
            case SEARCH_PROCESS:
                String strFromDay = edtFromDay.getText().toString().trim();
                String strToDay = edtToDay.getText().toString().trim();
                String strSearchContent = edtSearchContent.getText().toString().trim();
                mSearchFmLogic.SearchDocument(strSearchTop, strFromDay, strToDay, strSearchContent, ReceiveRoomID);
                break;
            case SEARCH_COMING:
                String strComingNumberReceive = edtComingNumberReceive.getText().toString().trim();
                String strComingNumberOfSymbol = edtComingNumberOfSymbol.getText().toString().trim();
                String strComingDayFromProduct = edtComingDayFromProduct.getText().toString().trim();
                String strComingDayToProduct = edtComingDayToProduct.getText().toString().trim();
                String strComingDayFromReceive = edtComingDayFromReceive.getText().toString().trim();
                String strComingDaytoReceive = edtComingDaytoReceive.getText().toString().trim();
                String strComingTypeDocument = edtComingTypeDocument.getText().toString().trim();
                String strComingAbstract = edtComingAbstract.getText().toString().trim();
                mSearchFmLogic.SearchDocumentComing(strSearchTop, strComingNumberReceive, strComingNumberOfSymbol, strComingDayFromProduct, strComingDayToProduct,
                        strComingDayFromReceive, strComingDaytoReceive, strComingTypeDocument, strComingAbstract,
                        mSeachTypeDocument.getTypeDocumentId());

                break;
            case SEARCH_SENT:
                String strSentPublishNumber = edtSentPublishNumber.getText().toString().trim();
                String strSentDocTypeId = edtSentDocTypeId.getText().toString().trim();
                String strSentBriefContent = edtSentBriefContent.getText().toString();
                String strSentFromPublishDate = edtSentFromPublishDate.getText().toString().trim();
                String strSentToPublishDate = edtSentToPublishDate.getText().toString().trim();
                String strSentFromCreateDate = edtSentFromCreateDate.getText().toString().trim();
                String strSentToCreateDate = edtSentToCreateDate.getText().toString().trim();
                mSearchFmLogic.SearchSentDocument(strSearchTop, strSentPublishNumber, strSentDocTypeId, strSentBriefContent, strSentFromPublishDate,
                        strSentToPublishDate, strSentFromCreateDate, strSentToCreateDate, mSeachTypeDocument.getTypeDocumentId());
                break;
            case SEARCH_INTERNAL:
                String strInternalDocNumber = edtInternalDocNumber.getText().toString().trim();
                String strInternalDocTypeId = edtInternalDocTypeId.getText().toString().trim();
                String strInternalBriefContent = edtInternalBriefContent.getText().toString().trim();
                String strInternalFromSignDate = edtInternalFromSignDate.getText().toString().trim();
                String strInternalToSignDate = edtInternalToSignDate.getText().toString().trim();
                String strInternalFromCreateDate = edtInternalFromCreateDate.getText().toString().trim();
                String strInternalToCreateDate = edtInternalToCreateDate.getText().toString().trim();
                mSearchFmLogic.SearchInternalDocument(strSearchTop, strInternalDocNumber, strInternalDocTypeId, strInternalBriefContent, strInternalFromSignDate,
                        strInternalToSignDate, strInternalFromCreateDate, strInternalToCreateDate, mSeachTypeDocument.getTypeDocumentId());
                break;
            default:
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOfficalActivity.showSoftwareKeyboard(false, mOfficalActivity);
    }

//    public void SearchDocument(String strSearchTop, String strFromDay, String strToDay, String strSearchContent) {
//        long DayFrom = 0;
//        long DayTo = 0;
//        JSONObject mJsonObject = new JSONObject();
//        String tieuChiTimKiem = nULL_STRING;
//        if (strSearchTop.equals(nULL_STRING)
//                && ReceiveRoomID.equals(FIRST_ITEM_SEARCH_SPINNER)
//                && strFromDay.equals(nULL_STRING)
//                && strToDay.equals(nULL_STRING)
//                && strSearchContent.equals(nULL_STRING)
//                ) {
//            tieuChiTimKiem = nULL_STRING;
//        } else {
//            if (!strSearchTop.equals(nULL_STRING)
//                    && ReceiveRoomID.equals(FIRST_ITEM_SEARCH_SPINNER)
//                    && strFromDay.equals(nULL_STRING)
//                    && strToDay.equals(nULL_STRING)
//                    && strSearchContent.equals(nULL_STRING)
//                    ) {
//                try {
//                    mJsonObject.put(ADVANCED, false);
//                    mJsonObject.put(KEYWORDS, edtSearchTop.getText().toString());
//                    mJsonObject.put(ORGANIZATION_EDITOR_ID, 0);
//                    mJsonObject.put(FROM_RECEIVE_DATE, 0);
//                    mJsonObject.put(TO_RECEIVE_DATE, 0);
//                    mJsonObject.put(PROCESS_CONTENT, nULL_STRING);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            } else {
//                if (!strFromDay.equals(nULL_STRING)) {
//                    DayFrom = mOfficalActivity.getMilisecondDay(edtFromDay);
//                }
//                if (!strToDay.equals(nULL_STRING)) {
//                    DayTo = mOfficalActivity.getMilisecondDay(edtToDay);
//                }
//                try {
//                    mJsonObject.put(ADVANCED, true);
//                    mJsonObject.put(KEYWORDS, strSearchTop);
//                    mJsonObject.put(ORGANIZATION_EDITOR_ID, ReceiveRoomID);
//                    mJsonObject.put(FROM_RECEIVE_DATE, DayFrom);
//                    mJsonObject.put(TO_RECEIVE_DATE, DayTo);
//                    mJsonObject.put(PROCESS_CONTENT, strSearchContent);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            }
//        }
//        if (DayFrom > DayTo) {
//            tvNotifySearchError.setVisibility(View.VISIBLE);
//            tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//            mOfficalActivity.closeProgressDialog();
//        } else {
//            if (tvNotifySearchError.isShown()) {
//                tvNotifySearchError.setVisibility(View.GONE);
//            }
//            mOfficalActivity.eventSearchStart(tieuChiTimKiem, LimitPager);
//        }
//
//    }
//private void ReferenceView(View view) {
//    mLinearSearchTop = (LinearLayout) view.findViewById(R.id.layout_search_top);
//    switchCompat = (SwitchCompat) view.findViewById(R.id.search_switch);
//    btnSearch = (ImageView) view.findViewById(R.id.search_button_form);
//    btnBack = (ImageView) view.findViewById(R.id.back_button_search_form);
//    edtFromDay = (EditText) view.findViewById(R.id.search_from_day);
//    edtToDay = (EditText) view.findViewById(R.id.search_to_day);
//    edtSearchContent = (EditText) view.findViewById(R.id.search_content);
//    edtSearchTop = (EditText) view.findViewById(R.id.search_character);
//    spnDepartment = (Spinner) view.findViewById(R.id.search_spinner);
//    mLayoutSearchComing = (LinearLayout) view.findViewById(R.id.search_lookup_coming);
//    mLayoutSearchProcess = (LinearLayout) view.findViewById(R.id.search_process_working);
//    mLayoutSearchInternal = (LinearLayout) view.findViewById(R.id.search_lookup_internal);
//    mLayoutSearchSent = (LinearLayout) view.findViewById(R.id.search_lookup_sent);
//    //init view comingDocument
//    edtComingNumberReceive = (EditText) view.findViewById(R.id.search_coming_number_receive);
//    edtComingNumberOfSymbol = (EditText) view.findViewById(R.id.search_coming_number_of_symblol);
//    edtComingDayToProduct = (EditText) view.findViewById(R.id.search_coming_day_to_product);
//    edtComingDayFromProduct = (EditText) view.findViewById(R.id.search_coming_day_from_product);
//    edtComingDayFromReceive = (EditText) view.findViewById(R.id.search_coming_day_from_receive);
//    edtComingDaytoReceive = (EditText) view.findViewById(R.id.search_coming_day_to_receive);
//    edtComingTypeDocument = (AutoCompleteTextView) view.findViewById(R.id.search_coming_type);
//    edtComingAbstract = (EditText) view.findViewById(R.id.search_coming_abstract);
//    //init view sendDocument
//    edtSentPublishNumber = (EditText) view.findViewById(R.id.search_sent_number_publish);
//    edtSentDocTypeId = (AutoCompleteTextView) view.findViewById(R.id.search_sent_docTypeId);
//    edtSentBriefContent = (EditText) view.findViewById(R.id.search_sent_briefContent);
//    edtSentFromPublishDate = (EditText) view.findViewById(R.id.search_sent_fromPublishDate);
//    edtSentToPublishDate = (EditText) view.findViewById(R.id.search_sent_toPublishDate);
//    edtSentFromCreateDate = (EditText) view.findViewById(R.id.search_sent_fromCreateDate);
//    edtSentToCreateDate = (EditText) view.findViewById(R.id.search_sent_toCreateDate);
//    //init view internalDocument
//    edtInternalDocNumber = (EditText) view.findViewById(R.id.search_internal_docNumber);
//    edtInternalDocTypeId = (AutoCompleteTextView) view.findViewById(R.id.search_internal_docTypeId);
//    edtInternalBriefContent = (EditText) view.findViewById(R.id.search_internal_briefContent);
//    edtInternalFromSignDate = (EditText) view.findViewById(R.id.search_internal_fromSignDate);
//    edtInternalToSignDate = (EditText) view.findViewById(R.id.search_internal_toSignDate);
//    edtInternalFromCreateDate = (EditText) view.findViewById(R.id.search_internal_fromCreateDate);
//    edtInternalToCreateDate = (EditText) view.findViewById(R.id.search_internal_toCreateDate);
//    tvNotifySearchError = (TextView) view.findViewById(R.id.notify_search);
//    tvNotifySearchError.setVisibility(View.GONE);
//    btnBack.setOnClickListener(this);
//    btnSearch.setOnClickListener(this);
//}
//    class ReadJsonDocumentType extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, TYPE_DOCUMENT);
//                JSONObject mJsonObjectData = new JSONObject();
//                mJsonObjectData.put(NUM_ROW, 10);
//                switch (screenNameInSide) {
//                    case SEARCH_COMING:
//                        mJsonObjectData.put(KEYWORDS, edtComingTypeDocument.getText().toString());
//                        break;
//                    case SEARCH_SENT:
//                        mJsonObjectData.put(KEYWORDS, edtSentDocTypeId.getText().toString());
//                        break;
//                    case SEARCH_INTERNAL:
//                        mJsonObjectData.put(KEYWORDS, edtInternalDocTypeId.getText().toString());
//                        break;
//                    default:
//                        break;
//                }
//                mJsonObject.put(DATA, mJsonObjectData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return mOfficalActivity.makePostRequest(params[0], mJsonObject.toString(), mOfficalActivity.getUserid(), mOfficalActivity.getPass());
//            return mOfficalActivity.eventPostRequest(mOfficalActivity.getModuleInfor(MODULE_LOOKUP_DOCUMENT, mOfficalActivity), mJsonObject.toString(), mOfficalActivity.getUserid(), mOfficalActivity.getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            arrayTypeDocumentName = new ArrayList<String>();
//            arrayTypeDocumentID = new ArrayList<Long>();
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mJsonObjectData = mJsonArray.getJSONObject(i);
//                    String TypeDocumentName = mJsonObjectData.getString(NAME);
//                    long TypeDocumentId = mJsonObjectData.getLong(ID);
//                    arrayTypeDocumentName.add(TypeDocumentName);
//                    arrayTypeDocumentID.add(TypeDocumentId);
//                }
//                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayTypeDocumentName);
//                switch (screenNameInSide) {
//                    case SEARCH_COMING:
//                        edtComingTypeDocument.setAdapter(adapter);
//                        edtComingTypeDocument.showDropDown();
//                        break;
//                    case SEARCH_SENT:
//                        edtSentDocTypeId.setAdapter(adapter);
//                        edtSentDocTypeId.showDropDown();
//                        break;
//                    case SEARCH_INTERNAL:
//                        edtInternalDocTypeId.setAdapter(adapter);
//                        edtInternalDocTypeId.showDropDown();
//                        break;
//                    default:
//                        break;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
    //    public void SearchDocumentComing(String strSearchTop, String strComingNumberReceive, String strComingNumberOfSymbol, String strComingDayFromProduct,
//                                     String strComingDayToProduct, String strComingDayFromReceive, String strComingDaytoReceive,
//                                     String strComingTypeDocument, String strComingAbstract
//
//    ) {
//        long fromPublishDate = 0;
//        long toPublishDate = 0;
//        long fromReceiveDate = 0;
//        long toReceiveDate = 0;
//        JSONObject mJsonObject = new JSONObject();
//        String tieuChiTimKiem = nULL_STRING;
//        if (strSearchTop.equals(nULL_STRING)
//                && strComingNumberReceive.equals(nULL_STRING)
//                && strComingNumberOfSymbol.equals(nULL_STRING)
//                && strComingDayToProduct.equals(nULL_STRING)
//                && strComingDayFromProduct.equals(nULL_STRING)
//                && strComingDayFromReceive.equals(nULL_STRING)
//                && strComingDaytoReceive.equals(nULL_STRING)
//                && strComingTypeDocument.equals(nULL_STRING)
//                && strComingAbstract.equals(nULL_STRING)
//                ) {
//            tieuChiTimKiem = nULL_STRING;
//        } else {
//            if (!strSearchTop.equals(nULL_STRING)
//                    && strComingNumberReceive.equals(nULL_STRING)
//                    && strComingNumberOfSymbol.equals(nULL_STRING)
//                    && strComingDayToProduct.equals(nULL_STRING)
//                    && strComingDayFromProduct.equals(nULL_STRING)
//                    && strComingDayFromReceive.equals(nULL_STRING)
//                    && strComingDaytoReceive.equals(nULL_STRING)
//                    && strComingTypeDocument.equals(nULL_STRING)
//                    && strComingAbstract.equals(nULL_STRING)
//                    ) {
//                try {
//                    mJsonObject.put(ADVANCED, false);
//                    mJsonObject.put(KEYWORDS, strSearchTop);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            } else {
//                if (!strComingDayFromProduct.equals(nULL_STRING)) {
//                    fromPublishDate = mOfficalActivity.getMilisecondDay(strComingDayFromProduct);
//                }
//                if (!strComingDayToProduct.equals(nULL_STRING)) {
//                    toPublishDate = mOfficalActivity.getMilisecondDay(strComingDayToProduct);
//                }
//                if (!strComingDayFromReceive.equals(nULL_STRING)) {
//                    fromReceiveDate = mOfficalActivity.getMilisecondDay(strComingDayFromReceive);
//                }
//                if (!strComingDaytoReceive.equals(nULL_STRING)) {
//                    toReceiveDate = mOfficalActivity.getMilisecondDay(strComingDaytoReceive);
//                }
//                try {
//                    mJsonObject.put(ADVANCED, true);
//                    mJsonObject.put(DOC_NUMBER_FULL, strComingNumberReceive);
//                    mJsonObject.put(NUMBER_OF_SYMBOL, strComingNumberOfSymbol);
//                    mJsonObject.put(DOC_TYPE_ID, mSeachTypeDocument.getTypeDocumentId());
//                    mJsonObject.put(BRIEF_CONTENT, strComingAbstract);
//                    mJsonObject.put(FROM_PUBLISH_DATE, fromPublishDate);
//                    mJsonObject.put(TO_PUBLISH_DATE, toPublishDate);
//                    mJsonObject.put(FROM_RECEIVE_DATE, fromReceiveDate);
//                    mJsonObject.put(TO_RECEIVE_DATE, toReceiveDate);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            }
//        }
//        if (fromPublishDate > toPublishDate) {
//            tvNotifySearchError.setVisibility(View.VISIBLE);
//            tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//            mOfficalActivity.closeProgressDialog();
//        } else {
//            if (fromReceiveDate > toReceiveDate) {
//                tvNotifySearchError.setVisibility(View.VISIBLE);
//                tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//                mOfficalActivity.closeProgressDialog();
//            } else {
//                if (tvNotifySearchError.isShown()) {
//                    tvNotifySearchError.setVisibility(View.GONE);
//                }
//                mOfficalActivity.eventSearchStart(tieuChiTimKiem, LimitPager);
//            }
//        }
//    }

//    public void SearchInternalDocument(String strSearchTop, String strInternalDocNumber, String strInternalDocTypeId, String strInternalBriefContent,
//                                       String strInternalFromSignDate, String strInternalToSignDate, String strInternalFromCreateDate, String strInternalToCreateDate,
//                                       long TypeDocumentId
//
//    ) {
//        long fromSignDate = 0;
//        long toSignDate = 0;
//        long fromCreateDate = 0;
//        long toCreateDate = 0;
//        JSONObject mJsonObject = new JSONObject();
//        String tieuChiTimKiem = nULL_STRING;
//        if (strSearchTop.equals(nULL_STRING)
//                && strInternalDocNumber.equals(nULL_STRING)
//                && strInternalDocTypeId.equals(nULL_STRING)
//                && strInternalBriefContent.equals(nULL_STRING)
//                && strInternalFromSignDate.equals(nULL_STRING)
//                && strInternalFromSignDate.equals(nULL_STRING)
//                && strInternalToSignDate.equals(nULL_STRING)
//                && strInternalFromCreateDate.equals(nULL_STRING)
//                && strInternalToCreateDate.equals(nULL_STRING)
//                ) {
//            tieuChiTimKiem = nULL_STRING;
//        } else {
//            if (!strSearchTop.equals(nULL_STRING)
//                    && strInternalDocNumber.equals(nULL_STRING)
//                    && strInternalDocTypeId.equals(nULL_STRING)
//                    && strInternalBriefContent.equals(nULL_STRING)
//                    && strInternalFromSignDate.equals(nULL_STRING)
//                    && strInternalFromSignDate.equals(nULL_STRING)
//                    && strInternalToSignDate.equals(nULL_STRING)
//                    && strInternalFromCreateDate.equals(nULL_STRING)
//                    && strInternalToCreateDate.equals(nULL_STRING)
//                    ) {
//                try {
//                    mJsonObject.put(ADVANCED, false);
//                    mJsonObject.put(KEYWORDS, strSearchTop);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            } else {
//
//                if (!strInternalFromSignDate.equals(nULL_STRING)) {
//                    fromSignDate = mOfficalActivity.getMilisecondDay(strInternalFromSignDate);
//                }
//                if (!strInternalToSignDate.equals(nULL_STRING)) {
//                    toSignDate = mOfficalActivity.getMilisecondDay(strInternalToSignDate);
//                }
//                if (!strInternalFromCreateDate.equals(nULL_STRING)) {
//                    fromCreateDate = mOfficalActivity.getMilisecondDay(strInternalFromCreateDate);
//                }
//                if (!strInternalToCreateDate.equals(nULL_STRING)) {
//                    toCreateDate = mOfficalActivity.getMilisecondDay(strInternalToCreateDate);
//                }
//                try {
//                    mJsonObject.put(ADVANCED, true);
//                    mJsonObject.put(DOC_NUMBER, strInternalDocNumber);
//                    mJsonObject.put(DOC_TYPE_ID, TypeDocumentId);
//                    mJsonObject.put(BRIEF_CONTENT, strInternalBriefContent);
//                    mJsonObject.put(FROM_SIGN_DATE, fromSignDate);
//                    mJsonObject.put(TO_SIGN_DATE, toSignDate);
//                    mJsonObject.put(FROM_CREATE_DATE, fromCreateDate);
//                    mJsonObject.put(TO_CREATE_DATE, toCreateDate);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            }
//        }
//        if (fromSignDate > toSignDate) {
//            tvNotifySearchError.setVisibility(View.VISIBLE);
//            tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//            mOfficalActivity.closeProgressDialog();
//        } else {
//            if (fromCreateDate > toCreateDate) {
//                tvNotifySearchError.setVisibility(View.VISIBLE);
//                tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//                mOfficalActivity.closeProgressDialog();
//            } else {
//                if (tvNotifySearchError.isShown()) {
//                    tvNotifySearchError.setVisibility(View.GONE);
//                }
//                mOfficalActivity.eventSearchStart(tieuChiTimKiem, LimitPager);
//            }
//        }
//    }

//    public void SearchSentDocument(String strSearchTop,String strSentPublishNumber, String strSentDocTypeId,String strSentBriefContent,
//                                   String strSentFromPublishDate, String strSentToPublishDate, String strSentFromCreateDate,String strSentToCreateDate
//    ) {
//        long fromPublishDate = 0;
//        long toPublishDate = 0;
//        long fromCreateDate = 0;
//        long toCreateDate = 0;
//        JSONObject mJsonObject = new JSONObject();
//        String tieuChiTimKiem = nULL_STRING;
//
//
//
//        if (strSearchTop.equals(nULL_STRING)
//                && strSentPublishNumber.equals(nULL_STRING)
//                && strSentDocTypeId.equals(nULL_STRING)
//                && strSentBriefContent.equals(nULL_STRING)
//                && strSentFromPublishDate.equals(nULL_STRING)
//                && strSentToPublishDate.equals(nULL_STRING)
//                && strSentFromCreateDate.equals(nULL_STRING)
//                && strSentToCreateDate.equals(nULL_STRING)
//                ) {
//            tieuChiTimKiem = nULL_STRING;
//        } else {
//            if (!strSearchTop.equals(nULL_STRING)
//                    && strSentPublishNumber.equals(nULL_STRING)
//                    && strSentDocTypeId.equals(nULL_STRING)
//                    && strSentBriefContent.equals(nULL_STRING)
//                    && strSentFromPublishDate.equals(nULL_STRING)
//                    && strSentToPublishDate.equals(nULL_STRING)
//                    && strSentFromCreateDate.equals(nULL_STRING)
//                    && strSentToCreateDate.equals(nULL_STRING)
//                    ) {
//                try {
//                    mJsonObject.put(ADVANCED, false);
//                    mJsonObject.put(KEYWORDS, strSearchTop);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            } else {
//
//
//                if (!strSentFromPublishDate.equals(nULL_STRING)) {
//                    fromPublishDate = mOfficalActivity.getMilisecondDay(strSentFromPublishDate);
//                }
//                if (!strSentToPublishDate.equals(nULL_STRING)) {
//                    toPublishDate = mOfficalActivity.getMilisecondDay(strSentToPublishDate);
//                }
//                if (!strSentFromCreateDate.equals(nULL_STRING)) {
//                    fromCreateDate = mOfficalActivity.getMilisecondDay(strSentFromCreateDate);
//                }
//                if (!strSentToCreateDate.equals(nULL_STRING)) {
//                    toCreateDate = mOfficalActivity.getMilisecondDay(strSentToCreateDate);
//                }
//                try {
//                    mJsonObject.put(ADVANCED, true);
//                    mJsonObject.put(PUBLISH_NUMBER, strSentPublishNumber);
//                    mJsonObject.put(DOC_TYPE_ID, mSeachTypeDocument.getTypeDocumentId());
//                    mJsonObject.put(BRIEF_CONTENT, strSentBriefContent);
//                    mJsonObject.put(FROM_PUBLISH_DATE, fromPublishDate);
//                    mJsonObject.put(TO_PUBLISH_DATE, toPublishDate);
//                    mJsonObject.put(FROM_CREATE_DATE, fromCreateDate);
//                    mJsonObject.put(TO_CREATE_DATE, toCreateDate);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                tieuChiTimKiem = mJsonObject.toString();
//            }
//        }
//        if (fromPublishDate > toPublishDate) {
//            tvNotifySearchError.setVisibility(View.VISIBLE);
//            tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//            mOfficalActivity.closeProgressDialog();
//        } else {
//            if (fromCreateDate > toCreateDate) {
//                tvNotifySearchError.setVisibility(View.VISIBLE);
//                tvNotifySearchError.setText("Ngày đến không được nhỏ hơn ngày bắt đầu.");
//                mOfficalActivity.closeProgressDialog();
//            } else {
//                if (tvNotifySearchError.isShown()) {
//                    tvNotifySearchError.setVisibility(View.GONE);
//                }
//                mOfficalActivity.eventSearchStart(tieuChiTimKiem, LimitPager);
//            }
//
//        }
//    }
    // set data for spinner department
//    public void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList arrNameDepartment) {
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnDepartment.setAdapter(adapter);
//        spnDepartment.setSelection(adapter.getCount());
//    }
}
