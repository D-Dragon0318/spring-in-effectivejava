package effective.java.item1.staticfactory.demo4;

public class ConsoleLogger implements Logger {
	@Override
	public void log(String message) {
		System.out.println("Console: " + message);
	}
}
