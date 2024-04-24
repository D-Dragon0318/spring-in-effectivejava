package ds.inaction.lru.demo3;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SafeLRUCacheWithGuava<K, V> {

    private LoadingCache<K, Optional<V>> cache;

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
        // 模拟查询数据库
        System.out.println("从数据库中加载key " + key);
        if(key.equals(1)){
            return (V) ("Data for " + key);
        }else {
            return null;
        }
    }

    public Set<K> getCacheKeys(){
        return cache.asMap().keySet();
    }

}

