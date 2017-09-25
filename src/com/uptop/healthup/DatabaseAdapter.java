package com.uptop.healthup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {

	SQLiteDatabase database;
	DatabaseOpenHelper dbHelper;
	String str;
	String str2;
	private static final String LOGIN_TABLE = "login";
	public static final String USER_PRIMITIVE_TABLE = "user_primitive";
	public static final String USER_ADVANCED_TABLE = "user_advanced";
	public static final String DIABETES_TABLE = "diabetes";
	MessageDigest digest;

	public DatabaseAdapter(Context context) {

		dbHelper = new DatabaseOpenHelper(context);
	}

	public void open() {

		database = dbHelper.getWritableDatabase();
	}

	public void close() {

		database.close();
	}

	public void drop_table() {
	}

	public long createPassword(String password) {
		final String PASSWORD_COLUMN = "password";
		try {
			digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			String salt = "e33ptcbnto8wo8c4o48kwws0g8ksck0";
			String mk = password + salt;
			digest.update(mk.getBytes());
			byte[] a = digest.digest();

			int len = a.length;
			StringBuilder sb = new StringBuilder(len << 1);
			for (int i = 0; i < len; i++) {
				sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
				sb.append(Character.forDigit(a[i] & 0x0f, 16));
			}
			str = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		ContentValues values = new ContentValues();
		values.put(PASSWORD_COLUMN, str);
		return database.insert(LOGIN_TABLE, "", values);
	}

	public Boolean login(String password) {
		final String PASSWORD_COLUMN = "password";
		try {
			digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			String salt = "e33ptcbnto8wo8c4o48kwws0g8ksck0";
			String mk = password + salt;
			digest.update(mk.getBytes());
			byte[] a = digest.digest();

			int len = a.length;
			StringBuilder sb1 = new StringBuilder(len << 1);
			for (int i = 0; i < len; i++) {
				sb1.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
				sb1.append(Character.forDigit(a[i] & 0x0f, 16));
			}
			str = sb1.toString();
			Cursor c = database.rawQuery("SELECT * FROM " + LOGIN_TABLE, null);
			if (c.getCount() != 0)
				if (c.moveToFirst())
					str2 = c.getString(c.getColumnIndex(PASSWORD_COLUMN));
			c.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (str.compareTo(str2) == 0)
			return true;
		else
			return false;
	}

	public boolean checkdb() {
		Cursor c = database.rawQuery("SELECT * FROM " + LOGIN_TABLE, null);
		if (!c.moveToFirst()) {
			c.close();
			return false;
		} else {
			c.close();
			return true;
		}
	}

	public long insertPrimitiveProfile(String name, int gender, String dob,
			int age, double height, double weight, String bloodGroup) {
		final String NAME_COLUMN = "name";
		final String GENDER_COLUMN = "gender";
		final String DOB_COLUMN = "dob";
		final String AGE_COLUMN = "age";
		final String HEIGHT_COLUMN = "height";
		final String WEIGHT_COLUMN = "weight";
		final String BLOOD_GROUP_COLUMN = "bloodGroup";
		ContentValues cv = new ContentValues();
		cv.put(NAME_COLUMN, name);
		cv.put(GENDER_COLUMN, gender);
		cv.put(DOB_COLUMN, dob);
		cv.put(AGE_COLUMN, age);
		cv.put(HEIGHT_COLUMN, height);
		cv.put(WEIGHT_COLUMN, weight);
		cv.put(BLOOD_GROUP_COLUMN, bloodGroup);
		return database.insert(USER_PRIMITIVE_TABLE, "", cv);
	}

	public long insertAdvancedProfile(int lifestyle, int ethnicity,
			int diabetes, int bp, int tobacco, int alcohol) {
		final String LIFESTYLE_COLUMN = "lifestyle";
		final String ETHNICITY_COLUMN = "ethnicity";
		final String DIABETES_COLUMN = "diabetes";
		final String BP_COLUMN = "bp";
		final String TOBACCO_COLUMN = "tobacco";
		final String ALCOHOL_COLUMN = "alcohol";
		ContentValues cv = new ContentValues();
		cv.put(LIFESTYLE_COLUMN, lifestyle);
		cv.put(ETHNICITY_COLUMN, ethnicity);
		cv.put(DIABETES_COLUMN, diabetes);
		cv.put(BP_COLUMN, bp);
		cv.put(TOBACCO_COLUMN, tobacco);
		cv.put(ALCOHOL_COLUMN, alcohol);
		return database.insert(USER_ADVANCED_TABLE, "", cv);
	}

	public long insertDiabetes(int age, double bmi, int lifestyle,
			int ethnicity, int bp, int tobacco, int alcohol) {
		final String AGE_COLUMN = "age";
		final String BMI_COLUMN = "bmi";
		final String LIFESTYLE_COLUMN = "lifestyle";
		final String ETHNICITY_COLUMN = "ethnicity";
		final String BP_COLUMN = "bp";
		final String TOBACCO_COLUMN = "tobacco";
		final String ALCOHOL_COLUMN = "alcohol";
		ContentValues cv = new ContentValues();
		cv.put(AGE_COLUMN, age);
		cv.put(BMI_COLUMN, bmi);
		cv.put(LIFESTYLE_COLUMN, lifestyle);
		cv.put(ETHNICITY_COLUMN, ethnicity);
		cv.put(BP_COLUMN, bp);
		cv.put(TOBACCO_COLUMN, tobacco);
		cv.put(ALCOHOL_COLUMN, alcohol);
		return database.insert(DIABETES_TABLE, "", cv);
	}

	public void updateDiabetesTable(int lifestyle, int ethnicity, int bp,
			int tobacco, int alcohol) {
		final String LIFESTYLE_COLUMN = "lifestyle";
		final String ETHNICITY_COLUMN = "ethnicity";
		final String BP_COLUMN = "bp";
		final String TOBACCO_COLUMN = "tobacco";
		final String ALCOHOL_COLUMN = "alcohol";
		ContentValues cv = new ContentValues();
		cv.put(LIFESTYLE_COLUMN, lifestyle);
		cv.put(ETHNICITY_COLUMN, ethnicity);
		cv.put(BP_COLUMN, bp);
		cv.put(TOBACCO_COLUMN, tobacco);
		cv.put(ALCOHOL_COLUMN, alcohol);
		database.update(DIABETES_TABLE, cv, null, null);
	}

}
