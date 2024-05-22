package effective.java.item7;

import java.util.WeakHashMap;

public class WeakHashMapExample {
	
    // 创建一个WeakHashMap实例
    static WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
    
    public static void main(String[] args) throws InterruptedException {

        // 添加键值对，键自动被WeakReference包裹
        String key = new String("A Key");
        String value = new String("Some Value");
        weakHashMap.put(key, value);

        // 移除对key的强引用，使垃圾回收可能回收该键
        key = null;
//        value = null;// 注意：通常无需将value置为null，除非它不再被其他地方使用

        // 建议进行垃圾回收，但实际回收由JVM决定
        System.gc();
        Thread.sleep(1000 * 5);

        // 此时，由于"key"的强引用被移除，若内存压力大，此键可能会被回收
        System.out.println("Size of WeakHashMap after GC: " + weakHashMap.size());

        // 注意：实际运行时，可能需要更复杂的设置来观察到即时的效果，
        // 因为GC的触发和执行不可预测，且现代JVM对finalize方法的使用已不鼓励。
    }
}
