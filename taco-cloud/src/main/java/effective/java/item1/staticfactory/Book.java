package effective.java.item1.staticfactory;

public class Book {
	
	private final String title;
	private final String author;

	// 私有构造器，防止直接使用
	private Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	// 静态工厂方法
	public static Book createFromTitleAndAuthor(String title, String author) {
		return new Book(title, author);
	}

	// 省略 getter 方法...
	
	// 使用示例
	public static void main(String[] args) {
		Book effectiveJava = Book.createFromTitleAndAuthor("Effective Java", "Joshua Bloch");
	}
}
