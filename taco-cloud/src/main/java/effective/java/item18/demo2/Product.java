package effective.java.item18.demo2;

public abstract class Product {  
	
    private String name;  
    private double price;  
    private String description;  
  
    public Product() {
		super();
	}

	public Product(String name, double price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}

	// 公共方法  
    public String getName() {  
        return name;  
    }  
  
    public double getPrice() {  
        return price;  
    }  
  
    public String getDescription() {  
        return description;  
    }  
  
    // 可能还有其他通用方法...  
}
