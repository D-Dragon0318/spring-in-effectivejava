package ds.inaction.lru.demo5;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class SafeLRUCacheWithGuavaAsyncReloading {

	private LoadingCache<Long, String> cache;

	public SafeLRUCacheWithGuavaAsyncReloading(int cacheSize, long expireAfterWrite) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CacheLoader<Long, String> cacheLoader = CacheLoader.asyncReloading(new CacheLoader<Long, String>() {

			@Override
			@Nonnull
			public String load(@Nonnull Long key) throws Exception {
				Thread.sleep(1000);
				return System.currentTimeMillis() + "：load";
			}
		}, executorService);
		cache = CacheBuilder.newBuilder().refreshAfterWrite(5, TimeUnit.SECONDS)
				// 初始容量
				.initialCapacity(1000)
				// 最大容量，超过LRU
				.maximumSize(2000).build(cacheLoader);
	}

	public String get(Long key) {
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void put(Long key, String value) {
		cache.put(key, value);
	}

	public Set<Long> getCacheKeys() {
		return cache.asMap().keySet();
	}

}
