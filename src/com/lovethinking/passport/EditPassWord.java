package com.lovethinking.passport;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lovethinking.passport.factory.DbFactory;
import com.lovethinking.passport.util.PassWordUtil;

public class EditPassWord extends Activity implements View.OnClickListener {
    private Context mContext;
    private EditText mWhaText;
    private EditText mUserNameText;
    private EditText mPasswordText;
    private Button mSaveButton;

    private boolean mCreateNew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_pass);

        mContext = this;

        mCreateNew = false;

        mWhaText = (EditText) findViewById(R.id.what);
        mUserNameText = (EditText) findViewById(R.id.username);
        mPasswordText = (EditText) findViewById(R.id.password);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(this);
    }

    public void onBackPressed() {
        if (save()) {

        }
        finish();
    }

    private boolean save() {
        String what = mWhaText.getText().toString();
        String username = mUserNameText.getText().toString();
        String password = mPasswordText.getText().toString();
        try {
            if (check(what, username, password)) {
                return DbFactory.instance().addSavedPwd(mContext, username,
                        PassWordUtil.encrypt(password), what);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean check(String what, String username, String password) {
        if (empty(what) && empty(username) && empty(password)) {
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

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            save();
            finish();
        }
    }
}
