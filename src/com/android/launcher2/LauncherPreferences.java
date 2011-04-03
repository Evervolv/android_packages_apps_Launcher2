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

public class LauncherPreferences extends PreferenceActivity implements OnPreferenceChangeListener {
	
	private static final String LAUNCHER_STYLE = "launcher_style";
	private ListPreference mLauncherStylePref;
	

	
	@Override
    public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.launcher_prefs);
		PreferenceScreen prefSet = getPreferenceScreen();
	
		
		mLauncherStylePref = (ListPreference) prefSet.findPreference(LAUNCHER_STYLE);
		mLauncherStylePref.setValueIndex(Settings.System.getInt(getContentResolver(),
                Settings.System.LAUNCHER_STYLE, 1));
		mLauncherStylePref.setOnPreferenceChangeListener(this);
		
		//if (!getResources(R.bool.device_is_tablet)) {
		//	CharSequence[] mNonTabValues = { "0", "1" };
		//	mLauncherStylePref.setEntryValues(mNonTabValues);
		//}
    }

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mLauncherStylePref) {
        	Settings.System.putInt(getContentResolver(), Settings.System.LAUNCHER_STYLE, Integer.valueOf((String) newValue));
        }
        return false;
	}
}
