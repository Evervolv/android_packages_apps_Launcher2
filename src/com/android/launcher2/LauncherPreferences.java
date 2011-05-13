package com.android.launcher2;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import com.android.launcher.R;

public class LauncherPreferences extends PreferenceActivity {
	
	public static final String LAUNCHER_FORCE_PORTRAIT = "pref_key_launcher_force_portrait";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.launcher_prefs);
		PreferenceScreen prefSet = getPreferenceScreen();
    }
}
