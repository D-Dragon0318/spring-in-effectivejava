package effective.java.item1.staticfactory;

import java.util.HashMap;
import java.util.Map;

public class Car {
	
	private static final Map<String, Car> carPool = new HashMap<>();
	private String model;

	private Car(String model) {
		this.model = model;
	}

	public static Car createCar(String model) {
		Car car = carPool.get(model);
		if (car == null) {
			car = new Car(model);
			carPool.put(model, car);
		}
		return car;
	}

	// 其他方法...
	
	// 使用示例
	public static void main(String[] args) {
		// 使用静态工厂方法创建对象
		Car myCar = Car.createCar("Tesla Model S");
	}
}
