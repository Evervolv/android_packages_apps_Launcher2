/*
 * Copyright (C) 2011, The Evervolv Project.
 * Portions Copyright (C) 2008 The Android Open Source Project
 *  * Portions Copyright (C) 2008, T-Mobile USA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.android.launcher.R;

public class LauncherStylePreference extends Activity implements OnClickListener {

    private static final String TAG = "LauncherStylePreference";
    public static final String LAUNCHER_STYLE = "pref_key_launcher_style";
    
    private Gallery mGallery;
    private TextView mStyleNameView;
    private TextView mCurrentPositionView;
	private Button applyButton;
    
    private int mCurrStylePosition;
    private SharedPreferences mSharedPrefs;
    
    private ImageAdapter mAdapter;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        mAdapter = new ImageAdapter(this);

        inflateActivity();
        
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mCurrStylePosition = Integer.valueOf(mSharedPrefs.getString(LauncherPreferences.LAUNCHER_STYLE, "1"));
        
        mGallery.setSelection(mCurrStylePosition);
    }

    private void inflateActivity() {
        setContentView(R.layout.style_chooser);

        mCurrentPositionView = (TextView)findViewById(R.id.adapter_position);
        mStyleNameView = (TextView)findViewById(R.id.theme_name);

        mGallery = (Gallery)findViewById(R.id.gallery);
        mGallery.setAdapter(mAdapter);
        mGallery.setOnItemSelectedListener(mItemSelected);

        applyButton = (Button)findViewById(R.id.apply);
        applyButton.setOnClickListener(this);
    }
	
    public class ImageAdapter extends BaseAdapter {
    	
        private Context myContext;


        private int[] myImageIds = {
        		R.drawable.style_0,
        		R.drawable.style_1
        };

        public ImageAdapter(Context c) {
        	this.myContext = c;
        }

        public int getCount() {
        	return this.myImageIds.length;
        }

        public Object getItem(int position) { 
        	return position; 
        }
        
        public long getItemId(int position) {
        	return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(this.myContext);

            i.setImageResource(this.myImageIds[position]);
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            i.setLayoutParams(new Gallery.LayoutParams(280, 540));
            
            return i;
        }

        public float getScale(boolean focused, int offset) {
            return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset)));
        }
    }
    
    private final OnItemSelectedListener mItemSelected = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mCurrentPositionView.setText(getString(R.string.item_count,
                    (position + 1), mAdapter.getCount()));
           
            String text = "text";
            Log.d(TAG, "position: " + position);
            if (position == 0) {
        		text = getString(R.string.style_gingerbread);
            } else if (position == 1) {
        		text = getString(R.string.style_evervolv);
            }
            if (mCurrStylePosition == position) {
               text += " (current)";
            }
            mStyleNameView.setText(text);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        	
        }
    };

	@Override
	public void onClick(View v) {
		
		SharedPreferences.Editor editor = mSharedPrefs.edit();
		
		if (v == applyButton) {
			editor.putString(LAUNCHER_STYLE, Integer.toString(mGallery.getSelectedItemPosition()));
			editor.commit();
			finish();
			
		}
		
	}

    
}
