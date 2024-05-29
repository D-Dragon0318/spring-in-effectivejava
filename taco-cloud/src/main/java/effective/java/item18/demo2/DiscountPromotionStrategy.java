package effective.java.item18.demo2;

//实现具体的促销策略类  
public class DiscountPromotionStrategy implements PromotionStrategy {
	private double discountRate;

	public DiscountPromotionStrategy(double discountRate) {
		this.discountRate = discountRate;
	}

	@Override
	public double applyDiscount(Product product) {
		return product.getPrice() * (1 - discountRate);
	}
}
