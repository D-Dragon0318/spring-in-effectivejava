package effective.java.item1.staticfactory.demo4;

public final class LoggerFactory {
	private LoggerFactory() {
	}

	public static Logger getLogger(String type) {
		switch (type.toLowerCase()) {
		case "console":
			return new ConsoleLogger();
		case "file":
			return new FileLogger();
		case "database":
			return new DatabaseLogger();
		default:
			throw new IllegalArgumentException("Unsupported logger type.");
		}
	}
}
