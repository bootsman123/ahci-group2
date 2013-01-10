package com.example.herdinator_mobile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.content.Context;

public class Connection implements Runnable {

	private static HttpResponse response;
	private AbstractHttpClient httpClient;
	private HttpUriRequest httpGet;

	private Connection(HttpGet httpGet, DefaultHttpClient httpClient) {
		this.httpGet = httpGet;
		this.httpClient = httpClient;
	}

	public static JSONObject getResponse(Context context, String url,
			List<NameValuePair> params) {
		Logger l = Logger.getLogger(SettingsActivity.class.getName());
		try {
			String paramString = URLEncodedUtils.format(params, "utf-8");

			HttpGet httpGet = new HttpGet(url + "/?" + paramString);
			DefaultHttpClient httpClient = new DefaultHttpClient();

			Thread t = new Thread(new Connection(httpGet, httpClient));
			t.start();
			while (t.isAlive()) {
				Thread.sleep(20);
			}

			HttpEntity entity = response.getEntity();
			String s = EntityUtils.toString(entity);

			JSONParser parser = new JSONParser();

			l.log(Level.WARNING, "err " + s);
			JSONObject map = (JSONObject) parser.parse(s);

			return map;
		} catch (Exception e) {
			l.log(Level.WARNING, "err Exception " + e.toString());
			l.log(Level.WARNING, "err Exception " + e.getLocalizedMessage());
			StackTraceElement[] stack = e.getStackTrace();
			for (int i = 0; i < stack.length; i++)
				l.log(Level.WARNING, "err " + stack[i].toString());
		}
		return null;
	}

	@Override
	public void run() {
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
