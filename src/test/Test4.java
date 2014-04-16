package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test4 {

	public static void main(String[] args) throws Exception {
		Date date= new Date();
		Date next = getSunday(date);
		System.out.println();
	}
	
	
	/**
	 * 获得周日(如果今天是周日,则返回今天,否则返回下一个周日)
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getSunday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if(week == 1){
			return date;
		}else if(week>1){
		    cal.add(Calendar.DAY_OF_MONTH,-(week-1)+7);
		}
		return cal.getTime();
	}

}
