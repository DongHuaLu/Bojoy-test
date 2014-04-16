import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolExecutorTest {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException{
		System.out.println("startMAIN@"+Thread.currentThread().getName());
		int run = 0;
		Executors.newCachedThreadPool();
		// 直接提交的阻塞队列
		BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
		// 无解队列,如果不设最大队列数,则默认为21亿多,在阻塞队列未满的情况下,线程池只有核心线程会启用,阻塞队列满了后会开启小于最大线程数的线程来处理多出来的线程
		//BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		// 线程池满则直接报错
		//RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
		// 挂起自己来等待线程池中任务执行完成后再添加
		RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
		// 直接丢弃
		//RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
		// 丢弃最早的线程
		//RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS,queue,
				Executors.defaultThreadFactory(),rejectedExecutionHandler){
			/*protected void afterExecute(Runnable r, Throwable t) { 
				System.out.println("afterExecute."); 
			} 
			protected void beforeExecute(Thread t, Runnable r){ 
				System.out.println("beforeExecute."); 
			} */
			
		}; 
//		threadPool.allowCoreThreadTimeOut(true);
		System.out.println("queue.size()=="+queue.size());
		threadPool.execute(new Commend(10,++run));
		System.out.println("queue.size()=="+queue.size());
		Thread.sleep(1000);
		threadPool.execute(new Commend(10,++run));
		Thread.sleep(1000);
		threadPool.execute(new Commend(10,++run));
		System.out.println("queue.size()=="+queue.size());
		Thread.sleep(1000);
		threadPool.execute(new Commend(10,++run));
		System.out.println("queue.size()=="+queue.size());
		for(int i=0;i<10;i++){
			threadPool.execute(new Commend(10,++run));
			System.out.println("for queue.size()=="+queue.size());
		}
		threadPool.execute(new Commend(10,++run));
		System.out.println("queue.size()=="+queue.size());
	}
	
	static class Commend implements Runnable{
		private int delaySec = 0;
		private int run = 0;
		public Commend(int delaySec,int run){
			this.delaySec = delaySec;
			this.run = run;
		}
		
		public String toString(){
			return String.valueOf(run);
		}
		public void run(){
			while(true){
				System.out.println("start@"+Thread.currentThread().getName()+"$"+this+"$"+Thread.activeCount());
				try {
					Thread.sleep(delaySec*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end@"+Thread.currentThread().getName()+"$"+this+"$"+Thread.activeCount());
				break;
			}
		}
	}

}
