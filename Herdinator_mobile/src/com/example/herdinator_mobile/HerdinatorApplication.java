package com.example.herdinator_mobile;

import android.app.Application;
import android.graphics.Color;

public class HerdinatorApplication extends Application {
	private int phone_id;
	private Color overlay_color;
	private String selected_object;
	private boolean connected; 

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getSelected_object() {
		return selected_object;
	}

	public void setSelected_object(String selected_object) {
		this.selected_object = selected_object;
	}

	public Color getOverlay_color() {
		return overlay_color;
	}

	public void setOverlay_color(Color overlay_color) {
		this.overlay_color = overlay_color;
	}

	public int getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(int phone_id) {
		this.phone_id = phone_id;
	}
}
