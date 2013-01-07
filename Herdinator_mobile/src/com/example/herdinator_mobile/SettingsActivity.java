package com.example.herdinator_mobile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
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
        
//        String default_url = "http://wlan-132-054.wlan.ru.nl:8080";
        String default_url = "http://127.0.0.1:8080";
        
        EditText server_adress = (EditText) findViewById(R.id.server_adress);
        server_adress.setText(default_url);
        
        Button connect_button = (Button) findViewById(R.id.connect_button);
        connect_button.setOnClickListener(new ConnectClickListener(server_adress));
        
        
    }
    
    private class ConnectClickListener implements OnClickListener{
    	private EditText server_adress;

		public ConnectClickListener(EditText server_adress) {
			this.server_adress = server_adress;
		}

		@Override
		public void onClick(View v) {
			final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
			final TextView error = (TextView)findViewById(R.id.error_field);
			
			EditText marker_id = (EditText) findViewById(R.id.marker_id);
			String markString = marker_id.getText().toString();
			final int markID;
			if(markString.isEmpty()){
				error.setText("Enter marker ID");
				return;
			}
			else
				markID = Integer.valueOf(markString);
			
			String urlString = server_adress.getText().toString();

			error.setText("thread!");
			Thread connectThread = new Thread(new RunConnect(markID, urlString){}); 
			connectThread.start();

			error.setText("start!");
			
			Time time = new Time(Time.getCurrentTimezone());
			time.setToNow();
			int start = time.SECOND;
			while (
					time.SECOND < start+20 &&
					!((HerdinatorApplication)getApplication()).isConnected()
					){
				
				error.setText(String.valueOf(time.SECOND));
				time.setToNow();
			}
			connectThread.stop();
			error.setText("Connected!");
		}
    }
    
    private class RunConnect implements Runnable{
    	private int markID;
		private String urlString;

		public RunConnect(int markID, String urlString){
    		this.markID = markID;
    		this.urlString = urlString;
    	}
    	
    	@Override
		public void run() {
			ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
			TextView error = (TextView)findViewById(R.id.error_field);
			
			try {

				List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				params.add(new BasicNameValuePair("action", "connect"));
				params.add(new BasicNameValuePair("markId", String.valueOf(markID)));
				String paramString = URLEncodedUtils.format(params, "utf-8");
				
				HttpGet httpGet = new HttpGet(urlString + "/?"+ paramString);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				
				
				Logger.getLogger( SettingsActivity.class.getName() ).log( Level.WARNING, "Http String");
				Logger.getLogger( SettingsActivity.class.getName() ).log( Level.WARNING, httpGet.getURI().toASCIIString() );

				// Throws exception after execute
				error.setText("0!");
				HttpResponse response = httpClient.execute(httpGet);
				error.setText("1!");
				String responseString = EntityUtils.toString(response.getEntity());
				error.setText("2!");
				JSONObject map = (JSONObject) JSONValue.parse(responseString);
				error.setText("3!");
				error.setText(String.valueOf(map.getBoolean("success")));
//				if(map.getBoolean("success")){
//					((HerdinatorApplication) getApplication()).setPhone_id(map.getInt("phoneId"));
//				}
//				progress.setVisibility(View.VISIBLE);
				
				((HerdinatorApplication)getApplication()).setConnected(true);
				
			} catch (ClientProtocolException e) {
//				error.setText("ClientProtocolException");
				progress.setVisibility(View.INVISIBLE);
			} catch (IOException e) {
//				error.setText("IOException");
				progress.setVisibility(View.INVISIBLE);
			} catch (JSONException e) {
//				error.setText("JSONException");
				progress.setVisibility(View.INVISIBLE);
			}
		}
    }
}
