package com.uptop.healthup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	public static final String DB_NAME = "healthup.db";
	public static final String LOGIN_TABLE = "login";
	public static final String USER_PRIMITIVE_TABLE = "user_primitive";
	public static final String USER_ADVANCED_TABLE = "user_advanced";
	public static final String DIABETES_TABLE = "diabetes";
	
	public static final String CREATE_LOGIN_TABLE = "Create table "+ LOGIN_TABLE 
											+"( password TEXT );";
	
	public static final String CREATE_USER_PRIMITIVE_TABLE = "Create table "+ USER_PRIMITIVE_TABLE 
			+"("
			+" name TEXT,"
			+" gender INTEGER,"
			+" dob TEXT,"
			+" age INTEGER,"
			+" height INTEGER,"
			+" weight INTEGER,"
			+" bloodGroup TEXT"
			+" );";
	
	public static final String CREATE_USER_ADVANCED_TABLE = "Create table "+ USER_ADVANCED_TABLE
			+"("
			+" lifestyle INTEGER,"
			+" ethnicity INTEGER,"
			+" diabetes INTEGER,"
			+" bp INTEGER,"
			+" tobacco INTEGER,"
			+" alcohol INTEGER"
			+" );";
	
	public static final String CREATE_DIABETES_TABLE = "Create table "+ DIABETES_TABLE 
			+"("
			+" age INTEGER,"
			+" bmi INTEGER,"
			+" lifestyle INTEGER,"
			+" ethnicity INTEGER,"
			+" bp INTEGER,"
			+" tobacco INTEGER,"
			+" alcohol INTEGER"
			+" );";
	
	public DatabaseOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_LOGIN_TABLE);
		database.execSQL(CREATE_USER_PRIMITIVE_TABLE);
		database.execSQL(CREATE_USER_ADVANCED_TABLE);
		database.execSQL(CREATE_DIABETES_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
