package ds.inaction.lru.demo3;

import ds.inaction.lru.demo2.LRUCacheWithGuava;

public class main {
	// 使用示例
	public static void main(String[] args) {
		SafeLRUCacheWithGuava<Integer, String> lruCache = new SafeLRUCacheWithGuava<>(3, 10);

		System.out.println(lruCache.get(1));
		System.out.println(lruCache.get(1));
		System.out.println(lruCache.get(2));
		System.out.println(lruCache.get(2));
	}
}
