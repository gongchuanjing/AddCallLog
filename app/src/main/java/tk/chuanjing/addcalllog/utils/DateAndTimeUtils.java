package tk.chuanjing.addcalllog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间工具类 V2.0
 * @author ChuanJing
 *
 */
public class DateAndTimeUtils {

	/** 初始化Calendar */
	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}



//--------------------------------------时间的一些转换-----------------------------------------

	/**
	 * 传入一个需要的 时间格式，返回这个格式的 当前时间
	 *
	 * @param timeFormat	时间格式，如：yyyy-MM-dd HH:mm:ss
	 * 									 yyyy年MM月dd日 HH:mm:ss
	 * 									 yyyy年MM月dd日 hh时mm分ss秒
	 * 									 yyyy-MM-dd EE HH-mm-ss
	 * 									 MM-dd HH:mm:ss
	 * 									 yyyy-MM-dd HH-mm-ss-SSS
	 * 									 ……
	 * 						当传入的格式不正确 会抛出非法参数异常
	 * @return
	 */
	public static String getCurrentTime(String timeFormat){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		String time = df.format(date);
		return time;
	}

	/**
	 * 传入一个需要的 时间格式 和 需要转换的Date类型的时间，返回这个格式的时间
	 *
	 * @param date	需要转换的Date类型的时间
	 * @param timeFormat
	 * @return
	 */
	public static String getTimeForDate(String timeFormat, Date date){
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		String time = df.format(date);
		return time;
	}

	/**
	 * 传入一个需要的 时间格式 和 需要转换的Date类型的时间，返回这个格式的时间
	 *
	 * @param longTime	需要转换的long类型的时间
	 * @param timeFormat
	 * @return
	 */
	public static String getTimeForLongTime(String timeFormat, long longTime){
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		String time = df.format(longTime);
		return time;
	}

	/**
	 * 把输入的一个有正确格式的字符串时间转成long类型的时间值
	 *
	 * @param timeFormat	要转换时间的格式，如：yyyy-MM-dd HH:mm
	 * @param strDate	要转换的时间，格式必须是第一个参数，如："2016-5-10 21:08"
	 * @return	转换后的long类型的时间，如果返回0，则转换失败
	 */
	public static long geLongTimeForStr(String timeFormat, String strDate) {
		SimpleDateFormat sdf= new SimpleDateFormat(timeFormat);

		Date dt;
		try {
			dt = sdf.parse(strDate);
			return dt.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0L;
	}

	/**
	 * 把输入的一个有正确格式的字符串时间转成Date类型的时间值
	 *
	 * @param timeFormat	要转换时间的格式，如：yyyy-MM-dd HH:mm
	 * @param strDate	要转换的时间，格式必须是第一个参数，如："2016-5-10 21:08"
	 * @return	转换后的Date类型的时间，如果返回null，则转换失败
	 */
	public static Date geDateTimeForStr(String timeFormat, String strDate) {
		SimpleDateFormat sdf= new SimpleDateFormat(timeFormat);
//    	sdf.applyPattern(timeFormat);

		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 把输入的一个有正确格式的字符串时间转成 需要的时间格式字符串
	 *
	 * @param timeFormat	要转换时间的格式（转换前的格式），如：yyyy-MM-dd HH:mm:ss
	 * @param hopeTimeFormat	希望的时间格式（转换后的格式），如：yyyy-MM-dd HH:mm
	 * @param strDate	要转换的时间，格式必须是第一个参数，如："2016-5-10 21:08"
	 * @return	转换后的Date类型的时间，如果返回null，则转换失败
	 */
	public static String geStrTimeForStr(String timeFormat, String strDate, String hopeTimeFormat) {
		SimpleDateFormat sdf= new SimpleDateFormat(timeFormat);

		try {
			Date date = sdf.parse(strDate);
			return getTimeForDate(hopeTimeFormat, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}


//--------------------------------------获取 日、星期、月、季度、年 相关方法---------------------------------------------

	/** 获得当前日期，其实就是当前月中的第几天 */
	public static int getCurrentDay() {
//    	return calendar().get(Calendar.DAY_OF_MONTH);一样
		return calendar().get(Calendar.DATE);
	}

	/** 获得当前是星期几 */
	public static int getCurrentWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK) - 1;
	}

	/** 获得当前月份 */
	public static int getCurrentMonth() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	/** 获得当前季度 */
	public static String getCurrentQur() {
		Date date = new Date();
		int x = date.getMonth() + 1;
		String strTime = getTimeForDate("yyyy", date);
		Date year = geDateTimeForStr("yyyy", strTime);

		String time;
		if (x < 4) {
			time = getTimeForDate("yyyy", date) + ".Q1";
//			time = getTimeForDate("yyyy", date) + "第一季度";
		} else if (x < 7) {
			time = getTimeForDate("yyyy", date) + ".Q2";
		} else if (x < 10) {
			time = getTimeForDate("yyyy", date) + ".Q3";
		} else {
			time = getTimeForDate("yyyy", date) + ".Q4";
		}
		return time;
	}

	/** 获得当前年份 */
	public static int getCurrentYear() {
		return calendar().get(Calendar.YEAR);
	}

	/** 获得传入时间的日期 */
	public static int getDay(Date date){
		Calendar c = calendar();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/** 获得传入时间是星期几 */
	public static int getWeek(Date date){
		Calendar c = calendar();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/** 获得传入时间的月份 */
	public static int getMonth(Date date){
		Calendar c = calendar();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/** 获得传入时间的季度 */
	public static String getQur(Date date) {
		int x = date.getMonth() + 1;
		String strTime = getTimeForDate("yyyy", date);
		Date year = geDateTimeForStr("yyyy", strTime);

		String time;
		if (x < 4) {
			time = getTimeForDate("yyyy", date) + ".Q1";
//			time = getTimeForDate("yyyy", date) + "第一季度";
		} else if (x < 7) {
			time = getTimeForDate("yyyy", date) + ".Q2";
		} else if (x < 10) {
			time = getTimeForDate("yyyy", date) + ".Q3";
		} else {
			time = getTimeForDate("yyyy", date) + ".Q4";
		}
		return time;
	}

	/** 获得传入时间的年份 */
	public static int getYear(Date date){
		Calendar c = calendar();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回前一天日期的long值
	 *
	 * @param longTime	参照日期
	 * @return	前一天日期的long值
	 */
	public static long lastDay(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime().getTime();
	}

	/**
	 * 返回前一月日期的long值
	 *
	 * @param longTime	参照日期
	 * @return 前一月日期的long值
	 */
	public static long lastMonth(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime().getTime();
	}

	/**
	 * 返回前一季度日期
	 *
	 * @param longTime	参照日期
	 * @return 前一季度日期的long值
	 */
	public static long lastQur(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.MONTH, -3);
		return cal.getTime().getTime();
	}

	/**
	 * 返回前一年日期的long值
	 *
	 * @param longTime	参照日期
	 * @return 前一年日期的long值
	 */
	public static long lastYear(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		return cal.getTime().getTime();
	}

	/**
	 * 返回下一天日期的long值
	 *
	 * @param longTime	参照日期
	 * @return 下一天日期的long值
	 */
	public static long nextDay(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime().getTime();
	}

	/**
	 * 返回下一月日期的long值
	 *
	 * @param longTime	参照日期
	 * @return 下一月日期的long值
	 */
	public static long nextMonth(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		return cal.getTime().getTime();
	}

	/**
	 * 返回下一季度日期
	 *
	 * @param longTime	参照日期
	 * @return 下一季度日期的long值
	 */
	public static long nextQur(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.MONTH, 3);
		return cal.getTime().getTime();
	}

	/**
	 * 返回下一年日期的long值
	 *
	 * @param longTime	参照日期
	 * @return 下一年日期的long值
	 */
	public static long nextYear(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		cal.add(Calendar.YEAR, 1);
		return cal.getTime().getTime();
	}

	/** 今天是年中的第几天 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}



//---------------------------------------------日期之间的一些计算 和 比较---------------------------------------

	/**
	 * 判断某月有多少天
	 *
	 * @param longTime	月的long值
	 * @return 天数
	 */
	public static int getMonthHaveDay(long longTime) {
		Calendar cal = calendar();
		Date date = new Date(longTime);
		cal.setTime(date);
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		return dateOfMonth;
	}

	/** 判断原日期是否在目标日期之前 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/** 判断原日期是否在目标日期之后 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	/** 判断两日期是否相同 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范围
	 *
	 * @param beginDate	日期范围开始
	 * @param endDate	日期范围结束
	 * @param src	需要判断的日期
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 判断某日是否为当月
	 *
	 * @param longTime	某日的long值
	 * @return	true为是
	 */
	public static boolean orCurrentMonth(long longTime) {
//		if (getTimeForLongTime("yyyy-MM", longTime).equals(getTimeForLongTime("yyyy-MM", System.currentTimeMillis()))) {
//			return true;
//		} else {
//			return false;
//		}
		return getTimeForLongTime("yyyy-MM", longTime).equals(getTimeForLongTime("yyyy-MM", System.currentTimeMillis()));
	}

	/**
	 * 输入一个已经过去的时间，计算出与当前时间的间隔
	 *
	 * @param outTime
	 * @return
	 */
	public static String timeGap(long outTime) {
		long currentTime = System.currentTimeMillis();
		long timeGap = currentTime - outTime;			// 与现在时间相差秒数
		String timeStr = null;
		if (timeGap > 1000 * 60 * 60 * 24) {			// 1天以上
			timeStr = timeGap / (1000 * 60 * 60 * 24) + "天前";
		} else if (timeGap > 1000 * 60 * 60) {			// 1小时-24小时
			timeStr = timeGap / (1000 * 60 * 60) + "小时前";
		} else if (timeGap > 1000 * 60) {				// 1分钟-59分钟
			timeStr = timeGap / (1000 * 60) + "分钟前";
		} else {										// 1秒钟-59秒钟
//        	timeStr = "刚刚";
			timeStr = timeGap / 1000 + "秒前";
		}
		return timeStr;
	}

	/**
	 * 获得当前月的最后一天
	 * HH:mm:ss为23:59:59，毫秒为999
	 */
	public static Date getCurrentMonthOfLastDay() {
		Calendar cal = calendar();

		cal.set(Calendar.DAY_OF_MONTH, 1);	// 转变为当前月的第一天，例如2016-08-01
		cal.add(Calendar.MONTH, 1);			// 转变为下个月的第一天，例如2016-09-01

		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1，下个月第一天，毫秒减一，即为当前月的最后一天的最后一毫秒。例如2016-08-31 23:59:59 999

		return cal.getTime();
	}

	/**
	 * 获得当前月的第一天
	 * HH:mm:ss为00:00:00，毫秒为000
	 */
	public static Date getCurrentMonthOfFirstDay() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}

	/** 把一个以秒为单位的数值，格式化为分种小时天 */
	public static String parseSecond(int second) {
		if (second >= 60 * 60 * 24) {
			return second / (60 * 60 * 24) + "天";
		} else if (second >= 60 * 60) {
			return second / (60 * 60) + "时";
		} else if (second >= 60) {
			return second / 60 + "分";
		} else {
			return second + "秒";
		}
	}

	/**
	 * 获得天数差
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long getDayDiff(Date begin, Date end){
		long day = 1;
		if(end.getTime() < begin.getTime()){
			day = -1;
		}else if(end.getTime() == begin.getTime()){
			day = 1;
		}else {
			day += (end.getTime() - begin.getTime())/(24 * 60 * 60 * 1000) ;
		}
//        return day;
		return day - 1;
	}



//-----------------------------------------------------------------------------

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 获得本周五的日期
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 获得本周六的日期
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 获得本周日的日期
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}
}