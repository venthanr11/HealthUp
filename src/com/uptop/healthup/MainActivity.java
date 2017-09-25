package com.uptop.healthup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button login, one, two, three, four, five, six, seven, eight, nine, zero,
			del;
	EditText pwd;
	DatabaseAdapter dbAdapter;
	AlertDialog alertDialog = null;
	Context context = this;

	private static final String PACKAGE = "com.uptop.healthup";
	private static final String SIGNUP = "com.uptop.healthup.SignUp";
	private static final String HOME = "com.uptop.healthup.Home";
	private static final String SIGNUP_MESSAGE = "Please sign-up to proceed further";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		login = (Button) findViewById(R.id.login);
		one = (Button) findViewById(R.id.one);
		two = (Button) findViewById(R.id.two);
		three = (Button) findViewById(R.id.three);
		four = (Button) findViewById(R.id.four);
		five = (Button) findViewById(R.id.five);
		six = (Button) findViewById(R.id.six);
		seven = (Button) findViewById(R.id.seven);
		eight = (Button) findViewById(R.id.eight);
		nine = (Button) findViewById(R.id.nine);
		zero = (Button) findViewById(R.id.zero);
		del = (Button) findViewById(R.id.delete);
		pwd = (EditText) findViewById(R.id.password);
		dbAdapter = new DatabaseAdapter(getApplicationContext());

		pwd.setText("");

		dbAdapter.open();
		if (!dbAdapter.checkdb()) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setTitle("SignUp");
			alertDialogBuilder
					.setMessage(SIGNUP_MESSAGE)
					.setCancelable(true)
					.setPositiveButton("Sign Up",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent();
									intent.setClassName(PACKAGE, SIGNUP);
									intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									context.startActivity(intent);
								}
							});
			alertDialog = alertDialogBuilder.create();
			alertDialog.show();

			TextView messageView = (TextView) alertDialog
					.findViewById(android.R.id.message);
			messageView.setGravity(Gravity.CENTER);
		}
		dbAdapter.close();

		pwd.setOnTouchListener(new OnTouchListener() {
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

				if (len <= 1) {
					pwd.setText(null);
				} else {
					String str = pwd.getText().toString();
					String newStr = str.substring(0, len - 1);
					pwd.setText(newStr);
				}
			}
		});

		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String password = pwd.getText().toString();
				dbAdapter.open();
				try {
					if (dbAdapter.checkdb()) {
						if (password.length() > 0) {
							if (dbAdapter.login(password)) {
								Intent i = new Intent();
								i.setClassName(PACKAGE, HOME);
								startActivity(i);
								finish();
							} else {
								pwd.setText("");
								Toast.makeText(
										MainActivity.this,
										"Invalid Password Provided\n Please Try Again.",
										Toast.LENGTH_LONG).show();
							}
						} else
							Toast.makeText(getApplicationContext(),
									"Please Enter a Password and Try again",
									Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(),
								"You have not Signed Up yet",
								Toast.LENGTH_SHORT).show();

					}
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
				dbAdapter.close();
			}
		});
	}

	public void update(int val) {

		int len = String.valueOf(pwd.getText().toString()).length();
		if (len == 0) {
			pwd.setText(String.valueOf(val));
		} else {
			String s = pwd.getText().toString();
			s = s + val;
			pwd.setText(s);
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public void onStop() {
		super.onStop();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
