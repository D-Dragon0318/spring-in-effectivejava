package effective.java.item1.staticfactory.demo5;

public class Client {
	public static void main(String[] args) {
		DataProcessor processor = PluginFactory.createDataProcessor("AdvancedFilter");
		String result = processor.processData("Sample data input");
		System.out.println(result);
	}
}
