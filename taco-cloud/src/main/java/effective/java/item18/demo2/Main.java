package effective.java.item18.demo2;

//主函数测试  
public class Main {
	public static void main(String[] args) {
		// 创建商品实例  
        Book book = new Book("Effective Java", 50.0, "A book about Java best practices", "Joshua Bloch", 300);  
        Electronics electronics = new Electronics("iPhone 13", 1000.0, "A smartphone from Apple", "Apple", "iPhone 13"); 
                		
		// 创建购物车实例
		ShoppingCart cart = new ShoppingCart();

		// 添加商品到购物车，并指定促销策略
		cart.addProduct(book, new DiscountPromotionStrategy(0.1)); // 书有10%的折扣
		// electronics没有指定促销策略，所以使用原价
		cart.addProduct(electronics, null);

		// 计算购物车总价
		double total = cart.calculateTotal();

		// 输出购物车总价
		System.out.println("购物车总价: " + total);
	}
}
