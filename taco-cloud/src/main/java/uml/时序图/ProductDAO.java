package uml.时序图;

public class ProductDAO {
    public Product getProductById(String id) {
        // 这里模拟从数据库中获取商品信息
        if ("1".equals(id)) {
            return new Product("1", "Example Product", 100.0);
        } else {
            return null;
        }
    }
}
