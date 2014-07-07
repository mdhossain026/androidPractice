package com.hossain.blood.search.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.hossain.blood.search.R;

public class AndroidFacebookConnectActivity extends Activity {
	 
    // Your Facebook APP ID
    private static String APP_ID = "308180782571605"; // Replace your App ID here
 
    // Instance of Facebook Class
    private Facebook facebook;
    private AsyncFacebookRunner mAsyncRunner;
    String FILENAME = "AndroidSSO_data";
    private SharedPreferences mPrefs;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        facebook = new Facebook(APP_ID);
        mAsyncRunner = new AsyncFacebookRunner(facebook);
    }

}
