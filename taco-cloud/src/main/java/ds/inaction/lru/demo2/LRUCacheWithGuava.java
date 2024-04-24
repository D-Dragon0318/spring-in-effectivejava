package ds.inaction.lru.demo2;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LRUCacheWithGuava<K, V> {
	
	private LoadingCache<K, V> cache;

	public LRUCacheWithGuava(int cacheSize, long expireAfterWrite) {
		cache = CacheBuilder.newBuilder()
				.maximumSize(cacheSize)
				.expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES) // 可选，设置写入后过期时间
				.build(new CacheLoader<K, V>() { // 或者使用CacheLoader.from()指定加载函数
					@Override
					public V load(K key) throws Exception {
						// 模拟从数据库或网络加载数据的逻辑
                        // 这里我们简单地返回一个与键相关的值
                        // 在实际应用中，您需要根据键从数据源加载数据
						System.out.println("从数据库中加载key " + key);
                        return (V) ("Data for " + key);
					}
				});
	}

	public V get(K key) {
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			throw new RuntimeException(e); // 或者更具体的异常处理
		}
	}

	public void put(K key, V value) {
		cache.put(key, value);
	}

	public Set<K> getCacheKeys(){
		return cache.asMap().keySet();
	}
	
}
