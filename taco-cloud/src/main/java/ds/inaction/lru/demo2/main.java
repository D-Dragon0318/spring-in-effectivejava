package ds.inaction.lru.demo2;

public class main {
	// 使用示例
	public static void main(String[] args) {
		LRUCacheWithGuava<Integer, String> lruCache = new LRUCacheWithGuava<>(3, 10);

		lruCache.put(1, "one");
		lruCache.put(2, "two");
		lruCache.put(3, "three");

		System.out.println(lruCache.get(1)); // 输出 "one"
		lruCache.put(4, "four"); // 这将导致key 2对应的"two"被移除，因为它是最近最少使用的

		System.out.println(lruCache.get(2)); // 执行load，因为key 2已经被移除出缓存；因为执行了get, 此时又会导致key 3对应的"three"被移除，因为它是最近最少使用的
		System.out.println(lruCache.get(3)); // 执行load，因为key 3已经被移除出缓存
		System.out.println(lruCache.getCacheKeys());
		System.out.println(lruCache.get(2));
		System.out.println(lruCache.getCacheKeys());
		System.out.println(lruCache.get(4)); // 输出 "four"
	}
}
