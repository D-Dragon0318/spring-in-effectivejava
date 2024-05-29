package effective.java.item18.demo1;

public class DiscountPromotion implements PromotionStrategy {  
	
    private double discountRate; // 折扣率  
  
    public DiscountPromotion(double discountRate) {
		this.discountRate = discountRate;
	}

	@Override  
    public double calculateDiscountedPrice(double originalPrice) {  
        return originalPrice * (1 - discountRate);  
    }

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}  
    
    
    
}
