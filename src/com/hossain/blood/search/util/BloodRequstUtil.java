package com.hossain.blood.search.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

public class BloodRequstUtil {
	
	public final static String APP_NAME = "Rufaida Medical Systems";
	public final static String INTERNET_CONN_FAILURE_TITLE = "No Internet Connection";
	public final static String INTERNET_CONN_FAILURE_MSG = "Please check your internet connection and try again.";
	
	public static final boolean isValidPhoneNumber(CharSequence target) {
		if (target == null || TextUtils.isEmpty(target)) {
			return false;
		} else {
			return android.util.Patterns.PHONE.matcher(target).matches();
		}
	}
	/**
	* Check the network state
	* @param context context of application
	* @return true if the phone is connected
	*/
	public static boolean isConnected(Context context) {
	    ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    return (networkInfo != null && networkInfo.isConnected());
	}
	public static void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        //alertDialog.setIcon((status) ? R.drawable.ic_launcher : R.drawable.ic_error_alert);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
	


}
