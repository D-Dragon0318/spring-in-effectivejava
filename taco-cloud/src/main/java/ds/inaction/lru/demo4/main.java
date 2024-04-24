package ds.inaction.lru.demo4;

import java.util.concurrent.*;

public class main {
	// 使用示例
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SafeLRUCacheWithGuava<Integer, String> lruCache = new SafeLRUCacheWithGuava<>(3, 10);

		ExecutorService executor = Executors.newFixedThreadPool(10);

		// 创建多个线程同时访问热点Key
		for (int i = 0; i < 10; i++) {
			Future<String> future = executor.submit(() -> lruCache.get(1));
			System.out.println(future.get());
		}

		// 关闭线程池
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
	}
}
