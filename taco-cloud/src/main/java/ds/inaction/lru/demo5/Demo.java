package ds.inaction.lru.demo5;

import java.util.concurrent.*;

public class Demo {	

	private static SafeLRUCacheWithGuava<Integer, String> lruCache = new SafeLRUCacheWithGuava<>(3, 10);
	
	// 使用示例
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		lruCache.put(1, "one");
		lruCache.put(2, "two");
		lruCache.put(3, "three");

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
	
	/**
	 * 定时异步刷新机制：初始延迟为5秒，之后每隔5秒执行一次。是全量刷新还是部分刷新根据实际业务决定。
	 */
	public void refreshCache() {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		Integer[] keysToRefresh = {1, 2, 3};
		executor.scheduleAtFixedRate(() -> {
		    for (Integer key : keysToRefresh) {
		    	lruCache.refreshCache(key);
		    }
		}, 5, 5, TimeUnit.SECONDS);
	}
	
}
