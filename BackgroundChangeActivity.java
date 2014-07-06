package com.hossain.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class BackgroundChangeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final LinearLayout linear = (LinearLayout) findViewById(R.id.mainLayout);
        Button btnChange =  (Button) findViewById(R.id.btnBgChange);
        Button btnRelease =  (Button) findViewById(R.id.btnBgRelease);
        
        btnChange.setOnClickListener(new OnClickListener() {
         
            public void onClick(View v) {
            	 // change background  color of your view here  
    			linear.setBackgroundResource(R.drawable.bg);

              }
        });
        
        btnRelease.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {

               // change background color of your view here  
            	linear.setBackgroundResource(R.drawable.bg_default);

              }
        });
        
        
        
    }
    
  
    
    


}