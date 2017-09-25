package com.uptop.healthup;

import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Profile extends Activity {

	private EditText nameEt, heightEt, weightEt;
	private RadioGroup radioSexGroup;
	private RadioButton radioSexButton;
	private Button saveBtn;
	private Spinner daySpinner, monthSpinner, yearSpinner, weightUnitSpinner,
			heightUnitSpinner, bloodGroupSpinner;
	private TextView primitiveProfileTv;

	private DatabaseAdapter dbAdapter;

	private String name, gender, bloodGroup, weightUnit, heightUnit, dob;
	private int day, month, year, age, genderInInt = 1;
	private double height, weight, heightInCms, heightInMtrs, weightInKgs, bmi;

	private static final String PACKAGE = "com.uptop.healthup";
	private static final String ADVANCED_PROFILE = "com.uptop.healthup.AdvancedProfile";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		nameEt = (EditText) findViewById(R.id.name);
		heightEt = (EditText) findViewById(R.id.height);
		weightEt = (EditText) findViewById(R.id.weight);
		radioSexGroup = (RadioGroup) findViewById(R.id.radioGender);
		saveBtn = (Button) findViewById(R.id.save);
		daySpinner = (Spinner) findViewById(R.id.day);
		monthSpinner = (Spinner) findViewById(R.id.month);
		yearSpinner = (Spinner) findViewById(R.id.year);
		weightUnitSpinner = (Spinner) findViewById(R.id.weight_unit);
		heightUnitSpinner = (Spinner) findViewById(R.id.height_unit);
		bloodGroupSpinner = (Spinner) findViewById(R.id.blood_group);
		primitiveProfileTv = (TextView) findViewById(R.id.primitive_profile_tv);

		dbAdapter = new DatabaseAdapter(getApplicationContext());

		final String PRIMITIVE_PROFILE = " Primitive Profile ";

		primitiveProfileTv.setBackgroundColor(getResources().getColor(
				R.color.green));
		primitiveProfileTv.setText(PRIMITIVE_PROFILE);

		saveBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				name = nameEt.getText().toString();
				height = Double.parseDouble(heightEt.getText().toString());
				weight = Double.parseDouble(weightEt.getText().toString());

				int selectedId = radioSexGroup.getCheckedRadioButtonId();
				radioSexButton = (RadioButton) findViewById(selectedId);
				gender = radioSexButton.getText().toString();

				if (gender.compareTo("Female") == 0)
					genderInInt = 0;

				day = Integer.parseInt(String.valueOf(daySpinner
						.getSelectedItem()));
				month = Integer.parseInt(String.valueOf(monthSpinner
						.getSelectedItem()));
				year = Integer.parseInt(String.valueOf(yearSpinner
						.getSelectedItem()));
				heightUnit = String.valueOf(heightUnitSpinner.getSelectedItem());
				weightUnit = String.valueOf(weightUnitSpinner.getSelectedItem());
				bloodGroup = String.valueOf(bloodGroupSpinner.getSelectedItem());

				dob = String.valueOf(day) + "/" + String.valueOf(month) + "/"
						+ String.valueOf(year);

				heightInCms = height;
				weightInKgs = weight;

				if (heightUnit.compareTo("ft") == 0)
					heightInCms = heightInCms * 30.48;

				if (weightUnit.compareTo("lb") == 0)
					weightInKgs = weightInKgs * 0.453592;

				heightInMtrs = heightInCms * 0.01;
				bmi = (weightInKgs / (heightInMtrs * heightInMtrs));

				age = Calendar.getInstance().get(Calendar.YEAR) - year;
				if ((Calendar.getInstance().get(Calendar.MONTH) - month) < 0)
					age--;

				dbAdapter.open();
				dbAdapter.insertPrimitiveProfile(name, genderInInt, dob, age,
						heightInCms, weightInKgs, bloodGroup);
				dbAdapter.insertDiabetes(age, bmi, -1, -1, -1, -1, -1);
				dbAdapter.close();

				Intent intent = new Intent();
				intent.setClassName(PACKAGE, ADVANCED_PROFILE);
				startActivity(intent);
				finish();
			}
		});
	}

}
