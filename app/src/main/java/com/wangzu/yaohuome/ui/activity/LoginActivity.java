package com.wangzu.yaohuome.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wangzu.yaohuome.R;
import com.wangzu.yaohuome.presenter.LoginPrester;
import com.wangzu.yaohuome.ui.view.LoginView;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements TextWatcher, LoginView, View.OnFocusChangeListener {

    @BindView(R.id.toorbar)
    Toolbar mToorbar;
    @BindView(R.id.accountEditext)
    EditText mAccountEditext;
    @BindView(R.id.accountLayout)
    TextInputLayout mAccountLayout;
    @BindView(R.id.passwordEditext)
    EditText mPasswordEditext;
    @BindView(R.id.passwordLayout)
    TextInputLayout mPasswordLayout;
    @BindView(R.id.loginButton)
    Button mLoginButton;
    private ProgressDialog mDialog;
    private LoginPrester mPrester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportToolBar(mToorbar);

    }

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        mPrester = new LoginPrester(this);

        mAccountEditext.addTextChangedListener(this);
        mPasswordEditext.addTextChangedListener(this);

        mAccountEditext.setOnFocusChangeListener(this);
        mPasswordEditext.setOnFocusChangeListener(this);

        //输入法软件盘登录
        mPasswordEditext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    hideSoftInput();
                    mPrester.login();
                    return true;
                }
                return false;
            }
        });

        //Button登录
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                mPrester.login();
            }
        });
    }

    //隐藏软键盘
    private void hideSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getWindow().getDecorView().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    private void showDialog() {
        mAccountLayout.setError(null);
        mPasswordLayout.setError(null);
        mDialog = new ProgressDialog(LoginActivity.this);
        mDialog.setMessage("登录中...");
        mDialog.setCancelable(false);
        mDialog.show();
    }

    private void hideDialog() {
        mDialog.dismiss();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mLoginButton.setEnabled(mAccountEditext.length() > 0 && mPasswordEditext.length() > 0);
    }

    @Override
    public String getUserName() {
        return mAccountEditext.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEditext.getText().toString();
    }

    @Override
    public void success() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void failed(String msg) {
        if (msg.contains("密码")) {
            mPasswordLayout.setError(msg);
        } else {
            mAccountLayout.setError(msg);
        }
    }

    @Override
    public void error() {

    }

    @Override
    public void showProgress() {
        showDialog();
    }

    @Override
    public void hideProgress() {
        hideDialog();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.accountEditext:
                if (!hasFocus) {
                    mAccountLayout.setError(null);
                }
                break;
            case R.id.passwordEditext:
                if (!hasFocus) {
                    mPasswordLayout.setError(null);
                }
                break;
        }
    }
}
