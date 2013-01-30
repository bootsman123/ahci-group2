package com.example.herdinator_mobile;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);

		// Network url
		String default_url = "http://fsw204200.socsci.ru.nl:8080";

		// AVD url
		// String default_url = "http://10.0.2.2:8080";

		EditText server_adress = (EditText) findViewById(R.id.server_adress);
		server_adress.setText(default_url);

		Button connect_button = (Button) findViewById(R.id.connect_button);
		connect_button.setOnClickListener(new ConnectClickListener(
				server_adress));
	}

	@Override
	protected void onResume() {
		super.onResume();
		ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
		TextView error = (TextView) findViewById(R.id.error_field);
		HerdinatorApplication app = (HerdinatorApplication) getApplication();

		progress.setVisibility(View.INVISIBLE);

		if (!app.isConnected() && app.getPhone_id() != null)
			error.setText("Diconnected...");
		else
			error.setText("");
	}

	private class ConnectClickListener implements OnClickListener {
		private EditText server_adress;

		public ConnectClickListener(EditText server_adress) {
			this.server_adress = server_adress;
		}

		@Override
		public void onClick(View v) {
			final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
			final TextView error = (TextView) findViewById(R.id.error_field);

			EditText marker_id = (EditText) findViewById(R.id.marker_id);
			String markString = marker_id.getText().toString();
			final int markID;
			if ( "".equals( markString ) ) {
				error.setText("Enter marker ID");
				return;
			}

			markID = Integer.valueOf(markString);
			progress.setVisibility(View.VISIBLE);
			String urlString = server_adress.getText().toString();

			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("action", "connect"));
			params.add(new BasicNameValuePair("markId", String.valueOf(markID)));

			JSONObject map = null;
			try {
				map = Connection.getResponse(getApplicationContext(),
						urlString, params);
			} finally {
				if (map == null) {
					error.setText("Unable to connect to server...");
					progress.setVisibility(View.INVISIBLE);
				} else if ((Boolean) map.get("success")) {
					HerdinatorApplication app = (HerdinatorApplication) getApplication();
					app.setPhone_id((String) map.get("phoneId"));
					app.setConnected(true);
					app.setUrl(urlString);

					Intent intent = new Intent(getBaseContext(),
							MainActivity.class);
					startActivity(intent);
				} else {
					error.setText("Unable to connect to server...");
					progress.setVisibility(View.INVISIBLE);
				}
			}

		}
	}
}
