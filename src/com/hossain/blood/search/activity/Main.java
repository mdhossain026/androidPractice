package com.hossain.blood.search.activity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.hossain.blood.search.R;
import com.hossain.blood.search.message.MessageFormat;
import com.hossain.blood.search.model.Message;
import com.hossain.blood.search.util.BloodRequstUtil;

public class Main extends SherlockActivity {	

	
	private static final String FACEBOOK_APPID = "267917673372632";
	private static final String FACEBOOK_PERMISSION = "publish_stream";
	private static final String TAG = "BloodSerach";
	private static final String MSG = "Hello...................";
	
	private final Handler mFacebookHandler = new Handler();	
	private Spinner spnBloodGroup;
	private AutoCompleteTextView autoDisease;;
	private  EditText edtPatName,edtContactNo,edtPatLocation; 
	private Button btnShare;
	private FacebookConnector facebookConnector;
	String msg = null;
	  final Runnable mUpdateFacebookNotification = new Runnable() {
	        public void run() {
	        	
	        	BloodRequstUtil.showAlertDialog(Main.this, "Blood Request", "Your Status have Posted", false);
	        	clear();
	        	//Toast.makeText(getBaseContext(), "Your Status have Posted !", Toast.LENGTH_LONG).show();
	        }
	    };
   
	    
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		actionBarColor();
		this.facebookConnector = new FacebookConnector(FACEBOOK_APPID, this, getApplicationContext(), new String[] {FACEBOOK_PERMISSION});
			
		initialize();
		initBloodGroup();
		populateDisease();
		
		btnShare.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if(v.getId() == btnShare.getId() ){
					Log.d(TAG, "CAlled......");		
					if(reqFormValidation()){
						Message  message =  new Message();
						message.setBloodGroup(spnBloodGroup.getSelectedItem().toString());
						message.setPatName(edtPatName.getText().toString());
						message.setPatLocation(edtPatLocation.getText().toString());
						message.setDiseaseName(autoDisease.getText().toString());
						message.setContactNo(edtContactNo.getText().toString());
						Log.d(TAG, "Validation is complete...");
						
						MessageFormat messageFormat = new MessageFormat();
						msg  = messageFormat.getMessage(message);						
						postMessage();
					}	
					
				}		
				
			}
			
		});
	}
	
	private void populateDisease() {
		// Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				        R.array.disease_array, android.R.layout.simple_dropdown_item_1line);
//				// Specify the layout to use when the list of choices appears
//				adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//				// Apply the adapter to the spinner
//				spnDiseaseName.setAdapter(adapter);		        
		         autoDisease.setAdapter(adapter);
	}

	private void initBloodGroup() {
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.blood_group_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spnBloodGroup.setAdapter(adapter);
		spnBloodGroup.setSelection(0);
		
	}

	private void initialize(){		
		
		spnBloodGroup =  (Spinner) findViewById(R.id.spnBloodGroup);
		spnBloodGroup.setFocusable(true); 
		spnBloodGroup.setFocusableInTouchMode(true);
		spnBloodGroup.requestFocus();	
		
		edtPatName = (EditText) findViewById(R.id.edtPatName);
		edtPatLocation = (EditText) findViewById(R.id.edtPatLocation);
		autoDisease = (AutoCompleteTextView) findViewById(R.id.autoDisease);		
		edtContactNo = (EditText) findViewById(R.id.edtContact);
		btnShare = (Button) findViewById(R.id.btnShare);
	}
	
	private boolean reqFormValidation() {
		String patName = edtPatName.getText().toString();
		String patLocation = edtPatLocation.getText().toString();
		String contactNo = edtContactNo.getText().toString();
		if (spnBloodGroup.getSelectedItem().toString().equals("")) {			
			Toast.makeText(getApplicationContext(),
					"Please select  blood group.", Toast.LENGTH_SHORT).show();
			return false;
		}else if (patName.equals("")) {
			edtPatName.requestFocus();
			Toast.makeText(getApplicationContext(), "Paitent Name is required.",
					Toast.LENGTH_SHORT).show();
			return false;
		}else if (patLocation.equals("")) {
			edtPatLocation.requestFocus();
			Toast.makeText(getApplicationContext(), "Paitent current Location  is required.",
					Toast.LENGTH_SHORT).show();
			return false;
		}else if (autoDisease.getText().toString().equals("")) {
			autoDisease.requestFocus();
			Toast.makeText(getApplicationContext(), "Disease Name is required.",Toast.LENGTH_SHORT).show();
			return false;
		}else if (contactNo.equals("")) {
			edtContactNo.requestFocus();
			Toast.makeText(getApplicationContext(),
					"Contact number  is required.", Toast.LENGTH_LONG).show();
			return false;
		}
		else {
			return true;
		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		this.facebookConnector.getFacebook().authorizeCallback(requestCode, resultCode, data);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		updateLoginStatus();
	}
	
	public void updateLoginStatus() {
		Log.d(TAG, "Logged into facebook : " + facebookConnector.getFacebook().isSessionValid());
		//loginStatus.setText("Logged into Twitter : " + facebookConnector.getFacebook().isSessionValid());
	}

	private String getFacebookMsg() {
		return MSG + " at " + new Date().toLocaleString();
	}

	public void postMessage( ) {
		
		if (facebookConnector.getFacebook().isSessionValid()) {
			postMessageInThread();
		} else {
			SessionEvents.AuthListener listener = new SessionEvents.AuthListener() {
				
				@Override
				public void onAuthSucceed() {
					postMessageInThread();
				}
				
				@Override
				public void onAuthFail(String error) {
					
				}
			};
			SessionEvents.addAuthListener(listener);
			facebookConnector.login();
		}
	}
	
	
	
	private void postMessageInThread() {
		Thread t = new Thread() {
			public void run() {		    	
		    	try {
		    		//Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		    		if(msg != null){
		    			facebookConnector.postMessageOnWall(msg);
		    			mFacebookHandler.post(mUpdateFacebookNotification);
		    			//clearCredentials();
		    		}		    		
				
				} catch (Exception ex) {
					Log.e(TAG, "Error sending msg",ex);
				}
		    }
		};
		t.start();
	}
	
	private void clear(){
		edtPatName.setText("");
		edtPatLocation.setText("");
		edtContactNo.setText("");
		spnBloodGroup.setSelection(0);
		autoDisease.setText("");
	}

	
	private void clearCredentials() {		
		    try {
		
		        facebookConnector.getFacebook().logout(getApplicationContext());
		
		    } catch (MalformedURLException e) {
		
		        e.printStackTrace();
		
		    } catch (IOException e) {
		
		        e.printStackTrace();
		
		    }
		
		}

	private void actionBarColor() {
		ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#1e5799")));
	}
}
