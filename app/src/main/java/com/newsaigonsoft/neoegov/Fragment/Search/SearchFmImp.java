package com.newsaigonsoft.neoegov.Fragment.Search;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface SearchFmImp {
    void setSearchView();

    void requestJsonDocumentType(String TypeDocument);

    void SearchDocument(String strSearchTop, String strFromDay, String strToDay, String strSearchContent, String ReceiveRoomID);

    void SearchDocumentComing(String strSearchTop, String strComingNumberReceive, String strComingNumberOfSymbol, String strComingDayFromProduct, String strComingDayToProduct, String strComingDayFromReceive, String strComingDaytoReceive, String strComingTypeDocument, String strComingAbstract, long TypeDocumentId);

    void SearchSentDocument(String strSearchTop, String strSentPublishNumber, String strSentDocTypeId, String strSentBriefContent, String strSentFromPublishDate, String strSentToPublishDate, String strSentFromCreateDate, String strSentToCreateDate, long TypeDocumentId);

    void SearchInternalDocument(String strSearchTop, String strInternalDocNumber, String strInternalDocTypeId, String strInternalBriefContent, String strInternalFromSignDate, String strInternalToSignDate, String strInternalFromCreateDate, String strInternalToCreateDate, long typeDocumentId);

    void eventGetDepartmentSearchList();
}
