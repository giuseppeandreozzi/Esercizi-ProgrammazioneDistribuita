package main;

public class InitThread implements Runnable{
	private int size;
	private int start;
	private static final int DIM = 1000000; //size array
	private static final int THREADS = 8;
	private int[] arr = new int[DIM];;
	
	public static void main(String[] args) {
		
		
		for (int j = 1; j <= THREADS; j++) {
			long begin = System.currentTimeMillis();
			Thread[] threads = new Thread[THREADS];
			for (int i = 0; i < j; i++) {
				threads[i] = new Thread(new InitThread(DIM/j, (DIM/j) * i));
				threads[i].start();
			}
			
			for (int i = 0; i < j; i++) {
				try {
					threads[i].join();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
			long end = System.currentTimeMillis();
			System.out.println( j + "Thread: " + (end - begin) + "ms");

		}
	}
	public void run() {
		for (int i = start; i < start + size; i++) {
			for (int j = 0; j < 10000; j++) {
				arr[i] = i;
			}
		}
	}

	public InitThread(int aSize, int aStart) {
		size = aSize;
		start = aStart;
	}
}
