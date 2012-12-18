package com.example.herdinator_mobile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class SettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);     
        
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        
        URL url;
        try {
        	String urlString = "http://84.27.117.195/HerdinatorServer-war";
			url = new URL(urlString);
			URLConnection connection = url.openConnection();
	        HttpURLConnection httpConnection = (HttpURLConnection) connection;
	        // Van hier...
	        int responseCode = httpConnection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            Log.d("MyApp", "Registration success");
	        } else {
	            Log.w("MyApp", "Registration failed for: " + urlString); 
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// ...komt ie hier!!!
			e.printStackTrace(); 
            progress.setVisibility(View.VISIBLE);          
		}
    }
}
