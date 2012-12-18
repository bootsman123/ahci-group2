package com.example.herdinator_mobile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
    Integer[] imageIDs = {
            R.drawable.fence,
            R.drawable.bridge,
            R.drawable.flute
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View overlay = (View) findViewById(R.id.coloroverlay);
//        overlay.setBackgroundColor(Color.argb(100, 0, 255, 0));
        
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));
 
        
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {                
            	Intent intent = new Intent(getBaseContext(), SelectedObjectActivity.class);
            	intent.putExtra("imageID", imageIDs[position]);
            	startActivity(intent);
            }
        });        
    }
    
    public class ImageAdapter extends BaseAdapter 
    {
        private Context context;
 
        public ImageAdapter(Context c) 
        {
            context = c;
        }
 
        //---returns the number of images---
        public int getCount() {
            return imageIDs.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position) {
            return position;
        }
 
        public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent) 
        {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
//                imageView.setLayoutParams(new GridView.LayoutParams(-1, -1));
//                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(1,1,1,1);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imageIDs[position]);
            return imageView;
        }
    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
