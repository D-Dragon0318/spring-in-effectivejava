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
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

public class SafeLRUCacheWithGuavaReload {

	private LoadingCache<Long, String> cache;

	public SafeLRUCacheWithGuavaReload(int cacheSize, long expireAfterWrite) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		cache = CacheBuilder.newBuilder().maximumSize(cacheSize).expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES)
				.refreshAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<Long, String>() {
					@Override
					public String load(Long key) throws Exception {
						// 查询数据库或其他数据源
                        Thread.sleep(1000);
                        return System.currentTimeMillis()+"：load";
					}
					@Override
                    @Nonnull
                    public ListenableFuture<String> reload(@Nonnull Long key, @Nonnull String oldValue) throws Exception {
                        ListenableFutureTask<String> futureTask = ListenableFutureTask.create(() -> {
                            Thread.sleep(1000);
                            return System.currentTimeMillis()+"：reload";
                        });
                        executorService.submit(futureTask);
                        return futureTask;
                    }
				});
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
