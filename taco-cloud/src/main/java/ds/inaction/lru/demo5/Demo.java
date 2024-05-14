package ds.inaction.lru.demo5;

import java.util.concurrent.*;

public class Demo {	

	private static SafeLRUCacheWithGuava<Integer, String> lruCache = new SafeLRUCacheWithGuava<>(3, 3);
	
	// 使用示例
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		  // 首次访问缓存项，触发加载
        System.out.println("Initial access: " + lruCache.get(1));

        // 等待一段时间，让刷新周期过去
        Thread.sleep(5000); // 等待5秒，确保超过refreshAfterWrite设置的时间

        // 再次访问，查看是否自动刷新
        System.out.println("After refresh period: " + lruCache.get(1));

        Thread.sleep(1000); // 等待1秒，确保未超过refreshAfterWrite设置的时间
        
        // 再次访问，查看是否读取的缓存
        System.out.println("After refresh period: " + lruCache.get(1));
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
