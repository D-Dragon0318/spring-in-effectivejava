package effective.java.item18.demo1;

public class ShoppingCartTest {

	public static void main(String[] args) {
		
		// 创建一个商品对象
		Product book = new Product();
		book.setName("Java编程思想");
		book.setPrice(100.0);

		// 创建一个促销策略对象
		PromotionStrategy discountPromotion = new DiscountPromotion(0.2); // 20% 折扣

		// 将促销策略应用到商品上
		book.setPromotionStrategy(discountPromotion);

		// 计算折扣后的价格并打印
		double discountedPrice = book.getDiscountedPrice();
		System.out.println("商品名称: " + book.getName());
		System.out.println("原价: " + book.getPrice());
		System.out.println("折扣价: " + discountedPrice);

	}

}
