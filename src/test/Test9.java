package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test9 {

	
	
	
	public static void main(String[] args) {
		Thread t = new Thread(){
			private List<BigModel> list = new ArrayList<BigModel>();
			private Map<Integer,BigModel> map = new HashMap<Integer, BigModel>();
			

			@Override
			public void run() {
				Scanner br = new Scanner(System.in);
				while(true){
					String line = br.nextLine();
					int num = Integer.parseInt(line);
					for(int i=0;i<num;i++){
						BigModel model = new BigModel();
						model.setL0(0L);
						model.setL1(1L);
						model.setL2(2L);
						model.setL3(3L);
						model.setL4(4L);
						model.setL5(5L);
						model.setL6(6L);
						model.setL7(7L);
						model.setL8(8L);
						model.setL9(9L);
						list.add(model);
						map.put(i, model);
						System.out.println(list.size());
						System.out.println(map.size());
					}
				}
			}
		};
		t.start();
	}
}
