package test;

import java.math.BigDecimal;

public class Test6 {
	
	public static void main(String[] args) {
		System.out.println(round(1,2,4));
	}
	public static double round(double val, int scale, int mode) {
		BigDecimal bd = new BigDecimal(val);
		try {
			return bd.setScale(scale, mode).doubleValue();
		} catch (Exception e) {
			return val;
		}
	}

}
