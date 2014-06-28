package com.lovethinking.passport.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.lovethinking.passport.R;

/**
 * @ClassName : DialogUtil
 * @Description : TODO
 * @author : ZGX zhangguoxiao_happy@163.com
 * @date : 2011-7-5 上午09:55:33
 * 
 */
public class DialogUtil {

    public static ProgressDialog getProgressDialog(Context context,
            String msgString) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msgString);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        return progressDialog;
    }

    /*
     * 选择日期，不包括时间
     */
    public static void selectDate(Context context, final Button edit,
            final Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        OnDateSetListener datetime = new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
                edit.setText(String.format("%04d", year) + "-"
                        + String.format("%02d", monthOfYear + 1) + "-"
                        + String.format("%02d", dayOfMonth));
            }
        };
        Dialog dateDlg = new DatePickerDialog(context, datetime,
                date.getYear() + 1900, date.getMonth(), date.getDate());
        dateDlg.show();
    }

    /*
    public static void showDatePicker(Context context, final Button editText,
            int resId) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) inflater.inflate(resId, null);
        final DatePicker datePicker = (DatePicker) view
                .findViewById(R.id.dtpicker);
        Dialog dialog = new AlertDialog.Builder(context).setTitle("选择日期")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText(datePicker.getYear() + "-"
                                + (datePicker.getMonth() + 1) + "-"
                                + datePicker.getDayOfMonth());
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null).create();
        dialog.show();
    }
    */

    /*
     * 选择日期，包括时间
     */
    /*
    public static void showDateTimePicker(Context context, final Button editText) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.datetime,
                null);
        final TimePicker timePicker = (TimePicker) view
                .findViewById(R.id.tmpicker);
        timePicker.setIs24HourView(true);
        final DatePicker datePicker = (DatePicker) view
                .findViewById(R.id.dtpicker);
        Dialog dialog = new AlertDialog.Builder(context).setTitle("选择日期")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText(datePicker.getYear()
                                + "-"
                                + (datePicker.getMonth() + 1)
                                + "-"
                                + datePicker.getDayOfMonth()
                                + " "
                                + String.format("%02d",
                                        timePicker.getCurrentHour())
                                + ":"
                                + String.format("%02d",
                                        timePicker.getCurrentMinute()) + ":00");
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null).create();
        dialog.show();
    }
    */

    /**
     * 类似spinner
     * 
     * @param options
     * @param listener
     * @return Dialog
     * @since v 1.0
     */
    public static void showOptionsDialog(Context context,
            final Button editText, final String[] options, final String title) {
        DialogInterface.OnClickListener lister = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                editText.setText(options[which]);
                dialog.dismiss();
            }
        };
        Dialog dialog = new AlertDialog.Builder(context).setTitle(title)
                .setItems(options, lister).create();
        dialog.show();
    }

}
