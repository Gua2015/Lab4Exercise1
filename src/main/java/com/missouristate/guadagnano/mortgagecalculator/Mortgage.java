package com.missouristate.guadagnano.mortgagecalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DecimalFormat;

public class Mortgage {

    private static final String PREFERENCE_AMOUNT = "amount";
    private static final String PREFERENCE_YEARS = "years";
    private static final String PREFERENCE_RATE = "rate";
    public final DecimalFormat MONEY = new DecimalFormat("#,##0.00");
    private float amount;
    private int years;
    private float rate;

    public Mortgage() {
        setAmount(100000.0f);
        setYears(30);
        setRate(0.035f);
    }
    public Mortgage(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences((context));
        setAmount(pref.getFloat(PREFERENCE_AMOUNT, 100000.0f));
        setYears(pref.getInt(PREFERENCE_YEARS, 30));
        setRate(pref.getFloat(PREFERENCE_RATE, .035f));

    }

    public void setAmount(float newAmount) {
        if (newAmount >= 0)
            amount = newAmount;
    }
    public void setYears(int newYears) {
        if (newYears >=0)
            years = newYears;
    }
    public void setRate(float newRate) {
        if (newRate >= 0)
            rate = newRate;
    }

    public float getAmount() {
        return amount;
    }
    public String getFormattedAmount() {
        return MONEY.format(amount);
    }
    public int getYears() {
        return years;
    }
    public float getRate() {
        return rate;
    }

    public float monthlyPayment() {
        //monthly interest rate
        float mRate = rate / 12;
        double temp = Math.pow(1 / (1 + mRate), years * 12);
        return amount * mRate / (float) (1 - temp);
    }

    public String formattedMonthlyPayment() {
        return MONEY.format(monthlyPayment());
    }
    public float totalPayment() {
        return monthlyPayment() * years * 12;
    }
    public String formattedTotalPayment() {
        return MONEY.format(totalPayment());
    }

    //write mortgage data
    public void setPreferences(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences((context));
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(PREFERENCE_AMOUNT, amount);
        editor.putInt(PREFERENCE_YEARS, years);
        editor.putFloat(PREFERENCE_RATE, rate);
        editor.commit();

    }

}
