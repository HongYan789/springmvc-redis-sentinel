package com.lppz.ehr.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author chenlisong
 */
public class DateTool {

	public static SimpleDateFormat standardSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfStandard = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    public static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	public static SimpleDateFormat sdayFormat = new SimpleDateFormat("yyyy.MM.dd");

	/***
	 * 将yyyy-MM-dd HH:mm:ss格式的日期数据转化为yyyyMMdd数据输出
	 * @param dataStr
	 * @return
	 */
	public static String dateFormat(String dataStr){
		Date date= new Date();
		try {
			date=sdfStandard.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat.format(date);
	}

    /***
     * 将格式化date字符串转化为另一种格式化date字符串
     * @param dataStr
     * @param beginFormat
     * @param endFormat
     * @return
     */
    public static String dateFormat(String dataStr,SimpleDateFormat beginFormat,SimpleDateFormat endFormat){
        Date date= new Date();
        try {
            date=beginFormat.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endFormat.format(date);
    }

    /***
     * 将date string转化为Date
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getDate(String dateStr,SimpleDateFormat format){
        Date date= new Date();
        try {
            date=format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateStr(Date date,SimpleDateFormat format){
        return format.format(date);
    }
	
	public static int getSat(Date date) {
		int count = 0;
		Date begin = getMonthBegin(date);
		Date end = getMonthEnd(date);
		do {
			int week = getWeek(begin);
			if(week == 6) {
				count ++;
			}
			begin = addDays(begin, 1);
		} while (begin.getTime() < end.getTime());

		return count;
	}

	/***
	 * 计算目标时间为周几
	 * @param date
	 * @param format
	 * @return
	 */
	public static int getWeek(String date,SimpleDateFormat format) {
        //String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return week;
    }

	public static int getWeek(Date date) {
		//String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week < 0)
			week = 0;
		return week;
	}

	public static Date addHours(Date date,int hours) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		date = calendar.getTime();
		return date;
	}
	
	public static Date addDays(Date date,int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		date = calendar.getTime();
		return date;
	}
	
	public static Date addYears(Date date,int years) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		date = calendar.getTime();
		return date;
	}
	
	public static Date addMonth(Date date,int months) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取一天中开始的时间
	 * @return
	 */
	public static Date getBegin(Date time){
		Calendar calendar = new GregorianCalendar();   
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);  
		
