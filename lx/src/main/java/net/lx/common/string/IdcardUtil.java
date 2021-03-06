package net.lx.common.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IdcardUtil {

	/**
	 * 功能：把15位身份证转换成18位
	 * 
	 * @param id
	 * @return newid or id
	 */
	public static final String getIDCard_18bit(String id) {
		// 若是15位，则转换成18位；否则直接返回ID
		if (15 == id.length()) {
			final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
					2, 1 };
			final String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
					"3", "2" };
			int i, j, s = 0;
			String newid;
			newid = id;
			newid = newid.substring(0, 6) + "19"
					+ newid.substring(6, id.length());
			for (i = 0; i < newid.length(); i++) {

				j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
				s = s + j;
			}
			s = s % 11;
			newid = newid + A[s];

			return newid;
		} else {
			return id;
		}

	}

	/**
	 * 从身份证获取出生日期
	 * 
	 * @param cardNumber
	 *            已经校验合法的身份证号
	 * @return Strig YYYY-MM-DD 出生日期
	 */
	public static String getBirthDateFromCard(String cardNumber) {
		String card = cardNumber.trim();
		String year;
		String month;
		String day;
		if (card.length() == 18) { // 处理18位身份证
			year = card.substring(6, 10);
			month = card.substring(10, 12);
			day = card.substring(12, 14);
		} else { // 处理非18位身份证
			year = card.substring(6, 8);
			month = card.substring(8, 10);
			day = card.substring(10, 12);
			year = "19" + year;
		}
		if (month.length() == 1) {
			month = "0" + month; // 补足两位
		}
		if (day.length() == 1) {
			day = "0" + day; // 补足两位
		}
		return year + "-" + month + "-" + day;
	}

	/**
	 * 从身份证获取性别
	 * 
	 * @param cardNumber
	 *            已经校验合法的身份证号
	 * @return int Sex 性别
	 */
	public static int getSexFromCard(String cardNumber) {
		String inputStr = cardNumber.toString();
		int sex;
		if (inputStr.length() == 18) {
			sex = inputStr.charAt(16);
			if (sex % 2 == 0) {
				return 0;//女
			} else {
				return 1;//男
			}
		} else {
			sex = inputStr.charAt(14);
			if (sex % 2 == 0) {
				return 0;//女
			} else {
				return 1;//男
			}
		}
	}

	/**
	 * 从身份证获取出生日期
	 * 
	 * @param cardNumber
	 *            已经校验合法的身份证号
	 * @return Strig YYYY-MM-DD 出生日期
	 */
	public static java.sql.Date getBirthFromCard(String cardNumber) {
		String birthString = getBirthDateFromCard(cardNumber);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date birthDate = new java.sql.Date(new Date().getTime());
		// 将字符串转换为Date
		try {
			Date date = df.parse(birthString);
			birthDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return birthDate;

	}

	/**
	 * 根据身份证计算周岁
	 */
	public static int getAge(String IDCardNum) {
		Calendar cal1 = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		// 如果身份证15位则将其转化为18位
		if (IDCardNum.length() == 15)
			IDCardNum = getIDCard_18bit(IDCardNum);
		cal1.set(Integer.parseInt(IDCardNum.substring(6, 10)),
				Integer.parseInt(IDCardNum.substring(10, 12)),
				Integer.parseInt(IDCardNum.substring(12, 14)));
		return getYearDiff(today, cal1);
	}

	public static int getYearDiff(Calendar cal, Calendar cal1) {
		int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
		int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
		return (y * 12 + m) / 12;
	}
	
	/**
	 * 身份证校验
	 * @param arrIdCard
	 * @return
	 */
	public static boolean isIdCard(String arrIdCard) {  
        int sigma = 0;  
        Integer[] a = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };  
        String[] w = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };  
        for (int i = 0; i < 17; i++) {  
            int ai = Integer.parseInt(arrIdCard.substring(i, i + 1));  
            int wi = a[i];  
            sigma += ai * wi;  
        }         
        int number = sigma % 11;          
        String check_number = w[number];          
        if (!arrIdCard.substring(17).equals(check_number)) {  
            return false;  
        } else {  
            return true;  
        }  
    }  
	
	public static void main(String[] args) {
		
	}

}
