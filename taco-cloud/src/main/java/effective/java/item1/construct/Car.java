package effective.java.item1.construct;

public class Car {
	
	private String model;

	public Car(String model) {
		this.model = model;
	}

	// 其他方法...
	
	// 使用示例
	public static void main(String[] args) {
		// 使用构造器创建对象
		Car myCar = new Car("Tesla Model S");
	}
}

