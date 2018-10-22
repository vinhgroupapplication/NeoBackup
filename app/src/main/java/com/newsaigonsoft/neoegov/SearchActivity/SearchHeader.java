package com.newsaigonsoft.neoegov.SearchActivity;

import android.content.Context;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.newsaigonsoft.neoegov.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class SearchHeader {
    Context context;
    @BindView(R.id.search_spinner)
    Spinner spnDepartment;
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
    // parent layout
    @BindView(R.id.search_process_working)
    LinearLayout lnSearchProcess;
    @BindView(R.id.search_lookup_coming)
    LinearLayout lnSearchComing;
    @BindView(R.id.search_lookup_sent)
    LinearLayout lnSearchSent;
    @BindView(R.id.search_lookup_internal)
    LinearLayout lnSearchInternal;
    @BindView(R.id.search_from_day)
    EditText edtFromDay;
    @BindView(R.id.search_to_day)
    EditText edtToDay;
    @BindView(R.id.search_content)
    EditText edtSearchContent;
    // document work arise view
    @BindView(R.id.search_work_arise)
    LinearLayout lnSearchWorkArise;
    @BindView(R.id.search_work_arise_code)
    EditText edtWorkAriseCode;
    @BindView(R.id.search_work_arise_name)
    EditText edtWorkAriseName;
    @BindView(R.id.search_work_arise_from_date)
    EditText edtWorkAriseFromDate;
    @BindView(R.id.search_work_arise_to_date)
    EditText edtWorkAriseToDate;
    @BindView(R.id.search_work_arise_department)
    Spinner spnWorkAriseDepartMent;
    @BindView(R.id.search_work_arise_content)
    EditText edtWorkAriseContent;

    public SearchHeader(View view, Context mContext) {
        context = mContext;
        ButterKnife.bind(this, view);

    }

    @Optional
    @OnClick({R.id.search_coming_day_from_product,
            R.id.search_coming_day_to_product,
            R.id.search_coming_day_from_receive,
            R.id.search_coming_day_to_receive,
            R.id.search_sent_toCreateDate,
            R.id.search_sent_fromCreateDate,
            R.id.search_sent_fromPublishDate,
            R.id.search_sent_toPublishDate,
            R.id.search_internal_fromCreateDate,
            R.id.search_internal_toCreateDate,
            R.id.search_internal_fromSignDate,
            R.id.search_internal_toSignDate,
            R.id.search_from_day,
            R.id.search_to_day,
            R.id.search_work_arise_from_date,
            R.id.search_work_arise_to_date
    })
    void OnClick(View view){
        ((SeachActivity) context).headerClick(view);
    }

}
