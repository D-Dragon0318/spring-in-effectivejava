package ds.inaction.skiplist.redis;

import java.util.List;
import redis.clients.jedis.Jedis;

public class Top10ProductRatings {

	private Jedis jedis;

	// 初始化Jedis客户端，这里假设已经连接到了本地的Redis服务器
	public Top10ProductRatings() {
		jedis = new Jedis("localhost", 6379);
	}

	// 添加商品评分
	public void addProductRating(String productId, double rating) {
		jedis.zadd("top10_products", rating, productId);
		// 维持有序集合最多只有10个元素
		jedis.zremrangeByRank("top10_products", 0, -11);
	}

	// 获取评分最高的前10个商品ID
	public List<String> getTop10Products() {
		return jedis.zrevrange("top10_products", 0, 9);
	}

	public static void main(String[] args) {
		Top10ProductRatings service = new Top10ProductRatings();

		// 添加商品评分
		service.addProductRating("product1", 4.5);
		service.addProductRating("product2", 4.8);
		service.addProductRating("product1", 4.7);
		service.addProductRating("product3", 5.7);

		// 获取当前的TOP10商品
		List<String> top10Products = service.getTop10Products();
		for (String productId : top10Products) {
			System.out.println(productId);
		}

		// 关闭Jedis连接
		service.jedis.close();
	}
}
