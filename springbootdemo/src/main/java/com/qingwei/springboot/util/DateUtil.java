package com.qingwei.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kongwc on 2019/4/23.
 */
public class DateUtil {
    public static String getDateStr(Date date){
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    public static String getDateTimeStr(Date date){
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
