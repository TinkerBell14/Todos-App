package com.example.sqllitedemo;

import android.content.Intent;
import android.widget.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anurashukla on 11/19/2015.
 */
public class Utility {
    public static Date getDateFromDatePicker(android.widget.DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public static Intent Share(SocialApps.Apps app, String Msg) {

        String appPackage = "";
        switch (app) {
            case WHATSAPP:
                appPackage = "com.whatsapp";
                break;
            default:
                appPackage = "com.whatsapp";
                break;

        }

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, Msg);
        sendIntent.setType("text/plain");
        sendIntent.setPackage(appPackage);
        return sendIntent;

    }
}



