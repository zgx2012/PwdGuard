package com.lovethinking.passport;

import java.util.List;
import java.util.Map;

import com.lovethinking.passport.adapter.PwdListAdapter;
import com.lovethinking.passport.factory.DbFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;

public class PassWordList extends Activity implements View.OnClickListener {
    private Context mContext;
    private Button mAddButton;
    private ListView mListView;
    private PwdListAdapter mAdapter;
    int mFirstVisibleLine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pass_list);

        mContext = this;

        mAddButton = (Button) findViewById(R.id.add);
        mAddButton.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.listview);
        mFirstVisibleLine = -1;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public void onResume() {
        super.onResume();

        List<Map<String, Object>> list = DbFactory.instance().getSavedPwd(
                mContext);
        mAdapter = new PwdListAdapter(mContext, list);
        mListView.setAdapter(mAdapter);
        if (mFirstVisibleLine != -1) {
            mListView.setSelection(mFirstVisibleLine);
        }
    }

    public void onPause() {
        super.onPause();
        mFirstVisibleLine = mListView.getSelectedItemPosition();
    }

    @Override
    public void onClick(View v) {
        if (v == mAddButton) {
            Intent intent = new Intent();
            intent.setClass(mContext, EditPassWord.class);
            startActivity(intent);
        }
    }
}
