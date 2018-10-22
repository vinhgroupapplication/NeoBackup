package com.newsaigonsoft.neoegov.Fragment.Login;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LINK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PASSWORD_USER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.USERNAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/11/2017.
 */

public class LoginFmLogic extends SumManager implements LoginFmImp {
    LoginFmView mLoginFmView;
    Context context;
    private static final String HTTPS = "Https://";
    private static final String HTTP = "Http://";


    public LoginFmLogic(LoginFmView mLoginFmView, Context context) {
        this.mLoginFmView = mLoginFmView;
        this.context = context;
    }

    @Override
    public void LoginOrShowError(EditText edtUser, EditText edtPass, Spinner spnBeforLocal, EditText edtLink) {
        setDefaults(USERNAME, edtUser.getText().toString(), context);
        setDefaults(PASSWORD_USER, edtPass.getText().toString(), context);
        setDefaults(LINK, spnBeforLocal.getSelectedItem().toString() + "://" + edtLink.getText().toString(), context);
        // check error null for information
        if (!isNetworkAvailable(context)) {
            showDialogUpdateError(context.getString(R.string.no_connection), context);
        } else {
            if (edtLink.getText().toString().length() != 0 && edtUser.getText().toString().length() != 0 && edtPass.getText().toString().length() != 0) {
                mLoginFmView.disibleFeilAfterTap();
                mLoginFmView.eventFmLogin(spnBeforLocal.getSelectedItem().toString() + "://" + edtLink.getText().toString(), edtUser.getText().toString(), edtPass.getText().toString());
            } else {
                mLoginFmView.showErrorMsg( context.getString(R.string.confirm_login_error));
                if (edtUser.getText().toString().length() != 0 && edtPass.getText().toString().length() != 0) {
                    mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                } else {
                    if (edtLink.getText().toString().length() != 0 && edtPass.getText().toString().length() != 0) {
                        mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                    } else {
                        if (edtLink.getText().toString().length() != 0 && edtUser.getText().toString().length() != 0) {
                            mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                        } else {
                            if (edtLink.getText().toString().length() != 0 && edtUser.getText().toString().length() == 0 && edtPass.getText().toString().length() == 0) {
                                mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                            } else {
                                if (edtLink.getText().toString().length() == 0 && edtUser.getText().toString().length() != 0 && edtPass.getText().toString().length() == 0) {
                                    mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                                } else {
                                    if (edtLink.getText().toString().length() == 0 && edtUser.getText().toString().length() == 0 && edtPass.getText().toString().length() != 0) {
                                        mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                                    } else {
                                        mLoginFmView.showErrorMsg(context.getString(R.string.confirm_login_error));
                                    }
                                }
                            }

                        }

                    }
                }

            }
        }

    }

    @Override
    public void setSpinnerBeforLocal(String[] arrBeforLocal) {
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, arrBeforLocal);
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.spinner_item_login, arrBeforLocal);
        mLoginFmView.setAdapterBeforeLocal(adapter);
    }

    @Override
    public void setLinkTitle(String Link) {
        if (Link != null) {
            if (Link.contains(HTTPS)) {
                mLoginFmView.setHttpSpinner(Link.replace(HTTPS, nULL_STRING));
            } else {
                if (Link.contains(HTTP)) {
                    mLoginFmView.setHttpSpinner(Link.replace(HTTP, nULL_STRING));
                }
            }
        }
    }


}
