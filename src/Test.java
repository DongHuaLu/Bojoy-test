import java.util.Random;


public class Test {
	public static void main(String[] args) {
		while(true){
			System.out.println("1");
		}
//		Object obj = new Object();
//		for (int i=0;i<10;i++){
//			new MyThread(obj).start();
//			new MyThread(obj).start();
//			new MyThread(obj).start();
//		}
	}
}

class MyThread extends Thread{
	private Object obj;
	private Random ran;
	public MyThread(Object obj) {
		this.obj = obj;
		ran = new Random();
	}
	@Override
	public void run() {
		Thread.currentThread().setPriority(ran.nextInt(10)+1);
		System.out.println(Thread.currentThread().getName()+" p="+Thread.currentThread().getPriority());
		synchronized (obj) {
			System.out.println(Thread.currentThread().getName()+" get obj");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
