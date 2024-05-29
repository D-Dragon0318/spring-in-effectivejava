package effective.java.item18.demo1;

public class Product {
	
	private String name;
	private double price;
	private String description;	
	
	private PromotionStrategy promotionStrategy;  	
	
	public Product() {
		super();
	}

	public Product(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}
  
    public double getDiscountedPrice() {  
        if (promotionStrategy != null) {  
            return promotionStrategy.calculateDiscountedPrice(this.price);  
        } else {  
            return this.price;  
        }  
    } 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PromotionStrategy getPromotionStrategy() {
		return promotionStrategy;
	}

	public void setPromotionStrategy(PromotionStrategy promotionStrategy) {
		this.promotionStrategy = promotionStrategy;
	}

}
