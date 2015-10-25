package net.lx.common.date;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 时间工具
 * 
 */
public class DateUtil {
	/**
	 * 24小时制 yyyy-MM-dd HH:mm:ss
	 */
	public static String DATETIME_PATTERN24H = "yyyy_MM_dd_HH_mm_ss";
	/**
	 * 获取当前随即毫秒数
	 * 
	 * @return
	 */
	public static Long getDynamicSeconds() {
		return new Random().nextLong();
	}

	/**
	 * 获取当前时间
	 * 
	 * @param formatStr
	 *            时间格式 如："yyyy-MM-dd HH:mm:ss.SSS"
	 * @return
	 */
	public static String getNowDate(String formatStr) {
		// 创建一个当前时间Date对象
		Date d = new Date();
		// 时间格式话
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(d);
	}
	/**
	 * 返回当前日期+时间字符串 yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStringWithTime(Date date) {

		return dateToString(date, DATETIME_PATTERN24H);
	}
	/**
	 * 根据时间变量返回时间字符串
	 * 
	 * @return 返回时间字符串
	 * @param pattern
	 *            时间字符串样式
	 * @param date
	 *            时间变量
	 */
	public static String dateToString(Date date, String pattern) {

		if (date == null) {

			return null;
		}

		try {

			SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
			sfDate.setLenient(false);

			return sfDate.format(date);
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 把date类型转换成String类型
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getDate(Date date, String formatStr) {
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}

	/**
	 * 将传入时间改为指定格式的字符串
	 * 
	 * @param d
	 * @param formatStr
	 * @return
	 */
	public static String getDateStr(Date d, String formatStr) {
		// 时间格式话
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(d);
	}

	/**
	 * 获取Timestamp类型的当前时间
	 * 
	 * @param formatStr
	 * @return
	 */
	public static Timestamp getNowTimestamp(String formatStr) {

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Timestamp ts = null;
		try {
			d = sdf.parse(sdf.format(d));
		} catch (Exception e) {
		}
		ts = new Timestamp(d.getTime());
		return ts;
	}

	/**
	 * 获取Timestamp类型的当前时间
	 * 
	 * @param formatStr
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		return getNowTimestamp("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 把Date转化成Timestamp类型
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static Timestamp getTimestamp(Date date, String formatStr) {

		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Timestamp ts = null;
		try {
			date = sdf.parse(sdf.format(date));
		} catch (Exception e) {
		}
		ts = new Timestamp(date.getTime());
		return ts;
	}

	/**
	 * 把Timestamp转化成字符串类型
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param dateStr
	 *            字符串格式
	 * @return
	 */
	public static String getTimestamp(Timestamp timestamp, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(timestamp);
	}

	/**
	 * 把字符串转化成Timestamp类型
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param dateStr
	 *            字符串格式
	 * @return
	 */
	public static Timestamp getTimestamp(String dateStr, String formatStr) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Timestamp ts;
		try {
			d = sdf.parse(dateStr);
		} catch (Exception e) {
		}
		ts = new Timestamp(d.getTime());
		return ts;
	}

	/**
	 * 字符串转换成日期类型
	 * 
	 * @param sDate
	 * @return
	 * @throws Exception
	 */
	public static Date getDate(String sDate) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(sDate);
	}

