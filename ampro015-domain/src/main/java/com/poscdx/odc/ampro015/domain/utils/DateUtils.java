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
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String customtDateTime = "yyyy-MM-dd_HH:mm:ss";

    public static String defaultDateTime = "yyyy-MM-dd HH:mm:ss";
    public static String originalDate = "EEEEE MMMMM yyyy HH:mm.SSSZ";
    public static String simpleDate = "dd-MM-yyyy";

    /**
     * convertDateTimeToString
     * @author 202293 - Trieu Le
     *
     * @param date
     * @param format
     * @return
     * @author 202293 - Trieu Le
     */
    public static String convertDateTimeToString(Date date, String format) {
        DateFormat dateFormatter = new SimpleDateFormat(format);
        return dateFormatter.format(date);
    }

    /**
     * convertStringToDate
     *
     * @author 202293 - Trieu Le
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    /**
     * DateTypeAdapter
     *
     * @author 202293 - Trieu Le
     *
     * This one is used to convert date json of body request to Date (read(JsonReader in) method)
     * and convert datetime to string (write(JsonWriter out, Date value) method)
     */
    public static class DateTypeAdapter extends TypeAdapter<Date> {

        @Override
        public void write(JsonWriter out, Date value)  {
            try {
                out.value(convertDateTimeToString(value, defaultDateTime));
            } catch (IOException ioE) {
                logger.error("IOException - There is an exception when converting date to string: {}", ioE.getMessage());
            }
        }

        @Override
        public Date read(JsonReader in) {
            Date response = null;
            try {
                String dateString = in.nextString();
                response = convertStringToDate(dateString, defaultDateTime);
            } catch (ParseException pE) {
                logger.error("ParseException - There is an exception when parsing date: {}", pE.getMessage());
            } catch (IOException ioE) {
                logger.error("IOException - There is an exception when converting string to date: {}", ioE.getMessage());
            }
            return response;
        }
    }
}
