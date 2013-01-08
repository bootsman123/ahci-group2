package com.example.herdinator_mobile;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class SelectedObjectActivity extends Activity {   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_object_activity);
        
        View overlay = (View) findViewById(R.id.coloroverlay_2);
//        overlay.setBackgroundColor(Color.argb(100, 0, 255, 0));
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	Integer imageID = extras.getInt("imageID");
        	ImageView selected_image = (ImageView) findViewById(R.id.selected_image_view);
        	selected_image.setImageResource(imageID);
        }
        
        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((HerdinatorApplication)getApplication()).setSelected_object("");
				
				finish();
			}
		});
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
