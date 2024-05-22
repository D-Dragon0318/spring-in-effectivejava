package effective.java.item7;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

interface ObserverSafe {
	void update();
}

class SubjectSafe {
	private List<WeakReference<ObserverSafe>> observers = new ArrayList<>();

	void addObserver(ObserverSafe observer) {
		observers.add(new WeakReference<>(observer));
	}

	void notifyObservers() {
		List<WeakReference<ObserverSafe>> toRemove = new ArrayList<>();
		for (WeakReference<ObserverSafe> ref : observers) {
			ObserverSafe observer = ref.get();
			if (observer != null) {
				observer.update();
			} else {
				toRemove.add(ref); // 收集无效的引用，稍后清理
			}
		}
		observers.removeAll(toRemove);
	}	

	int observerSize() {
		return observers.size(); 
	}
}

class ConcreteObserverSafe implements ObserverSafe {
	private String data;

	public ConcreteObserverSafe(String data) {
		this.data = data;
	}

	@Override
	public void update() {
		System.out.println("Observer updated with data: " + data);
	}
}

public class SafeMemoryLeakSimpleDemo {
	public static void main(String[] args) throws InterruptedException {
		SubjectSafe subject = new SubjectSafe();
		ConcreteObserverSafe observer = new ConcreteObserverSafe("Initial Data");

		// 使用弱引用注册观察者
		subject.addObserver(observer);

		// 尝试让observer可被回收
		observer = null;
		
		// 如果这里没有其他对observer的引用，我们期望它被回收，但由于Subject持有强引用，它不会被回收
		// 建议进行垃圾回收，但实际回收由JVM决定
        System.gc();
        Thread.sleep(1000 * 5);

		// 由于使用了WeakReference，理论上此时observer可以被回收
		// 但由于立即调用了notifyObservers，可能观察者还未被回收
		subject.notifyObservers();

        // 此时，由于"key"的强引用被移除，若内存压力大，此键可能会被回收
        System.out.println("Size of observers after GC: " + subject.observerSize());
	}
}
