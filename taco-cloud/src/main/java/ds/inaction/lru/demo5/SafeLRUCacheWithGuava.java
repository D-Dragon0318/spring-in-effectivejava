package ds.inaction.lru.demo5;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class SafeLRUCacheWithGuava {

	private LoadingCache<Long, String> cache;

	public SafeLRUCacheWithGuava(int cacheSize, long expireAfterWrite) {
		cache = CacheBuilder.newBuilder().maximumSize(cacheSize).expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES)
				.refreshAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<Long, String>() {
					@Override
					public String load(Long key) throws Exception {
						// 查询数据库或其他数据源
						String value = queryFromDatabase(key);
						return value;
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

	// 示例查询方法，实际项目中应替换为真实的数据源查询
	private String queryFromDatabase(Long key) throws InterruptedException {
		// 这里是模拟数据库查询，实际开发中请替换为真实数据库查询
		System.out.println("数据库查询");
        Thread.sleep(1000);
		return "Value for " + key + ", loaded at: " + System.currentTimeMillis();
	}

	public Set<Long> getCacheKeys() {
		return cache.asMap().keySet();
	}

}
