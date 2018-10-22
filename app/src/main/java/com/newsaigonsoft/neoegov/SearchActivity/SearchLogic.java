package com.newsaigonsoft.neoegov.SearchActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocComing;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocInternal;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocSent;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchInternal;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchLookup;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchProcess;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchSent;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchWorkArise;
import com.newsaigonsoft.neoegov.GsonObject.GsonWorkAriseList;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocumentLookUp;
import com.newsaigonsoft.neoegov.Subjects.JsonKeyManager;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ADVANCED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BRIEF_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CODE_WORK;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NUMBER_FULL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_TYPE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_CREATE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DUE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_PROCESS_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_PUBLISH_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_RECEIVE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_SIGN_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.KEYWORDS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.LABLE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NUMBER_OF_SYMBOL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NUM_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORG_CREATE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PUBLISH_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ROW_PER_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_CREATE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DUE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_PROCESS_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_PUBLISH_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_RECEIVE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_SIGN_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DEPARTMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DOCUMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_LIST_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_LIST_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_WORK_ARISE_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DEPARTMENT_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.KEYURLNA;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OVER_NETWORK_INTENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PAGEPOSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_DOCUMENT_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_LIST_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_LIST_DEPARTMENT_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_WORK_ARISE_DEPARTMENT_LIST;

public class SearchLogic extends SearchBase implements SeachImp, VolleyTaskCompletedListenner<ResuiltObject> {

    Context context;
    SeachView mSeachView;
    String Screen;

    public SearchLogic(Context context, SeachView mSeachView, String screen) {
        this.context = context;
        this.mSeachView = mSeachView;
        this.Screen = screen;
        initNewArrayList();
    }

    @Override
    public void setSearchView(String stringExtra, boolean b) {
        switch (stringExtra) {
            case SEARCH_COMING:
                mSeachView.showComing(b);
                break;
            case SEARCH_SENT:
                mSeachView.showSent(b);
                break;
            case SEARCH_INTERNAL:
                mSeachView.showInternal(b);
                break;
            case SEARCH_WORK_ARISE:
                mSeachView.showWorkArise(b);
                break;
            default:
                mSeachView.showProcess(b);
                break;
        }
    }

    @Override
    public void searchSimpleProcess(GsonSearchProcess.DataBean mDataBean) {
        new VolleyTask(context, SEARCH_PROCESS, addJsonSearchProcess(MODULE_PROCESSING_WORKING, TYPE_DOCUMENT_LIST, mDataBean), this);
    }

    @Override
    public void eventGetDepartmentSearchList() {
        new VolleyTask(context, SEARCH_LIST_DEPARTMENT, addJonRequestListDPM(), this);
    }

    @Override
    public void requestJsonDocumentType(String s) {
        mTypeDocument = s;
        new VolleyTask(context, SEARCH_DOCUMENT_TYPE, addJsonDocumentType(), this);
    }

    @Override
    public void searchSimpleComing(GsonSearchLookup.DataBean mLookup) {
        new VolleyTask(context, SEARCH_COMING, addJsonSearchComing(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_COMING, mLookup), this);

    }

    @Override
    public void searchSimpleSent(GsonSearchSent.DataBean mSent) {
        new VolleyTask(context, SEARCH_SENT, addJsonSearchSent(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_SENT, mSent), this);
    }

    @Override
    public void searchSimpleInternal(GsonSearchInternal.DataBean mInternal) {
        new VolleyTask(context, SEARCH_INTERNAL, addJsonSearchInternal(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL, mInternal), this);

    }

    @Override
    public void searchSimpleWorkArise(GsonSearchWorkArise.DataBean requestWorkArise) {
        new VolleyTask(context, SEARCH_WORK_ARISE, addJsonSearchWorkArise(MODULE_WORK_ARISE, TYPE_WORK_ARISE_LIST, requestWorkArise), this);
    }

