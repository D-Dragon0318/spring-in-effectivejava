package effective.java.item1.staticfactory.demo5;

public final class PluginFactory {
	private PluginFactory() {
	} // 防止实例化

	public static DataProcessor createDataProcessor(String pluginName) {
		// 假设这里会有逻辑根据pluginName从注册表或配置中查找并实例化插件
		// 但实际插件类在编写此工厂方法时可能还不存在
		if ("AdvancedFilter".equals(pluginName)) {
			// 尽管"AdvancedFilter"插件类可能还未被编写，但接口是确定的
			return new AdvancedFilter(); // 假设的插件类名
		} else {
			throw new IllegalArgumentException("Unsupported plugin name.");
		}
	}
}
