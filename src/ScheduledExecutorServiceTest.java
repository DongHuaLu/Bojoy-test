
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorServiceTest {
	public static void main(String[] args) {
		final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			final ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(1);
			System.out.println("nowTime"+formater.format(new Date()));
			final ScheduledFuture c0 = scheduleThreadPool.schedule(new Callable(){
				public Object call() throws Exception {
//					int d = 8/0;
					System.out.println("------call----");
					System.out.println("nowTime"+formater.format(new Date()));
					scheduleThreadPool.shutdown();
					return 123;
				}},10,TimeUnit.SECONDS);
			System.out.println(c0.get(10, TimeUnit.SECONDS));
//			System.out.println(c0.get());
			
//			final ScheduledFuture c1 = scheduleThreadPool.schedule(new Runnable(){
//				public void run() {
//					int d = 8/0;
//					System.out.println("------run----");
//					System.out.println("nowTime"+format.format(new Date()));
//					scheduleThreadPool.shutdown();
//				}
//			},2,TimeUnit.SECONDS);
//			System.out.println(c1.get());
			
//			final ScheduledFuture c2 = scheduleThreadPool.scheduleAtFixedRate(new Runnable(){
//				public void run() {
//					System.out.println("------run2----");
//					//int d = 8/0;
//					System.out.println("nowTime"+format.format(new Date())+"@"+Thread.currentThread().getName());
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
////					
//				}
//			}, 5,3,TimeUnit.SECONDS);
			
//			final ScheduledFuture c3 = scheduleThreadPool.scheduleWithFixedDelay(new Runnable(){
//				public void run() {
//					System.out.println("------run3----");
//					//int d = 8/0;
//					System.out.println("nowTime"+formater.format(new Date())+"@"+Thread.currentThread().getName());
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			}, 5,3,TimeUnit.SECONDS);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