    private JSONObject addJsonSearchWorkArise(String MODULEs, String TYPEs, GsonSearchWorkArise.DataBean requestWorkArise) {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            mData.put(ROW_PER_PAGE, requestWorkArise.getRowPerPage());
            mData.put(NUM_PAGE, requestWorkArise.getNumPage());
            mData.put(ADVANCED, requestWorkArise.isAdvanced());
            mData.put(KEYWORDS, requestWorkArise.getKeywords());
            mData.put(CODE_WORK, requestWorkArise.getCodeWork());
            mData.put(TITLE, requestWorkArise.getTitle());
            mData.put(SCHEDULE_CONTENT, requestWorkArise.getContent());
            mData.put(ORG_CREATE_ID, requestWorkArise.getOrgCreateId());
            mData.put(FROM_CREATE_DATE, requestWorkArise.getFromCreateDate());
            mData.put(TO_CREATE_DATE, requestWorkArise.getToCreateDate());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void putIntentDocument(Intent intent, int position, ArrayList<GsonDocument.DataBean> arrDocument, int positionPage, String stringExtra) {
        long JobID = arrDocument.get(position).getJobId();
        intent.putExtra(DOCUMENTID, JobID);
        intent.putExtra(RESOURCECODEID, arrDocument.get(position).getResourceCodeId());
        intent.putExtra(KEYURLNA, urlNA);
        intent.putExtra(PAGEPOSITION, positionPage + 1);
        intent.putExtra(DOCUMENT_NUMBER, arrDocument.get(position).getTitle());
        intent.putExtra(LOOKUP_SCREEN, stringExtra);
        intent.putExtra(OVER_NETWORK_INTENT, arrDocument.get(position).isOverNetwork());
        mSeachView.startIntent(intent);

    }

    @Override
    public void putIntentLookup(Intent intent, long id, int statistic, String departmentPosition, String stringExtra) {
        intent.putExtra(DOCUMENTID, id);
        intent.putExtra(STATISTIC_TYPE, statistic);
        intent.putExtra(DEPARTMENT_POSITION, departmentPosition);
        intent.putExtra(LOOKUP_SCREEN, stringExtra);
        mSeachView.startIntent(intent);
    }


    public JSONObject addJsonDocumentType() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_DOCUMENT);
            JSONObject mJsonObjectData = new JSONObject();
            mJsonObjectData.put(ROW_PER_PAGE, LimitPager);
            mJsonObjectData.put(KEYWORDS, mTypeDocument);
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonSearchInternal(String MODULEs, String TYPEs, GsonSearchInternal.DataBean mInternal) {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            mData.put(ROW_PER_PAGE, mInternal.getRowPerPage());
            mData.put(NUM_PAGE, mInternal.getDocNumber());
            mData.put(ADVANCED, mInternal.isAdvanced());
            mData.put(KEYWORDS, mInternal.getKeywords());
            mData.put(DOC_NUMBER, mInternal.getDocNumber());
            mData.put(DOC_TYPE_ID, mInternal.getDocTypeId());
            mData.put(BRIEF_CONTENT, mInternal.getBriefContent());
            mData.put(FROM_SIGN_DATE, mInternal.getFromSignDate());
            mData.put(TO_SIGN_DATE, mInternal.getToSignDate());
            mData.put(FROM_CREATE_DATE, mInternal.getFromCreateDate());
            mData.put(TO_CREATE_DATE, mInternal.getToCreateDate());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;

    }

