package com.lppz.ehr.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtils {
    public final static String defaultPattern = "yyyy-MM-dd";
    public final static String ehrDatePattern = "yyyy.MM.dd";
    public final static String ehrTimeHMSPattern = "HH:mm:ss";
    public final static String sapDatePattern = "yyyyMMdd";
    public final static String sapTimeHMSPattern = "HHmmss";
    public final static String defaultTimeHMSPattern = "HH:mm:ss";
    public final static String dateTimePattern = "yyyy-MM-dd HH:mm";
    private final static String dateMonthHourPattern = "M月d日HH:mm";
    public final static String dateMonthPattern = "yyyy年MM月dd日";
    public final static String dateTimeSecondPattern = "yyyy-MM-dd HH:mm:ss";
    public final static String dateTimeYNDPattern = "yyyy年MM月dd日HH点mm分";
    public final static String dateTimeYNDHMSPattern = "yyyyMMddHHmmss";
    public final static String dateYNDPattern = "yyyyMMdd";
    public final static String dateYNDPattern2 = "yyyyMM";
    public final static String dateYNDPattern3 = "yyyy-MM";
    private final static ThreadLocal<HashMap<String, SimpleDateFormat>> customerMapThread = new
            ThreadLocal<HashMap<String,
                    SimpleDateFormat>>();
    
    public static Calendar getCalendar() {
    	return Calendar.getInstance();
    }
    
    /**
     * Description: 获取当前时间的毫秒
     * @Version1.0 2015-2-26 下午2:28:55 by 崔吉祥
     * @return
     */
    public static long getNowTime() {
    	return getCalendar().getTimeInMillis();
    }
    
    /**
     * Description: 获取当前日期
     * @Version1.0 2015-2-26 下午2:30:19 by 崔吉祥
     * @return
     */
    public static Date getNowDate() {
    	return getCalendar().getTime();
    }
    
    public static int getNowYear() {
    	return getCalendar().get(Calendar.YEAR);
    }
    
    public static String getNowMonth() {
    	Integer month = getCalendar().get(Calendar.MONTH)+1;
    	return  (month<10?("0" + month.toString()):(month.toString()));
    }
    
    public static int getNowDay() {
    	return getCalendar().get(Calendar.DATE);
    }
    
    /**
     * Description: 获取给定格式的时间
     * @Version1.0 2015-2-26 下午2:44:35 by 崔吉祥
     * @param pattern
     * @return
     */
    public static String getFormateDate(String pattern) {
    	if(StringUtil.isEmpty(pattern)) {
    		pattern = defaultPattern;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(getNowDate());
    }
    
    /**
     * Description: 获取默认格式的时间
     * @Version1.0 2015-2-26 下午2:45:08 by 崔吉祥
     * @return
     */
    public static String getDefaultFormateDate() {
    	
    	return getFormateDate(defaultPattern);
    }

    /**
     * 根据传入字符串 返回yyyy年mm月dd日,月份日期为单位时显示为双位
     */
    public static String getCurrentDateToCNWhole(String dateTime) {

        String year = dateTime.substring(0, 4);
        String month;
        String day;

        month = dateTime.substring(5, 7);
        day = dateTime.substring(8, 10);
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * Description: 将日期字符串转换成日期型
     *
     * @param dateStr
     * @return
     * @Version1.0 2012-11-5 上午08:50:21 by 崔吉祥
     */
    public static Date dateString2Date(String dateStr) {
        return dateString2Date(dateStr, defaultPattern);
    }

    /**
     * Description: 将日期字符串转换成指定格式日期
     *
     * @param dateStr
     * @param partner
     * @return
     * @Version1.0 2012-11-5 上午08:50:55 by 崔吉祥
     */
    public static Date dateString2Date(String dateStr, String partner) {

        try {
            SimpleDateFormat formatter = getSimpleDateFormat(partner);
            ParsePosition pos = new ParsePosition(0);
            return formatter.parse(dateStr, pos);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 获取指定日期的年份
     *
     * @param p_date util.Date日期
     * @return int 年份
     */
    public static int getYearOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.YEAR);
    }

    /**
     * Description: 获取日期字符串的年份
     *
     * @param p_date 字符串日期
     * @return int 年份
     * @Version1.0 2012-11-5 上午08:51:51 by 崔吉祥
     */
    public static int getYearOfDate(String p_date) {
        return getYearOfDate(dateString2Date(p_date));
    }

    /**
     * Description: 获取指定日期的月份
     *
     * @param p_date java.util.Date
     * @return int 月份
     * @Version1.0 2012-11-5 上午08:52:14 by 崔吉祥
     */
    public static int getMonthOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * Description: 获取日期字符串的月份
     *
     * @param date 字符串日期
     * @return int 月份
     * @Version1.0 2012-11-5 上午08:53:22 by 崔吉祥
     */
    public static int getMonthOfDate(String date) {
        return getMonthOfDate(dateString2Date(date));
    }

    /**
     * 获取指定日期的日份
     *
     * @param p_date util.Date日期
     * @return int 日份
     */
    public static int getDayOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期的周 与 Date .getDay()兼容
     *
     * @param date String 日期
     * @return int 周
     */
    public static int getWeekOfDate(String date) {
        Date p_date = dateString2Date(date);
        return getWeekOfDate(p_date);
    }

    /**
     * 获取指定日期的周 与 Date .getDay()兼容
     *
     * @param date util.Date日期
     * @return int 周
     */
    public static int getWeekOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取指定日期的小时
     *
     * @param p_date util.Date日期
     * @return int 日份
     */
    public static int getHourOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定日期的分钟
     *
     * @param p_date util.Date日期
     * @return int 分钟
     */
    public static int getMinuteOfDate(Date p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(p_date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * Description: 日期转化指定格式的字符串型日期
     *
     * @param p_utilDate java.util.Date
     * @param p_format   日期格式
     * @return 字符串格式日期
     * @Version1.0 2012-11-5 上午08:58:44 by 崔吉祥
     */
    public static String date2String(
            Date p_utilDate, String p_format) {
        String l_result = "";
        if (p_utilDate != null) {
            SimpleDateFormat sdf = getSimpleDateFormat(p_format);
            l_result = sdf.format(p_utilDate);
        }
        return l_result;
    }

    /**
     * Description: 日期转化指定格式的字符串型日期
     *
     * @param p_utilDate java.util.Date
     * @return 字符串格式日期
     * @Version1.0 2012-11-5 上午08:58:58 by 崔吉祥
     */
    public static String date2String(
            Date p_utilDate) {
        return date2String(p_utilDate, defaultPattern);
    }

    public static String date2StringTime(
            Date p_utilDate) {
        return date2String(p_utilDate, dateTimeSecondPattern);
    }

    /**
     *
     * <p>方法说明:
     * <p>TODO 时间字符转换20150101 转 2015-01-01
     *
     * @param str
     * @return
     */
    public static String strFormatStr(String str){
    	if(str.length() == 8){
    		String year = str.substring(0, 4);
    		String month = str.substring(4, 6);
    		String day = str.substring(6, 8);
    		return year + "-" + month + "-" + day;
    	}
    	return null;
    }
    /**
     *
     * <p>方法说明:
     * <p>TODO 时间字符转换20150101 转 2015.01.01
     *
     * @param str
     * @return
     */
    public static String strFormatStrPoint(String str){
    	if(str.length() == 8){
    		String year = str.substring(0, 4);
    		String month = str.substring(4, 6);
    		String day = str.substring(6, 8);
    		return year + "." + month + "." + day;
    	}
    	return null;
    }
    /**
     * 获取yyyyMMdd格式的当前时间
     * @return
     */
    public static String getNowDateStr(){
    	return date2String(getNowDate(),dateYNDPattern);
    }
    /**
     *
   	 * 获取yyyyMM格式的当前时间
     * @return
     */
    public static String getNowDateStr4(){
    	return date2String(getNowDate(),dateYNDPattern2);
    }
    /**
     *
     * 获取yyyy-MM格式的当前时间
     * @return
     */
    public static String getNowDateStr5(){
    	return date2String(getNowDate(),dateYNDPattern3);
    }
    /**
     *
     * 根据所给格式获取当前时间，默认为yyyyMMdd
     * @return
     */
    public static String getNowDateStrByPattern(String Pattern){
    	if(StringUtil.isEmpty(Pattern)){
    		Pattern=dateYNDPattern;
    	}
    	return date2String(getNowDate(),Pattern);
    }
    /**
     * 获取默认yyyy-MM-dd格式的当前时间
     * @return
     */
    public static String getNowDateStr2(){
    	return date2String(getNowDate(),dateTimeYNDHMSPattern);
    }
    /**
     * 获取默认yyyy-MM-dd格式的当前时间
     * @return
     */
    public static String getNowDateStr3(){
    	return date2String(getNowDate(),defaultPattern);
    }
    /**
     * Description: 时间计算(根据时间推算另个日期)
     *
     * @param date  日期
     * @param type  类型 y,M,d,h,m,s 年、月、日、时、分、秒
     * @param value 添加值
     * @return
     * @Version1.0 2012-4-12 下午12:59:39 by 王星皓（albertwxh@gmail.com）创建
     */
    public static Date dateAdd(Date date, String type, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (type.toLowerCase().equals("y") || type.toLowerCase().equals("year"))
            c.add(Calendar.YEAR, value);
        else if (type.equals("M") || type.toLowerCase().equals("month"))
            c.add(Calendar.MONTH, value);
        else if (type.toLowerCase().equals("d") || type.toLowerCase().equals("date"))
            c.add(Calendar.DATE, value);
        else if (type.toLowerCase().equals("h") || type.toLowerCase().equals("hour"))
            c.add(Calendar.HOUR, value);
        else if (type.equals("m") || type.toLowerCase().equals("minute"))
            c.add(Calendar.MINUTE, value);
        else if (type.toLowerCase().equals("s") || type.toLowerCase().equals("second"))
            c.add(Calendar.SECOND, value);
        return c.getTime();
    }

    /**
     * Description:
     *
     * @param date
     * @param type
     * @param value
     * @return
     * @Version1.0 2012-11-5 上午09:39:21 by 崔吉祥
     */
    public static Date dateAdd2Date(Date date, String type, int value) {
        return dateAdd(date, type, value);
    }

    /**
     * Description:
     *
     * @param dateStr
     * @param type
     * @param value
     * @param pattern
     * @return
     * @Version1.0 2012-11-5 上午09:18:13 by 崔吉祥
     */
    public static Date dateAdd2Date(String dateStr, String type, int value, String pattern) {
        Date date = DateUtils.dateString2Date(dateStr, pattern);
        return dateAdd(date, type, value);

    }

    /**
     * Description:
     *
     * @param dateStr
     * @param type
     * @param value
     * @return
     * @Version1.0 2012-11-5 上午09:19:59 by 崔吉祥
     */
    public static Date dateAdd2Date(String dateStr, String type, int value) {
        Date date = DateUtils.dateString2Date(dateStr, DateUtils.defaultPattern);
        return dateAdd(date, type, value);

    }

    /**
     * Description:
     *
     * @param date
     * @param type
     * @param value
     * @return
     * @Version1.0 2012-11-5 上午09:43:47 by 崔吉祥
     */
    public static String dateAdd2String(Date date, String type, int value) {
        Date dateD = dateAdd2Date(date, type, value);
        return date2String(dateD);
    }

    /**
     * Description:
     *
     * @param date
     * @param type
     * @param value
     * @param pattern
     * @return
     * @Version1.0 2012-11-5 上午10:01:50 by 崔吉祥
     */
    public static String dateAdd2String(Date date, String type, int value, String pattern) {
        Date dateD = dateAdd2Date(date, type, value);
        return date2String(dateD, pattern);
    }

    /**
     * Description:
     *
     * @param dateStr
     * @param type
     * @param value
     * @param pattern
     * @return
     * @Version1.0 2012-11-5 上午09:43:24 by 崔吉祥
     */
    public static String dateAdd2String(String dateStr, String type, int value, String pattern) {
        Date date = dateAdd2Date(dateStr, type, value, pattern);
        return date2String(date);
    }

    /**
     * Description:
     *
     * @param dateStr
     * @param type
     * @param value
     * @return
     * @Version1.0 2012-11-5 上午09:42:12 by 崔吉祥
     */
    public static String dateAdd2String(String dateStr, String type, int value) {
        Date date = dateAdd2Date(dateStr, type, value);
        return date2String(date);
    }

    public static String dateAdd2String(String dateStr, int value) {
        return dateAdd2String(dateStr, "d", value);
    }

    /**
     * Description:
     *
     * @param dateStr
     * @param isAddDay
     * @return
     * @Version1.0 2012-11-5 上午10:19:24 by 崔吉祥
     */
    public static String dateAdd2String(String dateStr, boolean isAddDay) {
        String returndateStr = dateStr;
        try {
            if (isAddDay) {
                dateStr = dateAdd2String(dateStr, "d", 1);
            }
            Date date = dateString2Date(dateStr);
            int month = getMonthOfDate(date);
            int day = getDayOfDate(date);
            returndateStr = month + "." + day;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returndateStr;
    }

    /**
     * Description:
     *
     * @param dateStr
     * @return
     * @Version1.0 2012-11-5 上午10:23:46 by 崔吉祥
     */
    public static String dateAdd2String(String dateStr) {
        return dateAdd2String(dateStr, false);
    }

    /**
     * Description:
     *
     * @param dateStr
     * @param type
     * @param value
     * @param pattern
     * @return
     * @Version1.0 2012-11-5 上午09:43:24 by 崔吉祥
     */
    public static String dateAdd2PatternString(String dateStr, String type, int value, String pattern) {
        Date date = dateAdd2Date(dateStr, type, value, pattern);
        return date2String(date, pattern);
    }

    /**
     * @param @param  p_date
     * @param @return
     * @return boolean
     * @throws
     * @Title: checkWeekendDay
     * @Description: 判断是平时还是周末
     */

    public static boolean checkWeekendDay(String p_date) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateString2Date(p_date));
        int num = c.get(Calendar.DAY_OF_WEEK);

        //如果为周六 周日则为周末  1星期天 2为星期六
        return num == 6 || num == 7 || num == 1;
    }

    /**
     * @param @param  startTime
     * @param @param  endTime
     * @param @return
     * @param @throws ParseException
     * @return String[][]
     * @throws
     * @Title: getMonthsByTime
     * @Description: 按时间段计算月份跨度  计算出所包含的月份
     */
    @SuppressWarnings("static-access")
    public static int[][] getMonthsByTime(String startTime, String endTime) {
        Date st;
        Date et;

        try {
            et = getSimpleDateFormat(defaultPattern).parse(endTime);
            st = getSimpleDateFormat(defaultPattern).parse(startTime);
        } catch (ParseException e) {
            return null;
        }


        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(st);
        ca2.setTime(et);

        int ca1Year = ca1.get(Calendar.YEAR);
        int ca1Month = ca1.get(Calendar.MONTH);

        int ca2Year = ca2.get(Calendar.YEAR);
        int ca2Month = ca2.get(Calendar.MONTH);
        int countMonth;//这个是用来计算得到有多少个月时间的一个整数,
        if (ca1Year == ca2Year) {
            countMonth = ca2Month - ca1Month;
        } else {
            countMonth = (ca2Year - ca1Year) * 12 + (ca2Month - ca1Month);
        }

        int months[][] = new int[countMonth + 1][2];        //年月日二维数组

        for (int i = 0; i < countMonth + 1; i++) {
            //每次在原基础上累加一个月

            months[i][0] = ca1.get(Calendar.YEAR);
            months[i][1] = ca1.get(Calendar.MONTH);
            months[i][1] += 1;
            ca1.add(ca1.MONTH, 1);
        }

        return months;
    }

    /**
     * yyyy-MM-dd HH:mm 格式日期 转化 为 M月d日HH:mm 格式日期
     *
     * @param date String
     * @return String
     */
    public static String string2String(String date) throws ParseException {
        return date2String(dateString2Date(date, dateTimePattern), dateMonthHourPattern);
    }

    /**
     * Description:
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     * @Version1.0 2012-11-9 上午10:57:30 by 崔吉祥
     */
    public static String string2String(String date, String pattern) throws ParseException {
        return date2String(dateString2Date(date), pattern);
    }

    /**
     * Description: 得到两个时间差
     *
     * @param startTime 开始时间
     * @param toTime    结束时间
     * @param pattern   日期格式字符串
     * @return long 时间差
     * @Version1.0 2012-11-5 上午09:04:45 by 崔吉祥
     */
    public static long getDateDiff(String startTime, String toTime, String pattern) {
        long diff = getDateDiffLong(startTime, toTime, pattern);
        diff = diff / 1000 / 60;
        return diff;
    }

    /**
     * Description:
     *
     * @param startTime
     * @param toTime
     * @param pattern
     * @return
     * @Version1.0 2012-11-9 上午10:25:23 by 崔吉祥
     */
    public static long getDateDiffLong(String startTime, String toTime, String pattern) {
        long diff = 0l;
        if (StringUtil.isNotEmpty(startTime) && StringUtil.isNotEmpty(toTime)) {
            SimpleDateFormat format = getSimpleDateFormat(pattern);
            ParsePosition pos = new ParsePosition(0);
            Date startTimeD = format.parse(startTime, pos);
            pos.setIndex(0);
            Date toTimeD = format.parse(toTime, pos);
            if (startTimeD != null && toTimeD != null) {
                diff = startTimeD.getTime() - toTimeD.getTime();
            }
        }
        return diff;
    }

    /**
     * Description: 得到两个时间差
     *
     * @param startTime 开始时间
     * @param toTime    结束时间
     * @return long 时间差
     * @Version1.0 2012-11-5 上午09:05:27 by 崔吉祥
     */
    public static long getDateDiff(String startTime, String toTime) {
        return getDateDiff(startTime, toTime, dateTimePattern);
    }

    /**
     * Description: 得到两个时间差
     *
     * @param startTimeD 开始时间
     * @param toTime     结束时间
     * @param pattern    日期格式字符串
     * @return long 时间差
     * @Version1.0 2012-11-5 上午09:09:34 by 崔吉祥
     */
    public static long getDateDiff(Date startTimeD, String toTime, String pattern) {
        long diff;
        Date toTimeD = dateString2Date(toTime, pattern);
        diff = startTimeD.getTime() - toTimeD.getTime();
        return diff;
    }

    /**
     * Description:
     *
     * @param hour
     * @param minute
     * @return
     * @Version1.0 2012-11-5 上午10:26:46 by 崔吉祥
     */
    public static Integer getMinuteTotal(String hour, String minute) {
        return getMinuteTotal(Integer.parseInt(hour), Integer.parseInt(minute));
    }

    /**
     * Description:
     *
     * @param hour
     * @param minute
     * @return
     * @Version1.0 2012-11-5 上午10:26:50 by 崔吉祥
     */
    public static Integer getMinuteTotal(Integer hour, Integer minute) {
        return hour * 60 + minute;
    }

    /**
     * Description:
     *
     * @param leaseTime
     * @param leaseDays
     * @return
     * @Version1.0 2012-11-5 上午10:27:25 by 崔吉祥
     */
    public static String[] getallyearMonth(Date leaseTime, int leaseDays) {
        List<String> yearList = new ArrayList<String>();
        List<String> monthList = new ArrayList<String>();
        String yearString;
        String monthString;
        StringBuilder dateString = new StringBuilder();
        StringBuilder sBuffer = new StringBuilder();
        String[] returnResult = new String[3];
        for (int i = 0; i < leaseDays; i++) {
            String correctDate = DateUtils.dateAdd2String(leaseTime, "d", i);
            String year = correctDate.split("-")[0];
            String month = correctDate.split("-")[1];
            if (!yearList.contains(year))
                yearList.add(year);
            if (!monthList.contains(month))
                monthList.add(month);
            if (i == leaseDays - 1)
                dateString.append(correctDate);
            else
                dateString.append(correctDate).append(",");

        }
        for (String month : monthList) {
            sBuffer.append(month).append(",");
        }
        monthString = sBuffer.toString();
        sBuffer.delete(0, sBuffer.length());
        for (String year : yearList) {
            sBuffer.append(year).append(",");
        }
        yearString = sBuffer.toString();
        if (monthString.lastIndexOf(",") == monthString.length() - 1)
            monthString = monthString.substring(0, monthString.length() - 1);
        if (yearString.lastIndexOf(",") == yearString.length() - 1)
            yearString = yearString.substring(0, yearString.length() - 1);
        returnResult[0] = yearString;
        returnResult[1] = monthString;
        returnResult[2] = dateString.toString();
        return returnResult;
    }

    private static SimpleDateFormat getSimpleDateFormat(String pattern) {
        SimpleDateFormat simpleDateFormat;
        HashMap<String, SimpleDateFormat> simpleDateFormatMap = customerMapThread.get();
        if (simpleDateFormatMap != null && simpleDateFormatMap.containsKey(pattern)) {
            simpleDateFormat = simpleDateFormatMap.get(pattern);
        } else {
            simpleDateFormat = new SimpleDateFormat(pattern);
            if (simpleDateFormatMap == null) {
                simpleDateFormatMap = new HashMap<String, SimpleDateFormat>();
            }
            simpleDateFormatMap.put(pattern, simpleDateFormat);
            customerMapThread.set(simpleDateFormatMap);
        }

        return simpleDateFormat;
    }





    /**
     * 获得指定日期及指定天数之内的所有日期列表
     *
     * @param pDate 指定日期 格式:yyyy-MM-dd
     * @param count 取指定日期后的count天
     * @return
     * @throws ParseException
     */
    public static Vector<String> getDatePeriodDay(String pDate, int count)
            throws ParseException {
        Vector<String> v = new Vector<String>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.dateString2Date(pDate));
        v.add(DateUtils.date2String(calendar.getTime()));

        for (int i = 0; i < count - 1; i++) {
            calendar.add(Calendar.DATE, 1);
            v.add(DateUtils.date2String(calendar.getTime()));
        }

        return v;
    }

    /**
     * 获得指定日期内的所有日期列表
     *
     * @param sDate 指定开始日期 格式:yyyy-MM-dd
     * @param sDate 指定开始日期 格式:yyyy-MM-dd
     * @return String[]
     * @throws ParseException
     */
    public static String[] getDatePeriodDay(String sDate, String eDate)
            throws ParseException {
        if (dateCompare(sDate, eDate)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar_ = Calendar.getInstance();
        calendar.setTime(DateUtils.dateString2Date(sDate));
        long l1 = calendar.getTimeInMillis();
        calendar_.setTime(DateUtils.dateString2Date(eDate));
        long l2 = calendar_.getTimeInMillis();
        // 计算天数
        long days = (l2 - l1) / (24 * 60 * 60 * 1000) + 1;

        String[] dates = new String[(int) days];
        dates[0] = (DateUtils.date2String(calendar.getTime()));
        for (int i = 1; i < days; i++) {
            calendar.add(Calendar.DATE, 1);
            dates[i] = (DateUtils.date2String(calendar.getTime()));
        }
        return dates;
    }
    public static List<String> getDatePeriodDayList(String sDate, String eDate){
    	List<String> list = null;
    	try {
    		String[] dateArray = getDatePeriodDay(sDate,eDate);
    		if(dateArray != null && dateArray.length > 0){
    			list = new ArrayList<String>();
    			for (String date : dateArray) {
    				list.add(date);
				}
    		}
		} catch (Exception e) {

		}
    	return list;
    }
    /**
     * 比较日期大小
     *
     * @param compareDate
     * @param toCompareDate
     * @return
     */

    public static boolean dateCompare(String compareDate, String toCompareDate) {
        boolean comResult = false;
        Date comDate = DateUtils.dateString2Date(compareDate);
        Date toComDate = DateUtils.dateString2Date(toCompareDate);

        if (comDate.after(toComDate)) {
            comResult = true;
        }


        return comResult;
    }
    /**
     * @Title getMaxDaysByMonth
     * @Description TODO【根据yyyyMM 获取当月最大天数】
     * @param date
     * @return
     * @Author cui jixiang
     * @Date 2016年1月27日 下午2:59:01
     * @Return int 返回类型
     * @Throws
     */
    public static int getMaxDaysByMonth(String date){
    	 Calendar c = Calendar.getInstance();
         c.setTime(dateString2Date(date,"yyyyMM"));
         return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @Title getweekByday
     * @Description TODO【这里用一句话描述这个方法的作用】
     * @param date
     * @return
     * @Author cui jixiang
     * @Date 2016年1月27日 下午3:10:41
     * @Return int 返回类型
     * @Throws
     */
    public static int getweekByday(String date){
    	 Calendar c = Calendar.getInstance();
         c.setTime(dateString2Date(date,"yyyyMMdd"));
         return c.getActualMaximum(Calendar.DAY_OF_WEEK);
    }
    /**
     * @Title dateCompareWithCurrtent
     * @Description 判断日期字符串与当前日期比较yyyyMMdd
     * @param compareDate
     * @return
     * @Author cui jixiang
     * @Date 2015年11月4日 下午6:39:35
     * @Return boolean 返回类型
     * @Throws
     */
    public static boolean dateCompareWithCurrtent(String compareDate){
    	 boolean comResult = false;
         Date comDate = DateUtils.dateString2Date(compareDate+"235959",dateTimeYNDHMSPattern);
         Date toComDate = DateUtils.getNowDate();
         if (comDate.after(toComDate)) {
             comResult = true;
         }
         return comResult;
    }
    /**
     * Description: 判断字符串是否日期格式(yyyy-MM-dd 或者 yyyy-MM-dd HH:mm)
     *
     * @param time
     * @return
     * @Version1.0 2013-1-5 下午01:47:09 by 崔吉祥
     */
    public static boolean isDateFormat(String time) {
        boolean isDate = true;
        if (StringUtil.isNotEmpty(time)) {
            SimpleDateFormat format = getSimpleDateFormat(defaultPattern);
            ParsePosition pos = new ParsePosition(0);
            Date timeD = format.parse(time, pos);
            if (timeD == null) {
                format = getSimpleDateFormat(dateTimePattern);
                pos.setIndex(0);
                timeD = format.parse(time, pos);
                if (timeD == null) {
                    isDate = false;
                }
            }

        }
        return isDate;
    }


    public static String getMaxDateByMonth(Date currentDate){
    	return getMaxDateByMonth(date2String(currentDate));
    }
    public static String getMinDateByMonth(Date currentDate){
	    return getMinDateByMonth(date2String(currentDate));
    }
    public static String getMaxDateByMonth(String currentDate){
    	Date sDate1 = DateUtils.dateString2Date(currentDate);
		Calendar   cDay1   =   Calendar.getInstance();  
	    cDay1.setTime(sDate1);  
	    final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);  
	    return currentDate.substring(0,8)+lastDay;
    }
    public static String getMinDateByMonth(String currentDate){
	    return currentDate.substring(0,8)+"01";
    }
    
    public static String getDateToHMSStr(Date date){
    	SimpleDateFormat sdf = new SimpleDateFormat(defaultTimeHMSPattern);
    	return sdf.format(date);
    } 
    /**
     * 两个日期的天数相差天数
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }
    
    public static String getLastyearmonth() {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return sdf.format(cal.getTime());
    }
    public static String getLastyearmonth(String pattern) {
    	if(StringUtil.isEmpty(pattern)) {
    		pattern = dateYNDPattern3;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, -1);
    	return sdf.format(cal.getTime());
    }
    public static String getLastyearmonth(String pattern, int addmonth) {
    	if(StringUtil.isEmpty(pattern)) {
    		pattern = dateYNDPattern3;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, addmonth);
    	return sdf.format(cal.getTime());
    }
    public static String getnextmonth() {
    	SimpleDateFormat sdf = new SimpleDateFormat("M");
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, 1);
    	return sdf.format(cal.getTime());
    }
    public static String getnextmonth(String pattern) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    	if(pattern !=null){
    		sdf.applyPattern(pattern);
    	}
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, 1);
    	return sdf.format(cal.getTime());
    }
    /**
     * Description: 获取日期（星期五16点之前获取本周六，星期五16点之后获取下周六）
     *
     * @return
     * @Version1.0 2016-12-29 下午01:47:09 by 张方奇
     */
    public static String getnextweekbytime() {
    	String datestr = "";
    	Date date=new Date();
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dateFormatH = new SimpleDateFormat("HH");
		calendar.setTime(date); 
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
		if(intWeek<5){
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			datestr = dateFormat.format(calendar.getTime());
		}else if(intWeek==5){
			if(Integer.parseInt(dateFormatH.format(date))>=16){
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
				datestr = dateFormat.format(calendar.getTime());
			}else{
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				calendar.getTime();
				datestr = dateFormat.format(calendar.getTime());
			}
		}else{
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
			datestr = dateFormat.format(calendar.getTime());
		}
		return datestr;
    }
    
    public static void main(String[] args) throws ParseException {
    	System.out.println(getnextmonth());
	}
    
    /**
     * EHR日期格式转SAP日期格式
     * @param ehrDate
     * @return
     */
    public static String parseEhr2SapDate(String ehrDate){
    	SimpleDateFormat sdf = new SimpleDateFormat(ehrDatePattern);
    	SimpleDateFormat sdf1 = new SimpleDateFormat(sapDatePattern);
    	try {
    		Date ehrDateDt = sdf.parse(ehrDate);
    		return sdf1.format(ehrDateDt);
		} catch (Exception e) {
			//如果报错，则原样返回
			return ehrDate;
		}
    }
    
    /**
     * EHR时间格式转SAP时间格式
     * @return
     */
    public static String parseEhr2SapTime(String ehrTime){
    	SimpleDateFormat sdf = new SimpleDateFormat(ehrTimeHMSPattern);
    	SimpleDateFormat sdf1 = new SimpleDateFormat(sapTimeHMSPattern);
    	try {
    		Date ehrTimeDt = sdf.parse(ehrTime);
    		return sdf1.format(ehrTimeDt);
		} catch (Exception e) {
			//如果报错，则原样返回
			return ehrTime;
		}
    }
    
    /**
     * SAP日期格式转EHR日期格式
     * @return
     */
    public static String parseSap2EhrDate(String sapDate){
    	SimpleDateFormat sdf = new SimpleDateFormat(sapDatePattern);
    	SimpleDateFormat sdf1 = new SimpleDateFormat(ehrDatePattern);
    	try {
    		Date sapDateDt = sdf.parse(sapDate);
    		return sdf1.format(sapDateDt);
		} catch (Exception e) {
			//如果报错，则原样返回
			return sapDate;
		}
    }
    
    /**
     * SAP时间格式转EHR时间格式
     * @return
     */
    public static String parseSap2EhrTime(String sapTime){
    	SimpleDateFormat sdf = new SimpleDateFormat(sapTimeHMSPattern);
    	SimpleDateFormat sdf1 = new SimpleDateFormat(ehrTimeHMSPattern);
    	try {
    		Date sapTimeDt = sdf.parse(sapTime);
    		return sdf1.format(sapTimeDt);
		} catch (Exception e) {
			//如果报错，则原样返回
			return sapTime;
		}
    }
}
