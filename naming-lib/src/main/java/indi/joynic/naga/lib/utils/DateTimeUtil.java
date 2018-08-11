package indi.joynic.naga.lib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtil {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS";


    private static ThreadLocal<SimpleDateFormat> threadLocalJar = new ThreadLocal<SimpleDateFormat>();

    private static SimpleDateFormat getCurrentThreadLocalValue(String pattern)
            throws IllegalArgumentException {

        if (null == pattern || pattern.equals("")) {
            throw new IllegalArgumentException("Pattern mustn't be null!");
        }

        SimpleDateFormat currentValue;

        if (null == (currentValue = threadLocalJar.get()) || !pattern.equals(currentValue.toPattern())) {
            currentValue = new SimpleDateFormat(pattern, Locale.US);
            threadLocalJar.set(currentValue);
        }
        return currentValue;
    }

    public static String date(String pattern) {
        return date(System.currentTimeMillis(), pattern);
    }

    /**
     * return format date time
     *
     * @param timestamp 13-digit timestamp
     * @param pattern
     * @return
     */
    public static String date(long timestamp, String pattern) {

        if (null == pattern) {
            pattern = DEFAULT_PATTERN;
        }

        SimpleDateFormat df;
        try {
            df = getCurrentThreadLocalValue(pattern);
            Date date = new Date(timestamp);
            return df.format(date);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String date(Date date, String pattern) {
        if (null == pattern) {
            pattern = DEFAULT_PATTERN;
        }

        SimpleDateFormat df;
        try {
            df = getCurrentThreadLocalValue(pattern);
            return df.format(date);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get format datetime after {dayCount}
     *
     * @return
     */
    public static synchronized String dateTimeDaysLater(int dayCount, String pattern)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, dayCount);
        Date date = calendar.getTime();
        return date(date, pattern);
    }

    public static long getTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static double getTimeFloat() {
        return System.currentTimeMillis() / 1000D;
    }

    public static long getTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long strToTime(String str, String pattern) {
        long millisRet = strToTimeMillis(str, pattern);
        return -1 != millisRet ? millisRet / 1000 : -1;
    }

    public static long strToTimeMillis(String str, String pattern) {
        if (null == pattern) {
            pattern = DEFAULT_PATTERN;
        }

        SimpleDateFormat sf;
        try {
            sf = getCurrentThreadLocalValue(pattern);

            Date date = sf.parse(str);
            if (null != date) {
                return date.getTime();
            }
        } catch (IllegalArgumentException | ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static double strToTimeFloat(String str, String pattern) {
        long ret = strToTimeMillis(str, pattern);
        return -1 != ret ? ret / 1000D : -1;
    }

    /**
     * get today starting time
     *
     * @return 10 digit unix time
     */
    public static synchronized long getDayStartingTime() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);

        return cld.getTimeInMillis() / 1000;
    }

    /**
     * @param dateTimeStr date time of string representation like "2016-10-11 10:09:00"
     * @return -1 on failure or 10 digit long on success
     */
    public static long getDayStartingTime(String dateTimeStr, String pattern) {
        if (null == dateTimeStr) {
            throw new NullPointerException("dateTimeStr can not be null");
        }

        if (null == pattern) {
            pattern = DEFAULT_PATTERN;
        }

        long timeMillis = strToTimeMillis(dateTimeStr, pattern);
        if (-1 != timeMillis) {
            return getDayStartingTime(timeMillis);
        }

        return -1;
    }

    /**
     * get 10-digit Time representing the starting of the day,
     *
     * @param dateTime 10-digit or 13-digit time
     * @return 10-digit time
     */
    public static synchronized long getDayStartingTime(long dateTime) {
        if (1E11 <= dateTime) {
            dateTime = dateTime / 1000;
        }

        Calendar cld = Calendar.getInstance();
        cld.setTimeInMillis(dateTime * 1000);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);

        return cld.getTimeInMillis() / 1000;
    }

    public static boolean checkDate(String date, String pattern) {
        if (null == date || date.equals("")) {
            return false;
        }

        if (null == pattern || pattern.equals("")) {
            pattern = DEFAULT_DATE_PATTERN;
        }

        SimpleDateFormat sdf = getCurrentThreadLocalValue(pattern);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static String[] rangeDate(String startDate, String endDate, int step, String inputPattern, String outputPattern) {
        if (null == inputPattern || inputPattern.equals("")) {
            inputPattern = DEFAULT_DATE_PATTERN;
        }

        if (!checkDate(startDate, inputPattern) || !checkDate(endDate, inputPattern)) {
            return new String[0];
        }

        if (0 >= step) {
            return new String[0];
        }

        long startDateTimestamp = strToTime(startDate, inputPattern);
        long endDateTimestamp = strToTime(endDate, inputPattern);

        int timestampStep = 86400 * step;
        int dateCount = (int) ((endDateTimestamp - startDateTimestamp) / timestampStep);
        dateCount += 1;

        String[] rangeDate = new String[dateCount];
        for (int i = 0; i < dateCount; i ++) {
            rangeDate[i] = date((startDateTimestamp + i * timestampStep) * 1000L, outputPattern);
        }

        return rangeDate;
    }

    public static String[] rangeDate(String startDate, String endDate, int step) {
        return rangeDate(startDate, endDate, step, DEFAULT_DATE_PATTERN, DEFAULT_DATE_PATTERN);
    }

    public static String[] rangeDate(String startDate, String endDate, int step, String pattern) {
        return rangeDate(startDate, endDate, step, pattern, pattern);
    }

    private static String ensureString(String input, String defaultValue) {
        if (null == input || input.equals("")) {
            input = defaultValue;
        }

        return input;
    }

    public static boolean ensureFmtDate(String date, String pattern) {
        if (!checkDate(date, pattern)) {
            return false;
        }

        pattern = ensureString(pattern, DEFAULT_DATE_PATTERN);
        if (- 1 != strToTime(date, pattern)) {
            return true;
        }

        return false;
    }
}
