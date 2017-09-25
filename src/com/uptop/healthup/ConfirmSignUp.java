package com.uptop.healthup;

import com.uptop.healthup.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmSignUp extends Activity {
	
	Button done, one, two, three, four, five, six, seven, eight, nine, zero, del;
	EditText pwd;
	TextView confirmSignUpTv;
	DatabaseAdapter dbAdapter;
	
	private static final String PACKAGE = "com.uptop.healthup";
	private static final String SIGN_UP = "com.uptop.healthup.SignUp";
	private static final String PROFILE = "com.uptop.healthup.Profile";
	private static final String MAIN_ACTIVITY = "com.uptop.healthup.MainActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_sign_up);
        
        pwd =(EditText) findViewById(R.id.password);
        done =(Button) findViewById(R.id.done);
        one =(Button) findViewById(R.id.one);
        two =(Button) findViewById(R.id.two);
        three =(Button) findViewById(R.id.three);
        four =(Button) findViewById(R.id.four);
        five =(Button) findViewById(R.id.five);
        six =(Button) findViewById(R.id.six);
        seven =(Button) findViewById(R.id.seven);
        eight =(Button) findViewById(R.id.eight);
        nine =(Button) findViewById(R.id.nine);
        zero =(Button) findViewById(R.id.zero);
        del =(Button) findViewById(R.id.delete);
        confirmSignUpTv = (TextView) findViewById(R.id.confirm_sign_up_tv);
        
        dbAdapter = new DatabaseAdapter(getApplicationContext());
        
        final String CONFIRM_SIGN_UP_TV = " Confirm Password ";
		
        confirmSignUpTv.setBackgroundColor(getResources().getColor(R.color.green));
        confirmSignUpTv.setText(CONFIRM_SIGN_UP_TV);
        
        pwd.setText("");
        
        pwd.setOnTouchListener(new OnTouchListener(){		
		    public boolean onTouch(View v, MotionEvent event) {
		        int inType = pwd.getInputType();
		        pwd.setInputType(InputType.TYPE_NULL);
		        pwd.onTouchEvent(event);
		        pwd.setInputType(inType);
		        return true;
		    }
		});
        
        one.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(1);				
			}
		});
        
        two.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(2);				
			}
		});
        
        three.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(3);				
			}
		});
        
        four.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(4);				
			}
		});
        
        five.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(5);				
			}
		});
        
        six.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(6);				
			}
		});
        
        seven.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(7);				
			}
		});
        
        eight.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(8);				
			}
		});
        
        nine.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				update(9);				
			}
		});
        
        zero.setOnClickListener(new OnClickListener() {		
			public void onClick(View v) {
				update(0);			
			}
		});
        
		del.setOnClickListener(new OnClickListener() {				
					public void onClick(View v) {
						int len = String.valueOf(pwd.getText().toString()).length();
						
						if(len <= 1) {
							pwd.setText(null);
						}
						else {
							String str = pwd.getText().toString();
							String newStr = str.substring(0, len-1);
							pwd.setText(newStr);
						}						
					}
		});
		
		done.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String password = pwd.getText().toString();
				Intent i = getIntent();
				Bundle b = i.getExtras();
				dbAdapter.open();
				String str = b.getString("PASSWORD");
				if(password.compareTo(str) == 0) {
					long x = dbAdapter.createPassword(password);
					if(x > 0) {
						i.setClassName(PACKAGE, PROFILE);
						startActivity(i);
					}
					else {
				    	i = new Intent();
				    	i.setClassName(PACKAGE, SIGN_UP);
				    	startActivity(i);
				    }
				}
				else {
					Toast.makeText(getApplicationContext(), "Password Mismatch. Please try again.", Toast.LENGTH_LONG).show();
					i = new Intent();
					i.setClassName(PACKAGE, SIGN_UP);
					startActivity(i);
				}
				dbAdapter.close();
			}		
		});
	}
	
	public void update(int val) {
		int len = String.valueOf(pwd.getText().toString()).length();
		
		if(len == 0) {
			pwd.setText(String.valueOf(val));
		}
		else {
			String s = pwd.getText().toString();
			s = s + val;
			pwd.setText(s);
		}		
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	    finish();
	}
	    
	@Override
	public void onBackPressed() {			
		Intent i = new Intent();
		i.setClassName(PACKAGE, MAIN_ACTIVITY);
		startActivity(i);
	}

}
