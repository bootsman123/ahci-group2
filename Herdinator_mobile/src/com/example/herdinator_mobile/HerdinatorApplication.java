package com.example.herdinator_mobile;

import android.app.Application;
import android.graphics.Color;

public class HerdinatorApplication extends Application {

	public final Integer[] imageIDs = { R.drawable.cookie, R.drawable.whistle, };
	public final Integer[] soundIDs = {R.raw.cookie_s, R.raw.whistle_s};
	public final String[] object_strings = { "cookie", "whistle" };

	private String phone_id;
	private Color overlay_color;
	private int selected_object;
	private boolean connected; 
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public int getSelected_object() {
		return selected_object;
	}

	public void setSelected_object(int selected_object) {
		this.selected_object = selected_object;
	}

	public Color getOverlay_color() {
		return overlay_color;
	}

	public void setOverlay_color(Color overlay_color) {
		this.overlay_color = overlay_color;
	}

	public String getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}

	public int getImageID() {
		return imageIDs[selected_object];
	}

	public String getSelected_string() {
		return object_strings[selected_object];
	}
	
	public int getSoundID(){
		return soundIDs[selected_object];
	}
}
