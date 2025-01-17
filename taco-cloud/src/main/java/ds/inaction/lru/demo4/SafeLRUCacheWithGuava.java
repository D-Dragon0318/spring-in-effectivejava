package ds.inaction.lru.demo4;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeLRUCacheWithGuava<K, V> {

    private LoadingCache<K, Optional<V>> cache;

    private final Lock lock = new ReentrantLock();

    public SafeLRUCacheWithGuava(int cacheSize, long expireAfterWrite) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES)
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
    private V queryFromDatabase(K key) {
        try {
            // 加锁，只有获取到锁的线程才能进行数据库查询和缓存更新
            lock.lock();

            // 这里是模拟数据库查询，实际开发中请替换为真实数据库查询
            // 如果缓存击穿发生，只有一个线程会执行这里的逻辑
            System.out.println("数据库查询");
            Thread.sleep(10000); // 模拟耗时查询
            return (V) ("Value for " + key + ", loaded at: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 查询完毕后释放锁
            lock.unlock();
        }
    }

    public Set<K> getCacheKeys(){
        return cache.asMap().keySet();
    }

}

