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
		case "database": // 新版本中，虽然用户请求的是database，但可能返回CloudLogger以提供更好的服务
			if (isVersionCompatibleWithNewFeature()) {
				return new CloudLogger();
			} else {
				return new DatabaseLogger();
			}
		default:
			throw new IllegalArgumentException("Unsupported logger type.");
		}
	}

	// 假设的版本检查方法
	private static boolean isVersionCompatibleWithNewFeature() {
		// 根据运行环境或配置检查版本兼容性
		return true; // 简化示例，实际应有具体检查逻辑
	}
}
