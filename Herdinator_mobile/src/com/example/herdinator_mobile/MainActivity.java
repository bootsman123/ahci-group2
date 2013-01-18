package com.example.herdinator_mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setColumnWidth(1);
		gridView.setAdapter(new ImageAdapter(this));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = new Intent(getBaseContext(),
						SelectedObjectActivity.class);
				HerdinatorApplication app = (HerdinatorApplication) getApplication();
				app.setSelected_object(position);

				List<NameValuePair> params = new ArrayList<NameValuePair>(3);
				params.add(new BasicNameValuePair("action", "select"));
				params.add(new BasicNameValuePair("phoneId", app.getPhone_id()));
				params.add(new BasicNameValuePair("item", app
						.getSelected_string()));

				JSONObject map = Connection.getResponse(
						getApplicationContext(), app.getUrl(), params);
				if ((Boolean) map.get("success")) {
					startActivity(intent);
				}

			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context c) {
			context = c;
		}

		// ---returns the number of images---
		public int getCount() {
			return ((HerdinatorApplication) getApplication()).imageIDs.length;
		}

		// ---returns the ID of an item---
		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		// ---returns an ImageView view---
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				// imageView.setLayoutParams(new GridView.LayoutParams(-1, -1));
				// imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setPadding(1, 1, 1, 1);
			} else {
				imageView = (ImageView) convertView;
			}
			int imageID = ((HerdinatorApplication) getApplication()).imageIDs[position];
			imageView.setImageResource(imageID);
			return imageView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			HerdinatorApplication app = (HerdinatorApplication) getApplication();

			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("action", "disconnect"));
			params.add(new BasicNameValuePair("phoneId", app.getPhone_id()));

			JSONObject map = Connection.getResponse(getApplicationContext(),
					app.getUrl(), params);
			if ((Boolean) map.get("success")) {
				app.setConnected(false);
				app.setPhone_id("");
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
