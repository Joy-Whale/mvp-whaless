package me.whaless.app.common.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

import me.whaless.app.common.AppKeeper;


class SharePreferenceHelper {

	private SharedPreferences sp;


	public static SharePreferenceHelper getInstance(String sharePreferenceName) {
		return new SharePreferenceHelper(AppKeeper.getInstance().getContext(), sharePreferenceName);
	}

	public SharePreferenceHelper(Context context, String sharePreferenceName) {
		sp = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
	}

	public int getIntValue(String name, int defaultValue) {
		return sp.getInt(name, defaultValue);
	}

	public float getFloatValue(String name, float defaultValue) {
		return sp.getFloat(name, defaultValue);
	}

	public boolean getBooleanValue(String name, boolean defaultValue) {
		return sp.getBoolean(name, defaultValue);
	}

	public String getStringValue(String name, String defaultValue) {
		return sp.getString(name, defaultValue);
	}

	public long getLongValue(String name, long defaultValue) {
		return sp.getLong(name, defaultValue);
	}

	public boolean clear() {
		Editor edit = sp.edit();
		edit.clear();
		edit.apply();
		return true;
	}


	public boolean editIntValue(String name, int value) {
		Editor edit = sp.edit();
		edit.putInt(name, value);
		edit.apply();
		return true;
	}


	public boolean editStringValue(String name, String value) {
		Editor edit = sp.edit();
		edit.putString(name, value);
		edit.apply();
		return true;
	}


	public boolean editBooleanValue(String name, boolean value) {
		Editor edit = sp.edit();
		edit.putBoolean(name, value);
		edit.apply();
		return true;
	}


	public boolean editFloatValue(String name, float value) {
		Editor edit = sp.edit();
		edit.putFloat(name, value);
		edit.apply();
		return true;
	}

	public boolean editLongValue(String name, long value) {
		Editor edit = sp.edit();
		edit.putLong(name, value);
		edit.apply();
		return true;
	}

	public void remove(String key) {
		Editor edit = sp.edit();
		edit.remove(key);
		edit.apply();
	}

	public Map<String, ?> getAll(){
		return sp.getAll();
	}
}
