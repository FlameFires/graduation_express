package com.express.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static java.sql.Date strToDate(String strDate) {
        if(strDate == null || strDate.length() < 1)
            return null;
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    public static Timestamp strToStamp(String strDate){
        Timestamp ts = null;
        try {
            if(strDate == null || strDate.strip().length() < 1){
                return ts;
            }
            var date = strToDate(strDate);
            ts = new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    public static String stampToStr(Timestamp timestamp,String formatter){
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        var str = format.format(timestamp);
        return str;
    }
}
