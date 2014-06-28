package com.lovethinking.passport.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lovethinking.passport.R;
import com.lovethinking.passport.constant.DbConstant;
import com.lovethinking.passport.util.PassWordUtil;

public class PwdListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mDataList;

    /*
     * 构造方法
     */
    public PwdListAdapter(Context context, List<Map<String, Object>> list) {
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDataList = list;
    }

    /*
     * 修改列表
     */
    public void setList(List<Map<String, Object>> list) {
        mDataList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDataList == null)
            return 0;
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mDataList == null)
            return null;
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * getView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.pass_item, null);
        } else {
            view = (View) convertView;
        }
        /**
         * 获取控件
         */
        TextView what = (TextView) view.findViewById(R.id.what);
        TextView username = (TextView) view.findViewById(R.id.username);
        TextView password = (TextView) view.findViewById(R.id.password);

        /*
         * 设置控件数据
         */
        final Map<String, Object> map = mDataList.get(position);
        what.setText(map.get(DbConstant.KEY_PWD_WHAT).toString());
        username.setText(map.get(DbConstant.KEY_PWD_USERNAME).toString());
        try {
            password.setText(PassWordUtil.decrypt(map.get(
                    DbConstant.KEY_PWD_PASSWORD).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
