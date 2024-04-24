package ds.inaction.lru.demo1;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
	
	private static final long serialVersionUID = 7039427335927531842L;
	
	private final int cacheSize;

    public LRUCache(int cacheSize) {
        // 第三个参数true表示按访问顺序排序，即最近访问过的放在头部
        super((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当缓存数量超过设定的cacheSize时，移除最老（最近最少使用）的条目
        return size() > cacheSize;
    }
}





