package effective.java.item18.demo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//购物车类现在维护一个促销策略映射  
public class ShoppingCart {

	private List<Product> products = new ArrayList<>();
	private Map<Product, PromotionStrategy> promotionStrategies = new HashMap<>();

	// 添加商品到购物车，并可以指定促销策略
	public void addProduct(Product product, PromotionStrategy strategy) {
		products.add(product);
		promotionStrategies.put(product, strategy);
	}

	// 计算购物车总价
	public double calculateTotal() {
		double total = 0.0;
		for (Product product : products) {
			PromotionStrategy strategy = promotionStrategies.getOrDefault(product, null);
			if (strategy != null) {
				total += strategy.applyDiscount(product);
			} else {
				total += product.getPrice();
			}
		}
		return total;
	}

	// ...其他购物车相关的方法
}
