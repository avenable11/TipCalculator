package edu.ivytech.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

  private EditText billAmountEditText;
  private TextView percentTextView;
  private TextView tipTextView;
  private TextView totalTextView;
  private float tipPercent = .15f;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    billAmountEditText = findViewById(R.id.billAmountEditText);
    percentTextView = findViewById(R.id.percentTextView);
    tipTextView = findViewById(R.id.tipTextView);
    totalTextView = findViewById(R.id.totalTextView);
  }

  public void calculateAndDisplay() {
    String billAmountString = billAmountEditText.getText().toString();
    float billAmount;
    if(billAmountString.equals("")) {
      billAmount = 0;
    }
    else {
      billAmount = Float.parseFloat(billAmountString);
    }
    float tipAmount = billAmount * tipPercent;
    float totalAmount = billAmount + tipAmount;

    NumberFormat currency = NumberFormat.getCurrencyInstance();
    tipTextView.setText(currency.format(tipAmount));
    totalTextView.setText(currency.format(totalAmount));

    NumberFormat percent = NumberFormat.getPercentInstance();
    percentTextView.setText(percent.format(tipPercent));

  }

  @Override
  public void onResume() {
    super.onResume();
    calculateAndDisplay();
  }

  public void onCalculateClick(View v) {
    calculateAndDisplay();
  }

  public void onPercentClick(View v) {
    switch (v.getId()) {
      case R.id.percentDownButton:
        tipPercent = tipPercent - .01f;
        break;
      case R.id.percentUpButton:
        tipPercent = tipPercent + .01f;
        break;
    }

    calculateAndDisplay();
  }
}
