package com.newsaigonsoft.neoegov.Fragment.Search;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SearchRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.KEYWORDS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ROW_PER_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DEPARTMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FIRST_ITEM_SEARCH_SPINNER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_DOCUMENT_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_LIST_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/23/2017.
 */

public class SearchFmLogic extends SumManager implements SearchFmImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    SearchFmView mSearchFmView;
    Context context;
    String screenNameInSide;
    ArrayList<String> arrayTypeDocumentName;
    ArrayList<Long> arrayTypeDocumentID;
    String mTypeDocument;
    ArrayList<ReceivePerson> arrListDepartmentSearch;

    public SearchFmLogic(SearchFmView mSearchFmView, Context context, String screenNameInSide) {
        this.mSearchFmView = mSearchFmView;
        this.context = context;
        this.screenNameInSide = screenNameInSide;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void setSearchView() {
        switch (screenNameInSide) {
            case SEARCH_COMING:
                mSearchFmView.enableSearchComing();
                break;
            case SEARCH_SENT:
                mSearchFmView.enableSearchSent();
                break;
            case SEARCH_INTERNAL:
                mSearchFmView.enableSearchInternal();
                break;
            case SEARCH_PROCESS:
                // department list select linstenter
                mSearchFmView.enableSearchProcess();
                break;
            default:
                break;
        }
    }

    @Override
    public void requestJsonDocumentType(String TypeDocument) {
        mTypeDocument = TypeDocument;
        new VolleyTask(context, SEARCH_DOCUMENT_TYPE, addJsonDocumentType(), this);
//        new ReadJsonDocumentType().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_DOCUMENT_TYPE, addJsonDocumentType().toString(),getLink() + URL_CENTER_6_1 ));

    }



    @Override
    public void SearchDocument(String strSearchTop, String strFromDay, String strToDay, String strSearchContent, String ReceiveRoomID) {
        SearchRow mSearchRow = new SearchRow();
        long DayFrom = 0;
        long DayTo = 0;
        if (strSearchTop.equals(nULL_STRING) && ReceiveRoomID.equals(FIRST_ITEM_SEARCH_SPINNER) && strFromDay.equals(nULL_STRING) && strToDay.equals(nULL_STRING) && strSearchContent.equals(nULL_STRING)) {
        } else {
            if (!strSearchTop.equals(nULL_STRING) && ReceiveRoomID.equals(FIRST_ITEM_SEARCH_SPINNER) && strFromDay.equals(nULL_STRING) && strToDay.equals(nULL_STRING) && strSearchContent.equals(nULL_STRING)) {
                mSearchRow.setAdvanced(false);
                mSearchRow.setKeywords(strSearchTop);
                mSearchRow.setOrganizationId(0);
                mSearchRow.setFromReceiveDate(0);
                mSearchRow.setToReceiveDate(0);
                mSearchRow.setContent(nULL_STRING);
            } else {
                if (!strFromDay.equals(nULL_STRING)) {
                    DayFrom = getMilisecondDay(strFromDay);
                }
                if (!strToDay.equals(nULL_STRING)) {
                    DayTo = getMilisecondDay(strToDay);
                }
                mSearchRow.setAdvanced(true);
                mSearchRow.setKeywords(strSearchTop);
                if (ReceiveRoomID.equals("")) {
                    mSearchRow.setOrganizationId(0);
                } else {
                    mSearchRow.setOrganizationId(Long.parseLong(ReceiveRoomID));
                }
                mSearchRow.setFromReceiveDate(DayFrom);
                mSearchRow.setToReceiveDate(DayTo);
                mSearchRow.setContent(strSearchContent);
            }
        }
        if (DayFrom > DayTo) {
            if (DayTo == 0) {
                mSearchFmView.hideNotifySearchError();
                mSearchFmView.startEvent(mSearchRow);
            } else {
                mSearchFmView.showNotifySearchError();
                mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
                mSearchFmView.closeProgress();
            }
        } else {
            mSearchFmView.hideNotifySearchError();
            mSearchFmView.startEvent(mSearchRow);
        }
    }

    @Override
    public void SearchDocumentComing(String strSearchTop, String strComingNumberReceive, String strComingNumberOfSymbol, String strComingDayFromProduct, String strComingDayToProduct, String strComingDayFromReceive, String strComingDaytoReceive, String strComingTypeDocument, String strComingAbstract, long TypeDocumentId) {
        SearchRow mSearchRow = new SearchRow();
        long fromPublishDate = 0;
        long toPublishDate = 0;
        long fromReceiveDate = 0;
        long toReceiveDate = 0;
        if (strSearchTop.equals(nULL_STRING) && strComingNumberReceive.equals(nULL_STRING) && strComingNumberOfSymbol.equals(nULL_STRING) && strComingDayToProduct.equals(nULL_STRING) && strComingDayFromProduct.equals(nULL_STRING) && strComingDayFromReceive.equals(nULL_STRING) && strComingDaytoReceive.equals(nULL_STRING) && strComingTypeDocument.equals(nULL_STRING) && strComingAbstract.equals(nULL_STRING)) {
        } else {
            if (!strSearchTop.equals(nULL_STRING) && strComingNumberReceive.equals(nULL_STRING) && strComingNumberOfSymbol.equals(nULL_STRING) && strComingDayToProduct.equals(nULL_STRING) && strComingDayFromProduct.equals(nULL_STRING) && strComingDayFromReceive.equals(nULL_STRING) && strComingDaytoReceive.equals(nULL_STRING) && strComingTypeDocument.equals(nULL_STRING) && strComingAbstract.equals(nULL_STRING)) {
                mSearchRow.setAdvanced(false);
                mSearchRow.setKeywords(strSearchTop);
            } else {
                if (!strComingDayFromProduct.equals(nULL_STRING)) {
                    fromPublishDate = getMilisecondDay(strComingDayFromProduct);
                }
                if (!strComingDayToProduct.equals(nULL_STRING)) {
                    toPublishDate = getMilisecondDay(strComingDayToProduct);
                }
                if (!strComingDayFromReceive.equals(nULL_STRING)) {
                    fromReceiveDate = getMilisecondDay(strComingDayFromReceive);
                }
                if (!strComingDaytoReceive.equals(nULL_STRING)) {
                    toReceiveDate = getMilisecondDay(strComingDaytoReceive);
                }
                mSearchRow.setAdvanced(true);
                mSearchRow.setDocNumberFull(strComingNumberReceive);
                mSearchRow.setNumberOfSymbol(strComingNumberOfSymbol);
                mSearchRow.setDocTypeId(TypeDocumentId);
                mSearchRow.setBriefContent(strComingAbstract);
                mSearchRow.setFromPublishDate(fromPublishDate);
                mSearchRow.setToPublishDate(toPublishDate);
                mSearchRow.setFromReceiveDate(fromReceiveDate);
                mSearchRow.setToReceiveDate(toReceiveDate);
            }
        }
        if (fromPublishDate > toPublishDate) {
            if (toPublishDate == 0){
                mSearchFmView.hideNotifySearchError();
                mSearchFmView.startEvent(mSearchRow);
            }else {
                mSearchFmView.showNotifySearchError();
                mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
                mSearchFmView.closeProgress();
            }
        } else {
            if (fromReceiveDate > toReceiveDate) {
                if (toReceiveDate == 0 ){
                    mSearchFmView.hideNotifySearchError();
                    mSearchFmView.startEvent(mSearchRow);
                }else {
                    mSearchFmView.showNotifySearchError();
                    mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
                    mSearchFmView.closeProgress();
                }
            } else {
                mSearchFmView.hideNotifySearchError();
                mSearchFmView.startEvent(mSearchRow);
            }
        }
    }

    @Override
    public void SearchSentDocument(String strSearchTop, String strSentPublishNumber, String strSentDocTypeId, String strSentBriefContent, String strSentFromPublishDate, String strSentToPublishDate, String strSentFromCreateDate, String strSentToCreateDate, long TypeDocumentId) {
        SearchRow mSearchRow = new SearchRow();
        long fromPublishDate = 0;
        long toPublishDate = 0;
        long fromCreateDate = 0;
        long toCreateDate = 0;
        if (strSearchTop.equals(nULL_STRING) && strSentPublishNumber.equals(nULL_STRING) && strSentDocTypeId.equals(nULL_STRING) && strSentBriefContent.equals(nULL_STRING) && strSentFromPublishDate.equals(nULL_STRING) && strSentToPublishDate.equals(nULL_STRING) && strSentFromCreateDate.equals(nULL_STRING) && strSentToCreateDate.equals(nULL_STRING)) {
        } else {
            if (!strSearchTop.equals(nULL_STRING) && strSentPublishNumber.equals(nULL_STRING) && strSentDocTypeId.equals(nULL_STRING) && strSentBriefContent.equals(nULL_STRING) && strSentFromPublishDate.equals(nULL_STRING) && strSentToPublishDate.equals(nULL_STRING) && strSentFromCreateDate.equals(nULL_STRING) && strSentToCreateDate.equals(nULL_STRING)) {
                mSearchRow.setAdvanced(false);
                mSearchRow.setKeywords(strSearchTop);
            } else {
                if (!strSentFromPublishDate.equals(nULL_STRING)) {
                    fromPublishDate = getMilisecondDay(strSentFromPublishDate);
                }
                if (!strSentToPublishDate.equals(nULL_STRING)) {
                    toPublishDate = getMilisecondDay(strSentToPublishDate);
                }
                if (!strSentFromCreateDate.equals(nULL_STRING)) {
                    fromCreateDate = getMilisecondDay(strSentFromCreateDate);
                }
                if (!strSentToCreateDate.equals(nULL_STRING)) {
                    toCreateDate = getMilisecondDay(strSentToCreateDate);
                }
                mSearchRow.setAdvanced(true);
                mSearchRow.setPublishNumber(strSentPublishNumber);
                mSearchRow.setDocTypeId(TypeDocumentId);
                mSearchRow.setBriefContent(strSentBriefContent);
                mSearchRow.setFromPublishDate(fromPublishDate);
                mSearchRow.setToPublishDate(toPublishDate);
                mSearchRow.setFromCreateDate(fromCreateDate);
                mSearchRow.setToCreateDate(toCreateDate);
            }
        }
        if (fromPublishDate > toPublishDate) {
            mSearchFmView.showNotifySearchError();
            mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
            mSearchFmView.closeProgress();
        } else {
            if (fromCreateDate > toCreateDate) {
                if (toCreateDate==0){
                    mSearchFmView.hideNotifySearchError();
                    mSearchFmView.startEvent(mSearchRow);
                }else {
                    mSearchFmView.showNotifySearchError();
                    mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
                    mSearchFmView.closeProgress();
                }
            } else {
                mSearchFmView.hideNotifySearchError();
                mSearchFmView.startEvent(mSearchRow);
            }
        }
    }

    @Override
    public void SearchInternalDocument(String strSearchTop, String strInternalDocNumber, String strInternalDocTypeId, String strInternalBriefContent, String strInternalFromSignDate, String strInternalToSignDate, String strInternalFromCreateDate, String strInternalToCreateDate, long typeDocumentId) {
        long fromSignDate = 0;
        long toSignDate = 0;
        long fromCreateDate = 0;
        long toCreateDate = 0;
        SearchRow mSearchRow = new SearchRow();
        if (strSearchTop.equals(nULL_STRING) && strInternalDocNumber.equals(nULL_STRING) && strInternalDocTypeId.equals(nULL_STRING) && strInternalBriefContent.equals(nULL_STRING) && strInternalFromSignDate.equals(nULL_STRING) && strInternalFromSignDate.equals(nULL_STRING) && strInternalToSignDate.equals(nULL_STRING) && strInternalFromCreateDate.equals(nULL_STRING) && strInternalToCreateDate.equals(nULL_STRING)) {
        } else {
            if (!strSearchTop.equals(nULL_STRING) && strInternalDocNumber.equals(nULL_STRING) && strInternalDocTypeId.equals(nULL_STRING) && strInternalBriefContent.equals(nULL_STRING) && strInternalFromSignDate.equals(nULL_STRING) && strInternalFromSignDate.equals(nULL_STRING) && strInternalToSignDate.equals(nULL_STRING) && strInternalFromCreateDate.equals(nULL_STRING) && strInternalToCreateDate.equals(nULL_STRING)) {
                mSearchRow.setAdvanced(false);
                mSearchRow.setKeywords(strSearchTop);

            } else {
                if (!strInternalFromSignDate.equals(nULL_STRING)) {
                    fromSignDate = getMilisecondDay(strInternalFromSignDate);
                }
                if (!strInternalToSignDate.equals(nULL_STRING)) {
                    toSignDate = getMilisecondDay(strInternalToSignDate);
                }
                if (!strInternalFromCreateDate.equals(nULL_STRING)) {
                    fromCreateDate = getMilisecondDay(strInternalFromCreateDate);
                }
                if (!strInternalToCreateDate.equals(nULL_STRING)) {
                    toCreateDate = getMilisecondDay(strInternalToCreateDate);
                }
                mSearchRow.setAdvanced(true);
                mSearchRow.setDocNumber(strInternalDocNumber);
                mSearchRow.setDocTypeId(typeDocumentId);
                mSearchRow.setBriefContent(strInternalBriefContent);
                mSearchRow.setFromSignDate(fromSignDate);
                mSearchRow.setToSignDate(toSignDate);
                mSearchRow.setFromCreateDate(fromCreateDate);
                mSearchRow.setToCreateDate(toCreateDate);
            }
        }
        if (fromSignDate > toSignDate) {
            mSearchFmView.showNotifySearchError();
            mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
            mSearchFmView.closeProgress();
        } else {
            if (fromCreateDate > toCreateDate) {
                mSearchFmView.showNotifySearchError();
                mSearchFmView.setMsgNotifySearchError("Thời gian tìm kiếm không hợp lệ.");
                mSearchFmView.closeProgress();
            } else {
                mSearchFmView.hideNotifySearchError();
                mSearchFmView.startEvent(mSearchRow);
            }
        }
    }

    /*==========================
     when search, user must choose a department user wanna search
 ============================*/
    @Override
    public void eventGetDepartmentSearchList() {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new ReadJsonListDepartmentSearch().execute(getLink() + URL_CENTER_6_1);
//            }
//        });
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_LIST_DEPARTMENT, addJonRequestListDPM().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(context, SEARCH_LIST_DEPARTMENT, addJonRequestListDPM(), this);
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case SEARCH_LIST_DEPARTMENT:
                arrListDepartmentSearch = new ArrayList<>();
                ArrayList<String> arrNameDepartment = new ArrayList<String>();
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
                    mSearchFmView.getSrrListDepartmentSearch(arrListDepartmentSearch);
                    DepartmentAdapter adapter = new DepartmentAdapter(context, arrNameDepartment, android.R.layout.simple_spinner_dropdown_item);
                    mSearchFmView.setAdatperDepartmentName(adapter, arrNameDepartment);
                } catch (JSONException e) {
                    mSearchFmView.showToast();
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
                    mSearchFmView.setArrTypeDocumentID(arrayTypeDocumentID);
                    ArrayAdapter adapter = new ArrayAdapter(context, R.layout.spinner_item_multiline, R.id.item, arrayTypeDocumentName);
                    switch (screenNameInSide) {
                        case SEARCH_COMING:
                            mSearchFmView.setAdapterEdtComing(adapter);
                            break;
                        case SEARCH_SENT:
                            mSearchFmView.setAdapterEdtSent(adapter);
                            break;
                        case SEARCH_INTERNAL:
                            mSearchFmView.setAdapterEdtInternal(adapter);
                            break;
                        default:
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
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

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mSearchFmView.closeProgress();
        mSearchFmView.ToastError(s);

    }

//    /*===========================================
//    get list department search screen
//=============================================*/
//
//    class ReadJsonListDepartmentSearch extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            arrListDepartmentSearch = new ArrayList<>();
//            ArrayList<String> arrNameDepartment = new ArrayList<String>();
//            arrNameDepartment.add(context.getString(R.string.chon));
//            arrListDepartmentSearch.add(new ReceivePerson(nULL_STRING, nULL_STRING));
//            try {
//                JSONObject mJsonObjectFullData = new JSONObject(s);
//                JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mJsonObject = mJsonArray.getJSONObject(i);
//                    String DepartmentName = mJsonObject.getString(ORGANIZATION_NAME);
//                    String DetpartmentID = mJsonObject.getString(ORGANIZATION_ID);
//                    arrListDepartmentSearch.add(new ReceivePerson(DepartmentName, DetpartmentID));
//                }
//                for (int i = 1; i < arrListDepartmentSearch.size(); i++) {
//                    arrNameDepartment.add(arrListDepartmentSearch.get(i).getReceivePersonID());
//                }
//                mSearchFmView.getSrrListDepartmentSearch(arrListDepartmentSearch);
//                DepartmentAdapter adapter = new DepartmentAdapter(context, arrNameDepartment, android.R.layout.simple_spinner_item);
//                mSearchFmView.setAdatperDepartmentName(adapter, arrNameDepartment);
//            } catch (JSONException e) {
//                mSearchFmView.showToast();
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
    //    class ReadJsonDocumentType extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, TYPE_DOCUMENT);
//                JSONObject mJsonObjectData = new JSONObject();
//                mJsonObjectData.put(ROW_PER_PAGE, LimitPager);
//                mJsonObjectData.put(KEYWORDS, mTypeDocument);
//                mJsonObject.put(DATA, mJsonObjectData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
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
//                mSearchFmView.setArrTypeDocumentID(arrayTypeDocumentID);
//                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, arrayTypeDocumentName);
//                switch (screenNameInSide) {
//                    case SEARCH_COMING:
//                        mSearchFmView.setAdapterEdtComing(adapter);
//                        break;
//                    case SEARCH_SENT:
//                        mSearchFmView.setAdapterEdtSent(adapter);
//                        break;
//                    case SEARCH_INTERNAL:
//                        mSearchFmView.setAdapterEdtInternal(adapter);
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
}
