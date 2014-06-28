package com.lovethinking.passport;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lovethinking.passport.constant.DbConstant;
import com.lovethinking.passport.factory.DbFactory;
import com.lovethinking.passport.util.PassWordUtil;

public class LoginActivity extends Activity implements View.OnClickListener,
        DbConstant {
    private Context mContext;

    private Map<String, Object> mLoginMap;
    private TextView mLoginError;
    private EditText mUsernameText;
    private EditText mPasswordText;
    private Button mLoginButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mContext = LoginActivity.this;

        mLoginMap = DbFactory.instance().getLoginPwd(mContext);
        if (mLoginMap == null) {
            Intent intent = new Intent();
            intent.setClass(mContext, LoginRegister.class);
            startActivity(intent);
            finish();
        }

        mLoginError = (TextView) findViewById(R.id.login_error);
        mUsernameText = (EditText) findViewById(R.id.username);
        mPasswordText = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginButton) {
            String username = mUsernameText.getText().toString();
            String password = mPasswordText.getText().toString();
            try {
                if (!mLoginMap.get(KEY_LOGIN_USERNAME).toString()
                        .equals(username)) {
                    mLoginError.setText(R.string.user_name_error);
                } else if (!PassWordUtil.decrypt(
                        mLoginMap.get(KEY_LOGIN_PASSWORD).toString()).equals(
                        password)) {
                    mLoginError.setText(R.string.pass_word_error);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(mContext, PassWordList.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
