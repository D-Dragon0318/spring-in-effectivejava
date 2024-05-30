package effective.java.item19.demo2;

public abstract class AbstractOrder implements Order {
	
	private String id;
	protected OrderStatus status; // 假设OrderStatus是一个枚举，表示订单状态

	public AbstractOrder(String id) {
		this.id = id;
		this.status = OrderStatus.NEW; // 假设默认状态为待处理
	}

	public String getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	// 提供默认实现的方法
	@Override
	public void create() {
		// 公共的创建订单逻辑，如记录日志、发送通知等
		// ...
		this.status = OrderStatus.NEW; // 设置订单状态为待处理
	}
}
