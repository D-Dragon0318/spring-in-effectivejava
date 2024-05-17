package effective.java.item1.staticfactory.demo3;

public abstract class Book {
	
	protected String title;
	protected String author;

	// 私有构造器，防止外部直接实例化
	protected Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	// 静态工厂方法
	public static Book createBook(String type, String title, String author) {
		switch (type.toLowerCase()) {
		case "ebook":
			return new EBook(title, author); // EBook类可以是包访问权限或私有的
		case "paperback":
			return new Paperback(title, author); // 同样，Paperback类也可以隐藏
		default:
			throw new IllegalArgumentException("Invalid book type.");
		}
	}

	// 其他公共方法...

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
	// 使用示例
	public static void main(String[] args) {
		Book book = Book.createBook("ebook", "Quantum Physics", "Albert Einstein");
		book.getTitle(); // 仍然可以访问公共接口，但不知道也不需要知道它是EBook还是其他类型
	}
}

class EBook extends Book {
	EBook(String title, String author) {
		super(title, author);
	}
	// EBook特有的方法...
}

class Paperback extends Book {
	Paperback(String title, String author) {
		super(title, author);
	}
	// Paperback特有的方法...
}
