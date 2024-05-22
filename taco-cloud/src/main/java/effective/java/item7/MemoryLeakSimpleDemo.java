package effective.java.item7;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

interface Observer {
	void update();
}

class Subject {
	
	private List<Observer> observers = new ArrayList<>();

	void addObserver(Observer observer) {
		observers.add(observer);
	}

	void notifyObservers() {
		List<Observer> toRemove = new ArrayList<>();
		for (Observer observer : observers) {
			if (observer != null) {
				observer.update();
			} else {
				toRemove.add(observer); // 收集无效的引用，稍后清理
			}
		}
		observers.removeAll(toRemove);
	}
	
	int observerSize() {
		return observers.size(); 
	}
}

class ConcreteObserver implements Observer {
	private String data;

	public ConcreteObserver(String data) {
		this.data = data;
	}

	@Override
	public void update() {
		System.out.println("Observer updated with data: " + data);
	}
}

public class MemoryLeakSimpleDemo {
	public static void main(String[] args) throws InterruptedException {
		Subject subject = new Subject();
		ConcreteObserver observer = new ConcreteObserver("Initial Data");

		// 注册观察者
		subject.addObserver(observer);
		
		// 假设这里我们不再需要observer，但在Subject中仍有强引用
		observer = null; // 这里尝试让observer可被回收，但实际上Subject还持有强引用

		// 如果这里没有其他对observer的引用，我们期望它被回收，但由于Subject持有强引用，它不会被回收
		// 建议进行垃圾回收，但实际回收由JVM决定
        System.gc();
        Thread.sleep(1000 * 5);

		// 模拟通知观察者，此处简化处理，实际情况可能更复杂
		subject.notifyObservers();		

        // 此时，由于"key"的强引用被移除，若内存压力大，此键可能会被回收
        System.out.println("Size of observers after GC: " + subject.observerSize());
	}
}
