package edu.chl.leep.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eli on 2017-05-03.
 *
 * A class which converts longs to string to a format that we requires and the opposite
 */

public class ConvertUtils {
    private final static long MILLIS_TO_MINUTES = 60000;
    private final static long MILLIS_TO_HOURS = 3600000;
    Date date = new Date();

    public ConvertUtils(){
    }

    public String calculateTimeToString(long curTimeMillis){

        if(curTimeMillis >= 359999000){
            return "99:59:59";
        }
        int seconds = (int) ((curTimeMillis / 1000) % 60);
        int minutes = (int) ((curTimeMillis / MILLIS_TO_MINUTES) % 60);
        int hours = (int) ((curTimeMillis / MILLIS_TO_HOURS));

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public String calculateDateToString(Date date){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format(date);

        return str;
    }

    public String timeMillisToString (long milliseconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date result = new Date(milliseconds);
        return sdf.format(result);
    }

    public String calculateYearToString(){
        DateFormat dateFormatYear = new SimpleDateFormat("yyyy");

        return dateFormatYear.format(date);
    }

    public String calculateMonthToString(){
        DateFormat dateFormatMonth = new SimpleDateFormat("MM");

        return dateFormatMonth.format(date);
    }

    public String calculateDayToString(){
        DateFormat dateFormatDay = new SimpleDateFormat("dd");

        return dateFormatDay.format(date);
    }

    public String longToString(long longNumber){
        String longAsString = Long.toString(longNumber);
        return longAsString;
    }

}

