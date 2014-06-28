package com.lovethinking.passport.factory;

import com.lovethinking.passport.db.PwdBusinessHelper;

public class DbFactory {
    private static PwdBusinessHelper mBusinessHelper;

    public static PwdBusinessHelper instance() {
        if (null == mBusinessHelper) {
            mBusinessHelper = new PwdBusinessHelper();
        }
        return mBusinessHelper;
    }
}
