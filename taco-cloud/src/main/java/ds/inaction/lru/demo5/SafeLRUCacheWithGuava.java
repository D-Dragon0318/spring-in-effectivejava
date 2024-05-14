package ds.inaction.lru.demo5;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeLRUCacheWithGuava<K, V> {

    private LoadingCache<K, Optional<V>> cache;

    private final Lock lock = new ReentrantLock();

    public SafeLRUCacheWithGuava(int cacheSize, long expireAfterWrite) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
//                .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                .refreshAfterWrite(3, TimeUnit.SECONDS) // 每次写入后10秒尝试刷新
                .build(new CacheLoader<K, Optional<V>>() {
                    @Override
                    public Optional<V> load(K key) throws Exception {
                        // 查询数据库或其他数据源
                        V value = queryFromDatabase(key);
                        if (value != null) {
                            return Optional.of(value);
                        } else {
                            // 即使数据库查询结果为空，也将空值放入缓存，防止穿透
                            return Optional.empty();
                        }
                    }
                });
    }

    public V get(K key) {
        try {
            Optional<V> optionalValue = cache.get(key);
            return optionalValue.orElse(null);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void put(K key, V value) {
        cache.put(key, Optional.of(value));
    }

    // 示例查询方法，实际项目中应替换为真实的数据源查询
    private V queryFromDatabase(K key) throws InterruptedException {
    	// 这里是模拟数据库查询，实际开发中请替换为真实数据库查询
        // 如果缓存击穿发生，只有一个线程会执行这里的逻辑
        System.out.println("数据库查询");
        return (V) ("Value for " + key + ", loaded at: " + System.currentTimeMillis());
    }

    public Set<K> getCacheKeys(){
        return cache.asMap().keySet();
    }
    
    public void refreshCache(K key) {
    	cache.refresh(key);
	}

}

