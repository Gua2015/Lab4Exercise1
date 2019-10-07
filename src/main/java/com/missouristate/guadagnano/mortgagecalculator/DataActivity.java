package com.missouristate.guadagnano.mortgagecalculator;

//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(/*/@Nullable/*/ Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        updateDataActivityView();
    }

    private void updateDataActivityView() {
        Mortgage mortgage = MainActivity.mortgage;

        if (mortgage.getYears() == 10) {
            RadioButton rb10 = (RadioButton) findViewById(R.id.ten);
            rb10.setChecked(true);
        } else if (mortgage.getYears() == 15) {
            RadioButton rb15 = (RadioButton) findViewById(R.id.fifteen);
            rb15.setChecked(true);
        }

        EditText amountET = findViewById(R.id.data_amount);
        amountET.setText("" + mortgage.getAmount());
        EditText rateET = findViewById(R.id.data_rate);
        rateET.setText("" + mortgage.getRate());
    }


    private void updateMortgage() {
        Mortgage mortgage = MainActivity.mortgage;

        RadioButton rb10 = findViewById(R.id.ten);
        RadioButton rb15 = findViewById(R.id.fifteen);
        int years = 30;
        if (rb10.isChecked())
            years = 10;
        else if (rb15.isChecked())
            years = 15;
        mortgage.setYears(years);

        EditText amountET = findViewById(R.id.data_amount);
        String amountString = amountET.getText().toString();
        EditText rateET = findViewById(R.id.data_rate);
        String rateString = rateET.getText().toString();

        try {
            float amount = Float.parseFloat(amountString);
            mortgage.setAmount(amount);
            float rate = Float.parseFloat(rateString);
            mortgage.setRate(rate);
            mortgage.setPreferences(this);
        } catch (NumberFormatException nfo) {
            mortgage.setAmount(100000.0f);
            mortgage.setRate(.035f);
        }
    }

    public void goBack(View view) {
        updateMortgage();
        this.finish();
        overridePendingTransition(R.anim.fade_in_and_scale, 0);
    }
}
