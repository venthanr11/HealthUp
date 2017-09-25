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

public class SignUp extends Activity {
	
	Button done, one, two, three, four, five, six, seven, eight, nine, zero, del;
	EditText pwd;
	TextView signUpTv;
	
	private static final String PACKAGE = "com.uptop.healthup";
	private static final String CONFIRM_SIGN_UP = "com.uptop.healthup.ConfirmSignUp";
	private static final String MAIN_ACTIVITY = "com.uptop.healthup.MainActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        
        
        
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
        signUpTv = (TextView) findViewById(R.id.sign_up_tv);
        
        final String SIGN_UP_TV = " Enter Password ";
		
        signUpTv.setBackgroundColor(getResources().getColor(R.color.green));
        signUpTv.setText(SIGN_UP_TV);
        
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
				
				if(pwd.length() < 4) {
					Toast.makeText(getApplicationContext(), "Password should be atleast 4 characters long.", Toast.LENGTH_LONG).show();
					pwd.setText("");
				}
				else {
					Intent i = new Intent();
					String password = pwd.getText().toString();
					i.setClassName(PACKAGE, CONFIRM_SIGN_UP);
					i.putExtra("PASSWORD", password);
					startActivity(i);
				}
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
