package com.lovethinking.passport.constant;

import android.view.Menu;

public interface MsgConstant extends Constant {
    int MSG_DELETE_LIST_ITEM = 1;
    int MSG_SELECT_LIST_ITEM = 2;
    int MSG_SHOW_NEXT = 3;
    int MSG_DELETE_LIST_ITEM_OK = 4;

    int MSG_NOTIFY_NORMAL = 5;
    int MSG_NOTIFY_ANDROID_UPGRADE = 6;
    int MSG_NOTIFY_NO_UPGRADE = 7;

    public String TOAST_ALREADY_NEW = "当前已经是最新版本";

    int MSG_LOVE_SCALE = 100;
    int MSG_FIRST_SCALE = MSG_LOVE_SCALE + 1;
    int MSG_SECOND_SCALE = MSG_LOVE_SCALE + 2;
    int MSG_THIRD_SCALE = MSG_LOVE_SCALE + 3;
    int MSG_fOURTH_SCALE = MSG_LOVE_SCALE + 4;
    int MSG_FIVTH_SCALE = MSG_LOVE_SCALE + 5;

    int MENU_ABOUT = Menu.FIRST - 1;
    int MENU_EXIT = Menu.FIRST - 2;
    int MENU_UPDATE = Menu.FIRST + 2;
    int MENU_INFO = Menu.FIRST + 1;
    int MENU_RECOMMEND = Menu.FIRST;

    int MENU_DELETE = 0;
    int MENU_CANCEL = 1;
    int MENU_MODIFY = 2;

    int REQ_SELECT_FILE = 1;
    int REQ_TAKE_PHOTO = 2;
    int REQ_PROFILE_WHEIGHT = 3;
    int REQ_PROFILE_BABY_INFO = 4;
    int REQ_PROFILE_EVENT = 5;
    int REQ_PHOTOZOOM = 6;
    int REQ_PHOTORESOULT = 7;
    int REQ_TAKE_VIDEO = 8;
    int REQ_TAKE_AUDIO = 9;

}
