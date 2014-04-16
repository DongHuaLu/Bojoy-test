package lock;

public class Volatile {
	private static int nothingCount=0;
	private volatile static int count = 0;
	private static int realCount = 0;
	 
	public static void main(String[] args) {
	    for (int i = 0; i < 10; i++) {
	        new Volatile().call();
	    }
	        try {
	            Thread.sleep(4000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("nothingCount="+count);
	        System.out.println("count=" + count);
	        System.out.println("realCount=" + realCount);
	}
	 
	public void call() {
	    for (int i = 0; i < 100; i++) {
	        new Thread() {
	            @Override
	            public void run() {
	            	try {
		                count++;
	                    Thread.sleep(100);
		                count++;
		                nothingCount++;
		                Thread.sleep(100);
		                nothingCount++;
	                } catch (InterruptedException e) {
	                	e.printStackTrace();
	                }
	                synchronized (Volatile.class) {
	                    realCount++;
	                    realCount++;
	                }
	            }
	        }.start();
	    }
	}
}
