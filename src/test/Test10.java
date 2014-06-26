package test;

import java.util.HashMap;
import java.util.Map;

public class Test10 {

	public static void main(String[] args) {
		String roleids = "941,5111,5474,5483,5485,5486,5489,5497,5517,5519,5590,5592,5749";
		String[] rs = roleids.split(",");
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(String r : rs){
			sb.append("5_").append(r).append("_999").append(",");
		}
		System.out.println(sb.toString());
	}
}
