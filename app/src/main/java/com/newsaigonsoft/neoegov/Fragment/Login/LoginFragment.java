package com.newsaigonsoft.neoegov.Fragment.Login;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.LoginActivity.MainActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.SumManager;
import com.newsaigonsoft.neoegov.SupportActivity.SupportActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INSERT_INTO;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOCAL_HOST_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOCAL_HOST_TABLE_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.POSITION_SPINNER_LOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SELECT_FROM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by VinhCN on 4/7/2017.
 */

public class LoginFragment extends LoginFmBase implements LoginFmView {
    @BindView(R.id.login)
    TextView btnLogin;
    @BindView(R.id.user)
    EditText edtUser;
    @BindView(R.id.pass)
    EditText edtPass;
    @BindView(R.id.link)
    AutoCompleteTextView edtLink;
    @BindView(R.id.error_login)
    TextView tvErrorLogin;
    @BindView(R.id.web_view_start)
    WebView mWebView;
    @BindView(R.id.login_eye_button)
    TextView btnEye;
    @BindView(R.id.before_local)
    Spinner spnBeforLocal;
    @BindView(R.id.ip_text_user)
    TextInputLayout ipUser;
    @BindView(R.id.ip_text_pass)
    TextInputLayout ipPass;
    @BindView(R.id.linear_support)
    LinearLayout lnSupport;

