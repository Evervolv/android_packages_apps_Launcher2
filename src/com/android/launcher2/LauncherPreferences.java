package com.android.launcher2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.util.Log;

import com.android.launcher.R;

public class LauncherPreferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	public static final String LAUNCHER_FORCE_PORTRAIT = "pref_key_launcher_force_portrait";
	public static final String LAUNCHER_STYLE = "pref_key_launcher_style";
	
	private boolean restartLauncher = false;
	private SharedPreferences mSharedPrefs;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.launcher_prefs);
		PreferenceScreen prefSet = getPreferenceScreen();
		
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPrefs.registerOnSharedPreferenceChangeListener(this);
		
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
		if (key.equals(LAUNCHER_STYLE)) {
			Log.d("PREFS", "key = LAUNCHER_STYLE");
			restartLauncher = true;
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onPause(){
		if (restartLauncher) {
			android.os.Process.killProcess(android.os.Process.myPid());
		}
		super.onPause();
	}
}
