package ds.inaction.lru.demo5;

import java.util.concurrent.*;

public class main {
	// 使用示例
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SafeLRUCacheWithGuavaAsyncReloading cache = new SafeLRUCacheWithGuavaAsyncReloading(3, 10);

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
