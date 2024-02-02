package com.poscdx.odc.ampro015.domain.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtils.java
 *
 * @author 202293 - Trieu Le
 */
public class DateUtils {

    public static String customtDateTime = "yyyy-MM-dd_HH:mm:ss";
    public static String defaultDateTime = "yyyy-MM-dd HH:mm:ss";
    public static String originalDate = "EEEEE MMMMM yyyy HH:mm.SSSZ";
    public static String simpleDate = "dd-MM-yyyy";
    public static String dbDate = "yyyy-MM-dd";

    /**
     * convertDateTimeToString
     *
     * @param date
     * @param format
     * @return
     * @author 202293 - Trieu Le
     * @author 202293 - Trieu Le
     */
    public static String convertDateTimeToString(Date date, String format) {
        DateFormat dateFormatter = new SimpleDateFormat(format);
        return date != null ? dateFormatter.format(date) : null;
    }

    /**
     * convertStringToDate
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     * @author 202293 - Trieu Le
     */
    public static Date convertStringToDate(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    /**
     * DateTypeAdapter
     *
     * @author 202293 - Trieu Le
     * <p>
     * This one is used to convert date json of body request to Date (read(JsonReader in) method)
     * and convert datetime to string (write(JsonWriter out, Date value) method)
     */
    public static class DateTypeAdapter extends TypeAdapter<Date> {

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            out.value(convertDateTimeToString(value, defaultDateTime));
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            String dateString = in.nextString();
            try {
                return convertStringToDate(dateString, defaultDateTime);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