	public static Date StringToDate(String sDate, String formatStr)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		return df.parse(sDate);
	}

	/**
	 * 获取本周第一天日期
	 * 
	 * @return
	 */
	public static Timestamp getMonday() {
		int this_day = getWeekOfDay(new Date());
		// 如果周日作为一周的最后一天
		int step_s2 = -this_day + 1; // 上周日距离今天的天数（负数表示）
		if (this_day == 0) {
			step_s2 = -7; // 如果今天是周日
		}
		long thisTime = System.currentTimeMillis();// 返回以毫秒为单位的当前时间。
		Date monday = new Date(thisTime + (long) step_s2 * 24 * 3600 * 1000);
		// 把Date类型转换成Timestamp
		return getTimestamp(monday, "yyyy-MM-dd");
	}

	/**
	 * 获取本周最后一天日期
	 * 
	 * @return
	 */
	public static Timestamp getSunday() {
		int this_day = getWeekOfDay(new Date());
		int step_m2 = 7 - this_day; // 周日距离今天的天数（负数表示）
		long thisTime = System.currentTimeMillis();// 返回以毫秒为单位的当前时间。
		Date sunday = new Date(thisTime + (long) step_m2 * 24 * 3600 * 1000);
		// 把Date类型转换成Timestamp
		return getTimestamp(sunday, "yyyy-MM-dd");
	}
	
	/**
	 * 获取指定日期该周第一天日期
	 * 
	 * @return
	 */
	public static Timestamp getMonday(Date date) {
		Calendar rili = Calendar.getInstance();
		// 将日历翻到year年month月1日、0表示1月
		rili.setTime(date);
		// 获取今天 是星期几（get方法返回的值是0表示星期日，返回的值是6表示星期六）
		int this_day = rili.get(Calendar.DAY_OF_WEEK) - 1;
		// 如果周日作为一周的最后一天
		int step_s2 = -this_day + 1; // 上周日距离今天的天数（负数表示）
		if (this_day == 0) {
			step_s2 = -6; // 如果今天是周日
		}
		rili.add(Calendar.DATE, step_s2);
		// 把Date类型转换成Timestamp
		return getTimestamp(rili.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取指定日期该周最后一天日期
	 * 
	 * @return
	 */
	public static Timestamp getSunday(Date date) {
		Calendar rili = Calendar.getInstance();
		// 将日历翻到year年month月1日、0表示1月
		rili.setTime(date);
		// 获取今天 是星期几（get方法返回的值是0表示星期日，返回的值是6表示星期六）
		int this_day = rili.get(Calendar.DAY_OF_WEEK) - 1;
		int step_m2 = 7 - this_day; // 周日距离今天的天数（负数表示）
		if (this_day == 0) {
			step_m2 = 0; // 如果今天是周日
		}
		rili.add(Calendar.DATE, step_m2);
		// 把Date类型转换成Timestamp
		return getTimestamp(rili.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前是星期几 1表示星期日，返回的值7是表示星期六
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfDay(Date date) {
		Calendar rili = Calendar.getInstance();
		// 将日历翻到year年month月1日、0表示1月
		rili.setTime(new Date());
		// 获取今天 是星期几（get方法返回的值是0表示星期日，返回的值是6表示星期六）
		return rili.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static boolean compareToDateTime(Date firstDate, Date secondDate) {
		return firstDate.after(secondDate);
	}

	/**
	 * 判别是否是润年
	 * 
	 * @param year
	 *            输入的年份
	 * @return [ture润年][false非润年]
	 */
	public static boolean isProYear(int year) {
		boolean isproyear = false;
		if ((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
			isproyear = true;
		} else {
			isproyear = false;
		}
		return isproyear;
	}

	/**
	 * 根据特定年中的第多少天，获得新日期
	 * 
	 * @param year
	 *            输入特定年份
	 * @param day
	 *            输入的第多少天
	 * @return Calendar
	 */
	public static Calendar getDateByday(int year, int day) {

		if (day > 366) {
			if (isProYear(year)) {
				day = 366;
			} else {
				day = 355;
			}
		}

		boolean isproyear = isProYear(year);
		int dayofmonth = 0;
		int i = day;
		int month = 0;

		if (isproyear) {
			// 润年情况
			if (i <= 31) {
				dayofmonth = day;
				month = 1;
			} else if (i > 31 && i <= 60) {
				dayofmonth = day - 31;
				month = 2;
			} else if (i > 60 && i <= 91) {
				dayofmonth = day - 60;
				month = 3;
			} else if (i > 91 && i <= 121) {
				dayofmonth = day - 91;
				month = 4;
			} else if (i > 121 && i <= 152) {
				dayofmonth = day - 121;
				month = 5;
			} else if (i > 152 && i <= 182) {
				dayofmonth = day - 152;
				month = 5;
			} else if (i > 182 && i <= 213) {
				dayofmonth = day - 182;
				month = 7;
			} else if (i > 213 && i <= 244) {
				dayofmonth = day - 213;
				month = 8;
			} else if (i > 244 && i <= 274) {
				dayofmonth = day - 244;
				month = 9;
			} else if (i > 274 && i <= 305) {
				dayofmonth = day - 274;
				month = 10;
			} else if (i > 305 && i <= 335) {
				dayofmonth = day - 305;
				month = 11;
			} else if (i > 335 && i <= 366) {
				dayofmonth = day - 335;
				month = 12;
			}
		} else {
			// 非润年情况
			if (i <= 31) {
				dayofmonth = day;
				month = 1;
			} else if (i > 31 && i <= 59) {
				dayofmonth = day - 31;
				month = 2;
			} else if (i > 59 && i <= 90) {
				dayofmonth = day - 59;
				month = 3;
			} else if (i > 90 && i <= 120) {
				dayofmonth = day - 90;
				month = 4;
			} else if (i > 120 && i <= 151) {
				dayofmonth = day - 120;
				month = 5;
			} else if (i > 151 && i <= 181) {
				dayofmonth = day - 151;
				month = 6;
			} else if (i > 181 && i <= 212) {
				dayofmonth = day - 181;
				month = 7;
			} else if (i > 212 && i <= 243) {
				dayofmonth = day - 212;
				month = 8;
			} else if (i > 243 && i <= 273) {
				dayofmonth = day - 243;
				month = 9;
			} else if (i > 273 && i <= 304) {
				dayofmonth = day - 273;
				month = 10;
			} else if (i > 304 && i <= 334) {
				dayofmonth = day - 304;
				month = 11;
			} else if (i > 334 && i <= 365) {
				dayofmonth = day - 334;
				month = 12;
			}
		}

		java.util.Calendar ca = java.util.Calendar.getInstance();

		// 设定时间从当前的0时0分1秒开始
		// 所有的月份均是0 based:0-11
		ca.set(year, month - 1, dayofmonth, 0, 0, 1);
		return ca;
	}

	/**
	 * 通过年
	 * 
	 * @param year
	 *            年
	 * @param week
	 *            第几周
	 * @return
	 */
	public static String[] getWeeksDate(int year, int week) {
		String[] dates = null;

		Calendar calendar = getDateByday(year, week);

		int this_day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		long thisTime = System.currentTimeMillis();// 返回以毫秒为单位的当前时间。
		// 获取本周最后一天
		int step_m2 = 7 - this_day; // 周日距离今天的天数（负数表示）
		Date sunday = new Date(thisTime + (long) step_m2 * 24 * 3600 * 1000);

		// 获取本周第一天
		int step_s2 = -this_day + 1; // 上周日距离今天的天数（负数表示）
		if (this_day == 0) {
			step_s2 = -7; // 如果今天是周日
		}
		Date monday = new Date(thisTime + (long) step_s2 * 24 * 3600 * 1000);
		System.out.println(getTimestamp(monday, "yyyy-MM-dd"));
		System.out.println(getTimestamp(sunday, "yyyy-MM-dd"));
		return dates;
	}

	/**
	 * 比较两Date相差几天,几周。。。。
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate, int type) {

		// Calendar aCalendar = Calendar.getInstance();
		//
		// aCalendar.setTime(fDate);
		//
		// int day1 = aCalendar.get(type);
		//
		// aCalendar.setTime(oDate);
		//
		// int day2 = aCalendar.get(type);
		//
		// return day2 - day1;
		long timeLong = 0;
		int dayNum = 0;
		switch (type) {

		case Calendar.HOUR:
			timeLong = oDate.getTime() - fDate.getTime();
			dayNum = (int) ((timeLong / 1000) / 3600);
			return dayNum;
		case Calendar.DAY_OF_YEAR:
			timeLong = oDate.getTime() - fDate.getTime();
			dayNum = (int) (((timeLong / 1000) / 3600) / 24);
			return dayNum;
		case Calendar.WEEK_OF_YEAR:
			timeLong = oDate.getTime() - fDate.getTime();
			dayNum = (int) (((timeLong / 1000) / 3600) / 24 / 7);
			return dayNum;
		case Calendar.MONTH:
			timeLong = oDate.getTime() - fDate.getTime();
			dayNum = (int) (((timeLong / 1000) / 3600) / 24 / 31);
			return dayNum;
		case Calendar.YEAR:
			timeLong = oDate.getTime() - fDate.getTime();
			dayNum = (int) (((timeLong / 1000) / 3600) / 24 / 360);
			return dayNum;

		}

		return 0;

	}

	/**
	 * 叠加日期
	 * 
	 * @param d
	 * @param amount
	 * @return
	 */
	public static Date increaseDate(Date d, int type, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(type, amount);//
		return calendar.getTime();
	}

	public static void main(String[] args) throws java.text.ParseException {
		// GetWeek(new Date());
		// String date = "09";
		// System.out.println(date);
		// Integer.parseInt(date);
		// int num = Integer.parseInt(date) + 1;
		// System.out.println(num);
		// getDaysByWeek("2010-1-42");
		// getWeeksDate(2010,1);
		/*
		 * System.out.print("请输入一个十进制整数:"); try { BufferedReader br = new
		 * BufferedReader(new InputStreamReader( System.in)); String i =
		 * br.readLine(); System.out.println("字符串转十六进制：");
		 * System.out.println(encode(i)); System.out.println("十六进制转字符串:");
		 * System.out.println(decode(i)); System.out.println();
		 * 
		 * String inputStr = "简单加密"; System.err.println("原文:\n" + inputStr);
		 * 
		 * byte[] inputData = inputStr.getBytes(); String code =
		 * encryptBASE64(inputData);
		 * 
		 * System.err.println("BASE64加密后:\n" + encode(code));
		 * 
		 * byte[] output = decryptBASE64(decode(encode(code)));
		 * 
		 * String outputStr = new String(output);
		 * 
		 * System.err.println("BASE64解密后:\n" + outputStr); } catch (Exception e)
		 * { // TODO: handle exception }
		 */
	}

	/**
	 * 
	 * @功能：获取日期区间(包括自身)
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> getDateRange(Date start, Date end) {
		List<Date> list = new ArrayList<Date>();
		list.add(start);
		long mid = end.getTime() - start.getTime();
		int day = (int) (mid / (1000 * 60 * 60 * 24));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		// SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < day - 1; i++) {
			calendar.add(Calendar.DATE, 1);
			list.add(calendar.getTime());
			// System.out.println(simple.format(calendar.getTime()));
		}
		if (day != 0) {
			list.add(end);
		}

		return list;
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		System.out.println(String.valueOf(bytes[1]));
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date nowDate = new Date();
		return nowDate;
	}

	/**
	 * 判断是否在线
	 * 
	 * @param mtime
	 * @return
	 */
	public static boolean compareDate(long mtime) {
		if (System.currentTimeMillis() - mtime >= 30000) {
			return false;
		}
		return true;
	}

	/**
	 * 获取是年月日时分秒
	 * 
	 * @param d
	 * @param formatStr
	 * @return
	 */
	public static int getTime(Date d, String formatStr) {
		// 时间格式话
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return new Integer(sdf.format(d));
	}

	public static int getWeek(Date da) {
		Calendar c = Calendar.getInstance();
		c.setTime(da);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		c.getTime().getTime();
		c.set(year, month, date, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(c.getTime().getTime());
		int day = c.get(Calendar.DAY_OF_WEEK) - 1;
		System.out.println("date" + date + "day:" + day);
		int newDate = date - (day + 6) % 7 + 3;
		c.set(Calendar.DATE, newDate);
		long dms = c.getTime().getTime();
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 4);
		return Math.round((dms - c.getTime().getTime()) / (604800000)) + 1;

	}

	public static Date updatTime(int weekid, Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		switch (weekid) {
		case 1:
			break;
		case 2:
			c.add(Calendar.DAY_OF_MONTH, +1);
			break;
		case 3:
			c.add(Calendar.DAY_OF_MONTH, +2);
			break;
		case 4:
			c.add(Calendar.DAY_OF_MONTH, +3);
			break;
		case 5:
			c.add(Calendar.DAY_OF_MONTH, +4);
			break;
		case 6:
			c.add(Calendar.DAY_OF_MONTH, +5);
			break;
		case 7:
			c.add(Calendar.DAY_OF_MONTH, +6);
			break;
		}

		return c.getTime();
	}

	/**
	 * 根据某年某月某周,获取这个周的开始时间和结束时间 "2008-4-4" 2008年4月份的第4周的每天日期
	 * 
	 */
	public static String[] getDaysByWeek(String week)
			throws java.text.ParseException {
		Calendar cal = Calendar.getInstance();
		// cal.set(GregorianCalendar.DAY_OF_WEEK,GregorianCalendar.MONDAY);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-WW");// 年-月-周格式
		Date d = format.parse(week);
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");// 标准格式
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d);
		// cal2.setFirstDayOfWeek(GregorianCalendar.MONDAY);
		String[] dates = new String[7];
		cal2.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
		dates[0] = format2.format(cal2.getTime());
		cal2.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.TUESDAY);
		dates[1] = format2.format(cal2.getTime());
		cal2.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.WEDNESDAY);
		dates[2] = format2.format(cal2.getTime());
		cal2.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.THURSDAY);
		dates[3] = format2.format(cal2.getTime());
		cal2.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY);
		dates[4] = format2.format(cal2.getTime());
		cal2.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.SATURDAY);
		dates[5] = format2.format(cal2.getTime());
		cal2.add(Calendar.DATE, 1);
		dates[6] = format2.format(cal2.getTime());
		return dates;
	}

	/**
	 * 比较日期
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(String date1, String date2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() >= dt2.getTime()) {
				// System.out.println("dt1 在dt2后 ");
				return true;
			} else if (dt1.getTime() <= dt2.getTime()) {
				// System.out.println("dt1在dt2前");
				return false;
			} else {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * 通过日期获取年份 月份 日期
	 * 
	 * @param d
	 * @param i
	 * @return
	 */
	public static int getDate(Date d, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		switch (i) {
		case Calendar.MONTH:
			int month = cal.get(Calendar.MONTH) + 1;
			return month;

		case Calendar.YEAR:
			int year = cal.get(Calendar.YEAR);
			return year;
		}
		return -1;

	}

	/*
	 * 获取一个时间 距离现在多久 的 字符串表示
	 * 2015-04-21
	 */
	public static String getDateTimeAgo(Date dateTime){
		String result = "";
		Date now = new Date();
		Long ts = (now.getTime() - dateTime.getTime()) / 1000;
		if(ts < 0)
			result = "未来";
		else if(ts <= 60)
			result = String.format("%s秒前", (int)(ts*1));
		else if(ts <= 60*60)
			result = String.format("%s分钟前", (int)(ts/60));
		else if(ts <= 60*60*24)
			result = String.format("%s小时前", (int)(ts/3600));
		else if(ts <= 60*60*24*30)
			result = String.format("%s天前", (int)(ts/3600/24));
		else if(ts <= 60*60*24*365)
			result = String.format("%s月前", (int)(ts/3600/24/30));
		else if(ts <= 60*60*24*365*3)
			result = String.format("%s年前", (int)(ts/3600/24/365));
		else
			result = "太遥远了";
		return result;
	}
	
	
}
