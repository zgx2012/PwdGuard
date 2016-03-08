package com.lovethinking.passport.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DownloadUtil {
	private static String TAG = DownloadUtil.class.getSimpleName();
	private Context mContext;

	private ProgressDialog mProgressDialog;
	private ProgressBar mProgressBar;

	private long mTotalSize = 0; // Bytes
	private long mCurSize = 0; // Bytes
	private String mUrlString, mFilePathName;
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	/*
	 * DonwLoad
	 */
	private volatile boolean mIsDownloadCanceled;
	// private Thread mDownloadThread;
	/*
	 * Handler
	 */
	private Handler mActivityHandler;
	final public static int BEGIN_DOWNLOAD = 0;
	final public static int NEW_DOWNLOAD = 1;
	final public static int FINISH_DOWNLOAD = 2;
	final public static int CANCEL_DOWNLOAD = 3;

	public DownloadUtil(Context context) {
		mContext = context;
		mHandler = new DownloadHandler();
		// mDownloadThread = new Thread(new DownloadRunable());
	}


	public void startDownload(String urlStr, String toFile, Handler handler,
			ProgressBar progressBar) {
		mProgressBar = progressBar;
		startDownload(urlStr, toFile, handler, false);
	}


	public void startDownload(String urlStr, String toFile, Handler handler,
			boolean hasDlg) {
		mUrlString = urlStr;
		mFilePathName = toFile;
		mActivityHandler = handler;
		mCurSize = 0;
		mTotalSize = 0;
		mIsDownloadCanceled = false;
		if (hasDlg)
			createProgressDlg();
		downloadFile();
	}


	private void downloadFile() {
		new Thread(new DownloadRunable()).start();
		// if (mDownloadThread == null)
		// mDownloadThread = new Thread(new DownloadRunable());
		// if (mDownloadThread.isAlive())
		// mDownloadThread.interrupt();
		// mDownloadThread.start();
	}


	public void closeDownload() {
		mIsDownloadCanceled = true;
	}


	private void createProgressDlg() {
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setProgress(0);
		mProgressDialog.setTitle("Title");
		mProgressDialog.setMessage("Message...");
		mProgressDialog.setButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						sendMessage(CANCEL_DOWNLOAD);
					}
				});
		mProgressDialog.setOnKeyListener(new DialogOnKeyListener());
	}


	private class DialogOnKeyListener implements OnKeyListener {
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			sendMessage(CANCEL_DOWNLOAD);
			return true;
		}
	}

	private void sendMessage(final int msg) {
		Message message = new Message();
		message.what = msg;
		if (mHandler != null)
			mHandler.sendMessage(message);
		Message message2 = new Message();
		message2.what = msg;
		if (mActivityHandler != null) {
			// Log.i(TAG, "message");
			mActivityHandler.sendMessage(message2);
		}
	}


	private DownloadHandler mHandler;

	private class DownloadHandler extends Handler {
		public void handleMessage(Message message) {
			switch (message.what) {
			case BEGIN_DOWNLOAD:
				if (mProgressDialog != null) {
					mProgressDialog.setMax((int) mTotalSize);
					mProgressDialog.setProgress((int) mCurSize);
					mProgressDialog.show();
				}
				if (mProgressBar != null) {
					mProgressBar.setMax((int) mTotalSize);
					mProgressBar.setProgress((int) mCurSize);
				}
				break;
			case NEW_DOWNLOAD:
				if (mProgressDialog != null)
					mProgressDialog.setProgress((int) mCurSize);
				if (mProgressBar != null)
					mProgressBar.setProgress((int) mCurSize);
				break;
			case FINISH_DOWNLOAD:
				Log.i(TAG, "Download OK");
				break;
			case CANCEL_DOWNLOAD:
				if (mProgressDialog != null)
					mProgressDialog.dismiss();
				break;
			default:
				break;
			}
			super.handleMessage(message);
		}
	}

	private class DownloadRunable implements Runnable {
		@Override
		public void run() {
			try {
				URL url = new URL(mUrlString);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				if(connection==null){
					Toast.makeText(mContext, "error", Toast.LENGTH_LONG).show();
					sendMessage(CANCEL_DOWNLOAD);
					return;
				}

				mTotalSize = connection.getContentLength();
				sendMessage(BEGIN_DOWNLOAD);
				Log.d(TAG, "total size = " + mTotalSize / 1024 + " KB");

				mInputStream = connection.getInputStream();
				mOutputStream = new FileOutputStream(mFilePathName);
				Log.d(TAG, "load:" + mFilePathName);
				int len = 0;
				byte buffer[] = new byte[1024];
				while (mCurSize<mTotalSize && mIsDownloadCanceled == false
						&& (len = mInputStream.read(buffer)) != -1) {

					mCurSize += len;
					sendMessage(NEW_DOWNLOAD);
					// mIndex = (int) ((mCurSize * 100) / mTotalSize);
					mOutputStream.write(buffer, 0, len);
				}
				Log.d(TAG, "download pause");
				mInputStream.close();
				mOutputStream.close();
				if (mIsDownloadCanceled == false) {

					sendMessage(FINISH_DOWNLOAD);
					Log.d(TAG, "download OK!");
				} else {

					sendMessage(CANCEL_DOWNLOAD);
					Log.d(TAG, "Download is stopped!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (mProgressDialog != null)
					mProgressDialog.dismiss();
			}
		}
	};
}
