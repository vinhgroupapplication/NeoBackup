package com.newsaigonsoft.neoegov.Fragment.Document;

import android.content.Context;
import android.content.Intent;
import android.text.Html;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocument;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocumentLookUp;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONFIRM_COMPLETED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DEPARTMENT_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.KEYURLNA;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OVER_NETWORK_INTENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PAGEPOSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMIND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/24/2017.
 */

public class DocumentFmLogic extends SumManager implements DocumentFmImp {

    DocumentFmView mDocumentFmView;
    Context context;
    String screenNameInSide;

    public DocumentFmLogic(DocumentFmView mDocumentFmView, Context context, String screenNameInSide) {
        this.mDocumentFmView = mDocumentFmView;
        this.context = context;
        this.screenNameInSide = screenNameInSide;
        getInforAccountFromShareReferenced(context);
    }


    @Override
    public void startEvent(String TypeHomeListDocument, int positionPage) {
        switch (screenNameInSide) {
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH VỤ VIỆC");
                break;
            case HOT_PROCESS:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH PHẢN ÁNH");
                break;
            case HOT_PROCESS_DEMURRAGE:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH PHẢN ÁNH");
                break;
            case HOT_PROCESS_ONDUE:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH PHẢN ÁNH");
                break;
            case SEARCH_FRAGMENT_TAG:
                mDocumentFmView.eventSearch(positionPage, false);
                break;
            case DOCUMENT_FRAGMENT_TAG:
                mDocumentFmView.getListSQLDocument(positionPage - 1, urlNA);
                break;
            case LOOKUP_COMING:
                mDocumentFmView.eventGetListSQL("TRA CỨU VĂN BẢN ĐẾN");
                break;
            case LOOKUP_SENT:
                mDocumentFmView.eventGetListSQL("TRA CỨU VĂN BẢN ĐI");
                break;
            case LOOKUP_INTERNAL:
                mDocumentFmView.eventGetListSQL("TRA CỨU VĂN BẢN NỘI BỘ");
                break;
            case WORK_ARISE:
                mDocumentFmView.eventGetListSQL("VỤ VIỆC");
                break;
            case DOC_NOT_PROCESS_TYPE:
            case DOC_NOT_SEEN_TYPE:
            case DOC_DEMURRAGE_TYPE:
                switch (TypeHomeListDocument) {
                    case TYPE_HOME_LIST_DOCUMENT_COMING:
                        mDocumentFmView.eventChangeToLookup("DANH SÁCH VĂN BẢN ĐẾN");
                        break;
                    case TYPE_HOME_LIST_DOCUMENT_SENT:
                        mDocumentFmView.eventChangeToLookup("DANH SÁCH VĂN BẢN ĐI");
                        break;
                }
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH VĂN BẢN ĐẾN");
                break;
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH NHIỆM VỤ");
                break;
            case TAP_DPM_ONTIME:
            case TAP_DPM_DELAYS:
            case TAP_DPM_INDUE:
            case TAP_DPM_OUT_OF_DATE:
                mDocumentFmView.eventChangeToLookup("DANH SÁCH NHIỆM VỤ");
                break;
            default:
                break;
        }
    }

    @Override
    public void startSrollList(int positionPage, ArrayList<ItemsDocumentLookUp> arrDocumentLookUp, int numberOfPage) {
        switch (screenNameInSide) {
            case SEARCH_FRAGMENT_TAG:
                mDocumentFmView.eventSearch(positionPage, false);
                break;
            case DOCUMENT_FRAGMENT_TAG:
                mDocumentFmView.eventChangePageDocument();
                break;
            case LOOKUP_COMING:
            case LOOKUP_SENT:
            case LOOKUP_INTERNAL:
            case WORK_ARISE:
                mDocumentFmView.eventChangePageLookupComing();
                break;
            case HOT_PROCESS:
            case HOT_PROCESS_DEMURRAGE:
            case HOT_PROCESS_ONDUE:
            case DOC_NOT_PROCESS_TYPE:
            case DOC_NOT_SEEN_TYPE:
            case DOC_DEMURRAGE_TYPE:
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
            case LIST_DOCUMENT_DEPARTMENT:
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
                // department statistical
            case TAP_DPM_DELAYS:
            case TAP_DPM_INDUE:
            case TAP_DPM_ONTIME:
            case TAP_DPM_OUT_OF_DATE:
                mDocumentFmView.changePageLookup();
                break;
            default:
                break;
        }
    }

//    getMsgAfterTrainfer(Resuilt, InputComBack, DocumentNumber, context);
//    @Override
//    public void setMsgAfterTrainfer(String creencomeback, String Resuilt, String InputComBack, String DocumentNumber) {
//        if (creencomeback != null && !creencomeback.equals(nULL_STRING)) {
//            if (Resuilt != null) {
//                if (Resuilt.equals(FALSE)) {
//                    switch (InputComBack) {
//                        case CHANGE_HANDLE:
//                            mDocumentFmView.visibleNotifyForward(true, "Yêu cầu thay đổi thất bại.");
//                            break;
//                        case CANCEL_SCREEN:
//                            mDocumentFmView.visibleNotifyForward(true, "Rút lại văn bản không thành công.");
//                            break;
//                        case RETURN_SCREEN:
//                            mDocumentFmView.visibleNotifyForward(true, "Yêu cầu trả lại thất bại.");
//                            break;
//                        case FEED_BACK:
//                            mDocumentFmView.visibleNotifyForward(true, "Góp ý thất bại.");
//                            break;
//                        case EXTEND_SCREEN:
//                            mDocumentFmView.visibleNotifyForward(true, "Gia hạn thất bại.");
//                            break;
//                        default:
//                            mDocumentFmView.visibleNotifyForward(true, context.getString(R.string.chuyen_that_bai) + " " + Html.fromHtml(DocumentNumber) + ".");
//                            break;
//                    }
//                    mDocumentFmView.setRedMsgColor();
//                } else {
//                    switch (InputComBack) {
//                        case CHANGE_HANDLE:
//                            mDocumentFmView.visibleNotifyForward(true, "Yêu cầu thay đổi thành công.");
//                            break;
//                        case CANCEL_SCREEN:
//                            mDocumentFmView.visibleNotifyForward(true, "Yêu cầu rút lại thành công.");
//                            break;
//                        case RETURN_SCREEN:
//                            mDocumentFmView.visibleNotifyForward(true, "Yêu cầu trả lại thành công.");
//                            break;
//                        case FEED_BACK:
//                            mDocumentFmView.visibleNotifyForward(true, "Góp ý thành công.");
//                            break;
//                        case EXTEND_SCREEN:
//                            mDocumentFmView.visibleNotifyForward(true, "Gia hạn thành công.");
//                            break;
//                        default:
//                            mDocumentFmView.visibleNotifyForward(true, context.getString(R.string.chuyen_thanh_cong) + " " + Html.fromHtml(DocumentNumber) + ".");
//                            break;
//                    }
//                    mDocumentFmView.setBlueMsg();
//                }
//            } else {
//                mDocumentFmView.visibleNotifyForward(true, context.getString(R.string.da_luu_vao_hop_thu_di) + " " + Html.fromHtml(DocumentNumber) + " sẽ lưu vào hàng đợi để gửi lại.");
//                mDocumentFmView.setRedMsgColor();
//            }
//            mDocumentFmView.setNullCreenComeBack();
//        }
//    }

