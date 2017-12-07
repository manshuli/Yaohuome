package com.wangzu.yaohuome.activity;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.wangzu.yaohuome.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements TextWatcher {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportToolBar(mToorbar);

    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mAccountEditext.addTextChangedListener(this);
        mPasswordEditext.addTextChangedListener(this);

        mPasswordEditext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            getWindow().getDecorView().getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    //登录
                    showDialog();

                    return true;
                }
                return false;
            }
        });

        //登录
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("登录中...");
        dialog.setCancelable(false);
        dialog.show();
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

}
