package com.lovethinking.passport;

import com.lovethinking.passport.factory.DbFactory;
import com.lovethinking.passport.util.PassWordUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginRegister extends Activity implements View.OnClickListener {
    private Context mContext;
    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mPasswordAgainText;
    private Button mRegisterButton;
    private TextView mRegisterError;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mContext = this;

        mUsernameText = (EditText) findViewById(R.id.username);
        mPasswordText = (EditText) findViewById(R.id.password);
        mPasswordAgainText = (EditText) findViewById(R.id.password_confirm);
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(this);
        mRegisterError = (TextView) findViewById(R.id.register_error);
    }

    @Override
    public void onClick(View v) {
        if (v == mRegisterButton) {
            String username = mUsernameText.getText().toString();
            String password = mPasswordText.getText().toString();
            String passwordAgain = mPasswordAgainText.getText().toString();
            try {
                if (check(username, password, passwordAgain)) {
                    boolean registed;
                    registed = DbFactory.instance().registerLogin(mContext,
                            username, PassWordUtil.encrypt(password));
                    if (registed) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, PassWordList.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(mContext, R.string.register_db_error,
                                Toast.LENGTH_SHORT);
                        mRegisterError.setText(R.string.register_db_error);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean check(String username, String password, String passwordAgain) {
        if (empty(username)) {
            mRegisterError.setText(R.string.username_can_not_empty);
            return false;
        }
        if (empty(password)) {
            mRegisterError.setText(R.string.password_can_not_empty);
            return false;
        }
        if (empty(passwordAgain)) {
            mRegisterError.setText(R.string.password_can_not_empty);
            return false;
        }
        if (!password.equals(passwordAgain)) {
            mRegisterError.setText(R.string.password_not_match);
            return false;
        }
        return true;
    }

    private boolean empty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }
}