    private JSONObject addJsonSearchSent(String MODULEs, String TYPEs, GsonSearchSent.DataBean mSent) {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            mData.put(ROW_PER_PAGE, mSent.getRowPerPage());
            mData.put(NUM_PAGE, mSent.getNumPage());
            mData.put(ADVANCED, mSent.isAdvanced());
            mData.put(KEYWORDS, mSent.getKeywords());
            mData.put(PUBLISH_NUMBER, mSent.getPublishNumber());
            mData.put(NUMBER_OF_SYMBOL, mSent.getDocTypeId());
            mData.put(DOC_TYPE_ID, mSent.getDocTypeId());
            mData.put(BRIEF_CONTENT, mSent.getBriefContent());
            mData.put(FROM_PUBLISH_DATE, mSent.getFromPublishDate());
            mData.put(TO_PUBLISH_DATE, mSent.getToPublishDate());
            mData.put(FROM_CREATE_DATE, mSent.getFromCreateDate());
            mData.put(TO_CREATE_DATE, mSent.getToCreateDate());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonSearchComing(String MODULEs, String TYPEs, GsonSearchLookup.DataBean mLookup) {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            mData.put(ROW_PER_PAGE, mLookup.getRowPerPage());
            mData.put(NUM_PAGE, mLookup.getNumPage());
            mData.put(ADVANCED, mLookup.isAdvanced());
            mData.put(KEYWORDS, mLookup.getKeywords());
            mData.put(DOC_NUMBER_FULL, mLookup.getDocNumberFull());
            mData.put(NUMBER_OF_SYMBOL, mLookup.getNumberOfSymbol());
            mData.put(DOC_TYPE_ID, mLookup.getDocTypeId());
            mData.put(BRIEF_CONTENT, mLookup.getBriefContent());
            mData.put(FROM_PUBLISH_DATE, mLookup.getFromPublishDate());
            mData.put(TO_PUBLISH_DATE, mLookup.getToPublishDate());
            mData.put(FROM_RECEIVE_DATE, mLookup.getFromReceiveDate());
            mData.put(TO_RECEIVE_DATE, mLookup.getToReceiveDate());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public JSONObject addJonRequestListDPM() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    /*==============================
   this Asyntask get total document when you search
================================*/
    private JSONObject addJsonSearchProcess(String MODULEs, String TYPEs, GsonSearchProcess.DataBean mDataBean) {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            mData.put(LABLE_CODE, mDataBean.getLabelCode());
            mData.put(ROW_PER_PAGE, mDataBean.getRowPerPage());
            mData.put(NUM_PAGE, mDataBean.getNumPage());
            mData.put(ADVANCED, mDataBean.isAdvanced());
            mData.put(KEYWORDS, mDataBean.getKeywords());
            mData.put(ORGANIZATION_ID, mDataBean.getOrganizationId());
            mData.put(FROM_RECEIVE_DATE, mDataBean.getFromReceiveDate());
            mData.put(TO_RECEIVE_DATE, mDataBean.getToReceiveDate());
            mData.put(FROM_PROCESS_DATE, mDataBean.getFromProcessDate());
            mData.put(TO_PROCESS_DATE, mDataBean.getToProcessDate());
            mData.put(FROM_DUE_DATE, mDataBean.getFromDueDate());
            mData.put(TO_DUE_DATE, mDataBean.getToDueDate());
            mData.put(SCHEDULE_CONTENT, mDataBean.getContent());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case SEARCH_PROCESS:
                GsonDocument mGsonDocument = getGson().fromJson(s, GsonDocument.class);
                List<GsonDocument.DataBean> mArray = mGsonDocument.getData();
                for (int i = 0; i < mArray.size(); i++) {
                    arrDocument.add(mArray.get(i));
                }
                if (mSeachView.getPositionPage() > 1) {
                    adapterSearch.notifyDataSetChanged();
                } else {
                    adapterSearch = new DocumentAdapter(context, arrDocument);
                    mSeachView.setListProcess(adapterSearch);
                }
                mSeachView.turnOffSearchAdvance(false);
                mSeachView.hideEmpty(adapterSearch.getCount() != 0 ? View.GONE : View.VISIBLE);
                break;
            case SEARCH_WORK_ARISE:
                Log.d(KeyManager.TAG, s);
                GsonWorkAriseList mGsonWorkAriseList = getGson().fromJson(s, GsonWorkAriseList.class);
                List<GsonWorkAriseList.DataBean> arrWorkArise = mGsonWorkAriseList.getData();
                for (int i = 0; i < arrWorkArise.size(); i++) {
                    GsonWorkAriseList.DataBean object = arrWorkArise.get(i);
                    arrDocumentLookUp.add(new ItemsDocumentLookUp(object.getWorkAriseId(), object.getCreateDate(), object.getTitle(), object.getWorkCode(), specialValid(object.getContent()), WORK_ARISE));
                }
                if (mSeachView.getPositionPage() > 1) {
                    adapterDocumentLookup.notifyDataSetChanged();
                } else {
                    adapterDocumentLookup = new DocumentLookupAdapter(context, arrDocumentLookUp);
                    mSeachView.setListComing(adapterDocumentLookup);
                }
                mSeachView.turnOffSearchAdvance(false);
                mSeachView.hideEmpty(adapterDocumentLookup.getCount() != 0 ? View.GONE : View.VISIBLE);
                break;
            case SEARCH_COMING:
                GsonDocComing mGsonDocComing = getGson().fromJson(s, GsonDocComing.class);
                List<GsonDocComing.DataBean> arrayComing = mGsonDocComing.getData();
                for (int i = 0; i < arrayComing.size(); i++) {
                    GsonDocComing.DataBean object = arrayComing.get(i);
                    arrDocumentLookUp.add(new ItemsDocumentLookUp(object.getDocReceiptId(), object.getReceiveDate(), object.getNumberOfSymbol(), object.getDocNumberFull(), replaceStringSpecicalCharacterFromSQL(object.getBriefContent()), LOOKUP_COMING));
                }
                if (mSeachView.getPositionPage() > 1) {
                    adapterDocumentLookup.notifyDataSetChanged();
                } else {
                    adapterDocumentLookup = new DocumentLookupAdapter(context, arrDocumentLookUp);
                    mSeachView.setListComing(adapterDocumentLookup);
                }
                mSeachView.turnOffSearchAdvance(false);
                mSeachView.hideEmpty(adapterDocumentLookup.getCount() != 0 ? View.GONE : View.VISIBLE);
                break;
            case SEARCH_SENT:
                Log.d(KeyManager.TAG, s);
                GsonDocSent mGsonDocSent = getGson().fromJson(s, GsonDocSent.class);
                List<GsonDocSent.DataBean> arrSent = mGsonDocSent.getData();
                for (int i = 0; i < arrSent.size(); i++) {
                    GsonDocSent.DataBean object = arrSent.get(i);
                    arrDocumentLookUp.add(new ItemsDocumentLookUp(object.getDocSendId(), object.getPublishDate(), null, object.getPublishNumber(), specialValid(object.getBriefContent()), LOOKUP_SENT));
                }
                if (mSeachView.getPositionPage() > 1) {
                    adapterDocumentLookup.notifyDataSetChanged();
                } else {
                    adapterDocumentLookup = new DocumentLookupAdapter(context, arrDocumentLookUp);
                    mSeachView.setListComing(adapterDocumentLookup);
                }
                mSeachView.turnOffSearchAdvance(false);
                mSeachView.hideEmpty(adapterDocumentLookup.getCount() != 0 ? View.GONE : View.VISIBLE);
                break;
            case SEARCH_INTERNAL:
                Log.d(KeyManager.TAG, s);
                GsonDocInternal mInternal = getGson().fromJson(s, GsonDocInternal.class);
                List<GsonDocInternal.DataBean> arrInternal = mInternal.getData();
                for (int i = 0; i < arrInternal.size(); i++) {
                    GsonDocInternal.DataBean object = arrInternal.get(i);
                    arrDocumentLookUp.add(new ItemsDocumentLookUp(object.getDocLocalId(), object.getCreateDateDoc(), null, object.getDocNumber(), specialValid(object.getBriefContent()), LOOKUP_INTERNAL));
                }
                if (mSeachView.getPositionPage() > 1) {
                    adapterDocumentLookup.notifyDataSetChanged();
                } else {
                    adapterDocumentLookup = new DocumentLookupAdapter(context, arrDocumentLookUp);
                    mSeachView.setListComing(adapterDocumentLookup);
                }
                mSeachView.turnOffSearchAdvance(false);
                mSeachView.hideEmpty(adapterDocumentLookup.getCount() != 0 ? View.GONE : View.VISIBLE);
                break;
            case SEARCH_LIST_DEPARTMENT:
                arrListDepartmentSearch = new ArrayList<>();
                arrNameDepartment = new ArrayList<String>();
                arrListDepartmentSearch.add(new ReceivePerson(context.getString(R.string.chon), nULL_STRING));
                try {
                    JSONObject mJsonObjectFullData = new JSONObject(s);
                    JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String DepartmentName = mJsonObject.getString(ORGANIZATION_NAME);
                        String DetpartmentID = mJsonObject.getString(ORGANIZATION_ID);
                        arrListDepartmentSearch.add(new ReceivePerson(DepartmentName, DetpartmentID));
                    }
                    for (int i = 0; i < arrListDepartmentSearch.size(); i++) {
                        String Name = arrListDepartmentSearch.get(i).getReceivePersonID();
                        arrNameDepartment.add(Name);

                    }
                    mSeachView.getSrrListDepartmentSearch(arrListDepartmentSearch);
                    DepartmentAdapter adapter = new DepartmentAdapter(context, arrNameDepartment, android.R.layout.simple_spinner_dropdown_item);
                    mSeachView.setAdatperDepartmentName(adapter, arrNameDepartment);
                } catch (JSONException e) {
                    mSeachView.showErrorToast();
                    e.printStackTrace();
                }
                break;
            case SEARCH_DOCUMENT_TYPE:
                arrayTypeDocumentName = new ArrayList<String>();
                arrayTypeDocumentID = new ArrayList<Long>();
                try {
                    JSONObject mJsonObject = new JSONObject(s);
                    JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        JSONObject mJsonObjectData = mJsonArray.getJSONObject(i);
                        String TypeDocumentName = mJsonObjectData.getString(NAME);
                        long TypeDocumentId = mJsonObjectData.getLong(ID);
                        arrayTypeDocumentName.add(TypeDocumentName);
                        arrayTypeDocumentID.add(TypeDocumentId);
                    }
                    mSeachView.setArrTypeDocumentID(arrayTypeDocumentID);
                    ArrayAdapter adapter = new ArrayAdapter(context, R.layout.spinner_item_multiline, R.id.item, arrayTypeDocumentName);
                    switch (Screen) {
                        case SEARCH_COMING:
                            mSeachView.setAdapterEdtComing(adapter);
                            break;
                        case SEARCH_SENT:
                            mSeachView.setAdapterEdtSent(adapter);
                            break;
                        case SEARCH_INTERNAL:
                            mSeachView.setAdapterEdtInternal(adapter);
                            break;
                        default:
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        mSeachView.closeProgress();
    }


    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);

    }

    @Override
    public void onVolleyError(String s) {
        mSeachView.closeProgress();
        mSeachView.ToastError(s);
    }


}
