/**  
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: DateUtil.java
 * @Prject: llmj-common
 * @Package: com.llmj.utils
 * @Description: <功能详细描述>
 * @author: pandaijun  
 * @date: 2016-7-22 上午11:00:33
 * @version: V1.0  
 */
package com.liuy.mproxy.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <一句话功能简述>
 * @Title: DateUtil.java
 * @Description: <功能详细描述>
 * @author  liuy
 * @date 2016-7-22上午11:00:33
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DateUtil {
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_FORMAT_08W = "yyyyMMdd";
	/**
	 * yyyyMMddHHmm
	 */
	public static final String DATE_FORMAT_12W = "yyyyMMddHHmm";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_14W = "yyyyMMddHHmmss";
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String DATE_FORMAT_17W = "yyyyMMddHHmmssSSS";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_10H = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String DATE_FORMAT_16H = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_19H = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy/MM/dd
	 */
	public static final String DATE_FORMAT_10X = "yyyy/MM/dd";
	/**
	 * yyyy/MM/dd HH:mm
	 */
	public static final String DATE_FORMAT_16X = "yyyy/MM/dd HH:mm";
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_19X = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 获取年份
	 * 
	 * @return
	 */
	public static String getYear() {
		String strYear = "";
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		strYear = formatter.format(currentDate);
		return strYear;
	}

	/**
	 * 获取月份
	 * 
	 * @return
	 */
	public static String getYearMonth() {
		String strYearMonth = "";
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		strYearMonth = formatter.format(currentDate);
		return strYearMonth;
	}

	/**
	 * 获得当前时间上个月的时间
	 * 比如当前时间2017-10-12，如则返回当前时间上个月的一号 即2017-09-01 具体的格式自己定义
	 * @Title: getLastMonthByFormat
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年10月23日 下午2:28:19 
	 * @param dateFormat
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getLastMonthByFormat(String dateFormat) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 获得当前时间指定月的1号，
	 * 比如当前时间2017-10-12，如果第二个参数传0 则返回当前月的一号 即2017-10-01；如果第二个参数传1 则返回当前时间上个月的一号 即2017-09-01
	 * @Title: getLastMonthByFormat
	 * @Description: <功能详细描述>
	 * @author caoju
	 * @date 2017年10月23日 下午2:19:58 
	 * @param dateFormat
	 * @param num
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getLastMonthByFormat(String dateFormat ,int num) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		if(num != 0){
			lastDate.add(Calendar.MONTH, -num);// 减一个月，变为下月的1号
		}
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}
	
	/**
	 * 获取指定日期格式的日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateByFormat(String format) {
		String date = "";
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		date = formatter.format(currentDate);
		return date;
	}

	/**
	 * 获取年-月-日
	 * 
	 * @return
	 */
	public static String getDate() {
		String strCurrentDate = "";
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		strCurrentDate = formatter.format(currentDate);
		return strCurrentDate;
	}

	public static String getTommorrow() {
		String strCurrentDate = "";
		// Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -3);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		strCurrentDate = formatter.format(c.getTime());
		return strCurrentDate;
	}

	/**
	 * 获取指定格式的日期
	 * 
	 * @param dateFormat
	 *            日期格式
	 * @param intervalDay
	 *            与当天相隔的天数(0:当天 1:明天 -1：昨天)
	 * @return
	 */
	public static String getSpecialDate(String dateFormat, int intervalDay) {
		String strCurrentDate = "";
		// Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, intervalDay);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		strCurrentDate = formatter.format(c.getTime());

		return strCurrentDate;
	}

	/**
	 * 功能：获取间隔一定分钟数的日期 作者： huangzhu 修改时间：Jul 27, 2011 10:46:53 AM
	 * 
	 * @param dateFormat
	 * @param intervalMinute
	 * @return
	 */
	public static String getSpecialDateByIntervalMinute(String dateFormat,
			int intervalMinute) {
		Date date = new Date();
		long time = date.getTime() + intervalMinute * 60 * 1000;
		Date preDate = new Date(time);

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		return formatter.format(preDate);
	}

	

	public static String getTime() {
		String strCurrentTime = "";
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		strCurrentTime = formatter.format(currentTime);
		return strCurrentTime;
	}

	public static String getCurrentHour() {
		String strCurrentTime = "";
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		strCurrentTime = formatter.format(currentTime);
		return strCurrentTime;
	}

	public static String getHourFromSpecialDate(Date specialDate) {
		String strCurrentTime = "";
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		strCurrentTime = formatter.format(specialDate);
		return strCurrentTime;
	}

	public static String getCurrentMinute() {
		String strCurrentTime = "";
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("mm");
		strCurrentTime = formatter.format(currentTime);
		return strCurrentTime;
	}

	public static String gettMinuteFromSpecialDate(Date specialDate) {
		String strCurrentTime = "";
		SimpleDateFormat formatter = new SimpleDateFormat("mm");
		strCurrentTime = formatter.format(specialDate);
		return strCurrentTime;
	}

	public static String gettDateStrFromSpecialDate(Date specialDate,
			String dateFormat) {
		String strCurrentTime = "";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		strCurrentTime = formatter.format(specialDate);
		return strCurrentTime;
	}

	public static String getNumberTime() {
		String strCurrentTime = "";
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		strCurrentTime = formatter.format(currentTime);
		return strCurrentTime;
	}

	/**
	 * 数字时间格式化 234359->23:43:59
	 * 
	 * @param time
	 * @return
	 */
	public static String numberToStringTime(String time) {
		String strTime = "";
		if (time.length() == 6) {
			String hh = time.substring(0, 2);
			String mm = time.substring(2, 4);
			String ss = time.substring(4, 6);
			strTime = (new StringBuilder(String.valueOf(hh))).append(":")
			.append(mm).append(":").append(ss).toString();
		}
		return strTime;
	}

	/**
	 * 数字日期格式化 20090806->2009-08-06
	 * 
	 * @param date
	 * @return
	 */
	public static String numberToStringDate(String date) {
		String strTime = "";
		if (date.length() == 8) {
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			strTime = (new StringBuilder(String.valueOf(year))).append("-")
			.append(month).append("-").append(day).toString();
		}
		return strTime;
	}

	/**
	 * 获取年月日时分秒
	 * 
	 * @return
	 */
	public static String getDateTime() {
		String strCurrentDateTime = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strCurrentDateTime = formatter.format(currentDateTime);
		return strCurrentDateTime;
	}

	public static String getYMD() {
		String strYMD = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		strYMD = formatter.format(currentDateTime);
		return strYMD;
	}

	public static String getYMDHMS() {
		String strYMDHMS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		strYMDHMS = formatter.format(currentDateTime);
		return strYMDHMS;
	}

	public static String getYMDHMSS() {
		String strYMDHMSS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		strYMDHMSS = formatter.format(currentDateTime);
		return strYMDHMSS;
	}

	public static String lengthConvesion(String strDateTime) {
		String strYMDHMS = "";
		if (strDateTime == null)
			return "";
		if (strDateTime.length() == 10)
			strYMDHMS = " 23:59:59";
		return (new StringBuilder(String.valueOf(strDateTime))).append(
				strYMDHMS).toString();
	}

	public static Date praseStringToDate(String dateFormat, String dateStr) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		Date date = null;
		date = df.parse(dateStr);

		return date;

	}

	public String getDateChange(String strCurrentDate, int iQuantity) {
		String strTarget = "";
		int iYear = Integer.parseInt(strCurrentDate.substring(0, 4));
		int iMonth = Integer.parseInt(strCurrentDate.substring(5, 7));
		int iDay = Integer.parseInt(strCurrentDate.substring(8, 10));
		Calendar cal = Calendar.getInstance();
		cal.set(1, iYear);
		cal.set(2, iMonth - 1);
		cal.set(5, iDay);
		cal.add(5, iQuantity);
		Date currentDate = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		strTarget = formatter.format(currentDate);
		return strTarget;
	}

	public static String stringToNumber(String strDateTime) {
		String strYMDHMS = "";
		if (strDateTime == null)
			return "";
		strDateTime = strDateTime.trim();
		if (strDateTime.length() == 8) {
			strYMDHMS = (new StringBuilder(String.valueOf(strDateTime
					.substring(0, 2)))).append(strDateTime.substring(3, 5))
					.append(strDateTime.substring(6, 8)).toString();
		}
		if (strDateTime.length() == 10) {
			strYMDHMS = (new StringBuilder(String.valueOf(strDateTime
					.substring(0, 4)))).append(strDateTime.substring(5, 7))
					.append(strDateTime.substring(8, 10)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(strYMDHMS))).append(
			"000000").toString();
			return strYMDHMS;
		}
		if (strDateTime.length() == 18) {
			int i = strDateTime.indexOf(" ");
			int ii = strDateTime.indexOf(":");
			String tempStr = strDateTime.substring(i + 1, ii);
			tempStr = (new StringBuilder("0")).append(tempStr).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(strDateTime
					.substring(0, 4)))).append(strDateTime.substring(5, 7))
					.append(strDateTime.substring(8, 10)).append(tempStr)
					.append(strDateTime.substring(13, 15))
					.append(strDateTime.substring(16, 18)).toString();
			return strYMDHMS;
		}
		if (strDateTime.length() == 19) {
			int i = strDateTime.indexOf(" ");
			int j = strDateTime.lastIndexOf(" ");
			int l = strDateTime.length();
			String temp = strDateTime;
			if (i != j)
				temp = (new StringBuilder(String.valueOf(strDateTime.substring(
						0, j)))).append("0")
						.append(strDateTime.substring(j + 1, l)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(temp.substring(0, 4))))
			.append(temp.substring(5, 7)).append(temp.substring(8, 10))
			.append(temp.substring(11, 13))
			.append(temp.substring(14, 16))
			.append(temp.substring(17, 19)).toString();
			return strYMDHMS;
		}
		if (strDateTime.length() > 19) {
			strDateTime = strDateTime.substring(0, 19);
			int i = strDateTime.indexOf(" ");
			int j = strDateTime.lastIndexOf(" ");
			int l = strDateTime.length();
			String temp = strDateTime;
			if (i != j)
				temp = (new StringBuilder(String.valueOf(strDateTime.substring(
						0, j)))).append("0")
						.append(strDateTime.substring(j + 1, l)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(temp.substring(0, 4))))
			.append(temp.substring(5, 7)).append(temp.substring(8, 10))
			.append(temp.substring(11, 13))
			.append(temp.substring(14, 16))
			.append(temp.substring(17, 19)).toString();
			return strYMDHMS;
		} else {
			return "";
		}
	}
	
	public static int dayOfWeek(Date day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		int dayForWeek = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	// 日期转化为字符串（后边不加0）
	public static String stringToNumber_null(String strDateTime) {
		String strYMDHMS = "";
		if (strDateTime == null)
			return "";
		strDateTime = strDateTime.trim();
		if (strDateTime.length() == 8) {
			strYMDHMS = (new StringBuilder(String.valueOf(strDateTime
					.substring(0, 2)))).append(strDateTime.substring(3, 5))
					.append(strDateTime.substring(6, 8)).toString();
			return strYMDHMS;
		}
		if (strDateTime.length() == 10) {
			strYMDHMS = (new StringBuilder(String.valueOf(strDateTime
					.substring(0, 4)))).append(strDateTime.substring(5, 7))
					.append(strDateTime.substring(8, 10)).toString();
			return strYMDHMS;
		}
		if (strDateTime.length() == 18) {
			int i = strDateTime.indexOf(" ");
			int ii = strDateTime.indexOf(":");
			String tempStr = strDateTime.substring(i + 1, ii);
			tempStr = (new StringBuilder("0")).append(tempStr).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(strDateTime
					.substring(0, 4)))).append(strDateTime.substring(5, 7))
					.append(strDateTime.substring(8, 10)).append(tempStr)
					.append(strDateTime.substring(13, 15))
					.append(strDateTime.substring(16, 18)).toString();
			return strYMDHMS;
		}
		if (strDateTime.length() == 19) {
			int i = strDateTime.indexOf(" ");
			int j = strDateTime.lastIndexOf(" ");
			int l = strDateTime.length();
			String temp = strDateTime;
			if (i != j)
				temp = (new StringBuilder(String.valueOf(strDateTime.substring(
						0, j)))).append("0")
						.append(strDateTime.substring(j + 1, l)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(temp.substring(0, 4))))
			.append(temp.substring(5, 7)).append(temp.substring(8, 10))
			.append(temp.substring(11, 13))
			.append(temp.substring(14, 16))
			.append(temp.substring(17, 19)).toString();
			return strYMDHMS;
		}
		if (strDateTime.length() > 19) {
			strDateTime = strDateTime.substring(0, 19);
			int i = strDateTime.indexOf(" ");
			int j = strDateTime.lastIndexOf(" ");
			int l = strDateTime.length();
			String temp = strDateTime;
			if (i != j)
				temp = (new StringBuilder(String.valueOf(strDateTime.substring(
						0, j)))).append("0")
						.append(strDateTime.substring(j + 1, l)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(temp.substring(0, 4))))
			.append(temp.substring(5, 7)).append(temp.substring(8, 10))
			.append(temp.substring(11, 13))
			.append(temp.substring(14, 16))
			.append(temp.substring(17, 19)).toString();
			return strYMDHMS;
		} else {
			return "";
		}
	}

	public static String numberToString(String strYMDHMS) {
		String strDateTime = "";
		if (strYMDHMS == null || "".equals(strYMDHMS))
			return "";
		strYMDHMS = strYMDHMS.trim();
		if (strYMDHMS.length() == 8) {
			strDateTime = (new StringBuilder(String.valueOf(strYMDHMS
					.substring(0, 4)))).append("-")
					.append(strYMDHMS.substring(4, 6)).append("-")
					.append(strYMDHMS.substring(6, 8)).toString();
			return strDateTime;
		}
		if (strYMDHMS.length() == 14) {
			if (strYMDHMS.endsWith("000000"))
				strDateTime = (new StringBuilder(String.valueOf(strYMDHMS
						.substring(0, 4)))).append("-")
						.append(strYMDHMS.substring(4, 6)).append("-")
						.append(strYMDHMS.substring(6, 8)).toString();
			else
				strDateTime = (new StringBuilder(String.valueOf(strYMDHMS
						.substring(0, 4)))).append("-")
						.append(strYMDHMS.substring(4, 6)).append("-")
						.append(strYMDHMS.substring(6, 8)).append(" ")
						.append(strYMDHMS.substring(8, 10)).append(":")
						.append(strYMDHMS.substring(10, 12)).append(":")
						.append(strYMDHMS.substring(12, 14)).toString();
			return strDateTime;
		} else {
			return strYMDHMS;
		}
	}

	public static String startDatetoString(String strYMD) {
		if (strYMD == null || "".equals(strYMD))
			return "";
		String strYMDHMS = "";
		if (strYMD.length() == 10) {
			strYMDHMS = (new StringBuilder(String.valueOf(strYMD
					.substring(0, 4)))).append(strYMD.substring(5, 7))
					.append(strYMD.substring(8, 10)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(strYMDHMS))).append(
			"000000").toString();
		}
		if (strYMD.length() == 19)
			return stringToNumber(strYMD);
		else
			return strYMDHMS;
	}

	public static String endDatetoString(String strYMD) {
		if (strYMD == null || "".equals(strYMD))
			return "";
		String strYMDHMS = "";
		if (strYMD.length() == 10) {
			strYMDHMS = (new StringBuilder(String.valueOf(strYMD
					.substring(0, 4)))).append(strYMD.substring(5, 7))
					.append(strYMD.substring(8, 10)).toString();
			strYMDHMS = (new StringBuilder(String.valueOf(strYMDHMS))).append(
			"235959").toString();
		}
		if (strYMD.length() == 19)
			return stringToNumber(strYMD);
		else
			return strYMDHMS;
	}

	public static String getWeek() {
		String strCurrentWeek = "";
		Date currentWeek = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("E");
		strCurrentWeek = formatter.format(currentWeek);
		return strCurrentWeek;
	}

	public static String getDateToWeek(String strDate) {
		String strWeek = "";
		int iYear = Integer.parseInt(strDate.substring(0, 4));
		int iMonth = Integer.parseInt(strDate.substring(5, 7));
		int iDay = Integer.parseInt(strDate.substring(8, 10));
		Calendar cal = Calendar.getInstance();
		cal.set(1, iYear);
		cal.set(2, iMonth - 1);
		cal.set(5, iDay);
		Date currentDate = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("E");
		strWeek = formatter.format(currentDate);
		return strWeek;
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateString(String date, String format) {
		// 20090803
		if (date.length() == 8) {
			if (format.equals("yyyy-MM-dd")) {
				date = DateUtil.numberToStringDate(date);
			} else if (format.equals("yyyyMMddHHmmss")) {
				date = date + "000000";
			} else if (format.equals("yyyy-MM-dd HH:mm:ss")) {
				date = DateUtil.numberToStringDate(date) + " 00:00:00";
			}
		}
		// 2009-08-03
		else if (date.length() == 10) {
			if (format.equals("yyyyMMdd")) {
				date = DateUtil.stringToNumber(date);
			}
			if (format.equals("yyyyMMddHHmmss")) {
				date = DateUtil.stringToNumber(date) + "000000";
			} else if (format.equals("yyyy-MM-dd HH:mm:ss")) {
				date = date + " 00:00:00";
			}
		} else if (date.length() == 14) {
			if (format.equals("yyyyMMdd")) {
				date = date.substring(0, 8);
			}
			if (format.equals("yyyy-MM-dd")) {
				date = DateUtil.numberToStringDate(date.substring(0, 8));
			} else if (format.equals("yyyy-MM-dd HH:mm:ss")) {
				date = DateUtil.numberToString(date);
			}
		} else if (date.length() == 19) {
			if (format.equals("yyyyMMdd")) {
				date = DateUtil.stringToNumber_null(date.substring(0, 10));
			}
			if (format.equals("yyyy-MM-dd")) {
				date = date.substring(0, 10);
			} else if (format.equals("yyyyMMddHHmmss")) {
				date = DateUtil.stringToNumber(date);
			} 
		} else if (date.length() > 19) {
			date = DateUtil.formatDateString(date.substring(0, 19), format);
		}

		return date;
	}
	
	public static String formatDateString(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	public static Date buildDateAddHour(Date date ,Integer hour){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.HOUR, hour);
        Date dt1=rightNow.getTime();
        return dt1;
	}
	
	/**
     * 把毫秒转化成日期
     * @param dateFormat(日期格式，例如：MM/dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
	public static String getLongToDateStr(Long millSec, String dateFormat)
    {
    	if(millSec == null || StringUtils.isBlank(dateFormat))
    		return null;
    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    	Date date= new Date(millSec);
            return sdf.format(date);
    }
	
	/**
     * 日期字符串转换为毫秒
     * @param dateStr(日期字符串，例如：2015-12-30 22:15:30)
     * @param dateFormat (日期格式: yyyy-MM-dd HH:mm:ss)
     * @return
     */
	public static Long getDateStrToLong(String dateStr, String dateFormat)
    {
    	if(StringUtils.isBlank(dateStr) || StringUtils.isBlank(dateFormat))
    		return null;
    	SimpleDateFormat  sdf = new SimpleDateFormat(dateFormat);
    	Long time = null;
		try {
			Date date = sdf.parse(dateStr);
			time = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        return time;
    }
}