    @Optional
    @OnClick({R.id.link_to_website, R.id.login, R.id.login_eye_button, R.id.linear_support})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.link_to_website:
                String url = "http://www.newsaigonsoft.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.login:
                mLoginFmLogic.LoginOrShowError(edtUser, edtPass, spnBeforLocal, edtLink);
                break;
            case R.id.login_eye_button:
                if (!isCheckShowPassWord) {
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnEye.setBackgroundResource(R.drawable.eyes_button_layer_list_orange);
                    isCheckShowPassWord = true;
                } else {
                    edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnEye.setBackgroundResource(R.drawable.eyes_button_layer_list_white);
                    isCheckShowPassWord = false;
                }
                break;
            case R.id.linear_support:
                mMainActivity.startActivity(new Intent(mMainActivity, SupportActivity.class));
                break;
            default:
                break;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mMainActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        mLoginFmLogic = new LoginFmLogic(this, mMainActivity);
        enableFeil();
        initEditText();
        btnEye.setBackground(getResources().getDrawable(R.drawable.eyes_button_layer_list_orange));
        //  hide password
        edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        // list local saved
        mLoginFmLogic.setSpinnerBeforLocal(arrBeforLocal);
        // get shareferend information user
        mMainActivity.getInforAccountFromShareReferenced(getActivity());
        // if link saved before, it get and replace link:
        mLoginFmLogic.setLinkTitle(mMainActivity.getLink());
        edtUser.setText(mMainActivity.getUserid());
        edtLink.setSelection(edtLink.getText().length());
        edtUser.setSelection(edtUser.getText().length());
        edtPass.setSelection(edtPass.getText().length());
        if (SumManager.getDefaults(POSITION_SPINNER_LOGIN, getActivity()) != null)
            spnBeforLocal.setSelection(Integer.parseInt(SumManager.getDefaults(POSITION_SPINNER_LOGIN, getActivity())));
        mSqLite = new SQLite(getActivity(), LOCAL_HOST_SQLITE, null, 1);
        getLocalName();
        edtPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mLoginFmLogic.LoginOrShowError(edtUser, edtPass, spnBeforLocal, edtLink);
                    return true;
                }
                return false;
            }
        });

        edtUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ipUser.setHint("Tên truy cập");
                    edtUser.setHint("");
                } else {
                    ipUser.setHint("");
                    edtUser.setHint("Tên truy cập");
                    if (edtUser.getText().toString().length() != 0) {
                        ipUser.setHint("Tên truy cập");
                    }
                }
            }
        });

        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ipPass.setHint("Mật khẩu");
                    edtPass.setHint("");
                } else {
                    ipUser.setHint("");
                    edtPass.setHint("Mật khẩu");
                    if (edtPass.getText().toString().length() != 0) {
                        ipPass.setHint("Mật khẩu");
                    }
                }
            }
        });


        spnBeforLocal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SumManager.setDefaults(POSITION_SPINNER_LOGIN, String.valueOf(i), getActivity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    private void initEditText() {
        if (edtUser.getText().toString().length() == 0) {
            ipUser.setHint("Tên truy cập");
        } else {
            ipUser.setHint(nULL_STRING);
        }
        if (edtPass.getText().toString().length() == 0) {
            ipPass.setHint("Mật khẩu");
        } else {
            ipPass.setHint(nULL_STRING);
        }
    }

    public void enableFeil() {
        edtUser.setFocusableInTouchMode(true);
        edtUser.setFocusable(true);
        edtPass.setFocusableInTouchMode(true);
        edtPass.setFocusable(true);
        edtLink.setFocusableInTouchMode(true);
        edtLink.setFocusable(true);
    }

    @Override
    public void disibleFeilAfterTap() {
        edtUser.setFocusable(false);
        edtPass.setFocusable(false);
        edtLink.setFocusable(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            String lik = savedInstanceState.getString("MyString");
            edtLink.setText(lik);
            edtLink.setText("");
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        String lik = edtLink.getText().toString();
        Log.d(TAG, lik);
        outState.putString("MyString", lik);

    }

    public void changeErrorText(String error) {
        tvErrorLogin.setVisibility(View.VISIBLE);
        tvErrorLogin.setText(error);
        mMainActivity.closeProgressDialog();
    }

    public void changWebView(String link) {
        mWebView.loadUrl(link);
    }

    public void getLocalName() {
        Cursor localhost = mSqLite.GetData(SELECT_FROM + LOCAL_HOST_TABLE_SQLITE);
        arrLocalHost = new ArrayList<String>();
        while (localhost.moveToNext()) {
            arrLocalHost.add(localhost.getString(1));
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrLocalHost);
        edtLink.setAdapter(adapter);
    }

    public void rememberLocal() {
        boolean mCheckEXISTS = false;
        Cursor mCursor = mSqLite.GetData(SELECT_FROM + LOCAL_HOST_TABLE_SQLITE);
        while (mCursor.moveToNext()) {
            if (mCursor.getString(1).equals(edtLink.getText().toString())) {
                mCheckEXISTS = true;
            }
        }
        if (!mCheckEXISTS) {
            mSqLite.QueryData(INSERT_INTO + LOCAL_HOST_TABLE_SQLITE + " VALUES(null, '" + edtLink.getText().toString() + "')");
        }
    }


    @Override
    public void showErrorMsg(String msg) {
        tvErrorLogin.setVisibility(View.VISIBLE);
        tvErrorLogin.setText(msg);
    }

    @Override
    public void setAdapterBeforeLocal(ArrayAdapter adapter) {
        spnBeforLocal.setAdapter(adapter);
    }

    @Override
    public void eventFmLogin(String user, String pass, String Link) {
        mMainActivity.eventLogin(spnBeforLocal.getSelectedItem().toString() + "://" + edtLink.getText().toString(), edtUser.getText().toString(), edtPass.getText().toString());
    }

    @Override
    public void setHttpSpinner(String http_https) {
        edtLink.setText(http_https);
    }


//    public void LoginOrShowError(EditText edtUser, EditText edtPass, Spinner spnBeforLocal){
//        setDefaults(USERNAME, edtUser.getText().toString(), getActivity());
//        setDefaults(PASSWORD_USER, edtPass.getText().toString(), getActivity());
//        setDefaults(LINK, spnBeforLocal.getSelectedItem().toString() + "://" + edtLink.getText().toString(), getActivity());
//        // check error null for information
//        if (!mMainActivity.isNetworkAvailable(getActivity())) {
//            mMainActivity.showDialogUpdateError(getString(R.string.no_connection), getActivity());
//        } else {
//            if (edtLink.getText().toString().length() != 0 && edtUser.getText().toString().length() != 0 && edtPass.getText().toString().length() != 0) {
//                mMainActivity.eventLogin(spnBeforLocal.getSelectedItem().toString() + "://" + edtLink.getText().toString(), edtUser.getText().toString(), edtPass.getText().toString());
//            } else {
//                tvErrorLogin.setVisibility(View.VISIBLE);
//                if (edtUser.getText().toString().length() != 0 && edtPass.getText().toString().length() != 0) {
//                    tvErrorLogin.setText(getString(R.string.plz_input_server_name));
//                } else {
//                    if (edtLink.getText().toString().length() != 0 && edtPass.getText().toString().length() != 0) {
//                        tvErrorLogin.setText(getString(R.string.plz_input_account));
//                    } else {
//                        if (edtLink.getText().toString().length() != 0 && edtUser.getText().toString().length() != 0) {
//                            tvErrorLogin.setText(getString(R.string.plz_input_password));
//                        } else {
//                            if (edtLink.getText().toString().length() != 0 && edtUser.getText().toString().length() == 0 && edtPass.getText().toString().length() == 0) {
//                                tvErrorLogin.setText(getString(R.string.plz_input_account_and_password));
//                            } else {
//                                if (edtLink.getText().toString().length() == 0 && edtUser.getText().toString().length() != 0 && edtPass.getText().toString().length() == 0) {
//                                    tvErrorLogin.setText(getString(R.string.plz_input_server_name_and_password));
//                                } else {
//                                    if (edtLink.getText().toString().length() == 0 && edtUser.getText().toString().length() == 0 && edtPass.getText().toString().length() != 0) {
//                                        tvErrorLogin.setText(getString(R.string.plz_input_server_name_and_account));
//                                    } else {
//                                        tvErrorLogin.setText(getString(R.string.input_login_must_not_null));
//                                    }
//                                }
//                            }
//
//                        }
//
//                    }
//                }
//
//            }
//        }
//    }
//public void onClick(View v) {
//    int id = v.getId();
//    switch (id) {
//        case R.id.link_to_website:
//            String url = "http://www.newsaigonsoft.com";
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            startActivity(i);
//            break;
//        case R.id.login:
//            mLoginFmLogic.LoginOrShowError(edtUser, edtPass, spnBeforLocal, edtLink);
//            break;
//        case R.id.login_eye_button:
//            if (!isCheckShowPassWord) {
//                edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                btnEye.setBackgroundResource(R.drawable.eyes_button_layer_list_orange);
//                isCheckShowPassWord = true;
//            } else {
//                edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                btnEye.setBackgroundResource(R.drawable.eyes_button_layer_list_white);
//                isCheckShowPassWord = false;
//            }
//            break;
//    }
//}
//private void ReferenceView(View view) {
//    btnLogin = (Button) view.findViewById(R.id.login);
//    edtLink = (AutoCompleteTextView) view.findViewById(R.id.link);
//    edtUser = (EditText) view.findViewById(R.id.user);
//    edtPass = (EditText) view.findViewById(R.id.pass);
//    mWebView = (WebView) view.findViewById(R.id.web_view_start);
//    tvErrorLogin = (TextView) view.findViewById(R.id.error_login);
//    spnBeforLocal = (Spinner) view.findViewById(R.id.before_local);
//    btnEye = (TextView) view.findViewById(R.id.login_eye_button);
//    tvLinkToWebsite = (TextView) view.findViewById(R.id.link_to_website);
//    tvLinkToWebsite.setOnClickListener(this);
//    btnLogin.setOnClickListener(this);
//    btnEye.setOnClickListener(this);
//}

}
