package com.example.bmicalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText heightEditText;
	private EditText weightEditText;
	private Button 	calcButton;
	private TextView result,result1;
	private RadioGroup selectGroup;
	private RadioButton engBtn, metBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		result = (TextView)findViewById(R.id.resultTextView);
		result1 = (TextView)findViewById(R.id.resultTextView1);
		heightEditText = (EditText)findViewById(R.id.heightEditText);
		weightEditText = (EditText)findViewById(R.id.weightEditText);
		selectGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		engBtn = (RadioButton)findViewById(R.id.engRadio);
		metBtn = (RadioButton)findViewById(R.id.metricRadio);
		calcButton = (Button)findViewById(R.id.calculateButton);
		
		calcButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double weight = Double.parseDouble(weightEditText.getText().toString());
				double height = Double.parseDouble(heightEditText.getText().toString());
				double bmi;
				if(engBtn.isChecked()){
					height = height * 12;	//feets to inches
					bmi = (weight*703)/(height*height);
				}
				else{
					height = height / 100;	//cms to meters
					bmi = (weight)/(height*height);
				}
				result.setText(String.valueOf(String.format("%.2f", bmi)));
				result1.setText(checkBmiCategory(bmi));
			}
		});
		selectGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.engRadio){
					heightEditText.setText("");
					weightEditText.setText("");
					result.setText("");
					result1.setText("");
					heightEditText.setHint("ft.");
					weightEditText.setHint("lb.");
				}
				else if(checkedId == R.id.metricRadio){
					heightEditText.setText("");
					weightEditText.setText("");
					result.setText("");
					result1.setText("");
					heightEditText.setHint("cm.");
					weightEditText.setHint("kg.");
				}
			}
		});
	}
	
	private String checkBmiCategory(double bmi){
		String category = "";
		if(bmi < 18.5){
			category = "Underweight";
		}
		else if(bmi >= 18.5 && bmi <= 24.9){
			category = "Normal";
		}
		else if(bmi >= 25 && bmi <= 29.9){
			category = "Overweight";
		}
		else if(bmi >= 30){
			category = "Obese";
		}
		return category;
		
	}

	
}
