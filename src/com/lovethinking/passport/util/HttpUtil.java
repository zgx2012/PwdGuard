package com.lovethinking.passport.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import android.util.Log;

public class HttpUtil {
	public InputStream httpGetUrlPicture(String strUrl) throws Exception {
		Log.d("URL", "" + strUrl);
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(10 * 1000);
		InputStream inStream = conn.getInputStream();
		return inStream;
	}

	public InputStream httpDel(String url) {
		HttpDelete httpdelete = new HttpDelete(url);
		httpdelete.addHeader("Content-Type", "text/xml");
		return httpRequest(httpdelete);
	}

	/**
	 * httpGet
	 */
	public InputStream httpGet(String url) {
		HttpGet request = new HttpGet(url);
		return httpRequest(request);
	}

	/**
	 * httpPost
	 */
	public InputStream httpPost(String url, String content) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "text/xml");
		StringEntity myEntity = null;
		try {
			myEntity = new StringEntity(content, HTTP.UTF_8);
			httpPost.setEntity(myEntity);
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		return httpRequest(httpPost);
	}

	/**
	 * httpPut
	 */
	public InputStream httpPut(String url, String content) {
		HttpPut httpput = new HttpPut(url);
		httpput.addHeader("Content-Type", "text/xml");
		try {
			StringEntity myEntity = new StringEntity(content, HTTP.UTF_8);
			httpput.setEntity(myEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return httpRequest(httpput);
	}

	private InputStream httpRequest(HttpUriRequest httpUriRequest) {
		InputStream result = null;
		HttpResponse response = null;
		HttpClient httpClient = initHttp();
		try {
			response = httpClient.execute(httpUriRequest);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (response != null && response.getStatusLine().getStatusCode() == 200) {
			try {
				HttpEntity httpentity = response.getEntity();
				result = httpentity.getContent();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private HttpClient initHttp() {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				60000);
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		return client;
	}
}
