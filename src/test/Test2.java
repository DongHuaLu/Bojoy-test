package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2 {

	public static void main(String[] args) {
		Map<Model,Model> map = new HashMap<Model, Model>();
		for(int i = 0;i<100;i++){
			Model m = new Model(i);
			map.put(m, m);
			map.put(m, m);
		}
		while(true){
			Set<Model> set = map.keySet();
			if(!"[57, 33, 20, 71, 4, 49, 42, 23, 39, 35, 15, 18, 74, 14, 94, 77, 58, 25, 81, 31, 19, 52, 91, 54, 3, 21, 67, 2, 44, 61, 92, 99, 10, 17, 65, 27, 64, 79, 86, 96, 88, 40, 62, 93, 90, 0, 26, 7, 43, 53, 51, 16, 55, 56, 78, 34, 30, 11, 13, 47, 32, 9, 66, 70, 76, 97, 24, 89, 41, 37, 87, 45, 83, 80, 22, 95, 85, 69, 63, 60, 29, 1, 84, 82, 8, 98, 59, 48, 75, 72, 6, 68, 5, 12, 28, 38, 36, 73, 50, 46]".equals(set.toString())){
				System.out.println(set);
			}
		}
	}

}
