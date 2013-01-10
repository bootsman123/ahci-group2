package com.example.herdinator_mobile;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SelectedObjectActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selected_object_activity);

		ImageView selected_image = (ImageView) findViewById(R.id.selected_image_view);
		int imageID = ((HerdinatorApplication) getApplication()).getImageID();
		selected_image.setImageResource(imageID);

		selected_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 HerdinatorApplication app = (HerdinatorApplication) getApplication();
				
				 List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				 params.add(new BasicNameValuePair("action", "use"));
				 params.add(new BasicNameValuePair("phoneId", app.getPhone_id()));
				
				 JSONObject map = Connection.getResponse(getApplicationContext(), app.getUrl(), params);
		}});
	}

	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	// HerdinatorApplication app = (HerdinatorApplication) getApplication();
	//
	// List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	// params.add(new BasicNameValuePair("action", "select"));
	// params.add(new BasicNameValuePair("phoneId", app.getPhone_id()));
	// params.add(new BasicNameValuePair("phoneId", ""));
	//
	// JSONObject map = Connection.getResponse(getApplicationContext(),
	// app.getUrl(), params);
	// if ((Boolean) map.get("success")) {
	// app.setConnected(false);
	// app.setPhone_id("");
	// }
	// }
	// return super.onKeyDown(keyCode, event);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
