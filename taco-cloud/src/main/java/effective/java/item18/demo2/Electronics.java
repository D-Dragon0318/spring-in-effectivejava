package effective.java.item18.demo2;

public class Electronics extends Product {
	
	private String brand;
	private String model;

	public Electronics(String name, double price, String description, String brand, String model) {
		super(name, price, description);
		this.brand = brand;
		this.model = model;
	}

	// getter和setter省略...
}