		return calendar.getTime();
	}
	
	/**
	 * 获取一天中结束的时间
	 * @return
	 */
	public static Date getEnd(Date time){
		Calendar calendar = new GregorianCalendar();   
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, 23);  
		calendar.set(Calendar.MINUTE, 59);  
		calendar.set(Calendar.SECOND, 59);  
		
		return calendar.getTime();
	}
	
	/**
	 * 获取一月开始的时间
	 * @return
	 */
	public static Date getMonthBegin(Date time){
		Calendar calendar = new GregorianCalendar();   
		calendar.setTime(time);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);  
		return calendar.getTime();
	}
	
	/**
	 * 获取一月结束的时间
	 * @return
	 */
	public static Date getMonthEnd(Date time){
		Calendar calendar = new GregorianCalendar();   
		calendar.setTime(time);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
		calendar.set(Calendar.HOUR_OF_DAY, 23);  
		calendar.set(Calendar.MINUTE, 59);  
		calendar.set(Calendar.SECOND, 59);  
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param begin	开始时间
	 * @param end	结束时间
	 * @return		0:小于一小时  1：小时级别（大于1h）   2：天级别（大于1天）  3：月级别（大于1月）
	 */
	public static int getTimeType(Date begin,Date end){
		
		long monthtime = 1000l * 60 * 60 * 24 * 30;
		long daytime = 1000l * 60 * 60 * 24;
		long hourtime = 1000l * 60 * 60;
		
		long time = end.getTime() - begin.getTime();
		if(time >= monthtime){
			return 3;
		}else if(time >= daytime){
			return 2;
		}else if(time >= hourtime){
			return 1;
		}
		return 0;
	}

	/***
	 * 计算begin到end时间段是否<=count的时间差
	 * @param begin
	 * @param end
	 * @param count
	 * @return
	 */
	public static boolean getTimeType(Date begin,Date end,int count){

		long monthtime = 1000l * 60 * 60 * 24 * 30 * count;
		long daytime = 1000l * 60 * 60 * 24 * count;
		long hourtime = 1000l * 60 * 60 * count;

		long time = end.getTime() - begin.getTime();
		if(time <= monthtime || time <= daytime || time <= hourtime){
			return true;
		}
		return false;
	}

    /***
     *计算begin到end时间段是否<=count的时间差
     * @param begin
     * @param end
     * @param type 1:月份 2：天数 3：小时
     * @param count
     * @param format
     * @return
     */
	public static boolean getTimeType(String begin,String end,int type,int count,SimpleDateFormat format){
        long times = 1000l * 60 * 60 * 24 * 30 * count;
		long time = 0;
		try {
			time = format.parse(end).getTime() - format.parse(begin).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		switch (type){
            case 1:
                times = 1000l * 60 * 60 * 24 * 30 * count;
                break;
            case 2:
                times = 1000l * 60 * 60 * 24 * count;
                break;
            case 3:
                times = 1000l * 60 * 60 * count;
                break;
        }
        if(time <= times){
            return true;
        }

		return false;
	}

	public static BigDecimal getTimes(String begin,String end,int type,int count,SimpleDateFormat format){
		long times = 1000l * 60 * 60 * 24 * 30 * count;
		long time = 0;
		try {
			time = format.parse(end).getTime() - format.parse(begin).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		switch (type){
			case 1:
				times = 1000l * 60 * 60 * 24 * 30 * count;
				break;
			case 2:
				times = 1000l * 60 * 60 * 24 * count;
				break;
			case 3:
				times = 1000l * 60 * 60 * count;
				break;
		}
		BigDecimal dec = new BigDecimal((time*1.0)/times);
		return dec;
	}
	
	public static List<Date> getUnknowDate(List<Date> existDates,Date begin, Date end) {
		List<Date> list = new ArrayList<Date>();
		int timetype = getTimeType(begin, end);
		
		Calendar beginC = Calendar.getInstance();
		beginC.setTime(begin);
		
		Calendar endC = Calendar.getInstance();
		endC.setTime(end);
		
		int beginIndex = 0;
		int endIndex = 0;
		int field = 0;
		if(timetype == 1) {
			beginIndex = beginC.get(Calendar.HOUR_OF_DAY);
			endIndex = endC.get(Calendar.HOUR_OF_DAY);
			field = Calendar.HOUR_OF_DAY;
		}else if(timetype == 2) {
			beginIndex = beginC.get(Calendar.DAY_OF_MONTH);
			endIndex = endC.get(Calendar.DAY_OF_MONTH);
			field = Calendar.DAY_OF_MONTH;
		}else if(timetype == 3) {
			beginIndex = beginC.get(Calendar.MONTH);
			endIndex = endC.get(Calendar.MONTH);
			field = Calendar.MONTH;
		}

		List<Integer> existIndex = new ArrayList<Integer>();
		for(Date date : existDates) {
			endC.setTime(date);
			int value = endC.get(field);
			existIndex.add(value);
		}
		
		for(int i = beginIndex; i <= endIndex ; i++) {
			if(!existIndex.contains(i)){
				beginC.set(field, i);
				list.add(beginC.getTime());
			}
		}
		return list;
	}
	
	/**
	 * 检测是否是同一年的数据
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean checkOneYear(Date begin,Date end) {
		if(begin == null || end == null) {
			throw new IllegalArgumentException("INVALID_PARAM");
		}
		
		Calendar cbegin = Calendar.getInstance();
		cbegin.setTime(begin);
		Calendar cend = Calendar.getInstance();
		cend.setTime(end);
		return cend.get(Calendar.YEAR) == cbegin.get(Calendar.YEAR);
	}

	public static void main(String[] args) {
//		计算接下来的一年里有多少个周六
//		Date date = new Date();
//		for(int i=0; i<12; i++) {
//			int sixCount = getSat(date);
//			System.out.println(new SimpleDateFormat("yyyy-MM").format(date) + " sat count:" + sixCount);
//			date = addMonth(date, 1);
//		}
		
//		System.out.println(new Date());
//		System.out.println(getBegin(new Date()));
//		System.out.println(getEnd(new Date()));
//		System.out.println(getMonthBegin(new Date()));
//		System.out.println(getMonthEnd(new Date()));
//		System.out.println(addMonth(new Date(), -1));
//		System.out.println(addDays(new Date(), -1));
//		
//		System.out.println("---------");
//		Date now = DateTool.addMonth(new Date(), -1);
//    	Date begin = DateTool.getMonthBegin(now);
//    	Date end = DateTool.getMonthEnd(now);
//    	System.out.println(begin);
//    	System.out.println(end);
//    	System.out.println(DateTool.getMonthEnd(new Date()));
//    	System.out.println(DateTool.addYears(new Date(), 30));
//
//		System.out.println("---------");
//		Date d1 = new Date();
//		Date d2 = addDays(d1, 1);
//		Date d5 = addDays(d1, 4);
//		System.out.println(d5.getTime() - d1.getTime());
//		System.out.println(standardSdf.format(d1));
//		System.out.println(standardSdf.format(d5));
//		List<Date> exist = new ArrayList<Date>();
//		exist.add(d2);
//		System.out.println(getUnknowDate(exist, d1, d5));
		
	}

    /***
     * 对比2个时间类型的字符串大小，beginTime<=endTime返回true
     * @param beginTime
     * @param endTime
     * @return
     */
	public static boolean  contrastTime(String beginTime, String endTime){
        try {
            Date beginDate=sdfStandard.parse(beginTime);
            Date endDate=sdfStandard.parse(endTime);
            if(beginDate.getTime() <= endDate.getTime()){
                return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
	}

	/***
	 * 对比2个时间类型的字符串大小，beginTime<=endTime返回true
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean  contrastTime(String beginTime, String endTime,SimpleDateFormat format){
		try {
			Date beginDate=format.parse(beginTime);
			Date endDate=format.parse(endTime);
			if(beginDate.getTime() <= endDate.getTime()){
				return true;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

    /***
     * 对比两个时间段是否在同一个月份
     */
	public static boolean compareMonth(String beginTime, String endTime){
        try {
            Date beginDate=sdfStandard.parse(beginTime);
            Date endDate=sdfStandard.parse(endTime);
            if(beginDate.getMonth() == endDate.getMonth()){
                return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 将时间strings按月份去重
     * @param dateStrs
     * @return
     */
//    public static String[] getMonths(String[] dateStrs){
//	    return null;
//    }
}
