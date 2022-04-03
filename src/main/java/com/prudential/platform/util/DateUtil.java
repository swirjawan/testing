/**
 * @author garryalfa
 */

package com.prudential.platform.util;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ApplicationScoped
public class DateUtil {

    public static String getCurrentTimestampString() {

        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    public static Timestamp getCurrentTimestampValue() {
        return new Timestamp((new Date()).getTime());
    }

    public static String localDateToDateStr(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }


}