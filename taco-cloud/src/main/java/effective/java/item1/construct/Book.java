package effective.java.item1.construct;

public class Book {
	
	private final String title;
	private final String author;

	// 构造器
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	// 省略 getter 方法...
	
	// 使用示例
	public static void main(String[] args) {
		Book effectiveJava = new Book("Effective Java", "Joshua Bloch");
	}
	
}