    //getMsgAfterTrainfer(Resuilt, InputComBack, DocumentNumber, context);
    @Override
    public void setMsgAfterTrainfer(String creencomeback, String Resuilt, String InputComBack, String DocumentNumber, String inputName) {
        if (creencomeback != null && !creencomeback.equals(nULL_STRING)) {
            if (Resuilt != null) {
                if (Resuilt.equals(FALSE)) {
                    mDocumentFmView.setRedMsgColor();
                } else {
                    mDocumentFmView.setBlueMsg();
                }
                mDocumentFmView.visibleNotifyForward(true, getMsgAfterTrainfer(Resuilt, InputComBack, DocumentNumber, context, inputName));
            } else {
                if (DocumentNumber != null) {
                    mDocumentFmView.visibleNotifyForward(true, inputName + " " + context.getString(R.string.da_luu_vao_hop_thu_di) + " " + Html.fromHtml(DocumentNumber) + " sẽ lưu vào hàng đợi để gửi lại.");
                    mDocumentFmView.setRedMsgColor();
                }
            }
            mDocumentFmView.getIntentFromAcitivity().removeExtra(FORWARD_RESUILD);
            mDocumentFmView.setNullCreenComeBack();
        }
    }


    @Override
    public void setMsgAferTask(String creencomeback, String Resuilt, String InputComBack) {
        if (creencomeback != null && !creencomeback.equals(nULL_STRING)) {
            if (Resuilt != null) {
                if (Resuilt.equals(FALSE)) {
                    switch (InputComBack) {
                        case CONFIRM_COMPLETED:
                            mDocumentFmView.visibleNotifyForward(true, "Xác nhận thất bại.");
                            break;
                        case REMIND:
                            mDocumentFmView.visibleNotifyForward(true, "Nhắc nhở nhiệm vụ thất bại.");
                            break;
                    }
                    mDocumentFmView.setRedMsgColor();
                } else {
                    switch (InputComBack) {
                        case CONFIRM_COMPLETED:
                            mDocumentFmView.visibleNotifyForward(true, "Xác nhận thành công.");
                            break;
                        case REMIND:
                            mDocumentFmView.visibleNotifyForward(true, "Nhắc nhở nhiệm vụ thành công.");
                            break;
                    }
                    mDocumentFmView.setBlueMsg();
                }
                mDocumentFmView.getIntentFromAcitivity().removeExtra(FORWARD_RESUILD);
            }
            mDocumentFmView.setNullCreenComeBack();
        }
    }

    @Override
    public void putIntentDocument(Intent intent, int position, ArrayList<GsonDocument.DataBean> arrDocument, int positionPage, String iScreen) {
        long JobID = arrDocument.get(position).getJobId();
        intent.putExtra(DOCUMENTID, JobID);
        intent.putExtra(RESOURCECODEID, arrDocument.get(position).getResourceCodeId());
        intent.putExtra(KEYURLNA, urlNA);
        intent.putExtra(PAGEPOSITION, positionPage + 1);
        intent.putExtra(DOCUMENT_NUMBER, arrDocument.get(position).getTitle());
        intent.putExtra(LOOKUP_SCREEN, iScreen);
        intent.putExtra(OVER_NETWORK_INTENT, arrDocument.get(position).isOverNetwork());
        mDocumentFmView.startMyIntent(intent);
    }

    @Override
    public void putIntentLookup(Intent intent, long id, int statistic, String departmentPosition, String iScreen) {
        intent.putExtra(DOCUMENTID, id);
        intent.putExtra(STATISTIC_TYPE, statistic);
        intent.putExtra(DEPARTMENT_POSITION, departmentPosition);
        intent.putExtra(LOOKUP_SCREEN, iScreen);
        mDocumentFmView.startMyIntent(intent);
    }
}
