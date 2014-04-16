package lock;

public class Volatile02 {
	private int count;
	private volatile int volatileCount;
	
	public static void main(String[] args) {
		
	}
	
	
	
	public void call(int ram){
		for(int i=0;i<100;i++){
			new Thread(){
				public void run() {
					
				};
			}.start();
		}
	}

}
