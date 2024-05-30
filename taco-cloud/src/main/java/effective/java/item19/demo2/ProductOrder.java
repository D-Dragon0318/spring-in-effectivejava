package effective.java.item19.demo2;

public class ProductOrder extends AbstractOrder {
	// 商品订单特有的属性或方法...

	public ProductOrder(String id) {
		super(id); // 调用父类的构造函数来初始化订单ID和状态
	}

	@Override
	public void confirm() {
		// 商品订单确认的特定逻辑...
		this.status = OrderStatus.COMPLETED; // 更新订单状态为已确认
	}

	@Override
	public void cancel() {
		// 商品订单取消的特定逻辑...
		this.status = OrderStatus.CANCELLED; // 更新订单状态为已取消
	}
}

// 同样地，可以为服务订单、订阅订单等创建类似的类
