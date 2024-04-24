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

		System.out.println(lruCache.get(2)); // 输出 null，因为2已经被移除出缓存
		System.out.println(lruCache.get(3)); // 输出 "three"
		System.out.println(lruCache.get(4)); // 输出 "four"

//		lruCache.put(5, "five"); // 这将导致key 1对应的"one"被移除，因为缓存只能容纳3个元素
	}
}
