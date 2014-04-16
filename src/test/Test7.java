package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test7 {

	public static void main(String[] args) {
		List<Integer> topList = new ArrayList<Integer>();
		for(int j= 1;j<100;j++){
			System.out.println("team size = " + j);
			for(int i = 1 ; i <= j ;i++){
				topList.add(i);
			}
		if(topList.size() % 2 ==0 && topList.size() %4 !=0){
			int size = topList.size();
			int f1 = topList.remove(size-1);
			int f2 = topList.remove(size-2);
			matching(f1, f2);
		}
		while (topList.size()%4!=0){
				topList.add(0);
		}
		for(int i=0;i<topList.size();){
			int f1 = topList.get(i++);
			int f2 = topList.get(i++);
			int f3 = topList.get(i++);
			int f4 = topList.get(i++);
			Random r = new Random();
			if(r.nextInt(2) == 0){
				matching(f1, f2);
				matching(f3, f4);
			}else{
				matching(f1, f3);
				matching(f2, f4);
			}
		}
		System.out.println("--------------------");
		topList.clear();
		}

		}

	private static void matching(int f1, int f2) {
		System.out.println(f1+ "_" + f2);
		
	}
	
	
}
