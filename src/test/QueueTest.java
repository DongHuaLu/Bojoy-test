package test;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		queue.offer("1");
		queue.offer("2");
		queue.offer("3");
		String str = null;
		while((str = queue.poll())!=null){
			System.out.println(str);
		}
		System.out.println(queue.size());
	}
}
