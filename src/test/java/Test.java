import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

	private AtomicInteger currentCount = new AtomicInteger(0);
	LinkedBlockingQueue<Object> missionQueue = new LinkedBlockingQueue<>(10);
	private Semaphore semaphore = new Semaphore(3);

	public void currentControl(String bMsg) throws InterruptedException {
		semaphore.acquire();
		System.out.println(currentCount.incrementAndGet());
		Thread.sleep(1000);
		semaphore.release(1);
	}

	public static void main(String[] args) {
		Test test = new Test();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						test.currentControl("aaa");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}