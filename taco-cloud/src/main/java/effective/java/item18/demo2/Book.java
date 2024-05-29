package effective.java.item18.demo2;

//假设的Book和Electronics类定义（仅包含构造器和特定于商品的字段）  
public class Book extends Product {
	
	private String author;
	private int numberOfPages;

	public Book(String name, double price, String description, String author, int numberOfPages) {
		super(name, price, description);
		this.author = author;
		this.numberOfPages = numberOfPages;
	}

	// getter和setter省略...
}
