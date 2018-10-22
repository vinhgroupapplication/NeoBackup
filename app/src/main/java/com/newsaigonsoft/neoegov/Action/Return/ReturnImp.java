package com.newsaigonsoft.neoegov.Action.Return;

import org.json.JSONArray;

/**
 * Created by Vinh on 12/19/2017.
 */

public interface ReturnImp {
    void RequestReturn(JSONArray mJsonArrayAttach);
    void getListPerson(long DocumentID);
}
