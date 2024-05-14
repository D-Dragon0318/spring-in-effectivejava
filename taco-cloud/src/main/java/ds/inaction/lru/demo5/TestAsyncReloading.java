package ds.inaction.lru.demo5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TestAsyncReloading {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
        CacheLoader<Long, String> cacheLoader = CacheLoader.asyncReloading(new CacheLoader<Long, String>() {

            @Override
            @Nonnull
            public String load(@Nonnull Long key) throws Exception {
                Thread.sleep(1000);
                return System.currentTimeMillis()+"：load";
            }
        }, executorService);
        LoadingCache<Long, String> cache
                // CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                // 设置并发级别为3，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(3)
                // 过期
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                // 初始容量
                .initialCapacity(1000)
                // 最大容量，超过LRU
                .maximumSize(2000).build(cacheLoader);


		System.out.println("cache get");
        String rs = cache.get(10L);
        System.out.println("cache rs:"+ rs);

        Thread.sleep(6000);

		System.out.println("cache get");
        rs = cache.get(10L);
        System.out.println("cache rs:"+ rs);

        Thread.sleep(3000);

		System.out.println("cache get");
        rs = cache.get(10L);
        System.out.println("cache rs:"+ rs);
	}
}
