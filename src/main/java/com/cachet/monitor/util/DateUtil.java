package com.cachet.monitor.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vins on 06/02/2018.
 */
public class DateUtil {

    public static String convertToString(Date date){
        if (date == null)
            return null;
        return new SimpleDateFormat("dd/MM/yyyy").format(date);

    }
}
