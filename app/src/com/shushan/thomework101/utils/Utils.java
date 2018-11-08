package com.shushan.thomework101.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static DisplayMetrics getScreenWH(Context context) {
        DisplayMetrics dMetrics = new DisplayMetrics();
        dMetrics = context.getResources().getDisplayMetrics();
        return dMetrics;
    }

    public static float dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);

    }

    /* 将U字符转化为其表示的字符串, 如： "\\u55\\u5b57\\u7b26" -> "U字符" ;按\\u分割，依次转化为对应字符*/
    public static String UStr_2_Str(String Ustr0) {
        String Ustr = Ustr0;

        int S = 0, E = 0;
        String C = "", Value = "";

        while (Ustr.contains("\\u")) {
            S = Ustr.indexOf("\\u") + "\\u".length();
            E = Ustr.indexOf("\\u", S);
            if (E == -1) E = Ustr.length();

            if (E > S) {
                C = Ustr.substring(S, E);
                if (C.length() > 4) C = C.substring(0, 4);
                Value = (char) Integer.parseInt(C, 16) + "";

                Ustr = Ustr.replace("\\u" + C, Value);
            }
        }

        return Ustr;
    }

    /**
     * 将字符串转成MD5值
     *
     * @param string 需要转换的字符串
     * @return 字符串的MD5值
     */
    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType) throws ParseException {
        Date date = longToDate(currentTime, formatType);
        // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss
    // yyyy年MM月dd日 // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss
    // yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType) throws ParseException {
        Date dateOld = new Date(currentTime);
        // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType);
        // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType);
        // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

}

