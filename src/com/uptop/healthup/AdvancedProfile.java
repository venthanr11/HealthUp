package com.uptop.healthup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class AdvancedProfile extends Activity {

	private RadioGroup diabetesRadioGroup, bpRadioGroup, tobaccoRadioGroup,
			alcoholRadioGroup;
	private RadioButton diabetesRadioBtn, bpRadioBtn, tobaccoRadioBtn,
			alcoholRadioBtn;
	private Button saveBtn;
	private Spinner lifestyleSpinner, ethnicitySpinner;
	private TextView advancedProfileTv;

	private DatabaseAdapter dbAdapter;

	private String lifestyle, ethnicity, diabetes, bp, tobacco, alcohol;
	private int diabetesInInt = 0, bpInInt = 0, tobaccoInInt = 0,
			alcoholInInt = 0, selectedId, lifestyleInInt = 0,
			ethnicityInInt = 0;
	
	private static final String PACKAGE = "com.uptop.healthup";
	private static final String HOME = "com.uptop.healthup.Home";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advanced_profile);

		diabetesRadioGroup = (RadioGroup) findViewById(R.id.diabetes);
		bpRadioGroup = (RadioGroup) findViewById(R.id.bp);
		tobaccoRadioGroup = (RadioGroup) findViewById(R.id.tobacco);
		alcoholRadioGroup = (RadioGroup) findViewById(R.id.alcohol);
		saveBtn = (Button) findViewById(R.id.save);
		lifestyleSpinner = (Spinner) findViewById(R.id.lifestyle);
		ethnicitySpinner = (Spinner) findViewById(R.id.ethnicity);
		diabetesRadioGroup = (RadioGroup) findViewById(R.id.diabetes);
		bpRadioGroup = (RadioGroup) findViewById(R.id.bp);
		tobaccoRadioGroup = (RadioGroup) findViewById(R.id.tobacco);
		alcoholRadioGroup = (RadioGroup) findViewById(R.id.alcohol);
		advancedProfileTv = (TextView) findViewById(R.id.advanced_profile_tv);

		dbAdapter = new DatabaseAdapter(getApplicationContext());

		final String ADVANCED_PROFILE = " Advanced Profile ";

		advancedProfileTv.setBackgroundColor(getResources().getColor(
				R.color.green));
		advancedProfileTv.setText(ADVANCED_PROFILE);

		saveBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				lifestyle = String.valueOf(lifestyleSpinner.getSelectedItem());
				ethnicity = String.valueOf(ethnicitySpinner.getSelectedItem());

				if (lifestyle.compareTo("Lightly Active") == 0)
					lifestyleInInt = 1;
				else if (lifestyle.compareTo("Moderately Active") == 0)
					lifestyleInInt = 2;
				else if (lifestyle.compareTo("Very Active") == 0)
					lifestyleInInt = 3;
				else if (lifestyle.compareTo("Extra Active") == 0)
					lifestyleInInt = 4;

				if (ethnicity.compareTo("European") == 0)
					ethnicityInInt = 0;
				else if (ethnicity.compareTo("Black") == 0)
					ethnicityInInt = 1;
				else if (ethnicity.compareTo("South Asian") == 0)
					ethnicityInInt = 2;
				else if (ethnicity.compareTo("Chinese") == 0)
					ethnicityInInt = 2;

				selectedId = diabetesRadioGroup.getCheckedRadioButtonId();
				diabetesRadioBtn = (RadioButton) findViewById(selectedId);
				diabetes = diabetesRadioBtn.getText().toString();

				if (diabetes.compareTo("Yes") == 0)
					diabetesInInt = 1;

				selectedId = bpRadioGroup.getCheckedRadioButtonId();
				bpRadioBtn = (RadioButton) findViewById(selectedId);
				bp = bpRadioBtn.getText().toString();

				if (bp.compareTo("Yes") == 0)
					bpInInt = 1;

				selectedId = tobaccoRadioGroup.getCheckedRadioButtonId();
				tobaccoRadioBtn = (RadioButton) findViewById(selectedId);
				tobacco = tobaccoRadioBtn.getText().toString();

				if (tobacco.compareTo("Yes") == 0)
					tobaccoInInt = 1;

				selectedId = alcoholRadioGroup.getCheckedRadioButtonId();
				alcoholRadioBtn = (RadioButton) findViewById(selectedId);
				alcohol = alcoholRadioBtn.getText().toString();

				if (alcohol.compareTo("Yes") == 0)
					alcoholInInt = 1;

				dbAdapter.open();
				dbAdapter.insertAdvancedProfile(lifestyleInInt, ethnicityInInt,
						diabetesInInt, bpInInt, tobaccoInInt, alcoholInInt);
				dbAdapter.updateDiabetesTable(lifestyleInInt, ethnicityInInt,
						bpInInt, tobaccoInInt, alcoholInInt);
				dbAdapter.close();
				
				Intent intent = new Intent();
				intent.setClassName(PACKAGE, HOME);
				startActivity(intent);
			}

		});
	}
}
